package edu.epam.web.util.mail;

import edu.epam.web.reader.PropertiesReader;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionCreator {

    private static PropertiesReader reader = new PropertiesReader();
    private static final String PATH = "src/main/resources/config/mail";
    private static final String USER_NAME_KEY = "mail.user.name";
    private static final String PASSWORD_KEY = "mail.user.password";

    public Session createSession() {
        Properties properties = reader.readPropertiesFile(PATH);
        String emailFrom = properties.getProperty(USER_NAME_KEY);
        String password = properties.getProperty(PASSWORD_KEY);
        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailFrom, password);
            }
        });
    }
}
