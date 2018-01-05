package com.testvagrant.mdb.android;

import com.testvagrant.commons.entities.SmartBOT;
import com.testvagrant.commons.entities.performance.Activity;
import com.testvagrant.commons.entities.performance.CpuStatistics;
import com.testvagrant.commons.entities.performance.Exceptions;
import com.testvagrant.commons.entities.performance.MemoryStatistics;
import com.testvagrant.mdb.core.CommandExecutor;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.testvagrant.mdb.utils.Commands.AndroidCommands.*;
import static com.testvagrant.mdb.utils.Commons.convertToMB;
import static java.util.stream.Collectors.toList;

public class DumpsysParser {


    private SmartBOT smartBOT;
    private CommandExecutor commandExecutor;
    private static String previousActivity = "OptimusActivity";

    public DumpsysParser(SmartBOT smartBOT) {
        this.smartBOT = smartBOT;
        this.commandExecutor = new CommandExecutor();
    }

    public CpuStatistics getCpuUsage() {
        HashMap<String, String> userKernelInfo = new HashMap<>();
        String cpuInfoCommand = String.format(GET_CPUINFO, smartBOT.getDeviceUdid(), smartBOT.getAppPackageName());
        List<String> cpuInfo = commandExecutor.exec(cpuInfoCommand).asList();
        for (String s : cpuInfo) {
            if (s.contains("TOTAL")) {
                String cpuUsageOutput = s.split(":")[1].trim();
                Pattern p = Pattern.compile(CPU_REGEX);
                Matcher matcher = p.matcher(cpuUsageOutput);
                matcher.find();
                userKernelInfo.put(matcher.group(3), matcher.group(1));
                userKernelInfo.put(matcher.group(7), matcher.group(5));
            }
        }
        CpuStatistics cpuStatistics = new CpuStatistics();
        cpuStatistics.setUser(userKernelInfo.get("user"));
        cpuStatistics.setKernel(userKernelInfo.get("kernel"));
        return cpuStatistics;
    }


    public MemoryStatistics getMemoryInfo() {
        MemoryStatistics memoryStatistics = new MemoryStatistics();
        String memUsageCommand = String.format(GET_MEMINFO, smartBOT.getDeviceUdid(), smartBOT.getAppPackageName());
        List<String> memInfo = commandExecutor.exec(memUsageCommand).asList();
        Optional<String> memoryDetails = memInfo.stream().filter(line -> line.trim().startsWith("TOTAL ")).findFirst();
        if (memoryDetails.isPresent()) {
            List<String> memoryTotal = Arrays.asList(memoryDetails.get().split(" "));
            List<String> collect = memoryTotal.stream().filter(line -> line.length() > 0).collect(toList());
            memoryStatistics.setTotal(convertToMB(collect.get(1)));
            memoryStatistics.setActual(convertToMB(collect.get(2)));
        }
        return memoryStatistics;
    }


    public Activity getCurrentActivity() {
        String focussedActivityCommand = String.format(GET_FOCUSSED_ACTIVITY, smartBOT.getDeviceUdid());
        List<String> activityDetails = commandExecutor.exec(focussedActivityCommand).asList();
        Optional<String> mCurrentFocus = activityDetails.stream().filter(line -> line.trim().startsWith("mCurrentFocus")).findFirst();
        Activity activity = new Activity();
        if (mCurrentFocus.isPresent()) {
            if (!mCurrentFocus.get().contains("null")) {
                String focussedActivity = Arrays.stream(mCurrentFocus.get().split("\\.")).filter(word -> word.endsWith("}")).findFirst().get().replaceAll("}", "");
                previousActivity = focussedActivity;
                activity.setFocussedActivity(focussedActivity);
            } else {
                activity.setFocussedActivity(previousActivity);
            }
        }
        return activity;
    }

    public Optional<Exceptions> getException() {
        String pid = "";
        String errorsCommand = String.format(GET_ERRORS, smartBOT.getDeviceUdid());
        List<String> errors = commandExecutor.exec(errorsCommand).asList();
        List<String> adbLogs = new ArrayList<>();
        if (errors.stream().anyMatch(line -> line.contains("FATAL EXCEPTION:"))) {
            Optional<String> packageLine = errors.stream().filter(line -> line.contains("Process: " + smartBOT.getAppPackageName())).findFirst();
            if (packageLine.isPresent()) {
                pid = packageLine.get().split("[\\(\\)]")[1];
            }

        } else {
            return Optional.empty();
        }
        Exceptions exception = new Exceptions();
        for (String line : errors) {
            if (line.contains("(") && line.contains(")"))
                if (line.split("[\\(\\)]")[1].contains(pid)) {
                    adbLogs.add(line);
                }
        }


        String stackTrace = String.join("\n", adbLogs);
        if (stackTrace.length() > 0) {
            String[] split = stackTrace.split("FATAL EXCEPTION: ");
            exception.setStacktrace("FATAL EXCEPTION: "+split[1]);
            String activity = getActiviy(stackTrace);
            exception.setActivityName(activity);
            return Optional.of(exception);
        } else
            return Optional.empty();
    }

    private String getActiviy(String stacktrace) {
        try {
            String caused_by = stacktrace.split("Caused by")[1];
            String activityLine = caused_by.split("\t")[1].split(" ")[1];
            return activityLine.substring(0, activityLine.lastIndexOf("."));
        } catch (Exception e) {
            return getCurrentActivity().getFocussedActivity();
        }
    }
}
