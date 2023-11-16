package com.clickup.requests.task;

import com.clickup.requests.CreateBaseRequest;
import com.clickup.url.ClickupUrl;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class UpdateTaskRequest {

    public static Response updateTask(JSONObject task, String taskId) {
        return given()
                .spec(CreateBaseRequest.requestSpecWithLogs())
                .body(task.toString())
                .when()
                .put(ClickupUrl.getTaskUrl(taskId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
