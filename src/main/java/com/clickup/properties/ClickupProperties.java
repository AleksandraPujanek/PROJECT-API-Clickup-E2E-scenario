package com.clickup.properties;

import java.util.ResourceBundle;

public class ClickupProperties {

    private static final String token = "clickup.token";
    private static final String teamId = "clickup.teamId";

    public static String getToken() {
        if (getProperty(token).isEmpty() || getProperty(token).startsWith("YOUR")) {
            return System.getProperty("token");
        } else {
            return getProperty(token);
        }
    }

    public static String getTeamId() {
        if (getProperty(teamId).isEmpty() || getProperty(teamId).startsWith("YOUR")) {
            return System.getProperty("teamId");
        } else {
            return getProperty(teamId);
        }
    }

    private static String getProperty(String key) {
        return ResourceBundle.getBundle("clickup").getString(key);
    }
}
