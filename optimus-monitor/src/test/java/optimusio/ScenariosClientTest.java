package optimusio;

import com.testvagrant.monitor.clients.BuildsClient;
import com.testvagrant.monitor.clients.ScenariosClient;
import com.testvagrant.monitor.requests.Build;
import com.testvagrant.monitor.requests.Scenario;
import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.testvagrant.monitor.constants.MongoDB.STATUS_PASSED;

public class ScenariosClientTest {

    @Test
    public void createNewScenario() {
        Build build = new BuildsClient().createNewBuild();
        Scenario scenario = new Scenario();
        scenario.setScenarioName("New Scenario");
        scenario.setDeviceUdid("afadgad");
        scenario.setBuildId(build.getId());
        String[] tags= {"tag1","tag2"};
        scenario.setTags(Arrays.asList(tags));
        scenario.setStartTime(new Date());
        Scenario newScenario = new ScenariosClient().createNewScenario(scenario, new ArrayList<>());
        Assert.assertEquals(scenario.getScenarioName(),newScenario.getScenarioName());
        Assert.assertEquals(scenario.getBuildId(),newScenario.getBuildId());
    }


    @Test
    public void updateScenarioAsComplete() {
        Scenario scenario = new Scenario();
        scenario.setScenarioName("Change-Customer-Email-on-MyAccount-page-4");
        scenario.setStatus("passed");
        scenario.setCompleted(true);
        Scenario response = new ScenariosClient().updateScenario("5a38cf12cebe50f49a989966", scenario);
        System.out.println(response);
    }

    @Test
    public void test() {
        String scenarioId = "Change-Customer-Email-on-MyAccount-page-4";
        String location = scenarioId.substring(scenarioId.lastIndexOf("-")+1);
        String scenarioName = scenarioId.substring(0,scenarioId.lastIndexOf("-")).replaceAll("-"," ");
        System.out.println(scenarioName);
    }

    @Test
    public void distinctScenarios() {
        List<Scenario> distinctScenarios = new ScenariosClient().getDistinctScenarios();
        int numberOfUniqueScenarios = distinctScenarios.size();
        int passedScenariosCount = distinctScenarios.stream().filter(scenario -> {
            boolean scenarioStatus = false;
            try {
                scenarioStatus = scenario.getStatus().equalsIgnoreCase(STATUS_PASSED);
            } catch (Exception e) {
            }
            return scenarioStatus;
        }).collect(Collectors.toList()).size();
        float pass_percentage = (passedScenariosCount*100.0f)/numberOfUniqueScenarios;
        DecimalFormat df = new DecimalFormat("#.0");
        String passRate = df.format(pass_percentage);
        Build buildById = new BuildsClient().findBuildById("5a4b780b52f86874ff098573");
        buildById.setScenariosCount(numberOfUniqueScenarios);
        buildById.setScenarioSuccessRate(passRate);
        new BuildsClient().updateBuildRecord(buildById);
    }
}
