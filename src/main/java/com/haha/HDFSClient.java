package com.haha;

import java.io.InputStream;
import java.security.PrivilegedExceptionAction;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileStatus;

public class HDFSClient {

    public static void main(String args[]) {

        HDFSClient hdfsClient = new HDFSClient();
        hdfsClient.haha();
    }

    public void haha() {
        try {
            UserGroupInformation ugi
                    = UserGroupInformation.createRemoteUser("fk-ip-data-service");

            ugi.doAs(new PrivilegedExceptionAction<Void>() {

                public Void run() throws Exception {

                    final FileSystem fs;
                    Configuration conf = new Configuration();
//                    conf.set("fs.defaultFS", "hdfs://1.2.3.4:8020/user/hbase");
                    InputStream in1 = this.getClass().getClassLoader().getResourceAsStream("core-site.xml");
                    InputStream in2 = this.getClass().getClassLoader().getResourceAsStream("hdfs-site.xml");
                    if(in1 != null && in2 != null ) {
                        conf.addResource(in1);
                        conf.addResource(in2);
                    } else {
                        throw new RuntimeException("Unknown cluster : ");
                    }
                    conf.set("hadoop.job.ugi", "fk-ip-data-service");
                    fs = FileSystem.get(conf);

                    fs.createNewFile(new Path("/tmp"));

                    FileStatus[] status = fs.listStatus(new Path("/tmp"));
                    for(int i=0;i<status.length;i++){
                        System.out.println(status[i].getPath());
                    }


                    String src = "/projects/planning/dsp_test/dataframes/f57698a0-75fa-42f1-bf88-a2d8c8eaea3d/f57698a0-75fa-42f1-bf88-a2d8c8eaea3d.csv";
                    String dest = "/projects/planning/dcp_fact.db/forecasted_ef_fact/vertical=AluminiumFoilAndShrinkWrap/refresh_id=4182540";
                    Path destination = new Path(dest);

                    if(!fs.exists(destination)){
                        if(!fs.exists(destination)) {
                            fs.mkdirs(destination);
                            fs.setPermission(destination, new FsPermission(FsAction.ALL,FsAction.ALL,FsAction.ALL));
                        }
                        FileUtil.copy(fs, new Path(src), fs, destination, false, true, conf);
                    } else {
                        FileUtil.copy(fs, new Path(src), fs, destination, false, true, conf);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}