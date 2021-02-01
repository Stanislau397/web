package edu.epam.web.util.mail;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

    private static final Logger logger = LogManager.getLogger(MailSender.class);
    private static final boolean CONDITION = true;
    private static SessionCreator sessionCreator = new SessionCreator();
    private MimeMessage message;

    public void sendEmail(String email, String subject, String body) {
        Session mailSession = sessionCreator.createSession();
        mailSession.setDebug(CONDITION);
        message = new MimeMessage(mailSession);
        try {
            message.setSubject(subject);
            message.setText(body);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            Transport.send(message);
        } catch (AddressException e) {
            logger.log(Level.ERROR, e.getMessage());
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }
}
