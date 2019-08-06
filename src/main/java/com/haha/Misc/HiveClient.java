package com.haha.Misc;

import com.flipkart.kloud.authn.AuthTokenService;

import java.sql.*;

public class HiveClient {
    public static void main(String[] args) throws SQLException {

        AuthTokenService.init("https://authn.ch.flipkart.com", "planning_forecast", "c79e61c3-7d41-46f6-a0da-72f40dfa5c44");
        final String localhost = AuthTokenService.getInstance().fetchToken("localhost").toAuthorizationHeader();
        Connection connection = DriverManager.getConnection("jdbc:hive2://10.34.15.185:10001/default;transportMode=http;httpPath=cliservice", "fk-ip-data-service", "");
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
