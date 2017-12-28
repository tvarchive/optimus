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

package com.testvagrant.optimus.utils;

public interface Commands {

    interface ADB {
        String ADB_HEADER = "List of devices attached";
        String LIST_ALL_DEVICES = "adb devices -l";
        String GET_DEVICE_MODEL = "adb -s %s shell getprop ro.product.model";
        String GET_DEVICE_OS = "adb -s %s shell getprop ro.build.version.release";
    }

    interface Instruments {
        String INSTRUMENTS_HEADER = "Known Devices:";
        String LIST_ALL_DEVICES = "instruments -s devices | grep 'iPhone'";
        String SIMULATOR_UDID_PATTERN = "[a-zA-Z0-9-]{36}";
        String DEVICE_UDID_PATTERN = "[a-zA-Z0-9]{40}";
        String LAPTOP_NAME = "MacBook";
        String XCODE_INSTALLATION = "xcode-select -p";
        String XCODE_INSTALLATION_DETAILS = "gcc --version";
        String XCODE_VERSION = "([A-z])\\w+ ([A-Z])\\w+ ([a-z])\\w+ ([0-9])(.)([0-9])(.)([0-9]) (.*)";
        String XCODE_VERSION_REGEX = "([0-9])(.)([0-9])(.)([0-9])";
    }

    interface FBSimctl {
        String RECORDVIDEO_XCODE8 = "fbsimrecord";
        String FILE_PATH_FLAG = "Started Recording video at path";
        String FBSIMCTL_GREP = "ps -e";
        String FBSIMRECORD_PROCESS_FLAG = "fbsimctl";
    }

    interface Paths {
        String USER_DIR = System.getProperty("user.dir");
        String VIDEOS_PATH = USER_DIR+"/build/videos/";
    }
}
