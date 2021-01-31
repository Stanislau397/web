package edu.epam.web.controller.command.impl;

import edu.epam.web.controller.command.Command;
import edu.epam.web.controller.command.PagePath;
import edu.epam.web.controller.command.RequestParameter;
import edu.epam.web.entity.User;
import edu.epam.web.service.impl.UserServiceImpl;
import edu.epam.web.util.mail.MailSender;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements Command<HttpServletRequest> {

    @Override
    public String execute(HttpServletRequest request) {
        String path = null;
        UserServiceImpl service = new UserServiceImpl();
        String accountName = request.getParameter(RequestParameter.USER_NAME);
        String accountPassword = request.getParameter(RequestParameter.USER_PASSWORD);
        String accountEmail = request.getParameter(RequestParameter.USER_EMAIL);
                User user = new User();
        user.setName(accountName);
        user.setEmail(accountEmail);

        if (service.insert(user, accountPassword)) {
            String message = "Account created";
            request.setAttribute(RequestParameter.MESSAGE_PARAM, message);
            request.setAttribute(RequestParameter.USER_NAME_PARAM, accountName);
            request.setAttribute(RequestParameter.EMAIL_PARAM, accountEmail);
            path =  PagePath.REGISTER_SUCCESS;
        }
        return path;
    }
}
