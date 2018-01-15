package com.testvagrant.monitor.clients;

import com.jayway.awaitility.Awaitility;
import com.jayway.awaitility.Duration;
import com.testvagrant.monitor.entities.MongoService;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.jayway.awaitility.Awaitility.await;
import static io.restassured.RestAssured.given;

public class ServiceClient {
    private static Process serviceProcess;

    public boolean isServiceRunning() {
        try {
            Response serviceResponse = given()
                    .header("Content-Type", "application/json")
                    .get(MongoService.getMongoService() + "/ping");
            return serviceResponse.asString().equals("PONG");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isServiceDown() {
        return !isServiceRunning();
    }

    public void startService() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> commands = new ArrayList<>();
        commands.add("java");
        commands.add("-jar");
        commands.add(System.getProperty("user.dir")+"/libs/optimus-services-1.0.jar");
        serviceProcess = processBuilder.command(commands)
                .start();
        await().atMost(Duration.FIVE_MINUTES).until(this::isServiceRunning);
    }

    public void startService(String... args) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(new String[0]);
        List<String> commands = new ArrayList();
        commands.add("java");
        commands.add("-jar");
        commands.add(System.getProperty("user.dir") + "/libs/optimus-services-1.0.jar");
        Collections.addAll(commands, args);
        serviceProcess = processBuilder.command(commands).start();
        Awaitility.await().atMost(Duration.FIVE_MINUTES).until(this::isServiceRunning);
    }

    public void stopService() {
        if(serviceProcess!=null)
            serviceProcess.destroy();
    }
}
