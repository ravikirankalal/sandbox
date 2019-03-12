package com.haha.Misc;

import java.sql.*;

public class HiveClient {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:hive2://10.34.185.217:10000?mapreduce.job.queuename=pds", "", "");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("show create table dcp_fact.zone_info");
        while(resultSet.next()) {
            if (resultSet.getString(1).equalsIgnoreCase("location")) {
                resultSet.next();
                break;
            }
        }
        System.out.printf(resultSet.getString(1).replaceAll("\'","").trim());
    }
}
