package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnect() {
        String url = "jdbc:mysql://localhost:3306/sys";
        String userName = "root";
        String paswd = "123456789";
        Connection connect = null;

        try {
            connect = DriverManager.getConnection(url, userName, paswd);
        } catch (SQLException e) {

        }
        if (connect == null) {
            throw new NullPointerException("Не удалось найти БД");
        }
        return connect;
    }
}
