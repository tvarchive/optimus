package com.testvagrant.optimus.utils;

import com.testvagrant.commons.helpers.ScenarioHelper;
import cucumber.api.Result;
import cucumber.api.Scenario;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.List;

public class ScenarioHelperTest{

    @Test
    public void getLineNumberFromScenarioIdThatContainsSingleColon(){
        ScenarioHelper scenarioHelper = new ScenarioHelper(getScenarioInstance("C\\Downloads\\Project\\LoginFeature:4"));
        String lineNumber = scenarioHelper.getLineNumber();
        Assert.assertEquals(lineNumber,"4");
    }

    @Test
    public void getLineNumberFromScenarioIdThatContainsMultipleColons(){
        ScenarioHelper scenarioHelper = new ScenarioHelper(getScenarioInstance("C\\Downloads\\Project:\\LoginFeature:4"));
        String lineNumber = scenarioHelper.getLineNumber();
        Assert.assertEquals(lineNumber,"4");

        scenarioHelper = new ScenarioHelper(getScenarioInstance("C\\Downloads:\\Project:\\LoginFeature:4"));
        lineNumber = scenarioHelper.getLineNumber();
        Assert.assertEquals(lineNumber,"4");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void getLineNumberFromScenarioIdThatDoesNotContainAColon(){
        ScenarioHelper scenarioHelper = new ScenarioHelper(getScenarioInstance("C\\Downloads\\Project\\LoginFeature4"));
        String lineNumber = scenarioHelper.getLineNumber();
        Assert.assertEquals(lineNumber,"4");
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void getLineNumberFromScenarioIdFromAnEmptyString(){
        ScenarioHelper scenarioHelper = new ScenarioHelper(getScenarioInstance(""));
        String lineNumber = scenarioHelper.getLineNumber();
        Assert.assertEquals(lineNumber,"4");
    }

    private Scenario getScenarioInstance(String withId){
        return new Scenario() {
            @Override
            public Collection<String> getSourceTagNames() {
                return null;
            }

            @Override
            public Result.Type getStatus() {
                return null;
            }

            @Override
            public boolean isFailed() {
                return false;
            }

            @Override
            public void embed(byte[] data, String mimeType) {

            }

            @Override
            public void write(String text) {

            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public String getId() {
                return withId;
            }

            @Override
            public String getUri() {
                return null;
            }

            @Override
            public List<Integer> getLines() {
                return null;
            }
        };
    }
}
