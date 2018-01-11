/*
 * Copyright (c) 2017.  TestVagrant Technologies
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.testvagrant.optimus.updater;


import com.testvagrant.commons.entities.DeviceDetails;
import com.testvagrant.commons.entities.device.DeviceType;
import com.testvagrant.commons.entities.device.Platform;
import com.testvagrant.commons.entities.device.Status;
import com.testvagrant.commons.exceptions.DeviceMatchingException;
import com.testvagrant.commons.exceptions.NoSuchDeviceTypeException;
import com.testvagrant.commons.exceptions.NoSuchPlatformException;
import com.testvagrant.commons.exceptions.OptimusException;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class DeviceMiner {

    private JSONObject testFeed;
    private List<DeviceDetails> deviceDetails;
    private JSONObject optimusDesiredCapabilities;
    private JSONObject appiumServerCapabilities;
    private String runsOn;
    private String udid;
    private String deviceName;
    private String platformVersion;

    public DeviceMiner(List<DeviceDetails> deviceDetails, JSONObject testFeed) {
        this.testFeed = testFeed;
        this.deviceDetails = deviceDetails;

        optimusDesiredCapabilities = testFeed.getJSONObject("optimusDesiredCapabilities");
        appiumServerCapabilities = optimusDesiredCapabilities.getJSONObject("appiumServerCapabilities");
    }

    public DeviceDetails getAvailableDevice() throws OptimusException {
        Set<DeviceDetails> commonElements = getAllDevicesThatMatchTheCriteria();

        if (commonElements.size() == 0)
            throw new RuntimeException("No devices found for your criteria");

        return commonElements.stream()
                .filter(d -> d.getStatus().equals(Status.Available))
                .findFirst().get();
    }

    public Set<DeviceDetails> getAllDevicesThatMatchTheCriteria() throws OptimusException {
        Map<String, Object> capabilitiesMap = toMap(appiumServerCapabilities);

        Platform platform = getPlatform(capabilitiesMap);

        // get all devices based on platform - Android OR iOS

        ArrayList<DeviceDetails> devicesBasedOnPlatform = getDevicesBasedOnPlatform(platform);
        if (devicesBasedOnPlatform.size() == 0) {
            throw new DeviceMatchingException(String.format("No devices found matching platform, %s. Check if you have devices connected for %s platform.", platform.getName(), platform.getName()));
        }

        //whether physical device or simulator or any
        ArrayList<DeviceDetails> devicesBasedOnRunsOn = getDevicesRunningOn(testFeed, devicesBasedOnPlatform);

        if (devicesBasedOnRunsOn.size() == 0) {
            if (runsOn.equals("any")) {
                throw new DeviceMatchingException("No devices, emulators or simulators found running on either android or IOS. Check if your devices are connected with debug mode on.");
            } else {
                throw new DeviceMatchingException(String.format("No devices found running on %s, check if you have %ss connected", runsOn, runsOn));
            }

        }

        ArrayList<DeviceDetails> devicesBasedOnDeviceName = getDevicesForDeviceName(capabilitiesMap, devicesBasedOnPlatform);
        if (devicesBasedOnDeviceName.size() == 0) {
            throw new DeviceMatchingException(String.format("No devices found matching device name - '%s'", deviceName));
        }

        ArrayList<DeviceDetails> devicesBasedOnVersion = getDevicesForPlatformVersion(capabilitiesMap, devicesBasedOnPlatform);
        if (devicesBasedOnVersion.size() == 0) {
            throw new DeviceMatchingException(String.format("No devices found with platform version %s", platformVersion));
        }
        ArrayList<DeviceDetails> devicesBasedOnUdid = getDeviceWithUdid(capabilitiesMap, devicesBasedOnPlatform);
        if (devicesBasedOnUdid.size() == 0) {
            throw new DeviceMatchingException(String.format("No devices found with udid %s", udid));
        }

        List<List<DeviceDetails>> combinedList = new ArrayList<>();
        combinedList.add(devicesBasedOnPlatform);
        combinedList.add(devicesBasedOnRunsOn);
        combinedList.add(devicesBasedOnDeviceName);
        combinedList.add(devicesBasedOnVersion);
        combinedList.add(devicesBasedOnUdid);

        return getCommonElements(combinedList);
    }

    private ArrayList<DeviceDetails> getDeviceWithUdid(Map<String, Object> capabilitiesMap, ArrayList<DeviceDetails> devicesBasedOnPlatform) {
        if (capabilitiesMap.get("udid") == null)
            return devicesBasedOnPlatform;
        udid = (String) capabilitiesMap.get("udid");
        return deviceDetails.stream()
                .filter(d -> d.getUdid().equalsIgnoreCase(udid))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<DeviceDetails> getDevicesRunningOn(JSONObject testFeed, ArrayList<DeviceDetails> devicesBasedOnPlatform) throws NoSuchDeviceTypeException {
        Map<String, Object> testFeedMap = toMap(testFeed);
        testFeedMap.putIfAbsent("runsOn", "any");
        DeviceType deviceType = null;
        runsOn = ((String) testFeedMap.get("runsOn")).toUpperCase();
        try {
            if (!runsOn.equalsIgnoreCase("any"))
                deviceType = DeviceType.valueOf(runsOn);
        } catch (Exception e) {
            throw new NoSuchDeviceTypeException(runsOn);
        }
        return testFeedMap.get("runsOn").equals("any") ? devicesBasedOnPlatform : filterDevicesRunningOn(deviceType);
    }

    private ArrayList<DeviceDetails> filterDevicesRunningOn(DeviceType runsOn) {
        return deviceDetails.stream()
                .filter(d -> d.getRunsOn().equals(runsOn))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static <T> Set<T> getCommonElements(Collection<? extends Collection<T>> collections) {

        Set<T> common = new LinkedHashSet<T>();
        if (!collections.isEmpty()) {
            Iterator<? extends Collection<T>> iterator = collections.iterator();
            common.addAll(iterator.next());
            while (iterator.hasNext()) {
                common.retainAll(iterator.next());
            }
        }
        return common;
    }

    private ArrayList<DeviceDetails> getDevicesForDeviceName(Map<String, Object> capabilitiesMap, ArrayList<DeviceDetails> devicesBasedOnPlatform) {
        deviceName = (String) capabilitiesMap.get("deviceName");
        if (deviceName == null)
            return devicesBasedOnPlatform;
        return getDevicesForDeviceName(deviceName);
    }

    private ArrayList<DeviceDetails> getDevicesForPlatformVersion(Map<String, Object> capabilitiesMap, ArrayList<DeviceDetails> devicesBasedOnPlatform) {
        platformVersion = (String) capabilitiesMap.get("platformVersion");
        if (platformVersion == null)
            return devicesBasedOnPlatform;
        return getDevicesForPlatformVersion(platformVersion);
    }

    private ArrayList<DeviceDetails> getDevicesForPlatformVersion(String platformVersion) {
        return deviceDetails.stream()
                .filter(d -> d.getPlatformVersion().equals(platformVersion))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private ArrayList<DeviceDetails> getDevicesForDeviceName(String deviceName) {
        return deviceDetails.stream()
                .filter(d -> d.getDeviceName().equalsIgnoreCase(deviceName))
                .collect(Collectors.toCollection(ArrayList::new));

    }

    private ArrayList<DeviceDetails> getDevicesBasedOnPlatform(Platform platform) {
        return deviceDetails.stream()
                .filter(d -> d.getPlatform().equals(platform))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Map<String, Object> toMap(JSONObject appiumServerCapabilities) {
        Iterator<String> keysIterator = appiumServerCapabilities.keys();
        Map<String, Object> capabilitiesMap = new HashMap<>();
        while (keysIterator.hasNext()) {
            String key = keysIterator.next();
            Object value = appiumServerCapabilities.get(key);

            capabilitiesMap.put(key, value);
        }
        return capabilitiesMap;
    }


    private Platform getPlatform(Map<String, Object> testFeed) throws NoSuchPlatformException {
        try {
            return Platform.valueOf(((String) testFeed.get("platformName")).toUpperCase());
        } catch (Exception e) {
            throw new NoSuchPlatformException((String) testFeed.get("platformName"));
        }
    }

}
