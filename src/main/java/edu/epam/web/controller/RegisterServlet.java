package edu.epam.web.controller;

import edu.epam.web.connection.ConnectionPool;
import edu.epam.web.controller.command.PagePath;
import edu.epam.web.controller.command.impl.RegistrationCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register_servlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RegistrationCommand command = new RegistrationCommand();
        String page = command.execute(request);
        if (page != null) {
            request.getRequestDispatcher(page).forward(request, response);
        } else {
            response.sendRedirect(PagePath.REGISTER_PAGE);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.INSTANCE.destroyPool();
        super.destroy();
    }
}
