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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MailSender implements Runnable {

    private static final Logger logger = LogManager.getLogger(MailSender.class);
    private static final boolean CONDITION = true;
    private static SessionCreator sessionCreator = new SessionCreator();
    private static Lock lock = new ReentrantLock();
    private MimeMessage message;
    private String email;

    public MailSender(String email) {
        this.email = email;
    }

    public void sendEmail() {
        Session mailSession = sessionCreator.createSession();
        mailSession.setDebug(CONDITION);
        message = new MimeMessage(mailSession);
        String text = "Hi, your account has benn successfully created.";
        String subject = "Account info";
        try {
            logger.log(Level.INFO, email + " " + email);
            message.setSubject(subject);
            message.setText(text);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            Transport.send(message);
        } catch (AddressException e) {
            logger.log(Level.ERROR, e.getMessage());
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

    @Override
    public void run() {
        lock.lock();
        try {
            sendEmail();
            Transport.send(message);
        } catch (MessagingException e) {
            logger.log(Level.ERROR, e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
