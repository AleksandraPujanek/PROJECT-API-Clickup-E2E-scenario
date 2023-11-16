package com.clickup.requests;

import com.clickup.properties.ClickupProperties;
import com.clickup.url.ClickupUrl;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class CreateBaseRequest {

    private static RequestSpecBuilder requestBuilder;

    public static RequestSpecification requestSpec() {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(ClickupUrl.getBaseUrl());
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addHeader("Authorisation", ClickupProperties.getToken());
        requestBuilder.addFilter(new AllureRestAssured());

        return requestBuilder.build();
    }

    public static RequestSpecification requestSpecWithLogs() {
        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri(ClickupUrl.getBaseUrl());
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addHeader("Authorization", ClickupProperties.getToken());
        requestBuilder.addFilter(new RequestLoggingFilter());
        requestBuilder.addFilter(new ResponseLoggingFilter());
        requestBuilder.addFilter(new AllureRestAssured());

        return requestBuilder.build();
    }
}
