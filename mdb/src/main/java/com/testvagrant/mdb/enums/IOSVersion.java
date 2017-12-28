package com.testvagrant.mdb.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum IOSVersion implements Version {

    BIG_BEAR("Big Bear",2.0,"2.0"),
    SUGARBOWL("Sugarbowl",2.1,"2.1"),
    TIMBERLINE("Timberline",2.2,"2.2"),
    KIRKWOOD("Kirkwood",3.0,"3.0"),
    NORTHSTAR("Northstar",3.1,"3.1"),
    WILDCAT("Wildcat",3.2,"3.2"),
    APEX("Apex",4.0,"4.0"),
    BAKER("Baker",4.1,"4.1"),
    JASPER("Jasper",4.2,"4.2"),
    DURANGO("Durango",4.3,"4.3"),
    TELLURIDE("Telluride",5.0,"5.0"),
    HOODOO("Hoodoo",5.1,"5.1"),
    SUNDANCE("Sundance",6.0,"6.0"),
    BRIGHTON("Brighton",6.1,"6.1"),
    INNSBRUCK("Innsbruck",7.0,"7.0"),
    SOCHI("Sochi",7.1,"7.1"),
    OKEMO("Okemo",8.0,"8.0"),
    OKEMO_TAOS("OkemoTaos",8.1,"8.1"),
    OKEMO_ZURS("OkemoZurs",8.2,"8.2"),
    STOWE("Stowe",8.3,"8.3"),
    COPPER("Copper",8.4,"8.4"),
    MONARCH("Monarch",9.0,"9.0"),
    BOULDER("Bourlder",9.1,"9.1"),
    CASTLEROCK("Castlerock",9.2,"9.2"),
    EAGLE("Eagle",9.3,"9.3"),
    WHITETAIL("Whitetail",10.0,"(10.0|10.0.2)"),
    BUTLER("Butler",10.1,"10.1"),
    CORRY("Corry",10.2,"10.2"),
    ERIE("Erie",10.3,"(10.3|10.3.1|10.3.2|10.3.3)"),
    TIGRIS("Tigris",11.0,"(11.0|11.0.1)");


    private String versionRegex;
    private String name;
    private String version;
    private double baseVersion;

    IOSVersion(String name, double baseVersion, String versionRegex) {
        this.name = name;
        this.baseVersion = baseVersion;
        this.versionRegex = versionRegex;
    }

    public void setVersion(String version) {
        Pattern pattern = Pattern.compile(versionRegex);
        Matcher matcher = pattern.matcher(version);
        if(matcher.find()) {
            if(versionRegex.contains(version)) {
                this.version = version;
            } else {
                this.version = version;
            }
        } else {
            this.version = "0.0";
        }
    }


    public double getBaseVersion() {
        return baseVersion;
    }
    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }
}
