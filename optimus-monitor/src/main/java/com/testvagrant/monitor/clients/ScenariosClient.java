package com.testvagrant.monitor.clients;

import com.testvagrant.monitor.entities.MongoService;
import com.testvagrant.monitor.requests.Scenario;
import com.testvagrant.monitor.requests.Screenshot;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ScenariosClient {

    private final String SCENARIOS = MongoService.getMongoService()+"/scenarios";
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

    public Scenario findRelevantScenario(String buildId, String scenarioName, Integer location,String udid) {
        Response get = given()
                .header("Content-Type", "application/json")
                .queryParam("buildId",buildId)
                .queryParam("scenarioName",scenarioName)
                .queryParam("location",location)
                .queryParam("deviceUdid",udid)
                .get(SCENARIOS+"/search/findByBuildIdAndScenarioNameAndLocationAndDeviceUdid");
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
                .post(MongoService.getMongoService()+"/screenshots");
        return screenshotPost.asString();
    }

    public List<Scenario> getDistinctScenarios() {
        Response distinctScenarios = given()
                .header("Content-Type", "application/json")
                .get(SCENARIOS + "/distinct");
        Scenario[] scenarios = distinctScenarios.as(Scenario[].class);
        return Arrays.asList(scenarios);
    }


    public Integer getBuildScenarioCount(String buildId) {
        Response buildScenarioCount = given()
                .header("Content-Type","application/json")
                .queryParam("buildId",buildId)
                .get(SCENARIOS+"/search/countByBuildId");
        return Integer.valueOf(buildScenarioCount.asString());
    }

    public Integer getBuildScenarioCountByStatus(String buildId, String status) {
        Response buildScenarioCountByStatus = given()
                .header("Content-Type","application/json")
                .queryParam("buildId",buildId)
                .queryParam("status",status)
                .get(SCENARIOS+"/search/countByBuildIdAndStatus");
        return Integer.valueOf(buildScenarioCountByStatus.asString());
    }


}
