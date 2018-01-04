package com.testvagrant.mdb.device;

import com.testvagrant.commons.entities.SmartBOT;
import com.testvagrant.commons.entities.performance.Exceptions;
import com.testvagrant.mdb.android.DumpsysParser;
import org.junit.Test;

public class CrashesTest {

    @Test
    public void captureCrash() {
        SmartBOT smartBOT = new SmartBOT();
        smartBOT.setDeviceUdid("ZY223D7XPB");
        smartBOT.setAppPackageName("com.gojek.app.staging");
        DumpsysParser dumpsysParser = new DumpsysParser(smartBOT);
        Exceptions exception = dumpsysParser.getException();
        System.out.println(exception.getActivityName());
        System.out.println(exception.getStacktrace());
//        System.out.println(exception.getStacktrace());

    }


}
