package com.testvagrant.optimus.device;///*
// * Copyright (c) 2017.  TestVagrant Technologies
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// */
//
//package com.testvagrant.optimus.device;
//
//import com.testvagrant.optimus.entity.SmartBOT;
//import com.testvagrant.optimus.register.DeviceRegistrar;
//import com.testvagrant.optimus.utils.DeviceMatrix;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import static org.junit.Assert.assertEquals;
//
//@Ignore
//public class OptimusControllerTest extends OptimusTestBase {
//
//
//    @Test
//    public void registerSmartBOTsForASingleAppTest() throws Exception {
////        String appJson = getAppJson("singleApp_Local_Sequential_Android_Emulator.json");
//        new DeviceRegistrar().setUpDevices(new DeviceMatrix());
//        OptimusController controller = new OptimusController("singleApp_Local_Sequential_Android_Emulator.json","test");
//        List<SmartBOT> smartBOTs = controller.registerSmartBOTs();
//        controller.deRegisterSmartBOTs(smartBOTs);
//    }
//
//    @Test
//    public void registerSmartBOTsForAnInterAppTest() throws Exception {
//        new DeviceRegistrar().setUpDevices(new DeviceMatrix());
//        List<SmartBOT> smartBOTs = new OptimusController("interApp_Local_Sequential_Android_To_Android.json","test")
//                .registerSmartBOTs();
//        new OptimusController("interApp_Local_Sequential_Android_To_Android.json","test").deRegisterSmartBOTs(smartBOTs);
//    }
//
//    @Test
//    public void registerSmartBOTsForASingleAppOnIOSTest() throws Exception {
//        new DeviceRegistrar().setUpDevices(new DeviceMatrix());
//        List<SmartBOT> smartBOTs = new OptimusController("singleApp_Local_Sequential_iOS.json","test").registerSmartBOTs();
//        new OptimusController("singleApp_Local_Sequential_iOS.json","test").deRegisterSmartBOTs(smartBOTs);
//    }
//
//
//    @Test
//    public void shouldBeAbleToUpdateDevicesFromDifferentThreads() throws InterruptedException {
//
//        new DeviceRegistrar().setUpDevices(new DeviceMatrix());
//
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        for (int iterator = 0; iterator < 2; iterator++) {
//            Runnable worker = new DeviceThread("singleApp_Local_Sequential_iOS.json");
//            executor.execute(worker);
//        }
//        executor.shutdown();
//        // Wait until all threads are finish
//        while (!executor.isTerminated()) {
//
//        }
//        System.out.println("\nFinished all threads");
//    }
//
//
//    @Test
//    public void registerSmartBOTsForASingleBrowserAndroidTest() throws Exception {
//        new DeviceRegistrar().setUpDevices(new DeviceMatrix());
//        OptimusController controller = new OptimusController(getAppJson("singleBrowser_Local_Sequential_Android.json"),"test");
//        List<SmartBOT> smartBOTs = controller.registerSmartBOTs();
//        controller.deRegisterSmartBOTs(smartBOTs);
//    }
//
//    @Test
//    public void registerSmartBOTsForASingleBrowserFragmentaionAndroidTest() throws Exception {
//        new DeviceRegistrar().setUpDevices(new DeviceMatrix());
//
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        for (int iterator = 0; iterator < 2; iterator++) {
//            Runnable worker = new DeviceThread("singleBrowser_Local_Sequential_Android.json");
//            executor.execute(worker);
//        }
//        executor.shutdown();
//        // Wait until all threads are finish
//        while (!executor.isTerminated()) {
//
//        }
//        System.out.println("\nFinished all threads");
//    }
//
//
//    private class DeviceThread implements Runnable {
//        private String appJsonString;
//
//        public DeviceThread(String appJson) {
//            this.appJsonString = appJson;
//        }
//
//        @Override
//        public void run() {
//            List<SmartBOT> smartBOTs = null;
//            OptimusController controller = new OptimusController(getAppJson("singleApp_Local_Sequential_iOS.json"),"test");
//            try {
//                smartBOTs = controller.registerSmartBOTs();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            controller.deRegisterSmartBOTs(smartBOTs);
//        }
//    }
//}
