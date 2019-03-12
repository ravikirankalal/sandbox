package com.haha.Misc;

public class random {
    public static void main(String[] args) {
        System.out.println("jdbc:hive2://10.34.185.217:10000/default?mapreduce.job.queuename=pds;hive.merge.mapfiles=true;hive.merge.mapredfiles=true;hive.merge.smallfiles.avgsize=2831155000;hive.merge.size.per.task=20971520000;mapred.max.split.size=68157440;mapred.min.split.size=68157440".split(";")[0]);
    }
}
