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

package com.testvagrant.optimus.entity;

public class AndroidOnlyCapabilities {

    private String avd;

    private boolean nativeWebScreenshot;

    private String appActivity;

    private String keystorePassword;

    private boolean noSign;

    private String chromedriverExecutable;

    private String appPackage;

    private boolean disableAndroidWatchers;

    private boolean dontStopAppOnReset;

    private int androidInstallTimeout;

    private String androidDeviceSocket;

    private String avdArgs;

    private boolean resetKeyboard;

    private boolean enablePerformanceLogging;

    private int deviceReadyTimeout;

    private int appWaitDuration;

    private String keystorePath;

    private String androidCoverage;

    private boolean recreateChromeDriverSessions;

    private String intentFlags;

    private boolean useKeystore;

    private String chromeOptions;

    private String avdReadyTimeout;

    private String optionalIntentArguments;

    private String intentCategory;

    private int autoWebviewTimeout;

    private String appWaitPackage;

    private String androidScreenshotPath;

    private String intentAction;

    private boolean ignoreUnimportantViews;

    private String appWaitActivity;

    private String keyAlias;

    private boolean unicodeKeyboard;

    private int androidDeviceReadyTimeout;

    private String keyPassword;

    private int adbPort;

    private int avdLaunchTimeout;

    private boolean autoGrantPermissions;

    public String getAvd() {
        return avd;
    }

    public void setAvd(String avd) {
        this.avd = avd;
    }

    public boolean isNativeWebScreenshot() {
        return nativeWebScreenshot;
    }

    public void setNativeWebScreenshot(boolean nativeWebScreenshot) {
        this.nativeWebScreenshot = nativeWebScreenshot;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    public String getKeystorePassword() {
        return keystorePassword;
    }

    public void setKeystorePassword(String keystorePassword) {
        this.keystorePassword = keystorePassword;
    }

    public boolean isNoSign() {
        return noSign;
    }

    public void setNoSign(boolean noSign) {
        this.noSign = noSign;
    }

    public String getChromedriverExecutable() {
        return chromedriverExecutable;
    }

    public void setChromedriverExecutable(String chromedriverExecutable) {
        this.chromedriverExecutable = chromedriverExecutable;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public boolean isDisableAndroidWatchers() {
        return disableAndroidWatchers;
    }

    public void setDisableAndroidWatchers(boolean disableAndroidWatchers) {
        this.disableAndroidWatchers = disableAndroidWatchers;
    }

    public boolean isDontStopAppOnReset() {
        return dontStopAppOnReset;
    }

    public void setDontStopAppOnReset(boolean dontStopAppOnReset) {
        this.dontStopAppOnReset = dontStopAppOnReset;
    }

    public int getAndroidInstallTimeout() {
        return androidInstallTimeout;
    }

    public void setAndroidInstallTimeout(int androidInstallTimeout) {
        this.androidInstallTimeout = androidInstallTimeout;
    }

    public String getAndroidDeviceSocket() {
        return androidDeviceSocket;
    }

    public void setAndroidDeviceSocket(String androidDeviceSocket) {
        this.androidDeviceSocket = androidDeviceSocket;
    }

    public String getAvdArgs() {
        return avdArgs;
    }

    public void setAvdArgs(String avdArgs) {
        this.avdArgs = avdArgs;
    }

    public boolean isResetKeyboard() {
        return resetKeyboard;
    }

    public void setResetKeyboard(boolean resetKeyboard) {
        this.resetKeyboard = resetKeyboard;
    }

    public boolean isEnablePerformanceLogging() {
        return enablePerformanceLogging;
    }

    public void setEnablePerformanceLogging(boolean enablePerformanceLogging) {
        this.enablePerformanceLogging = enablePerformanceLogging;
    }

    public int getDeviceReadyTimeout() {
        return deviceReadyTimeout;
    }

    public void setDeviceReadyTimeout(int deviceReadyTimeout) {
        this.deviceReadyTimeout = deviceReadyTimeout;
    }

    public int getAppWaitDuration() {
        return appWaitDuration;
    }

    public void setAppWaitDuration(int appWaitDuration) {
        this.appWaitDuration = appWaitDuration;
    }

    public String getKeystorePath() {
        return keystorePath;
    }

    public void setKeystorePath(String keystorePath) {
        this.keystorePath = keystorePath;
    }

    public String getAndroidCoverage() {
        return androidCoverage;
    }

    public void setAndroidCoverage(String androidCoverage) {
        this.androidCoverage = androidCoverage;
    }

    public boolean isRecreateChromeDriverSessions() {
        return recreateChromeDriverSessions;
    }

    public void setRecreateChromeDriverSessions(boolean recreateChromeDriverSessions) {
        this.recreateChromeDriverSessions = recreateChromeDriverSessions;
    }

    public String getIntentFlags() {
        return intentFlags;
    }

    public void setIntentFlags(String intentFlags) {
        this.intentFlags = intentFlags;
    }

    public boolean isUseKeystore() {
        return useKeystore;
    }

    public void setUseKeystore(boolean useKeystore) {
        this.useKeystore = useKeystore;
    }

    public String getChromeOptions() {
        return chromeOptions;
    }

    public void setChromeOptions(String chromeOptions) {
        this.chromeOptions = chromeOptions;
    }

    public String getAvdReadyTimeout() {
        return avdReadyTimeout;
    }

    public void setAvdReadyTimeout(String avdReadyTimeout) {
        this.avdReadyTimeout = avdReadyTimeout;
    }

    public String getOptionalIntentArguments() {
        return optionalIntentArguments;
    }

    public void setOptionalIntentArguments(String optionalIntentArguments) {
        this.optionalIntentArguments = optionalIntentArguments;
    }

    public String getIntentCategory() {
        return intentCategory;
    }

    public void setIntentCategory(String intentCategory) {
        this.intentCategory = intentCategory;
    }

    public int getAutoWebviewTimeout() {
        return autoWebviewTimeout;
    }

    public void setAutoWebviewTimeout(int autoWebviewTimeout) {
        this.autoWebviewTimeout = autoWebviewTimeout;
    }

    public String getAppWaitPackage() {
        return appWaitPackage;
    }

    public void setAppWaitPackage(String appWaitPackage) {
        this.appWaitPackage = appWaitPackage;
    }

    public String getAndroidScreenshotPath() {
        return androidScreenshotPath;
    }

    public void setAndroidScreenshotPath(String androidScreenshotPath) {
        this.androidScreenshotPath = androidScreenshotPath;
    }

    public String getIntentAction() {
        return intentAction;
    }

    public void setIntentAction(String intentAction) {
        this.intentAction = intentAction;
    }

    public boolean isIgnoreUnimportantViews() {
        return ignoreUnimportantViews;
    }

    public void setIgnoreUnimportantViews(boolean ignoreUnimportantViews) {
        this.ignoreUnimportantViews = ignoreUnimportantViews;
    }

    public String getAppWaitActivity() {
        return appWaitActivity;
    }

    public void setAppWaitActivity(String appWaitActivity) {
        this.appWaitActivity = appWaitActivity;
    }

    public String getKeyAlias() {
        return keyAlias;
    }

    public void setKeyAlias(String keyAlias) {
        this.keyAlias = keyAlias;
    }

    public boolean isUnicodeKeyboard() {
        return unicodeKeyboard;
    }

    public void setUnicodeKeyboard(boolean unicodeKeyboard) {
        this.unicodeKeyboard = unicodeKeyboard;
    }

    public int getAndroidDeviceReadyTimeout() {
        return androidDeviceReadyTimeout;
    }

    public void setAndroidDeviceReadyTimeout(int androidDeviceReadyTimeout) {
        this.androidDeviceReadyTimeout = androidDeviceReadyTimeout;
    }

    public String getKeyPassword() {
        return keyPassword;
    }

    public void setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
    }

    public int getAdbPort() {
        return adbPort;
    }

    public void setAdbPort(int adbPort) {
        this.adbPort = adbPort;
    }

    public int getAvdLaunchTimeout() {
        return avdLaunchTimeout;
    }

    public void setAvdLaunchTimeout(int avdLaunchTimeout) {
        this.avdLaunchTimeout = avdLaunchTimeout;
    }


    public boolean isAutoGrantPermissions() {
        return autoGrantPermissions;
    }

    public void setAutoGrantPermissions(boolean autoGrantPermissions) {
        this.autoGrantPermissions = autoGrantPermissions;
    }
}
