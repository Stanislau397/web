package edu.epam.web.controller;

import edu.epam.web.util.FileUploader;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UploadFileServlet", value = "/upload_file_servlet")
public class UploadFileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        FileUploader fileUploader = new FileUploader();
        String page = "jsp/upload_file_success.jsp";
        if (fileUploader.uploadFile(request)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }
}
