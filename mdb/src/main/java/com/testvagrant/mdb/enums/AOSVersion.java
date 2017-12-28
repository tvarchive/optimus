package com.testvagrant.mdb.enums;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum  AOSVersion implements Version {

    DONUT("Donut",1.6,"1.6"),
    ECLAIR("Eclair",2.0,"(2.0|2.0.1|2.1)"),
    FROYO("Froyo",2.2,"(2.2|2.2.1|2.2.2|2.2.3)"),
    GINGERBREAD("Gingerbread",2.3,"(2.3|2.3.1|2.3.2|2.3.3|2.3.4|2.3.5|2.3.6|2.3.7)"),
    HONEYCOMB("Honeycomb",3.0,"(3.0|3.1|3.2|3.2.1|3.2.2|3.2.3|3.2.4|3.2.5|3.2.6)"),
    ICE_CREAM_SANDWICH("Ice Cream Sandwich",4.0,"(4.0|4.0.1|4.0.2|4.0.3|4.0.4)"),
    JELLY_BEAN("Jelly Bean",4.1,"(4.1|4.1.1|4.1.2|4.2|4.2.1|4.2.2|4.3|4.3.1)"),
    KITKAT("Kitkat",4.4,"(4.4|4.4.1|4.4.2|4.4.3|4.4.4)"),
    LOLLIPOP("Lollipop",5.0,"(5.0|5.0.0|5.0.1|5.0.2|5.1|5.1.1)"),
    MARSHMALLOW("Marshmallow",6.0,"(6.0|6.0.1)"),
    NOUGAT("Nougat",7.0,"(7.0|7.1|7.1.1|7.1.2)"),
    OREO("Oreo",8.0,"(8.0|8.0.0|8.0.1)");

    private String versionRegex;
    private String version;
    private double baseVersion;
    private String name;
    AOSVersion(String name, double baseVersion, String versionRegex) {
        this.versionRegex = versionRegex;
        this.baseVersion = baseVersion;
        this.name = name;
    }

    public void setVersion(String version) {
        Pattern pattern = Pattern.compile(versionRegex);
        Matcher matcher = pattern.matcher(version);
        if(matcher.find()) {
            if(versionRegex.contains(version)) {
                this.version = version;
            } else {
                this.version = "0.0";
            }
        } else {
            this.version = "0.0";
        }
    }
    public String getVersion() {
        return version;
    }

    public double getBaseVersion() {
        return baseVersion;
    }

    public String getName() {
        return name;
    }
}
