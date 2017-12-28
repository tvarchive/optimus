package com.testvagrant.mdb.predicates.filters;


import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.mdb.enums.Models;
import com.testvagrant.mdb.predicates.MobileFilters;

import java.util.function.Predicate;

public class ModelFilter extends MobileFilters {


    public static Predicate<DeviceDetails> ofModel(Models model) {
        return deviceDetails -> deviceDetails.getDeviceName().startsWith(model.getModelName());
    }
}
