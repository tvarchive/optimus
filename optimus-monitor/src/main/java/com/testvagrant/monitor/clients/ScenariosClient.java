package com.testvagrant.monitor.clients;

import com.testvagrant.monitor.requests.Scenario;
import com.testvagrant.monitor.requests.Screenshot;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

import static com.testvagrant.monitor.clients.EndPoints.BASE_END_POINT;
import static io.restassured.RestAssured.given;

public class ScenariosClient {

    private final String SCENARIOS = BASE_END_POINT+"/scenarios";
    public Scenario createNewScenario(Scenario scenario, List<Integer> lines) {
        if(lines.size()>1) {
            Integer scenariosCount = getNumberOfExistingScenariosByName(scenario.getBuildId(), scenario.getScenarioName());
            scenario.setDataRowNumber(++scenariosCount);
        }
        Response scenarioId = given()
                .header("Content-Type", "application/json")
                .body(scenario)
                .post(SCENARIOS);
        return scenarioId.as(Scenario.class);
    }

    public Integer getNumberOfExistingScenariosByName(String buildId, String scenarioName) {
        Response response = given()
                .header("Content-Type", "application/json")
                .queryParam("buildId", buildId)
                .queryParam("scenarioName", scenarioName)
                .get(SCENARIOS + "/search/countByBuildIdAndScenarioName");
        return Integer.valueOf(response.asString());
    }

    public Scenario findRelevantScenario(String buildId, String scenarioName, Integer location) {
        Response get = given()
                .header("Content-Type", "application/json")
                .queryParam("buildId",buildId)
                .queryParam("scenarioName",scenarioName)
                .queryParam("location",location)
                .get(SCENARIOS+"/search/findByBuildIdAndScenarioNameAndLocation");
        return get.as(Scenario.class);
    }


    public Scenario updateScenario(String buildId, Scenario scenario) {
        Response updatedScenario = given()
                .header("Content-Type","application/json")
                .body(scenario)
                .patch(SCENARIOS+String.format("/%s",scenario.getId()));
        return updatedScenario.as(Scenario.class);
    }


    public String loadScreenshot(Screenshot screenshot) {
        Response screenshotPost = given()
                .header("Content-Type", "application/json")
                .body(screenshot)
                .post(BASE_END_POINT+"/screenshots");
        return screenshotPost.asString();
    }

    public List<Scenario> getDistinctScenarios() {
        Response distinctScenarios = given()
                .header("Content-Type", "application/json")
                .get(SCENARIOS + "/distinct");
        Scenario[] scenarios = distinctScenarios.as(Scenario[].class);
        return Arrays.asList(scenarios);
    }


}
