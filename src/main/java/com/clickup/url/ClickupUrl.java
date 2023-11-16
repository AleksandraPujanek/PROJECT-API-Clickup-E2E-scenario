package com.clickup.url;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClickupUrl {

    private static final String baseUrl = "https://api.clickup.com/api/v2";
    private static final String team = "/team";
    private static final String space = "/space";
    private static final String list = "/list";
    private static final String task = "/task";

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static String getTeams() {
        return team;
    }

    public static String getTeam(String teamId) {
        return team + "/" + teamId;
    }

    public static String getSpacesUrl(String teamId) {
        return getTeam(teamId) + space;
    }

    public static String getSpaceUrl(String spaceId) {
        return space + "/" + spaceId;
    }

    public static String getListsUrl(String spaceId) {
        return getSpaceUrl(spaceId) + list;
    }

    public static String getListUrl(String listId) {
        return list + "/" + listId;
    }

    public static String getTasksUrl(String listId) {
        return getListUrl(listId) + task;
    }

    public static String getTaskUrl(String taskId) {
        return task + "/" + taskId;
    }

}
