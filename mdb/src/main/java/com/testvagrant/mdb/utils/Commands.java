package com.testvagrant.mdb.utils;

public interface Commands {

    interface AndroidCommands {
        String ADB_HEADER = "List of devices attached";
        String LIST_ALL_DEVICES = "adb devices -l";
        String GET_DEVICE_MODEL = "adb -s %s shell getprop ro.product.model";
        String GET_DEVICE_OS = "adb -s %s shell getprop ro.build.version.release";
        String GET_MEMINFO = "adb -s %s shell dumpsys meminfo %s";
        String GET_CPUINFO = "adb -s %s shell dumpsys cpuinfo %s";
        String GET_FOCUSSED_ACTIVITY = "adb -s %s shell dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'";
        String CPU_REGEX = "([0-9]+(\\.[0-9]+)?%)\\s+(user)\\s+(\\+)\\s+([0-9]+(\\.[0-9]+)?%)\\s+(kernel)(.*)";
        String GET_ERRORS = "adb -s %s logcat -d -v process *:E";
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
        String VIDEOS_PATH = USER_DIR + "/build/videos/";
    }

    interface DumpSys {
        String FOCUSSED_ACTIVITY = "adb -s %s shell dumpsys window windows | grep -E 'mCurrentFocus|mFocusedApp'";
        String CPUINFO = "adb -s %s shell dumpsys cpuinfo %s";
        String MEMINFO = "adb -s %s shell dumpsys meminfo %s";


    }
}
