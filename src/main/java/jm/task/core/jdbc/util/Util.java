package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/sys";
    private static final String userName = "root";
    private static final String paswd = "123456789";
    private static Connection connect = null;
    private static SessionFactory sessionFactory = null;
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

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();

        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", url);
        configuration.setProperty("hibernate.connection.username", userName);
        configuration.setProperty("hibernate.connection.password", paswd);
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        //configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }
    public static void closeFactory() {
        if (sessionFactory != null)
            sessionFactory.close();
    }
}