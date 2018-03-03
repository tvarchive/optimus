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

package com.testvagrant.monitor.performance;

import com.testvagrant.commons.entities.SmartBOT;
import com.testvagrant.monitor.entities.ScreenshotStatistics;
import com.testvagrant.monitor.utils.ImageComparator;
import com.testvagrant.monitor.utils.ImageResizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.TimerTask;

public class ScreenShotGenerator extends TimerTask {
    int counter;
    private List<ScreenshotStatistics> screenShots;
    private SmartBOT smartBOT;
    private int interval;
    private final String CAPTURE_SCREENSHOT = "adb -s %s shell screencap /sdcard/%s/%s.png";
    private File lastImage;

    public ScreenShotGenerator(SmartBOT smartBOT) {
        this.smartBOT = smartBOT;
    }

    public ScreenShotGenerator(List<ScreenshotStatistics> screenShots, SmartBOT smartBOT, int interval) {
        this.screenShots = screenShots;
        this.smartBOT = smartBOT;
        this.interval = interval;
    }

    @Override
    public void run() {
        int itr = (++counter) * interval;
        byte[] screenshotAs = new byte[0];
        try {
            captureScreenshots(itr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScreenshotStatistics screenshot = new ScreenshotStatistics();
        screenshot.setInterval(itr);
        screenShots.add(screenshot);
    }


    private void captureScreenshots(int interval) throws IOException {
        createFolder(getUDID());
        Runtime rt = Runtime.getRuntime();
        rt.exec(String.format(CAPTURE_SCREENSHOT, smartBOT.getDeviceUdid(), getScenarioID(), interval));
    }

    public void updateScreenshotStatistics(List<ScreenshotStatistics> screenShots) {
        File file = new File("build/screenshotstemp/" + getUDID() + "/" + getScenarioID());
        if (file.exists()) {
            try {
                screenShots.stream().forEach(screenShot -> {
                    Optional<File> first = Arrays.stream(file.listFiles()).filter(fileName -> fileName.getName().equals(String.valueOf(screenShot.getInterval()) + ".png")).findFirst();
                    if (first.isPresent()) {
                        try {
                            if (lastImage != null) {
                                double imageDiff = ImageComparator.getImageDifference(lastImage, first.get());
                                if (imageDiff >= 0.3) {
                                    screenShot.setScreenshot(new ImageResizer(first.get()).resizeImage());
                                    screenShot.setUnique(true);
                                }
                            } else {
                                screenShot.setScreenshot(new ImageResizer(first.get()).resizeImage());
                                screenShot.setUnique(true);
                            }
                            lastImage = first.get();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    public void deleteImageFolder() {
        File file = new File("build/screenshotstemp/" + getUDID() + "/" + getScenarioID());
        if (file.exists()) {
            file.delete();
        }
    }

    private String getUDID() {
        return smartBOT.getDeviceUdid().replaceAll("\\.", "").replaceAll(":", "");
    }

    private String getScenarioID() {
        return smartBOT.getScenario().getId().replaceAll("[^a-zA-Z0-9]", "");
    }

    public void importScreenshots() {
        System.out.println("Importing screenshots from -- " + smartBOT.getDeviceUdid());
        String line;
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p = runtime.exec(String.format("adb -s %s pull /sdcard/%s build/screenshotstemp/%s", smartBOT.getDeviceUdid(), getScenarioID(), getUDID()));
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(
                    p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(
                    p.getErrorStream()));
            while ((line = stdInput.readLine()) != null) {
//                System.out.println(line);
            }
            while ((line = stdError.readLine()) != null) {
//                System.out.println(line);
            }
            while (p.isAlive()) {
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void createFolder(String udid) {
        System.out.println("creating a folder for -- " + udid.replaceAll("\\.", ""));
        File file = new File(String.format("build/screenshotstemp/%s", udid.replaceAll("\\.", "")));
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
