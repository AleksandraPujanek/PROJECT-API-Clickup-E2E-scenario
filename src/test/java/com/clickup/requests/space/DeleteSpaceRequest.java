package com.clickup.requests.space;

import com.clickup.properties.ClickupProperties;
import com.clickup.requests.CreateBaseRequest;
import com.clickup.url.ClickupUrl;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class DeleteSpaceRequest {
    public static Response deleteSpace(String spaceId) {
        return given()
                .spec(CreateBaseRequest.requestSpecWithLogs())
                .when()
                .delete(ClickupUrl.getSpaceUrl(spaceId))
                .then()
                .log().ifError()
                .extract()
                .response();
    }

}
