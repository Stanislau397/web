package edu.epam.web.controller.command;

import edu.epam.web.controller.command.impl.LogInCommand;
import edu.epam.web.controller.command.impl.RegistrationCommand;

import javax.servlet.http.HttpServletRequest;

public enum CommandType {
    LOGIN(new LogInCommand()),
    REGISTER(new RegistrationCommand());

    private Command<HttpServletRequest> command;

    CommandType(Command<HttpServletRequest> command) {
        this.command = command;
    }

    public Command<HttpServletRequest> getCommand() {
        return command;
    }
}
