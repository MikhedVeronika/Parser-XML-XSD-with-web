package com.epam.xmlparser.logic;


public class LoginLogic {
    public LoginLogic () {}

    public static boolean check (String login, String password) {
        if (login.equals("")) return false;
        return login.equals(password);
    }
}
