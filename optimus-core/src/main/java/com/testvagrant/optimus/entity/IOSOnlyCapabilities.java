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

public class IOSOnlyCapabilities {

    private String waitForAppScript;

    private int interKeyDelay;

    private String safariInitialUrl;

    private String localizableStringsDir;

    private boolean keepKeyChains;

    private String calendarFormat;

    private int screenshotWaitTimeout;

    private boolean nativeWebTap;

    private int launchTimeout;

    private boolean autoDismissAlerts;

    private boolean safariOpenLinksInBackground;

    private String sendKeyStrategy;

    private boolean safariAllowPopups;

    private String udid;

    private boolean locationServicesAuthorized;

    private String appName;

    private boolean safariIgnoreFraudWarning;

    private boolean autoAcceptAlerts;

    private boolean showIOSLog;

    private String processArguments;

    private boolean nativeInstrumentsLib;

    private String bundleId;

    private int webviewConnectRetries;

    private boolean locationServicesEnabled;

    public String getWaitForAppScript() {
        return waitForAppScript;
    }

    public void setWaitForAppScript(String waitForAppScript) {
        this.waitForAppScript = waitForAppScript;
    }

    public int getInterKeyDelay() {
        return interKeyDelay;
    }

    public void setInterKeyDelay(int interKeyDelay) {
        this.interKeyDelay = interKeyDelay;
    }

    public String getSafariInitialUrl() {
        return safariInitialUrl;
    }

    public void setSafariInitialUrl(String safariInitialUrl) {
        this.safariInitialUrl = safariInitialUrl;
    }

    public String getLocalizableStringsDir() {
        return localizableStringsDir;
    }

    public void setLocalizableStringsDir(String localizableStringsDir) {
        this.localizableStringsDir = localizableStringsDir;
    }

    public boolean isKeepKeyChains() {
        return keepKeyChains;
    }

    public void setKeepKeyChains(boolean keepKeyChains) {
        this.keepKeyChains = keepKeyChains;
    }

    public String getCalendarFormat() {
        return calendarFormat;
    }

    public void setCalendarFormat(String calendarFormat) {
        this.calendarFormat = calendarFormat;
    }

    public int getScreenshotWaitTimeout() {
        return screenshotWaitTimeout;
    }

    public void setScreenshotWaitTimeout(int screenshotWaitTimeout) {
        this.screenshotWaitTimeout = screenshotWaitTimeout;
    }

    public boolean isNativeWebTap() {
        return nativeWebTap;
    }

    public void setNativeWebTap(boolean nativeWebTap) {
        this.nativeWebTap = nativeWebTap;
    }

    public int getLaunchTimeout() {
        return launchTimeout;
    }

    public void setLaunchTimeout(int launchTimeout) {
        this.launchTimeout = launchTimeout;
    }

    public boolean isAutoDismissAlerts() {
        return autoDismissAlerts;
    }

    public void setAutoDismissAlerts(boolean autoDismissAlerts) {
        this.autoDismissAlerts = autoDismissAlerts;
    }

    public boolean isSafariOpenLinksInBackground() {
        return safariOpenLinksInBackground;
    }

    public void setSafariOpenLinksInBackground(boolean safariOpenLinksInBackground) {
        this.safariOpenLinksInBackground = safariOpenLinksInBackground;
    }

    public String getSendKeyStrategy() {
        return sendKeyStrategy;
    }

    public void setSendKeyStrategy(String sendKeyStrategy) {
        this.sendKeyStrategy = sendKeyStrategy;
    }

    public boolean isSafariAllowPopups() {
        return safariAllowPopups;
    }

    public void setSafariAllowPopups(boolean safariAllowPopups) {
        this.safariAllowPopups = safariAllowPopups;
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    public boolean isLocationServicesAuthorized() {
        return locationServicesAuthorized;
    }

    public void setLocationServicesAuthorized(boolean locationServicesAuthorized) {
        this.locationServicesAuthorized = locationServicesAuthorized;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public boolean isSafariIgnoreFraudWarning() {
        return safariIgnoreFraudWarning;
    }

    public void setSafariIgnoreFraudWarning(boolean safariIgnoreFraudWarning) {
        this.safariIgnoreFraudWarning = safariIgnoreFraudWarning;
    }

    public boolean isAutoAcceptAlerts() {
        return autoAcceptAlerts;
    }

    public void setAutoAcceptAlerts(boolean autoAcceptAlerts) {
        this.autoAcceptAlerts = autoAcceptAlerts;
    }

    public boolean isShowIOSLog() {
        return showIOSLog;
    }

    public void setShowIOSLog(boolean showIOSLog) {
        this.showIOSLog = showIOSLog;
    }

    public String getProcessArguments() {
        return processArguments;
    }

    public void setProcessArguments(String processArguments) {
        this.processArguments = processArguments;
    }

    public boolean isNativeInstrumentsLib() {
        return nativeInstrumentsLib;
    }

    public void setNativeInstrumentsLib(boolean nativeInstrumentsLib) {
        this.nativeInstrumentsLib = nativeInstrumentsLib;
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public int getWebviewConnectRetries() {
        return webviewConnectRetries;
    }

    public void setWebviewConnectRetries(int webviewConnectRetries) {
        this.webviewConnectRetries = webviewConnectRetries;
    }

    public boolean isLocationServicesEnabled() {
        return locationServicesEnabled;
    }

    public void setLocationServicesEnabled(boolean locationServicesEnabled) {
        this.locationServicesEnabled = locationServicesEnabled;
    }
}
