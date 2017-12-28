package com.testvagrant.mdb.predicates.filters;


import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.entities.device.DeviceType;
import com.testvagrant.mdb.predicates.MobileFilters;

import java.util.function.Predicate;

public class DeviceTypeFilter extends MobileFilters {

    public static Predicate<DeviceDetails> ofTypeEmulators(){
        return getDeviceTypePredicate(DeviceType.EMULATOR);
    }

    public static Predicate<DeviceDetails> ofTypeDevices() {
        return getDeviceTypePredicate(DeviceType.DEVICE);
    }

    public static Predicate<DeviceDetails> ofTypeSimulators() {
        return getDeviceTypePredicate(DeviceType.SIMULATOR);
    }

    private static Predicate<DeviceDetails> getDeviceTypePredicate(DeviceType deviceType) {
        return deviceDetails -> deviceDetails.getRunsOn().equals(deviceType);
    }
}
