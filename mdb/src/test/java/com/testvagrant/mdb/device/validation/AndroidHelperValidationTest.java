package com.testvagrant.mdb.device.validation;

import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.mdb.device.DeviceDetailsBase;
import com.testvagrant.mdb.helpers.AndroidHelper;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by krishnanand on 14/08/17.
 */
public class AndroidHelperValidationTest extends DeviceDetailsBase {

    @InjectMocks
    List<DeviceDetails> deviceDetailsList = new ArrayList<>();

    @Mock
    AndroidHelper androidHelper = Mockito.spy(new AndroidHelper(deviceDetailsList));

    @BeforeTest
    public void setup() {
        when(androidHelper.getModel("ZY223D7XPB")).thenReturn("Moto G(4)");
        when(androidHelper.getModel("ZY223D7GPB")).thenReturn("Moto G(5)");
        when(androidHelper.getOSVersion("ZY223D7XPB")).thenReturn("7.0");
        when(androidHelper.getOSVersion("ZY223D7GPB")).thenReturn("8.0");
        when(androidHelper.getModel("192.168.56.101:5555")).thenReturn("Google Nexus 5 - 5.0.0 - API 21");
        when(androidHelper.getOSVersion("192.168.56.101:5555")).thenReturn("5.0");
        when(androidHelper.getModel("emulator-5554")).thenReturn("Android_SDK_built_for_x86_64");
        when(androidHelper.getOSVersion("emulator-5554")).thenReturn("5.0.1");
    }


//    @Test(expectedExceptions = ConnectedDevicesException.class)
    public void whenAOSVersionIsFoundIsNotAvailableAsPartOfMDBItShouldThrowException() {
        androidHelper.initADevices(latestAndroidOSProcessLog());
    }
}
