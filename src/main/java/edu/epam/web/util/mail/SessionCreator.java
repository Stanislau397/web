package edu.epam.web.util.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.io.IOException;
import java.util.Properties;

public class SessionCreator {

    private static final Logger logger = LogManager.getLogger(SessionCreator.class);
    private static final String MAIL_PROPERTIES = "config/mail";
    private static final String USER_NAME_KEY = "mail.user.name";
    private static final String PASSWORD_KEY = "mail.user.password";

    public Session createSession() {
        Properties properties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            properties.load(classLoader.getResourceAsStream(MAIL_PROPERTIES));
        } catch (IOException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        String emailFrom = properties.getProperty(USER_NAME_KEY);
        String password = properties.getProperty(PASSWORD_KEY);
        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, password);
            }
        });
    }
}
