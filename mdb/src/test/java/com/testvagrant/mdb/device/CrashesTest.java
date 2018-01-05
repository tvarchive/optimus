package com.testvagrant.mdb.device;

import com.testvagrant.commons.entities.SmartBOT;
import com.testvagrant.commons.entities.performance.Exceptions;
import com.testvagrant.mdb.android.DumpsysParser;
import org.junit.Test;

import java.util.Optional;

public class CrashesTest {

    @Test
    public void captureCrash() {
        SmartBOT smartBOT = new SmartBOT();
        smartBOT.setDeviceUdid("4200935feed2a28f");
        smartBOT.setAppPackageName("com.gojek.app.staging");
        DumpsysParser dumpsysParser = new DumpsysParser(smartBOT);
        Optional<Exceptions> exception = dumpsysParser.getException();
       exception.ifPresent(exceptions -> {
           System.out.println(exceptions.getActivityName());
           System.out.println(exceptions.getStacktrace());
       });

    }


}
