package com.haha;

import org.activiti.engine.*;
import org.activiti.engine.form.FormData;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.impl.form.DateFormType;
import org.activiti.engine.impl.form.LongFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OnboardingRequest {

    public static final String ONBOARDING_BPMN20_XML = "onboarding.bpmn20.xml";

    public static void main(String[] args) throws ParseException, InterruptedException {
        int numberOfThreads = 10;
        if (args.length == 1) {
            numberOfThreads = Integer.parseInt(args[0]);
        }


        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        final ProcessEngine processEngine = cfg.buildProcessEngine();
        final TaskService taskService = processEngine.getTaskService();
        final FormService formService = processEngine.getFormService();
        final HistoryService historyService = processEngine.getHistoryService();
        final RepositoryService repositoryService = processEngine.getRepositoryService();
        final RuntimeService runtimeService = processEngine.getRuntimeService();


        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(ONBOARDING_BPMN20_XML).deploy();

        final ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId()).singleResult();
        System.out.println(
                "Found process definition ["
                        + processDefinition.getName() + "] with id ["
                        + processDefinition.getId() + "]");

        List<Thread> threadList = new ArrayList<Thread>();
        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(() -> {
                try {
                    runProcess(runtimeService, taskService, formService, historyService, processDefinition, new HashMap<>());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
//            System.out.printf("Starting thread number: %d", i);
            thread.start();
            threadList.add(thread);
        }
        while (!threadList.stream().allMatch(thread -> !thread.isAlive())) {
            System.out.println("Number of threads NEW: " + threadList.stream().filter(thread -> thread.getState().equals(Thread.State.NEW)).count()
            +"\n" + "RUNNABLE: " + threadList.stream().filter(thread -> thread.getState().equals(Thread.State.RUNNABLE)).count() +
            "BLOCKED: " + threadList.stream().filter(thread -> thread.getState().equals(Thread.State.BLOCKED)).count() +
            "TIMED_WAITING: " + threadList.stream().filter(thread -> thread.getState().equals(Thread.State.TIMED_WAITING)).count() +
            "WAITING: " + threadList.stream().filter(thread -> thread.getState().equals(Thread.State.WAITING)).count() +
            "TERMINATED: " + threadList.stream().filter(thread -> thread.getState().equals(Thread.State.TERMINATED)).count()
            );
            Thread.sleep(10 * 1000);
        }
    }

    private static void runProcess(RuntimeService runtimeService, TaskService taskService, FormService formService, HistoryService historyService, ProcessDefinition processDefinition, Map<String, Object> processVariables) throws ParseException {


        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(processDefinition.getKey());
        System.out.println("Onboarding process started with process instance id ["
                + processInstance.getProcessInstanceId()
                + "] key [" + processInstance.getProcessDefinitionKey() + "]");


        Scanner scanner = new Scanner(System.in);
        while (processInstance != null && !processInstance.isEnded()) {
            List<Task> tasks = taskService.createTaskQuery().processDefinitionId(processDefinition.getId()).processInstanceId(processInstance.getId())
                    .taskCandidateGroup("managers").list();
            System.out.println("Active outstanding tasks: [" + tasks.size() + "]");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println("Processing Task [" + task.getName() + "]");
                Map<String, Object> variables = new HashMap<String, Object>();
                FormData formData = formService.getTaskFormData(task.getId());
                for (FormProperty formProperty : formData.getFormProperties()) {
                    if (StringFormType.class.isInstance(formProperty.getType())) {
                        System.out.println(formProperty.getName() + "?");
                        String value = UUID.randomUUID().toString();
                        variables.put(formProperty.getId(), value);
                    } else if (LongFormType.class.isInstance(formProperty.getType())) {
                        System.out.println(formProperty.getName() + "? (Must be a whole number)");
                        Long value = 2L;
                        variables.put(formProperty.getId(), value);
                    } else if (DateFormType.class.isInstance(formProperty.getType())) {
                        System.out.println(formProperty.getName() + "? (Must be a date m/d/yy)");
                        DateFormat dateFormat = new SimpleDateFormat("m/d/yy");
                        Date value = dateFormat.parse("10/10/99");
                        variables.put(formProperty.getId(), value);
                    } else {
                        System.out.println("<form type not supported>");
                    }
                }
                variables.putAll(processVariables);
                taskService.complete(task.getId(), variables);

                HistoricActivityInstance endActivity = null;
                List<HistoricActivityInstance> activities =
                        historyService.createHistoricActivityInstanceQuery()
                                .processInstanceId(processInstance.getId()).finished()
                                .orderByHistoricActivityInstanceEndTime().asc()
                                .list();
                for (HistoricActivityInstance activity : activities) {
                    if (activity.getActivityType() == "startEvent") {
                        System.out.println("BEGIN " + processDefinition.getName()
                                + " [" + processInstance.getProcessDefinitionKey()
                                + "] " + activity.getStartTime());
                    }
                    if (activity.getActivityType() == "endEvent") {
                        // Handle edge case where end step happens so fast that the end step
                        // and previous step(s) are sorted the same. So, cache the end step
                        //and display it last to represent the logical sequence.
                        endActivity = activity;
                    } else {
                        System.out.println("-- " + activity.getActivityName()
                                + " [" + activity.getActivityId() + "] "
                                + activity.getDurationInMillis() + " ms");
                    }
                }
                if (endActivity != null) {
                    System.out.println("-- " + endActivity.getActivityName()
                            + " [" + endActivity.getActivityId() + "] "
                            + endActivity.getDurationInMillis() + " ms");
                    System.out.println("COMPLETE " + processDefinition.getName() + " ["
                            + processInstance.getProcessDefinitionKey() + "] "
                            + endActivity.getEndTime());
                }
            }
            // Re-query the process instance, making sure the latest state is available
            processInstance = runtimeService.createProcessInstanceQuery()
                    .processInstanceId(processInstance.getId()).singleResult();
        }
        scanner.close();
    }
}
