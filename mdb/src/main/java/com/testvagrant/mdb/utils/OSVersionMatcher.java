package com.testvagrant.mdb.utils;

import com.testvagrant.commons.entities.device.OSVersion;
import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.mdb.Exceptions.ConnectedDevicesException;
import com.testvagrant.mdb.enums.AOSVersion;
import com.testvagrant.mdb.enums.IOSVersion;
import com.testvagrant.mdb.enums.Version;

import java.util.Arrays;
import java.util.Optional;


public class OSVersionMatcher {

    public OSVersion getOSVersion(Platform platform, String versionNumber) {
        switch (platform) {
            case ANDROID:
                Optional<AOSVersion> aosVersion = Arrays.stream(AOSVersion.values()).filter(AOSVersion -> {
                    try {
                        AOSVersion.setVersion(versionNumber);
                        return AOSVersion.getVersion().equals(versionNumber);
                    } catch (Exception e) {
                    }
                    return false;
                }).findFirst();

                if(aosVersion.isPresent()) {
                    return getOSVer(aosVersion.get());
                } else
                    throw new ConnectedDevicesException(String.format("Cannot read the Android OS version %s , is the device valid??",versionNumber));
            case IOS:
                Optional<IOSVersion> iosVersion = Arrays.stream(IOSVersion.values()).filter(OSVersion ->{
                    OSVersion.setVersion(versionNumber);
                    return OSVersion.getVersion().equals(versionNumber);}).findFirst();
                if(iosVersion.isPresent()) {
                    return getOSVer(iosVersion.get());
                } else {
                    throw new ConnectedDevicesException(String.format("Cannot read the IOS OS version %s, is the device valid??",versionNumber));
                }
        }
        throw new RuntimeException("Failed to get OS Version");
    }



    private OSVersion getOSVer(Version version) {
        OSVersion osVersion = new OSVersion();
        osVersion.setName(version.getName());
        osVersion.setBaseVersion(version.getBaseVersion());
        osVersion.setVersion(version.getVersion());
        return osVersion;
    }
}
