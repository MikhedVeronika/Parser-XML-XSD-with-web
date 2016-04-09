package com.epam.xmlparser.manager;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static ConfigurationManager instance;
    private ResourceBundle resourceBundle;
    private static final String BUNDLE_NAME = "conf";

    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";
    public static final String RESULTS_PAGE_PATH = "RESULTS_PAGE_PATH";
    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }
    public String getProperty(String key) {
        return (String)resourceBundle.getObject(key);
    }
}
