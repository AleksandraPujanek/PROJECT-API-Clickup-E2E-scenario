package com.clickup.properties;

import java.util.ResourceBundle;

public class ClickupProperties {

    private static final String token = "clickup.token";
    private static final String teamId = "clickup.teamId";

    public static String getToken() {
        return getProperty(token);
    }

    public static String getTeamId() {
        return getProperty(teamId);
    }

    private static String getProperty(String key) {
        return ResourceBundle.getBundle("clickup").getString(key);
    }
}
