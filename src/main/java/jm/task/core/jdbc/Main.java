package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl use = new UserServiceImpl();

        use.createUsersTable();
        use.saveUser("Alex", "Luter", (byte) 27);
        use.saveUser("Bob", "MacBoberts", (byte) 19);
        use.saveUser("Test", "Testov", (byte) 111);
        use.saveUser("admin", "password", (byte) 1);
        System.out.println(use.getAllUsers());
        use.cleanUsersTable();
        use.dropUsersTable();
    }
}
