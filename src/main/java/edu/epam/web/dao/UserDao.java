package edu.epam.web.dao;

import edu.epam.web.entity.User;
import edu.epam.web.exception.DaoException;

public interface UserDao extends BaseDao<User>{

    boolean findByLoginAndPassword(String login, String password) throws DaoException;
    boolean updateUserName(User user, String newUserName) throws DaoException;
    boolean updatePassword(User user, String password) throws DaoException;
    boolean updateEmail(User user, String newEmail) throws DaoException;
}
