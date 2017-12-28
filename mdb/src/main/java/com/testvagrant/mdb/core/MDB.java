package com.testvagrant.mdb.core;


import com.testvagrant.commons.entities.DeviceDetails;

import java.util.List;
import java.util.function.Predicate;

public interface MDB {

    /**
     * Gets all connected devices
     * @return list of all devices by udid
     */
    List<DeviceDetails> getDevices();


    /**
     * Gets all connected devices based on device filter applied
     * @return list of all devices by udid
     */
    List<DeviceDetails> getDevices(Predicate<DeviceDetails> deviceFilter);

    /**
     * Gets all connected devices based on device filter applied
     * @return list of all devices by udid
     */
    List<DeviceDetails> getDevices(Predicate<DeviceDetails> deviceFilter, Predicate<DeviceDetails> deviceFilter1);

    /**
     * Gets all connected devices based on device filter applied
     * @return list of all devices by udid
     */
    List<DeviceDetails> getDevices(Predicate<DeviceDetails> deviceFilter, Predicate<DeviceDetails> deviceFilter2, Predicate<DeviceDetails> deviceFilter3);
}
