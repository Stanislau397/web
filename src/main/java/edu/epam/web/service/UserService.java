package edu.epam.web.service;

import edu.epam.web.entity.User;
import edu.epam.web.exception.DaoException;

import java.util.List;

public interface UserService<T> {

    boolean insert(T t, String password) throws ClassNotFoundException, DaoException;
    boolean findByUserNameAndPassword(String login, String password);
    List<T> findAllUsers();
    boolean updateUserPassword(User user, String password);
    boolean updateUserName(User user, String newUserName);
    boolean updateUserEmail(User user, String newEmail);
}
