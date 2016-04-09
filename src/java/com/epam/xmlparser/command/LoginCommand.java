package com.epam.xmlparser.command;

import com.epam.xmlparser.logic.LoginLogic;
import com.epam.xmlparser.manager.ConfigurationManager;
import com.epam.xmlparser.manager.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        if (LoginLogic.check(login, pass)) {
            request.getSession().setAttribute("user", login);
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        } else {
            request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            page = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        return page;
    }
}