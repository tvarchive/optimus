import com.testvagrant.commons.entities.device.OSVersion;
import com.testvagrant.mdb.android.ADB;
import com.testvagrant.mdb.android.Android;
import com.testvagrant.mdb.enums.AOSVersion;

public class AndroidDevicesTest {


    @Deprecated
    public void getAndroidDevices() {
        ADB adb = new Android();
        OSVersion osVersion = new OSVersion();
        osVersion.setName(AOSVersion.LOLLIPOP.getName());
        osVersion.setVersion(AOSVersion.LOLLIPOP.getVersion());
        osVersion.setBaseVersion(AOSVersion.LOLLIPOP.getBaseVersion());
//        List<DeviceDetails> devices = adb.getDevices(OSFilter.eq(osVersion));
//        Assert.assertEquals(true,devices.size()>0);
    }
}
