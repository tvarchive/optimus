package optimusio;

import com.testvagrant.monitor.clients.ServiceClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ServicesClientTest {

    @Mock
    ServiceClient serviceClient = Mockito.mock(ServiceClient.class);

    @Before
    public void setup(){
        Mockito.when(serviceClient.isServiceRunning()).thenReturn(true);
    }

    @Test
    public void isServiceRunning() {
        boolean serviceRunning = serviceClient.isServiceRunning();
        Assert.assertEquals(serviceRunning,true);
    }
}
