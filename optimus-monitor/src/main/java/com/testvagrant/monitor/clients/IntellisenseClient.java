package com.testvagrant.monitor.clients;

import com.testvagrant.monitor.requests.Intellisense;
import io.restassured.response.Response;

import static com.testvagrant.monitor.clients.EndPoints.BASE_END_POINT;
import static io.restassured.RestAssured.given;

public class IntellisenseClient {
    private final String INTELLISENSE = BASE_END_POINT+"/intellisense";


    public Intellisense recordExceptionSense(Intellisense intellisense) {
        Response response = given()
                .header("Content-Type", "application/json")
                .body(intellisense)
                .post(INTELLISENSE);
        return response.as(Intellisense.class);
    }
}
