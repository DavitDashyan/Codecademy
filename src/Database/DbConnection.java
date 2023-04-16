/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DbConnection {
    private static String connectionUrl = "jdbc:sqlserver://localhost;databaseName=Codecadamy;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
    private Connection connection;
    private static final String SQLDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";



    public void connectToDb() throws SQLException {

        try {
            Class.forName(SQLDriver);
            connection = DriverManager.getConnection(connectionUrl);
            
        } catch (Exception e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }

    }



    public ResultSet getResult(String SQL) throws SQLException {
        try {
            return connection.createStatement().executeQuery(SQL);
        } catch (SQLException e) {

            System.out.println(e);
            return null;
        }
    }
    public void execute(String SQL) throws SQLException {
        connection.createStatement().execute(SQL);

    }
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }
}