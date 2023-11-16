package com.clickup.requests.list;

import com.clickup.requests.CreateBaseRequest;
import com.clickup.url.ClickupUrl;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateListRequest {

    public static Response createList(JSONObject list, String spaceId) {
        return given()
                .spec(CreateBaseRequest.requestSpecWithLogs())
                .body(list.toString())
                .when()
                .post(ClickupUrl.getListsUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
