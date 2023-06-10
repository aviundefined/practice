package com.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SnowFlakeJDBCExample {

  public static void main(String[] args) throws Exception{
    // get connection
    try(final Connection connection = getConnection()) {
      System.out.println("Create JDBC connection");
      System.out.println("Done creating JDBC connection\n");
      // create statement
      System.out.println("Create JDBC statement");
      try (final Statement statement = connection.createStatement()) {
        System.out.println("Done creating JDBC statement");
      }
    }
  }

  private static Connection getConnection() throws SQLException {

    // build connection properties
    Properties properties = new Properties();
    properties.put("account", "OCA92998"); // replace "" with your account
    properties.put("user", "avinash"); // replace "" with your user name
    properties.put("password", "Samooha2022"); // replace "" with your password
    properties.put("warehouse", "APP_WH"); // replace "" with target warehouse name
//    properties.put("db", ""); // replace "" with target database name
//    properties.put("schema", ""); // replace "" with target schema name
    // properties.put("tracing", "all"); // optional tracing property

    // Replace <account_identifier> with your account identifier. See
    // https://docs.snowflake.com/en/user-guide/admin-account-identifier.html
    // for details.
    String connectStr = "jdbc:snowflake://OCA92998.snowflakecomputing.com";
    return DriverManager.getConnection(connectStr, properties);
  }
}
