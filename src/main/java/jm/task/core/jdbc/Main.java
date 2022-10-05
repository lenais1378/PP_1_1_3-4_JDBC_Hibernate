package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
//        Util.getConnection();
//        UserDao userDao = new UserDaoJDBCImpl();
//        userDao.createUsersTable();
//        List<User> userList = new ArrayList<>();
//
//        User user1 = new User ("Name1", "LastName1", (byte) 20);
//        User user2 = new User ("Name2", "LastName2", (byte) 25);
//        User user3 = new User ("Name3", "LastName3", (byte) 31);
//        User user4 = new User ("Name4", "LastName4", (byte) 38);
//        userList.add(user1);
//        userList.add(user2);
//        userList.add(user3);
//        userList.add(user4);
//        for (User user : userList) {
//            userDao.saveUser(user.getName(), user.getLastName(), user.getAge());
//            System.out.println("User " + user.getName() + " added.");
//        }
//        userDao.removeUserById(1);
//        userDao.getAllUsers();
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
