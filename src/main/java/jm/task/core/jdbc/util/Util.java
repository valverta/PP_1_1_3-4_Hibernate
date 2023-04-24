package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/sys";
    private static final String userName = "root";
    private static final String paswd = "123456789";
    private static Connection connect = null;
    public static Connection getConnect() {
        try {
            connect = DriverManager.getConnection(url, userName, paswd);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        if (connect == null) {
            throw new NullPointerException("Не удалось найти БД");
        }
        return connect;
    }
    public static void closeConnection() {
        try {
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
