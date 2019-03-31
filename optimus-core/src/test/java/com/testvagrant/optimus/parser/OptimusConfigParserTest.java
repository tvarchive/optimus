package com.testvagrant.optimus.parser;

import com.testvagrant.optimus.entity.ExecutionDetails;
import org.junit.Assert;
import org.junit.Test;

public class OptimusConfigParserTest {

    String appJsonWithRunConfig = "{\n" +
            "  \"executionDetails\": {\n" +
            "    \"appium_js_path\": \"/usr/local/bin/appium\",\n" +
            "    \"appium_node_path\": \"/usr/local/bin/node\",\n" +
            "    \"runConfig\":\"appRide\"\n" +
            "  },\n" +
            "  \"testFeed\":[\n" +
            "    {\n" +
            "      \"belongsTo\":\"Consumer\",\n" +
            "      \"runsOn\": \"any\",\n" +
            "      \"appDir\": \"app\",\n" +
            "      \"nativeApp\":true,\n" +
            "      \"optimusDesiredCapabilities\": {\n" +
            "        \"appiumServerCapabilities\": {\n" +
            "          \"app\": \"DummyConsumer.apk\",\n" +
            "          \"platformName\": \"Android\",\n" +
            "          \"noReset\":false,\n" +
            "          \"newCommandTimeout\":120\n" +
            "        },\n" +
            "        \"androidOnlyCapabilities\": {\n" +
            "          \"appActivity\": \"com.dummycompany.app.Splash\",\n" +
            "          \"appPackage\": \"com.dummycompany.app.staging\",\n" +
            "          \"avdLaunchTimeout\": 300000,\n" +
            "          \"useKeystore\": false,\n" +
            "          \"autoGrantPermissions\": true\n" +
            "        }\n" +
            "      },\n" +
            "      \"deviceState\": {\n" +
            "        \"captureVideo\": true\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}\n";

    String appJsonWithoutRunConfig = "{\n" +
            "  \"executionDetails\": {\n" +
            "    \"appium_js_path\": \"/usr/local/bin/appium\",\n" +
            "    \"appium_node_path\": \"/usr/local/bin/node\"\n" +
            "  },\n" +
            "  \"testFeed\":[\n" +
            "    {\n" +
            "      \"belongsTo\":\"Consumer\",\n" +
            "      \"runsOn\": \"any\",\n" +
            "      \"appDir\": \"app\",\n" +
            "      \"nativeApp\":true,\n" +
            "      \"optimusDesiredCapabilities\": {\n" +
            "        \"appiumServerCapabilities\": {\n" +
            "          \"app\": \"DummyConsumer.apk\",\n" +
            "          \"platformName\": \"Android\",\n" +
            "          \"noReset\":false,\n" +
            "          \"newCommandTimeout\":120\n" +
            "        },\n" +
            "        \"androidOnlyCapabilities\": {\n" +
            "          \"appActivity\": \"com.dummycompany.app.Splash\",\n" +
            "          \"appPackage\": \"com.dummycompany.app.staging\",\n" +
            "          \"avdLaunchTimeout\": 300000,\n" +
            "          \"useKeystore\": false,\n" +
            "          \"autoGrantPermissions\": true\n" +
            "        }\n" +
            "      },\n" +
            "      \"deviceState\": {\n" +
            "        \"captureVideo\": true\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}\n";

    String appJsonWithoutExecutionDetails = "{\n" +
            "  \"testFeed\":[\n" +
            "    {\n" +
            "      \"belongsTo\":\"Consumer\",\n" +
            "      \"runsOn\": \"any\",\n" +
            "      \"appDir\": \"app\",\n" +
            "      \"nativeApp\":true,\n" +
            "      \"optimusDesiredCapabilities\": {\n" +
            "        \"appiumServerCapabilities\": {\n" +
            "          \"app\": \"DummyConsumer.apk\",\n" +
            "          \"platformName\": \"Android\",\n" +
            "          \"noReset\":false,\n" +
            "          \"newCommandTimeout\":120\n" +
            "        },\n" +
            "        \"androidOnlyCapabilities\": {\n" +
            "          \"appActivity\": \"com.dummycompany.app.Splash\",\n" +
            "          \"appPackage\": \"com.dummycompany.app.staging\",\n" +
            "          \"avdLaunchTimeout\": 300000,\n" +
            "          \"useKeystore\": false,\n" +
            "          \"autoGrantPermissions\": true\n" +
            "        }\n" +
            "      },\n" +
            "      \"deviceState\": {\n" +
            "        \"captureVideo\": true\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}\n";
    @Test
    public void execDetailsWithRunConfig() {
        OptimusConfigParser optimusConfigParser = new OptimusConfigParser(appJsonWithRunConfig);
        ExecutionDetails executionDetails = optimusConfigParser.getExecutionDetails();
        Assert.assertEquals("AppRide",executionDetails.getRunConfig());
    }

    @Test
    public void execDetailsWithoutRunConfig() {
        OptimusConfigParser optimusConfigParser = new OptimusConfigParser(appJsonWithoutRunConfig);
        ExecutionDetails executionDetails = optimusConfigParser.getExecutionDetails();
        Assert.assertEquals("default",executionDetails.getRunConfig());
    }

    @Test
    public void execDetailsWithoutExecutionDetails() {
        OptimusConfigParser optimusConfigParser = new OptimusConfigParser(appJsonWithoutRunConfig);
        ExecutionDetails executionDetails = optimusConfigParser.getExecutionDetails();
        Assert.assertEquals("/usr/local/bin/appium",executionDetails.getAppium_js_path());
        Assert.assertEquals("/usr/local/bin/node",executionDetails.getAppium_node_path());
    }
}
