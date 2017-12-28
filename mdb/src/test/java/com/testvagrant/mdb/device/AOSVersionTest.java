package com.testvagrant.mdb.device;

import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.mdb.enums.AOSVersion;
import com.testvagrant.mdb.utils.OSVersionMatcher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class AOSVersionTest {

    private String v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,
    v16,v17,v18,v19,v20,v21,v22;

    private Platform platform = Platform.ANDROID;
    @BeforeMethod
    public void setup() {
        v1 = "1.6";v2 = "2.0";v3 = "2.1";v4 = "2.2";v5 = "2.2.3";v6 = "2.3";v7 = "2.3.1";
        v8 = "2.3.2";v9 = "3.0";v10 = "3.2.1";v11 = "4.0";v12 = "4.0.4";v13 = "4.1";v14 = "4.3.1";
        v15 = "4.4";v16 = "4.4.4";v17 = "5.0";v18 = "5.1.1";v19 = "6.0";v20 = "6.0.1";v21 = "7.0";v22 = "7.1.2";
    }

    @Test
    public void verifyAllTheAOSVersions() {
        assertEquals(AOSVersion.DONUT, getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v1).getName()));
        assertEquals(AOSVersion.ECLAIR, getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v2).getName()));
        assertEquals(AOSVersion.ECLAIR, getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v3).getName()));
        assertEquals(AOSVersion.FROYO, getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v4).getName()));
        assertEquals(AOSVersion.FROYO,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v5).getName()));
        assertEquals(AOSVersion.GINGERBREAD,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v6).getName()));
        assertEquals(AOSVersion.GINGERBREAD,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v7).getName()));
        assertEquals(AOSVersion.GINGERBREAD,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v8).getName()));
        assertEquals(AOSVersion.HONEYCOMB,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v9).getName()));
        assertEquals(AOSVersion.HONEYCOMB,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v10).getName()));
        assertEquals(AOSVersion.ICE_CREAM_SANDWICH,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v11).getName()));
        assertEquals(AOSVersion.ICE_CREAM_SANDWICH,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v12).getName()));
        assertEquals(AOSVersion.JELLY_BEAN,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v13).getName()));
        assertEquals(AOSVersion.JELLY_BEAN,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v14).getName()));
        assertEquals(AOSVersion.KITKAT,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v15).getName()));
        assertEquals(AOSVersion.KITKAT,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v16).getName()));
        assertEquals(AOSVersion.LOLLIPOP,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v17).getName()));
        assertEquals(AOSVersion.LOLLIPOP,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v18).getName()));
        assertEquals(AOSVersion.MARSHMALLOW,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v19).getName()));
        assertEquals(AOSVersion.MARSHMALLOW,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v20).getName()));
        assertEquals(AOSVersion.NOUGAT,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v21).getName()));
        assertEquals(AOSVersion.NOUGAT,getAOSVersion(new OSVersionMatcher().getOSVersion(platform,v22).getName()));
    }


    private AOSVersion getAOSVersion(String name) {
        return AOSVersion.valueOf(name.toUpperCase().replace(" ","_"));
    }
}
