package com.viur;

import java.sql.*;

/**
 * Created by ultrix on 21/12/2016.
 */
public class Main {

    private static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ignore) {
            }
        }
    }

    private static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ignore) {
            }
        }
    }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignore) {
            }
        }
    }

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:");

            statement = connection.createStatement();
            statement.execute("CREATE TABLE Table ENGINE \"com.viur.RestEngine\"");
            resultSet = statement.executeQuery("SELECT Date FROM Table order by Date asc"); // incorrect order
            //resultSet = statement.executeQuery("SELECT Date FROM Table order by Date desc"); // correct order

            while (resultSet.next()) {
                System.out.println(resultSet.getObject(1));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        } finally {
            closeResultSet(resultSet);
            closeStatement(statement);
            closeConnection(connection);
        }

    }


}
