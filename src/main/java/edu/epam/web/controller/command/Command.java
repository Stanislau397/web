package edu.epam.web.controller.command;

public interface Command<T> {

    String execute(T t) throws ClassNotFoundException;
}
