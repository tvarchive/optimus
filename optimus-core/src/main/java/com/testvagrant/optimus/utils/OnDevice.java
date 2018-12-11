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

import com.testvagrant.commons.entities.SmartBOT;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.exec.OS;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static com.testvagrant.optimus.utils.Commands.FBSimctl.RECORDVIDEO_XCODE8;

public class OnDevice {
    private SmartBOT smartBOT;
    private AppiumDriver driver;

    @FindBy(id = "android:id/status_bar_latest_event_content")
    private List<WebElement> latestItemsContent;

    String itemText_Phone_Locator_Text = "android:id/text";
    String itemTitle_Locator_Text = "android:id/title";
    String itemText_Tablet_Locator_Text = "android:id/big_text";


    public OnDevice(SmartBOT smartBOT) {
        this.smartBOT = smartBOT;
        this.driver = smartBOT.getDriver();
        PageFactory.initElements(this.driver, this);

    }

    private String getPID(String processLog) {
        String[] processDetails = getStringWithAPipe(processLog).split("\\|");
        String pid = processDetails[1];
        return pid;
    }

    private String getSpecificPID(String processLog) {
        String[] processDetails = getStringWithAPipe(processLog).split("\\|");
        String pid = processDetails[0];
        return pid;
    }

    private String getStringWithAPipe(String strToMatch) {
        String[] stringWithSpace = strToMatch.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : stringWithSpace) {
            if (!str.equals("")) {
                int nbspCode = str.toCharArray()[0];
                if (nbspCode != 160)
                    stringBuilder.append(str + "|");
            }
        }
        return stringBuilder.toString();
    }


    public void clearADBLogs() throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec(String.format("adb -s %s logcat -c", smartBOT.getDeviceUdid()));

    }


    public void grantAllPermissions() throws IOException, InterruptedException {
        String[] permissionNames = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.READ_CONTACTS", "android.permission.CALL_PHONE",
                "android.permission.RECEIVE_SMS", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA"};

        for (String permissionName : permissionNames) {
            new ADBUtil(smartBOT).grantPermission(permissionName);
        }
    }

    public void clearNotifications() {
        By by1 = By.id("com.android.systemui:id/clear_all_button");
        By by2 = By.id("com.android.systemui:id/clear_all_button_label");
        By by3 = By.id("com.android.systemui:id/clear_button");
        By by4 = By.id("com.android.systemui:id/dismiss_text");

//        smartBOT.getDriver().openNotifications();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!clickAny(by1, by2, by3, by4)) {
//            driver.pressKeyCode(AndroidKeyCode.BACK);
            new WaitUtil(driver).waitForElementToBeInvisible(By.xpath("android:id/status_bar_latest_event_content"));
        }

    }

    protected boolean clickAny(By... bys) {
        for (By by : bys) {
            AppiumDriver driver = smartBOT.getDriver();
            if (!driver.findElements(by).isEmpty()) {
                driver.findElement(by).click();
                new WaitUtil(driver).waitForElementToBeInvisible(by);
                return true;
            }
        }
        return false;
    }

    public void setFakeGPSLocationTo(String latLong) throws InterruptedException {
        String notification = getNotification("Fake GPS");
        if (notification != null) {
            System.out.println("GPS Location is already set");
        } else {
            System.out.println("Activating Fake GPS");
            setLocation(latLong);
        }

    }

    public void setLocation(String location) throws InterruptedException {
//        driver.startActivity("com.lexa.fakegps", "com.lexa.fakegps.ui.Main");

        By setLocationButton = By.id("com.lexa.fakegps:id/action_start");

        new WaitUtil(driver).waitForElementToBeVisible(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']"));
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']")).click();

        new WaitUtil(driver).waitForElementToBeVisible(By.xpath("//android.widget.CheckedTextView[@text='Go to']"));
        driver.findElement(By.xpath("//android.widget.CheckedTextView[@text='Go to']")).click();

        new WaitUtil(driver).waitForElementToBeVisible(By.xpath("//android.widget.EditText[@text='latitude, longitude']"));
        driver.findElement(By.xpath("//android.widget.EditText[@text='latitude, longitude']")).sendKeys(location);

        hideKeyboard();
        driver.findElement(By.id("button1")).click();


        new WaitUtil(driver).waitForElementToBeClickable(setLocationButton);
        driver.findElement(setLocationButton).click();
        try {
            if (driver.findElement(setLocationButton).isEnabled())
                driver.findElement(setLocationButton).click();
        } catch (NoSuchElementException ignored) {

        }
    }


    public void hideKeyboard() {
        try {
            driver.hideKeyboard();
        } catch (WebDriverException e) {
        }
    }

    private String getNotification(String notificationTitle) throws InterruptedException {
//        driver.openNotifications();
        int itemsListSize = getLastItemsContentSize();
        for (int i = 0; i < itemsListSize; i++) {
            if (getItemTitle(i).equals(notificationTitle)) {
                String itemText = getItemText(i);
//                driver.pressKeyCode(AndroidKeyCode.BACK);
                return itemText;
            }
        }
//        driver.pressKeyCode(AndroidKeyCode.BACK);
        return null;

    }

    public String getItemText(int num) throws InterruptedException {
        if (latestItemsContent.get(num).findElements(MobileBy.id(itemText_Tablet_Locator_Text)).isEmpty()) {
            return latestItemsContent.get(num).findElement(MobileBy.id(itemText_Phone_Locator_Text)).getText();
        } else {
            return latestItemsContent.get(num).findElement(MobileBy.id(itemText_Tablet_Locator_Text)).getText();
        }
    }

    public String getItemTitle(int num) {
        return latestItemsContent.get(num).findElement(By.id(itemTitle_Locator_Text)).getText();
    }


    private int getLastItemsContentSize() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/status_bar_latest_event_content")));
            return latestItemsContent.size();
        } catch (Exception e) {
        }
        if (driver.findElements(By.xpath("//*[@text='Silent']")).size() != 0) {
            swipeFromTo(driver.findElement(By.xpath("//*[@text='Silent']")), driver.findElement(By.xpath("//*[@text='Lock']")));
            wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/status_bar_latest_event_content")));
            return latestItemsContent.size();
        }
        return 0;

    }

    protected void swipeFromTo(WebElement startElement, WebElement stopElement) {
//        driver.swipe(startElement.getLocation().getX(), startElement.getLocation().getY(), stopElement.getLocation().getX(), stopElement.getLocation().getY(), 1000);
        PointOption startPoint = PointOption.point(startElement.getLocation().getX(), startElement.getLocation().getY());
        PointOption stopPoint = PointOption.point(stopElement.getLocation().getX(), stopElement.getLocation().getY());

        new TouchAction(driver).press(startPoint)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(stopPoint)
                .release().perform();
    }

    private void recordAndroidVideo(String fileName) {
        System.out.println("Starting capturing video");
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(String.format("adb -s %s shell screenrecord /sdcard/%s.mp4", smartBOT.getDeviceUdid()
                    , fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void killprocess(String pid) throws IOException {
        Runtime.getRuntime().exec("kill " + pid);
    }


}
