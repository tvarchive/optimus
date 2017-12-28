package com.testvagrant.mdb.device;

import java.util.ArrayList;
import java.util.List;

public class DeviceDetailsBase {


    protected List<String> androidProcessLog() {
        List<String> processLog = new ArrayList<>();
        processLog.add("192.168.56.101:5555    device product:vbox86p model:Google_Nexus_5___5_0_0___API_21 device:vbox86p");
        processLog.add("emulator-5554          device product:sdk_google_phone_x86_64 model:Android_SDK_built_for_x86_64 device:generic_x86_64");
        processLog.add("ZY223D7XPB             device usb:336592896X product:athene_f model:Moto_G__4_ device:athene_f");
        return processLog;
    }

    protected List<String> latestAndroidOSProcessLog() {
        List<String> processLog = new ArrayList<>();
        processLog.add("ZY223D7GPB             device usb:336592896X product:athene_f model:Moto_G__5_ device:athene_f");
        return processLog;
    }


    protected List<String> iOSProcessLog() {
        List<String> iosProcessLog = new ArrayList<>();
        iosProcessLog.add("junk Data null");
        iosProcessLog.add("iPhone 5 (9.3) [682DCDE8-E994-4098-98C5-A411A3D20ECA] (Simulator)");
        iosProcessLog.add("iPhone 5s (9.3) [7B906BD5-6331-48ED-B593-5D27F46FCF90] (Simulator)");
        iosProcessLog.add("iPhone 6 (9.3) [1BBD1612-8E25-4BC8-9A9F-DD711417FEA6] (Simulator)");
        iosProcessLog.add("iPhone 6s (9.3) [1C66C35B-021D-480C-8810-9D584DAB043C] (Simulator)");
        iosProcessLog.add("TFF (10.0.2) [181e41f71699aa7a374218a4d29bad7ee9b333b5]");
        return iosProcessLog;
    }
}
