//package com.haha.Misc;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.tools.DistCp;
//import org.apache.hadoop.tools.DistCpOptions;
//import org.apache.hadoop.util.ToolRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//public class DistcpTest {
//    public static void main(String[] args) throws Exception{
//        List<String> arguments = new ArrayList<>();
//        arguments.add("-overwrite");
//        arguments.add("-m 1000");
//        arguments.add("-pb");
//        arguments.add(args[0]);
//        arguments.add(args[1]);
//        Configuration configuration = new Configuration();
//        configuration.set("mapred.job.queue.name", "pds");
//        configuration.addResource(new Path(args[2]));
//
//        DistCpOptions distCpOptions = new DistCpOptions(new ArrayList<Path>() {
//            {
//                add(new Path(args[0]));
//            }
//        }, new Path(args[1]));
//        distCpOptions.setOverwrite(true);
//        DistCp distCp = new DistCp(configuration, distCpOptions);
//        log.info("{}",distCpOptions.toString());
//        ToolRunner.run(distCp, null);
//        System.out.println("Running : " + ToolRunner.run(distCp, arguments.toArray(new String[arguments.size()])));
//    }
//}