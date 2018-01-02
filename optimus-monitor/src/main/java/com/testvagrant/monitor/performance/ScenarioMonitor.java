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
import com.testvagrant.commons.entities.performance.Activity;
import com.testvagrant.commons.entities.performance.CpuStatistics;
import com.testvagrant.commons.entities.performance.MemoryStatistics;
import com.testvagrant.monitor.entities.CpuData;
import com.testvagrant.monitor.entities.MemoryData;
import com.testvagrant.monitor.entities.ScenarioTimeline;
import com.testvagrant.monitor.entities.ScreenshotStatistics;
import com.testvagrant.monitor.services.ScenariosServiceImpl;
import com.testvagrant.monitor.utils.Commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Timer;

public class ScenarioMonitor {
    private final int PERIOD = 1000;
    private List<CpuStatistics> cpuStat = new ArrayList<>();
    private List<MemoryStatistics> memoryStat = new ArrayList<>();
    private List<ScreenshotStatistics> screenShots = new ArrayList<>();
    private List<Activity> activities = new ArrayList<>();
    private SmartBOT smartBOT;
    private Thread countdown;
    private Timer timer = new Timer(true);

    public ScenarioMonitor(SmartBOT smartBOT) {
        this.smartBOT = smartBOT;
        countdown = new Thread(() -> {
            try {
                Thread.sleep(300 * PERIOD);
                timer.cancel();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void start() {
        int interval = PERIOD / 1000;
        new Commons().createTempFolder(smartBOT);
        timer.schedule(new CPUMonitor(cpuStat, smartBOT, interval), 0, PERIOD);
        timer.schedule(new ActivityMonitor(activities, smartBOT, interval), 0, PERIOD);
        timer.schedule(new MemoryMonitor(memoryStat, smartBOT, interval), 0, PERIOD);
        timer.schedule(new ScreenShotGenerator(screenShots, smartBOT, interval), 0, PERIOD);

        countdown.start();
    }

    public void stop() {
        timer.cancel();
        List<ScenarioTimeline> scenarioTimelines = new ArrayList<>();
        final Activity[] activity = {getBaseActivity()};
        final MemoryStatistics[] memoryStatistics = {new MemoryStatistics()};
        final ScreenshotStatistics[] screenshotStats = {new ScreenshotStatistics()};
        ScreenShotGenerator generator = new ScreenShotGenerator(smartBOT);
        generator.importScreenshots();
        generator.updateScreenshotStatistics(screenShots);
        cpuStat.forEach(cpuStatistics -> {
            Optional<Activity> activityOptional = activities.stream().filter(activity1 -> activity1.getInterval() == cpuStatistics.getInterval()).findFirst();
            activityOptional.ifPresent(activity1 -> activity[0] = activity1);
            Optional<MemoryStatistics> optionalMemoryStatistics = memoryStat.stream().filter(memoryStatistics1 -> memoryStatistics1.getInterval() == cpuStatistics.getInterval()).findFirst();
            optionalMemoryStatistics.ifPresent(memoryStatistics1 -> memoryStatistics[0] = memoryStatistics1);
            Optional<ScreenshotStatistics> statisticsOptional = screenShots.stream().filter(screenshotStatistics -> screenshotStatistics.getInterval() == cpuStatistics.getInterval()).findFirst();
            statisticsOptional.ifPresent(screenshotStatistics -> screenshotStats[0] = screenshotStatistics);
            ScenarioTimeline scenarioTimeline = getScenarioTimeLine(cpuStatistics.getInterval(), activity[0], cpuStatistics, memoryStatistics[0], screenshotStats[0]);
            scenarioTimelines.add(scenarioTimeline);
        });
        generator.deleteImageFolder();
        new ScenariosServiceImpl().updateScenarioTimeLine(smartBOT, scenarioTimelines);
        new Commons().deleteTempFolder(smartBOT);
    }


    private ScenarioTimeline getScenarioTimeLine(int interval, Activity activity, CpuStatistics cpuStatistics, MemoryStatistics memoryStatistics, ScreenshotStatistics screenshotStatistics) {
        ScenarioTimeline scenarioTimeline = new ScenarioTimeline();
        scenarioTimeline.setInterval(interval);
        scenarioTimeline.setActivity(activity.getFocussedActivity());
        scenarioTimeline.setCpuData(new CpuData().setUser(cpuStatistics.getUser()).setKernel(cpuStatistics.getKernel()));
        scenarioTimeline.setMemoryData(new MemoryData().setTotal(memoryStatistics.getTotal()).setActual(memoryStatistics.getActual()));
        if(screenshotStatistics.isUnique()) {
            scenarioTimeline.setScreenshotData(screenshotStatistics.getScreenshot());
        }
        return scenarioTimeline;
    }

    private Activity getBaseActivity() {
        Activity activity = new Activity();
        activity.setFocussedActivity("undefinedActivity");
        return activity;
    }


    private ScreenshotStatistics getLastScreenshotStatistics() {
        Optional<ScreenshotStatistics> reduce = screenShots.stream().filter(ScreenshotStatistics::isUnique).reduce((first, second) -> second);
        if(reduce.isPresent()) {
            return reduce.get();
        }
        ScreenshotStatistics screenshot = new ScreenshotStatistics();
        screenshot.setScreenshot(new byte[]{});
        return screenshot;
    }
}
