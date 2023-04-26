package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();
    private Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            try {
                transaction = session.getTransaction();
                transaction.begin();
                session.createSQLQuery("CREATE TABLE IF NOT EXISTS Users" +
                                "(id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age TINYINT, PRIMARY KEY (id));")
                        .executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null)
                    transaction.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            try {
                transaction = session.getTransaction();
                transaction.begin();
                session.createSQLQuery("DROP TABLE IF EXISTS Users;").executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null)
                    transaction.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            try {
                transaction = session.getTransaction();
                transaction.begin();
                User user = new User(name, lastName, age);
                session.save(user);
                System.out.printf("User с именем - %s добавлен в базу данных\n", name);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null)
                    transaction.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                transaction = session.getTransaction();
                transaction.begin();
                User user = session.get(User.class, id);
                session.delete(user);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null)
                    transaction.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = sessionFactory.openSession()) {
            try {
                transaction = session.getTransaction();
                transaction.begin();
                users = session.createQuery("FROM User").getResultList();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null)
                    transaction.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            try {
                transaction = session.getTransaction();
                transaction.begin();
                session.createQuery("DELETE User").executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null)
                    transaction.rollback();
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
}