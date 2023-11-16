package com.clickup.requests.task;

import com.clickup.requests.CreateBaseRequest;
import com.clickup.url.ClickupUrl;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateTaskRequest {

    public static Response createTask(JSONObject task, String listId) {
        return given()
                .spec(CreateBaseRequest.requestSpecWithLogs())
                .body(task.toString())
                .when()
                .post(ClickupUrl.getTasksUrl(listId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
