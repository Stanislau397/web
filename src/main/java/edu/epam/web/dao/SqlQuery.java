package edu.epam.web.dao;

public class SqlQuery {

    public static final String SELECT_USER = "SELECT username, password FROM users WHERE username = (?) and password = (?)";
    public static final String INSERT_TO_USER = "INSERT INTO users (username, email, password) VALUES (?,?,?)";
    public static final String SELECT_ALL_USERS = "SELECT username, password, email FROM users";
    public static final String UPDATE_PASSWORD = "UPDATE users SET password = (?) WHERE username = (?)";
    public static final String UPDATE_USER_NAME = "UPDATE users SET username = (?) WHERE username = (?)";
    public static final String UPDATE_EMAIL = "UPDATE users SET email = (?) WHERE username = (?)";
}
