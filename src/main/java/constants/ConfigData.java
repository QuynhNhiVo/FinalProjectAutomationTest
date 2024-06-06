package constants;

import helpers.PropertiesHelpers;

public class ConfigData {
    public static String AUTHOR = PropertiesHelpers.getValue("AUTHOR");
    public static String FRAMEWORK = PropertiesHelpers.getValue("FRAMEWORK");

    public static String URL = PropertiesHelpers.getValue("URL");
    public static String USERNAME = PropertiesHelpers.getValue("USERNAME");
    public static String PASSWORD = PropertiesHelpers.getValue("PASSWORD");

    public static String HEADLESS = PropertiesHelpers.getValue("HEADLESS");

    public static int TIMEOUT = Integer.parseInt(PropertiesHelpers.getValue("TIMEOUT"));
    public static int STEP_TIME = Integer.parseInt(PropertiesHelpers.getValue("STEP_TIME"));
    public static int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesHelpers.getValue("PAGE_LOAD_TIMEOUT"));

    public static String EXTENT_REPORT = PropertiesHelpers.getValue("EXTENT_REPORT");
    public static String SCREENSHOT = PropertiesHelpers.getValue("SCREENSHOT");
    public static String SCREENSHOT_PATH = PropertiesHelpers.getValue("SCREENSHOT_PATH");
    public static String RECORD = PropertiesHelpers.getValue("RECORD");
    public static String RECORD_PATH = PropertiesHelpers.getValue("RECORD_PATH");

    public static String LOGIN_HRM_EXCEL = PropertiesHelpers.getValue("LOGIN_HRM_EXCEL");
}
