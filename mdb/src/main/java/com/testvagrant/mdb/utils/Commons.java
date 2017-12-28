package com.testvagrant.mdb.utils;


import org.apache.commons.lang3.math.NumberUtils;

import java.text.DecimalFormat;

public class Commons {

    public static Double convertToMB(String dataInKB) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        try {
            Integer data = NumberUtils.toInt(dataInKB);
            double dataInDouble = data/1024.0;
            return dataInDouble;
        } catch (Exception  e) {

        }
        throw new RuntimeException("Unable to convert "+dataInKB+" to MB");
    }
}
