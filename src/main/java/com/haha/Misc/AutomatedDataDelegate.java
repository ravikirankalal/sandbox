package com.haha.Misc;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.Date;

public class AutomatedDataDelegate implements JavaDelegate {
    public void execute(DelegateExecution execution) throws Exception {
        Date now = new Date();
        execution.setVariable("autoWelcomeTime", now);
        System.out.printf("Going to sleep with thread id: %s \n",Thread.currentThread().getName());
        int count = 100000;
        while(count>0) {
            count--;
        }
        //        Thread.sleep(10*1000);
        System.out.printf("Done with sleeping with thread id: %s \n",Thread.currentThread().getName());
    }
}
