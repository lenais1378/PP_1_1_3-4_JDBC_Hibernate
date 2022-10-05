package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private org.hibernate.Transaction transaction = null;


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession() ) {
            transaction = session.beginTransaction();
            String SQL = """
                    CREATE TABLE IF NOT EXISTS User
                    (id BIGINT primary key not null auto_increment,
                    name VARCHAR(255),
                    lastname VARCHAR(255),
                    age TINYINT)
                    """;
            session.createSQLQuery(SQL).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }



    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession() ){
            transaction = session.beginTransaction();
            String SQL = "DROP TABLE IF EXISTS User";
            session.createSQLQuery(SQL).executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession() ){
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }



    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession() ){
            users = session.createQuery("FROM User", User.class).list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        transaction = null;
        try (Session session = Util.getSessionFactory().openSession() ){
            transaction = session.beginTransaction();
            session.createQuery("DELETE User").executeUpdate();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
