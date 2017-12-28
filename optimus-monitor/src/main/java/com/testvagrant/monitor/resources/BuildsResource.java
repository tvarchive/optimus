package com.testvagrant.monitor.resources;

import com.testvagrant.monitor.requests.Build;
import com.testvagrant.monitor.responses.BuildsResponse;
import io.restassured.response.Response;

import java.util.List;

import static com.testvagrant.monitor.clients.EndPoints.BASE_END_POINT;
import static io.restassured.RestAssured.given;

public class BuildsResource {

    private final String BUILDS = BASE_END_POINT+"/builds";

    public List<Build> getAllBuildsInDescendingOrder() {
        Response get = given()
                .header("Content-Type", "application/json")
                .get(BUILDS+"/search/findAllBuildsByOrderByBuildStartTimeDesc");
        BuildsResponse as = get.as(BuildsResponse.class);
        return as.getContent();
    }
}
