package edu.epam.web.service.impl;

import edu.epam.web.dao.impl.UserDaoImpl;
import edu.epam.web.entity.User;
import edu.epam.web.exception.DaoException;
import edu.epam.web.service.UserService;
import edu.epam.web.util.PasswordEncryption;
import edu.epam.web.validator.AccountValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService<User> {

    public static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private static final UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public boolean insert(User user, String password) {
        AccountValidator validator = new AccountValidator();
        PasswordEncryption encryption = new PasswordEncryption();
        boolean isRegistered = false;
        String email = user.getEmail();
        String userName = user.getName();
        try {
            if (validator.isValidAccountData(userName, password, email)) {
                String encryptedPassword = encryption.encryptPassword(password);
                isRegistered = userDao.create(user, encryptedPassword);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return isRegistered;
    }

    @Override
    public boolean findByUserNameAndPassword(String login, String password) {
        AccountValidator validator = new AccountValidator();
        PasswordEncryption encryption = new PasswordEncryption();
        boolean isLoggedIn = false;
        try {
            if (validator.isValidLogInData(login, password)) {
                String encryptedPassword = encryption.encryptPassword(password);
                isLoggedIn = userDao.findByLoginAndPassword(login, encryptedPassword);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return isLoggedIn;
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return users;
    }

    @Override
    public boolean updateUserPassword(User user, String password) {
        AccountValidator validator = new AccountValidator();
        PasswordEncryption encryption = new PasswordEncryption();
        boolean isUpdatedPassword = false;
        try {
            if (validator.isValidPassword(password)) {
                String encryptedPassword = encryption.encryptPassword(password);
                isUpdatedPassword = userDao.updatePassword(user, encryptedPassword);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return isUpdatedPassword;
    }

    @Override
    public boolean updateUserName(User user, String newUserName) {
        AccountValidator validator = new AccountValidator();
        String oldUserName = user.getName();
        boolean isUpdatedUserName = false;
        try {
            if (validator.isValidUserName(oldUserName) && validator.isValidUserName(newUserName)) {
                isUpdatedUserName = userDao.updateUserName(user, newUserName);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return isUpdatedUserName;
    }

    @Override
    public boolean updateUserEmail(User user, String newEmail) {
        AccountValidator validator = new AccountValidator();
        String oldEmail = user.getEmail();
        boolean isUpdatedEmail = false;
        try {
            if (validator.isValidEmail(oldEmail) && validator.isValidEmail(newEmail)) {
                isUpdatedEmail = userDao.updateEmail(user, newEmail);
            }
        } catch (DaoException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return isUpdatedEmail;
    }
}
