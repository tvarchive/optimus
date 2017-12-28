package com.testvagrant.monitor.clients;

import com.testvagrant.monitor.entities.MongoService;
import com.testvagrant.monitor.requests.Intellisense;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class IntellisenseClient {
    private final String INTELLISENSE = MongoService.getMongoService()+"/intellisense";


    public Intellisense recordExceptionSense(Intellisense intellisense) {
        Response response = given()
                .header("Content-Type", "application/json")
                .body(intellisense)
                .post(INTELLISENSE);
        return response.as(Intellisense.class);
    }
}
