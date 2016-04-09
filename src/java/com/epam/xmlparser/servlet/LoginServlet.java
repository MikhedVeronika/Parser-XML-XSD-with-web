package com.epam.xmlparser.servlet;

import com.epam.xmlparser.command.Command;
import com.epam.xmlparser.command.RequestHelper;
import com.epam.xmlparser.manager.ConfigurationManager;
import com.epam.xmlparser.manager.MessageManager;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class LoginServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(LoginServlet.class);

    RequestHelper requestHelper = RequestHelper.getInstance();

    public LoginServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        logger.info("GET");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        logger.info("POST");
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        try {
            Command command = requestHelper.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}