package optimusio;

import com.testvagrant.monitor.clients.BuildsClient;
import com.testvagrant.monitor.requests.Build;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class BuildsClientTest {

    @Test
    public void createAndFindBuildDocument() {
        Build newBuild = new BuildsClient().createNewBuild();
        Assert.assertEquals(true,newBuild.getBuildStartTime().getTime()<new Date().getTime());
        Assert.assertEquals(0,newBuild.getScenarioCount());
    }


    @Test
    public void getLatestBuildId() {
        Build newBuild = new BuildsClient().createNewBuild();
        String latestBuildId = new BuildsClient().getLatestBuildId();
        Assert.assertEquals(newBuild.getId(), latestBuildId);
    }

    @Test
    public void updateBuildDocument() {
        Build newBuild = new BuildsClient().createNewBuild();
        Build buildUpdate = new Build();
        buildUpdate.setScenarioCount(20);
        buildUpdate.setBuildEndTime(new Date());
        Build updatedBuild = new BuildsClient().updateBuildRecord(buildUpdate);
        Assert.assertEquals(buildUpdate.getScenarioCount(),updatedBuild.getScenarioCount());
        Assert.assertEquals(updatedBuild.getBuildStartTime(),updatedBuild.getBuildStartTime());
        Assert.assertEquals(updatedBuild.getBuildEndTime(),updatedBuild.getBuildEndTime());
        Assert.assertEquals(buildUpdate.getBuildEndTime(),updatedBuild.getBuildEndTime());
        Assert.assertEquals(updatedBuild.getScenarioSuccessRate(),updatedBuild.getScenarioSuccessRate());
    }
}

