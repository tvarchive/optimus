package com.testvagrant.mdb.core;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CommandExecutor {

    private StringBuilder log;

    public CommandExecutor() {
        log = new StringBuilder();
    }

    public CommandExecutor exec(String command) {
        String line;
        Process process;
        Runtime rt = Runtime.getRuntime();
        try {
            process = rt.exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(
                    process.getErrorStream()));
            while ((line = stdInput.readLine()) != null) {
                log.append(line);
                log.append(System.getProperty("line.separator"));
            }
            while ((line = stdError.readLine()) != null) {
                log.append(line);
                log.append(System.getProperty("line.separator"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getLog() {
        return log.toString();
    }


    public List<String> asList() {
        List<String> logAsList = new ArrayList<>();
        try (Scanner scanner = new Scanner(log.toString())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.length() > 0) {
                    logAsList.add(line);
                }
            }
        }
        return logAsList;
    }

    public String asLine() {
        Optional<String> s = asList().stream().reduce((first, second) -> second);
        if(s.isPresent()) {
            return s.get();
        }
        return "";
    }
}
