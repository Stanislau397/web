package edu.epam.web.controller.command.impl;

import edu.epam.web.controller.command.Command;
import edu.epam.web.controller.command.PagePath;
import edu.epam.web.controller.command.RequestParameter;
import edu.epam.web.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class LogInCommand implements Command<HttpServletRequest> {

    @Override
    public String execute(HttpServletRequest request) {
        String path = null;
        UserServiceImpl service = new UserServiceImpl();
        String logInName = request.getParameter(RequestParameter.LOGIN_USER_NAME);
        String logInPassword = request.getParameter(RequestParameter.LOGIN_PASSWORD);

        if (service.findByUserNameAndPassword(logInName, logInPassword)) {
            request.setAttribute(RequestParameter.USER_NAME_PARAM, logInName);
            request.setAttribute(RequestParameter.PASSWORD_PARAM, logInPassword);
            path = PagePath.LOGIN_SUCCESS;
        }
        return path;
    }
}
