package edu.epam.web.dao.impl;

import edu.epam.web.connection.ConnectionPool;
import edu.epam.web.dao.SqlQuery;
import edu.epam.web.dao.UserDao;
import edu.epam.web.entity.User;
import edu.epam.web.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    public static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public boolean create(User user, String password) throws DaoException {
        boolean isRegistered;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlQuery.INSERT_TO_USER)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, password);
            int result = ps.executeUpdate();
            isRegistered = result == 1;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return isRegistered;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public boolean update(User user) {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             Statement st = connection.createStatement()) {

            ResultSet resultSet = st.executeQuery(SqlQuery.SELECT_ALL_USERS);
            while (resultSet.next()) {
                String userName = resultSet.getString(2);
                String userEmail = resultSet.getString(3);
                String userPassword = resultSet.getString(4);
                User user = new User(userName, userEmail, userPassword);
                users.add(user);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return users;
    }

    @Override
    public boolean findByLoginAndPassword(String login, String password) throws DaoException {
        boolean isLoggedIn;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlQuery.SELECT_USER)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            isLoggedIn = resultSet.next();
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return isLoggedIn;
    }

    @Override
    public boolean updateUserName(User user, String newUserName) throws DaoException {
        boolean isUpdatedUserName;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlQuery.UPDATE_USER_NAME)) {
            ps.setString(1, newUserName);
            ps.setString(2, user.getName());
            int update = ps.executeUpdate();
            isUpdatedUserName = update == 1;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return isUpdatedUserName;
    }

    @Override
    public boolean updatePassword(User user, String password) throws DaoException {
        boolean isUpdatedPassword;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlQuery.UPDATE_PASSWORD)) {
            ps.setString(1, password);
            ps.setString(2, user.getName());
            int update = ps.executeUpdate();
            isUpdatedPassword = update == 1;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return isUpdatedPassword;
    }

    @Override
    public boolean updateEmail(User user, String newEmail) throws DaoException {
        boolean isUpdatedEmail;
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlQuery.UPDATE_EMAIL)) {
            ps.setString(1, newEmail);
            ps.setString(2, user.getEmail());
            int update = ps.executeUpdate();
            isUpdatedEmail = update == 1;
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
            throw new DaoException();
        }
        return  isUpdatedEmail;
    }
}
