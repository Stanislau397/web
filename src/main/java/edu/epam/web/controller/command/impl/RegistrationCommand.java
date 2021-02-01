package edu.epam.web.controller.command.impl;

import edu.epam.web.controller.command.Command;
import edu.epam.web.controller.command.PagePath;
import edu.epam.web.controller.command.RequestParameter;
import edu.epam.web.entity.User;
import edu.epam.web.service.impl.UserServiceImpl;
import edu.epam.web.util.mail.MailSender;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command<HttpServletRequest> {

    private static final String MESSAGE = "The letter was sent to your mail";
    private static final String SUBJECT = "WELCOME";
    private static final String BODY = "Your account has been created";

    @Override
    public String execute(HttpServletRequest request) {
        String path = null;
        UserServiceImpl service = new UserServiceImpl();
        MailSender mailSender = new MailSender();
        String accountName = request.getParameter(RequestParameter.USER_NAME);
        String accountPassword = request.getParameter(RequestParameter.USER_PASSWORD);
        String accountEmail = request.getParameter(RequestParameter.USER_EMAIL);
        User user = new User();
        user.setName(accountName);
        user.setEmail(accountEmail);

        if (service.insert(user, accountPassword)) {
            mailSender.sendEmail(accountEmail, SUBJECT, BODY);
            request.setAttribute(RequestParameter.MESSAGE_PARAMETER, MESSAGE);
            request.setAttribute(RequestParameter.USER_NAME_PARAMETER, accountName);
            request.setAttribute(RequestParameter.EMAIL_PARAMETER, accountEmail);
            path = PagePath.REGISTER_SUCCESS;
        }
        return path;
    }
}
