//package com.haha.Misc;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.URI;
//import java.nio.file.FileSystems;
//import java.nio.file.Files;
//import java.nio.file.LinkOption;
//import java.nio.file.Paths;
//import java.security.PrivilegedExceptionAction;
//import java.util.Optional;
//import java.util.Set;
//import java.util.TreeSet;
//import java.util.concurrent.atomic.AtomicReference;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.IOUtils;
//import org.apache.hadoop.conf.*;
//import org.apache.hadoop.fs.*;
//import org.apache.hadoop.fs.permission.FsAction;
//import org.apache.hadoop.fs.permission.FsPermission;
//import org.apache.hadoop.security.UserGroupInformation;
//
//@Slf4j
//public class HDFSClient {
//
//    private final FileSystem fs;
//
//    public static void main(String args[]) throws IOException{
//
//        HDFSClient hdfsClient = new HDFSClient();
//        hdfsClient.haha();
//    }
//
//    public void haha() {
//        try {
//            UserGroupInformation ugi = UserGroupInformation.createRemoteUser("fk-ip-data-service");
//            Configuration conf = new Configuration();
//            conf.set("fs.defaultFS", "hdfs://krios");
//            conf.set("hadoop.job.ugi", "fk-ip-data-service");
//            AtomicReference<FileSystem> fileSystemAtomicReference = new AtomicReference<>();
//            ugi.doAs((PrivilegedExceptionAction<Void>) () -> {
//                FileSystem fs = FileSystem.get(conf);
//                fileSystemAtomicReference.set(fs);
//                return null;
//            });
//
//            FileSystem fs = fileSystemAtomicReference.get();
//            System.out.println(fs.exists(new Path("/tmp/haha")));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public boolean createHDFSDirIfNotExists(Path hdfsDir) throws IOException{
//        if(!fs.exists(hdfsDir)) {
//            fs.mkdirs(hdfsDir);
//            fs.setPermission(hdfsDir, new FsPermission(FsAction.ALL,FsAction.ALL,FsAction.ALL));
//            log.info("Made hdfs directory " + hdfsDir);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//}