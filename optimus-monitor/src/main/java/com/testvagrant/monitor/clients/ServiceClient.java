package com.testvagrant.monitor.clients;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class ServiceClient {
    private static Process serviceProcess;
    private static String port="";
    private static List<String> commands = new ArrayList();

    public static void main(String[] args) throws IOException {
        ServiceClient serviceClient = new ServiceClient();
        List<String> commands = serviceClient.initCommands(args);
        serviceClient.getPort(commands);
        serviceClient.startService();
    }
    public boolean isServiceRunning() {
        try {
            String uri = String.format("http://localhost:%s/v1",port);
            Response serviceResponse = given()
                    .header("Content-Type", "application/json")
                    .get(uri + "/ping");
            return serviceResponse.asString().equals("PONG");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isServiceDown() {
        return !isServiceRunning();
    }

    public void startService() throws IOException {
        if(isServiceDown()) {
            ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
            serviceProcess = processBuilder.command(commands).start();
            Awaitility.await().atMost(Duration.FIVE_MINUTES).until(this::isServiceRunning);
        }
    }

    private List<String> initCommands(String[] args) {
        commands.add("java");
        commands.add("-jar");
        commands.add(System.getProperty("user.dir") + "/libs/optimus-services-1.0.jar");
        Collections.addAll(commands, args);
        return commands;
    }

    private String getPort(List<String> commands) {
        System.out.println("Service commands "+commands);
        Optional<String> first = commands.stream().filter(command -> command.contains("--port")).findFirst();
        if(first.isPresent()) {
            String s = first.get();
            port = s.split("=")[1];
            port = port.equals("")? "8080" : port;
        }
        return port;
    }

    public void stopService() {
        if(serviceProcess!=null)
            serviceProcess.destroy();
    }

}
