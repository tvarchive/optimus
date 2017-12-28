package com.testvagrant.optimus.recommender;

import com.testvagrant.commons.entities.reportParser.ExecutedScenario;
import com.testvagrant.commons.entities.reportParser.Step;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ExceptionCollatorTest {

    private List<ExecutedScenario> executedScenarioList;
    @Before
    public void setup() {
        executedScenarioList = new ArrayList<>();
        buildExecutedScenarioList();
    }


    private void buildExecutedScenarioList() {
        ExecutedScenario executedScenario = new ExecutedScenario();
        executedScenario.setId("Id1");
        executedScenario.setSteps(getSteps());
        ExecutedScenario executedScenario1 = new ExecutedScenario();
        executedScenario1.setId("Id2");
        executedScenario1.setSteps(getSteps1());
        ExecutedScenario executedScenario2 = new ExecutedScenario();
        executedScenario2.setId("Id3");
        executedScenario2.setSteps(getSteps2());
        ExecutedScenario executedScenario3 = new ExecutedScenario();
        executedScenario3.setId("Id4");
        executedScenario3.setSteps(getSteps3());
        executedScenarioList.add(executedScenario);
        executedScenarioList.add(executedScenario1);
        executedScenarioList.add(executedScenario2);
        executedScenarioList.add(executedScenario3);
    }

    @Test
    public void verifyExceptions() {
        ExceptionCollator collator =  new ExceptionCollator(executedScenarioList);
        Map<String, List<ExecutedScenario>> collate = collator.collate();
        collate.keySet().forEach(key -> {
            System.out.println("***********************************************");
            System.out.println(key);
            collate.get(key).forEach(keyVal -> System.out.println(keyVal.getId()));
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        });

    }

    private List<Step> getSteps() {
        Step step = new Step();
        step.setError_message(getSentence1());
        List<Step> steps = new ArrayList<>();
        steps.add(step);
        return steps;
    }

    private List<Step> getSteps1() {
        Step step1 = new Step();
        step1.setError_message(getSentence4());
        List<Step> steps = new ArrayList<>();
        steps.add(step1);
        return steps;
    }

    private List<Step> getSteps2() {
        Step step2 = new Step();
        step2.setError_message(getSentence2());
        List<Step> steps = new ArrayList<>();
        steps.add(step2);
        return steps;
    }


    private List<Step> getSteps3() {
        Step step3 = new Step();
        step3.setError_message(getSentence5());
        List<Step> steps = new ArrayList<>();
        steps.add(step3);
        return steps;
    }



    protected String getSentence1() {
        return "org.openqa.selenium.TimeoutException: Timed out after 30 seconds waiting for element to no longer be visible: By.id: input_field\n" +
                "Build info: version: '2.53.1', revision: 'a36b8b1cd5757287168e54b817830adce9b0158d', time: '2016-06-30 19:26:09'\n" +
                "System info: host: 'xyzw', ip: '0.0.0.0', os.name: 'os', os.arch: 'osarch', os.version: '0.0.0', java.version: '1.8.0_131'\n" +
                "Driver info: io.appium.java_client.android.AndroidDriver\n" +
                "Capabilities [{appPackage=com.tvapp.app.staging, networkConnectionEnabled=true, clearSystemFiles=true, noSign=true, warnings={}, databaseEnabled=false, deviceName=fd96de2a, platform=LINUX, deviceUDID=fd96de2a, appActivity=com.tvapp.app.Splash, desired={appActivity=com.tvapp.app.Splash, appPackage=com.tvapp.app.staging, clearSystemFiles=true, newCommandTimeout=600, noSign=true, autoGrantPermissions=true, platformName=Android, udid=fd96de2a, deviceName=Android}, newCommandTimeout=600, platformVersion=6.0.1, webStorageEnabled=false, locationContextEnabled=false, takesScreenshot=true, autoGrantPermissions=true, javascriptEnabled=true, platformName=Android, udid=fd96de2a}]\n" +
                "Session ID: 5edc92ad-7df4-4ea2-834b-c242c55a3ddb\n" +
                "\tat org.openqa.selenium.support.ui.WebDriverWait.timeoutException(WebDriverWait.java:80)\n" +
                "\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:261)\n" +
                "\tat pages.BasePage.waitForElementToBeInvisible(BasePage.java:115)\n" +
                "\tat pages.somePage.SignInPage.signIn(SignInPage.java:76)\n" +
                "\tat steps.HomePageSteps.loginAndTapOnPlusIcon(HomePageSteps.java:77)\n" +
                "\tat steps.HomePageSteps.onHomePageILogInUsingDetailsOfUserForMixpanel(HomePageSteps.java:82)\n" +
                "\tat ✽.Given On Home Page I log in using details of somepathUser for mixpanel(src/test/java/features/transport/regression/somepath/Somefeature.feature:26)";
    }

    protected String getSentence2() {
        return "org.openqa.selenium.TimeoutException: Timed out after 30 seconds waiting for element to be clickable: By.id: location_name_text\n" +
                "Build info: version: '2.53.1', revision: 'a36b8b1cd5757287168e54b817830adce9b0158d', time: '2016-06-30 19:26:09'\n" +
                "System info: host: 'xyzw', ip: '0.0.0.0', os.name: 'os', os.arch: 'osarch', os.version: '0.0.0', java.version: '1.8.0_131'\n" +
                "Driver info: io.appium.java_client.android.AndroidDriver\n" +
                "Capabilities [{appPackage=com.tvapp.app.staging, networkConnectionEnabled=true, clearSystemFiles=true, noSign=true, warnings={}, databaseEnabled=false, deviceName=8957e88a, platform=LINUX, deviceUDID=8957e88a, appActivity=com.tvapp.app.Splash, desired={appActivity=com.tvapp.app.Splash, appPackage=com.tvapp.app.staging, clearSystemFiles=true, newCommandTimeout=600, noSign=true, autoGrantPermissions=true, platformName=Android, udid=8957e88a, deviceName=Android}, newCommandTimeout=600, platformVersion=6.0.1, webStorageEnabled=false, locationContextEnabled=false, takesScreenshot=true, autoGrantPermissions=true, javascriptEnabled=true, platformName=Android, udid=8957e88a}]\n" +
                "Session ID: 993c26be-a34e-453a-a2fd-bbe15ad31184\n" +
                "\tat org.openqa.selenium.support.ui.WebDriverWait.timeoutException(WebDriverWait.java:80)\n" +
                "\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:261)\n" +
                "\tat pages.BasePage.waitForElementToBeClickable(BasePage.java:70)\n" +
                "\tat pages.test.SomePage.enterFromLocationWithoutDetails(SomePage.java:210)\n" +
                "\tat steps.test.SomePageSteps.onSomePageIEnterFromLocationLocation(SomePageSteps.java:131)\n" +
                "\tat ✽.When On some Page I enter from location Oakwood Premier Cozmo(src/test/java/features/transport/regression/somepath/Somefeature.feature:54)\n" +
                "Caused by: org.openqa.selenium.NoSuchElementException: An element could not be located on the page using the given search parameters. (WARNING: The server did not provide any stacktrace information)\n" +
                "Command duration or timeout: 362 milliseconds\n" +
                "For documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_element.html\n" +
                "Build info: version: '2.53.1', revision: 'a36b8b1cd5757287168e54b817830adce9b0158d', time: '2016-06-30 19:26:09'\n" +
                "System info: host: 'xyzw', ip: '0.0.0.0', os.name: 'os', os.arch: 'osarch', os.version: '0.0.0', java.version: '1.8.0_131'\n" +
                "Driver info: io.appium.java_client.android.AndroidDriver\n" +
                "Capabilities [{appPackage=com.tvapp.app.staging, networkConnectionEnabled=true, clearSystemFiles=true, noSign=true, warnings={}, databaseEnabled=false, deviceName=8957e88a, platform=LINUX, deviceUDID=8957e88a, appActivity=com.tvapp.app.Splash, desired={appActivity=com.tvapp.app.Splash, appPackage=com.tvapp.app.staging, clearSystemFiles=true, newCommandTimeout=600, noSign=true, autoGrantPermissions=true, platformName=Android, udid=8957e88a, deviceName=Android}, newCommandTimeout=600, platformVersion=6.0.1, webStorageEnabled=false, locationContextEnabled=false, takesScreenshot=true, autoGrantPermissions=true, javascriptEnabled=true, platformName=Android, udid=8957e88a}]\n" +
                "Session ID: 993c26be-a34e-453a-a2fd-bbe15ad31184\n" +
                "*** Element info: {Using=id, value=location_name_text}\n" +
                "\tat sun.reflect.GeneratedConstructorAccessor42.newInstance(Unknown Source)\n" +
                "\tat sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n" +
                "\tat java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n" +
                "\tat org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:206)\n" +
                "\tat org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:158)\n" +
                "\tat org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:678)\n" +
                "\tat io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:51)\n" +
                "\tat io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n" +
                "\tat io.appium.java_client.android.AndroidDriver.execute(AndroidDriver.java:1)\n" +
                "\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:363)\n" +
                "\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:67)\n" +
                "\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n" +
                "\tat io.appium.java_client.android.AndroidDriver.findElement(AndroidDriver.java:1)\n" +
                "\tat org.openqa.selenium.remote.RemoteWebDriver.findElementById(RemoteWebDriver.java:413)\n" +
                "\tat io.appium.java_client.DefaultGenericMobileDriver.findElementById(DefaultGenericMobileDriver.java:75)\n" +
                "\tat io.appium.java_client.AppiumDriver.findElementById(AppiumDriver.java:1)\n" +
                "\tat io.appium.java_client.android.AndroidDriver.findElementById(AndroidDriver.java:1)\n" +
                "\tat org.openqa.selenium.By$ById.findElement(By.java:218)\n" +
                "\tat org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:355)\n" +
                "\tat io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:63)\n" +
                "\tat io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n" +
                "\tat io.appium.java_client.android.AndroidDriver.findElement(AndroidDriver.java:1)\n" +
                "\tat org.openqa.selenium.support.ui.ExpectedConditions.findElement(ExpectedConditions.java:899)\n" +
                "\tat org.openqa.selenium.support.ui.ExpectedConditions.access$000(ExpectedConditions.java:41)\n" +
                "\tat org.openqa.selenium.support.ui.ExpectedConditions$7.apply(ExpectedConditions.java:205)\n" +
                "\tat org.openqa.selenium.support.ui.ExpectedConditions$7.apply(ExpectedConditions.java:201)\n" +
                "\tat org.openqa.selenium.support.ui.ExpectedConditions$22.apply(ExpectedConditions.java:653)\n" +
                "\tat org.openqa.selenium.support.ui.ExpectedConditions$22.apply(ExpectedConditions.java:646)\n" +
                "\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:238)\n" +
                "\tat pages.BasePage.waitForElementToBeClickable(BasePage.java:70)\n" +
                "\tat pages.test.SomePage.enterFromLocationWithoutDetails(SomePage.java:210)\n" +
                "\tat steps.test.SomePageSteps.onSomePageIEnterFromLocationLocation(SomePageSteps.java:131)\n" +
                "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                "\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
                "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" +
                "\tat cucumber.runtime.Utils$1.call(Utils.java:37)\n" +
                "\tat cucumber.runtime.Timeout.timeout(Timeout.java:13)\n" +
                "\tat cucumber.runtime.Utils.invoke(Utils.java:31)\n" +
                "\tat cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:38)\n" +
                "\tat cucumber.runtime.StepDefinitionMatch.runStep(StepDefinitionMatch.java:37)\n" +
                "\tat cucumber.runtime.Runtime.runStep(Runtime.java:299)\n" +
                "\tat cucumber.runtime.model.StepContainer.runStep(StepContainer.java:44)\n" +
                "\tat cucumber.runtime.model.StepContainer.runSteps(StepContainer.java:39)\n" +
                "\tat cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:44)\n" +
                "\tat cucumber.runtime.junit.ExecutionUnitRunner.run(ExecutionUnitRunner.java:91)\n" +
                "\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:63)\n" +
                "\tat cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:18)\n" +
                "\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n" +
                "\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n" +
                "\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n" +
                "\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n" +
                "\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n" +
                "\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n" +
                "\tat cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:70)\n" +
                "\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:93)\n" +
                "\tat cucumber.api.junit.Cucumber.runChild(Cucumber.java:37)\n" +
                "\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)\n" +
                "\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)\n" +
                "\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)\n" +
                "\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)\n" +
                "\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)\n" +
                "\tat org.junit.runners.ParentRunner.run(ParentRunner.java:363)\n" +
                "\tat cucumber.api.junit.Cucumber.run(Cucumber.java:98)\n" +
                "\tat org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecuter.runTestClass(JUnitTestClassExecuter.java:86)\n" +
                "\tat org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecuter.execute(JUnitTestClassExecuter.java:49)\n" +
                "\tat org.gradle.api.internal.tasks.testing.junit.JUnitTestClassProcessor.processTestClass(JUnitTestClassProcessor.java:64)\n" +
                "\tat org.gradle.api.internal.tasks.testing.SuiteTestClassProcessor.processTestClass(SuiteTestClassProcessor.java:50)\n" +
                "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                "\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
                "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" +
                "\tat org.gradle.messaging.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:35)\n" +
                "\tat org.gradle.messaging.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)\n" +
                "\tat org.gradle.messaging.dispatch.ContextClassLoaderDispatch.dispatch(ContextClassLoaderDispatch.java:32)\n" +
                "\tat org.gradle.messaging.dispatch.ProxyDispatchAdapter$DispatchingInvocationHandler.invoke(ProxyDispatchAdapter.java:93)\n" +
                "\tat com.sun.proxy.$Proxy2.processTestClass(Unknown Source)\n" +
                "\tat org.gradle.api.internal.tasks.testing.worker.TestWorker.processTestClass(TestWorker.java:106)\n" +
                "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                "\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
                "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                "\tat java.lang.reflect.Method.invoke(Method.java:498)\n" +
                "\tat org.gradle.messaging.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:35)\n" +
                "\tat org.gradle.messaging.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)\n" +
                "\tat org.gradle.messaging.remote.internal.hub.MessageHub$Handler.run(MessageHub.java:360)\n" +
                "\tat org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:54)\n" +
                "\tat org.gradle.internal.concurrent.StoppableExecutorImpl$1.run(StoppableExecutorImpl.java:40)\n" +
                "\tat java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)\n" +
                "\tat java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)\n" +
                "\tat java.lang.Thread.run(Thread.java:748)";
    }


    protected String getSentence3() {
        return "org.openqa.selenium.TimeoutException: Timed out after 30 seconds waiting for presence of any elements located by By.id: tvReason\n" +
                "Build info: version: '2.53.1', revision: 'a36b8b1cd5757287168e54b817830adce9b0158d', time: '2016-06-30 19:26:09'\n" +
                "System info: host: 'xyzw', ip: '0.0.0.0', os.name: 'os', os.arch: 'osarch', os.version: '0.0.0', java.version: '1.8.0_131'\n" +
                "Driver info: io.appium.java_client.android.AndroidDriver\n" +
                "Capabilities [{appPackage=com.tvapp.app.staging, networkConnectionEnabled=true, clearSystemFiles=true, noSign=true, warnings={}, databaseEnabled=false, deviceName=8957e88a, platform=LINUX, deviceUDID=8957e88a, appActivity=com.tvapp.app.Splash, desired={appActivity=com.tvapp.app.Splash, appPackage=com.tvapp.app.staging, clearSystemFiles=true, newCommandTimeout=600, noSign=true, autoGrantPermissions=true, platformName=Android, udid=8957e88a, deviceName=Android}, newCommandTimeout=600, platformVersion=6.0.1, webStorageEnabled=false, locationContextEnabled=false, takesScreenshot=true, autoGrantPermissions=true, javascriptEnabled=true, platformName=Android, udid=8957e88a}]\n" +
                "Session ID: 2290c620-7db9-49b3-902b-d7d6cb4a861d\n" +
                "\tat org.openqa.selenium.support.ui.WebDriverWait.timeoutException(WebDriverWait.java:80)\n" +
                "\tat org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:261)\n" +
                "\tat pages.BasePage.waitForPresenceOfAllElements(BasePage.java:78)\n" +
                "\tat pages.test.SomePage.selectCancelReasonFromList(SomePage.java:185)\n" +
                "\tat pages.test.SomePage.cancelTheDriverAcceptedBookingAndSelect(SomePage.java:264)\n" +
                "\tat steps.fullcycle.FullCycleRiderSteps.riderCancelsRideAndSelectIWaitedTooLong(FullCycleRiderSteps.java:34)\n" +
                "\tat ✽.And Rider cancels ride and select I waited too long(src/test/java/features/transport/regression/somepath/Somefeature.feature:153)";
    }

    protected String getSentence4() {
        return "org.openqa.selenium.remote.UnreachableBrowserException: Could not start a new session. Possible causes are invalid address of the remote server or browser start-up failure.\n" +
                "Build info: version: '2.53.1', revision: 'a36b8b1cd5757287168e54b817830adce9b0158d', time: '2016-06-30 19:26:09'\n" +
                "System info: host: 'somehost', ip: '0.0.0.0', os.name: 'os', os.arch: 'osarch', os.version: '0.0.0', java.version: '1.8.0_121'\n" +
                "Driver info: driver.version: AndroidDriver\n" +
                "        at org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:665)\n" +
                "        at io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:51)\n" +
                "        at io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n" +
                "        at io.appium.java_client.android.AndroidDriver.execute(AndroidDriver.java:1)\n" +
                "        at org.openqa.selenium.remote.RemoteWebDriver.startSession(RemoteWebDriver.java:249)\n" +
                "        at org.openqa.selenium.remote.RemoteWebDriver.<init>(RemoteWebDriver.java:131)\n" +
                "        at org.openqa.selenium.remote.RemoteWebDriver.<init>(RemoteWebDriver.java:144)\n" +
                "        at io.appium.java_client.DefaultGenericMobileDriver.<init>(DefaultGenericMobileDriver.java:47)\n" +
                "        at io.appium.java_client.AppiumDriver.<init>(AppiumDriver.java:114)\n" +
                "        at io.appium.java_client.AppiumDriver.<init>(AppiumDriver.java:132)\n" +
                "        at io.appium.java_client.android.AndroidDriver.<init>(AndroidDriver.java:97)\n" +
                "        at com.testvagrant.optimus.device.OptimusController.setUpDevice(OptimusController.java:225)\n" +
                "        at com.testvagrant.optimus.device.OptimusController.addDriver(OptimusController.java:215)\n" +
                "        at com.testvagrant.optimus.device.OptimusController.registerSmartBOTs(OptimusController.java:156)\n" +
                "        at steps.StartingSteps.setUp(StartingSteps.java:22)\n" +
                "        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                "        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
                "        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                "        at java.lang.reflect.Method.invoke(Method.java:498)\n" +
                "        at cucumber.runtime.Utils$1.call(Utils.java:40)\n" +
                "        at cucumber.runtime.Timeout.timeout(Timeout.java:16)\n" +
                "        at cucumber.runtime.Utils.invoke(Utils.java:34)\n" +
                "        at cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n" +
                "        at cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n" +
                "        at cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n" +
                "        at cucumber.runtime.Runtime.runBeforeHooks(Runtime.java:202)\n" +
                "        at cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:40)\n" +
                "        at cucumber.runtime.model.CucumberFeature.run(CucumberFeature.java:165)\n" +
                "        at cucumber.runtime.Runtime.run(Runtime.java:122)\n" +
                "        at cucumber.api.cli.Main.run(Main.java:36)\n" +
                "        at cucumber.api.cli.Main.main(Main.java:18)";
    }

    protected String getSentence5() {
        return "java.lang.NullPointerException\n" +
                "        at steps.StartingSteps.tearDown(StartingSteps.java:30)\n" +
                "        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                "        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
                "        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                "        at java.lang.reflect.Method.invoke(Method.java:498)\n" +
                "        at cucumber.runtime.Utils$1.call(Utils.java:40)\n" +
                "        at cucumber.runtime.Timeout.timeout(Timeout.java:16)\n" +
                "        at cucumber.runtime.Utils.invoke(Utils.java:34)\n" +
                "        at cucumber.runtime.java.JavaHookDefinition.execute(JavaHookDefinition.java:60)\n" +
                "        at cucumber.runtime.Runtime.runHookIfTagsMatch(Runtime.java:224)\n" +
                "        at cucumber.runtime.Runtime.runHooks(Runtime.java:212)\n" +
                "        at cucumber.runtime.Runtime.runAfterHooks(Runtime.java:206)\n" +
                "        at cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:46)\n" +
                "        at cucumber.runtime.model.CucumberFeature.run(CucumberFeature.java:165)\n" +
                "        at cucumber.runtime.Runtime.run(Runtime.java:122)\n" +
                "        at cucumber.api.cli.Main.run(Main.java:36)\n" +
                "        at cucumber.api.cli.Main.main(Main.java:18)";
    }

    protected String getSentence6() {
        return "java.lang.reflect.InvocationTargetException\n" +
                "        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                "        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
                "        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                "        at java.lang.reflect.Method.invoke(Method.java:498)\n" +
                "        at com.testvagrant.intents.core.MethodExecutor.exec(MethodExecutor.java:58)\n" +
                "        at com.testvagrant.intents.Intent.lambda$run$0(Intent.java:130)\n" +
                "        at java.util.ArrayList.forEach(ArrayList.java:1249)\n" +
                "        at com.testvagrant.intents.Intent.run(Intent.java:128)\n" +
                "        at steps.GenericSteps.intent(GenericSteps.java:58)\n" +
                "        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                "        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n" +
                "        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                "        at java.lang.reflect.Method.invoke(Method.java:498)\n" +
                "        at cucumber.runtime.Utils$1.call(Utils.java:40)\n" +
                "        at cucumber.runtime.Timeout.timeout(Timeout.java:16)\n" +
                "        at cucumber.runtime.Utils.invoke(Utils.java:34)\n" +
                "        at cucumber.runtime.java.JavaStepDefinition.execute(JavaStepDefinition.java:38)\n" +
                "        at cucumber.runtime.StepDefinitionMatch.runStep(StepDefinitionMatch.java:37)\n" +
                "        at cucumber.runtime.Runtime.runStep(Runtime.java:300)\n" +
                "        at cucumber.runtime.model.StepContainer.runStep(StepContainer.java:44)\n" +
                "        at cucumber.runtime.model.StepContainer.runSteps(StepContainer.java:39)\n" +
                "        at cucumber.runtime.model.CucumberScenario.run(CucumberScenario.java:44)\n" +
                "        at cucumber.runtime.model.CucumberFeature.run(CucumberFeature.java:165)\n" +
                "        at cucumber.runtime.Runtime.run(Runtime.java:122)\n" +
                "        at cucumber.api.cli.Main.run(Main.java:36)\n" +
                "        at cucumber.api.cli.Main.main(Main.java:18)";
    }


    protected String getSentence7() {
        return "org.openqa.selenium.NoSuchElementException: An element could not be located on the page using the given search parameters. (WARNING: The server did not provide any stacktrace information)\n" +
                "Command duration or timeout: 106 milliseconds\n" +
                "For documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_element.html\n" +
                "Build info: version: '2.53.1', revision: 'a36b8b1cd5757287168e54b817830adce9b0158d', time: '2016-06-30 19:26:09'\n" +
                "System info: host: 'somehost', ip: '0.0.0.0', os.name: 'os', os.arch: 'osarch', os.version: '0.0.0', java.version: '1.8.0_121'\n" +
                "Driver info: io.appium.java_client.android.AndroidDriver\n" +
                "Capabilities [{app=/Users/testvagrantserver/Documents/OptimusTestData/DeltaAirlines/app/FlyDelta.apk, appPackage=com.delta.mobile.android, deviceScreenSize=720x1280, networkConnectionEnabled=true, warnings={}, databaseEnabled=false, deviceName=HGEPX64G, platform=LINUX, deviceUDID=HGEPX64G, appActivity=com.delta.mobile.android.SplashScreen, desired={app=/Users/testvagrantserver/Documents/OptimusTestData/DeltaAirlines/app/FlyDelta.apk, appActivity=com.delta.mobile.android.SplashScreen, appPackage=com.delta.mobile.android, udid=HGEPX64G, platformName=Android, deviceName=Lenovo A7700, useKeystore=false}, platformVersion=6.0, webStorageEnabled=false, locationContextEnabled=false, takesScreenshot=true, javascriptEnabled=true, deviceModel=Lenovo A7700, udid=HGEPX64G, platformName=Android, deviceManufacturer=LENOVO, useKeystore=false}]\n" +
                "Session ID: 050a6f55-625d-4337-9776-adacae7b9a4f\n" +
                "*** Element info: {Using=xpath, value=//android.widget.EditText[@text='SkyMiles® Number or Username1']}\n" +
                "        at sun.reflect.GeneratedConstructorAccessor12.newInstance(Unknown Source)\n" +
                "        at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)\n" +
                "        at java.lang.reflect.Constructor.newInstance(Constructor.java:423)\n" +
                "        at org.openqa.selenium.remote.ErrorHandler.createThrowable(ErrorHandler.java:206)\n" +
                "        at org.openqa.selenium.remote.ErrorHandler.throwIfResponseFailed(ErrorHandler.java:158)\n" +
                "        at org.openqa.selenium.remote.RemoteWebDriver.execute(RemoteWebDriver.java:678)\n" +
                "        at io.appium.java_client.DefaultGenericMobileDriver.execute(DefaultGenericMobileDriver.java:51)\n" +
                "        at io.appium.java_client.AppiumDriver.execute(AppiumDriver.java:1)\n" +
                "        at io.appium.java_client.android.AndroidDriver.execute(AndroidDriver.java:1)\n" +
                "        at org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:363)\n" +
                "        at io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:67)\n" +
                "        at io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n" +
                "        at io.appium.java_client.android.AndroidDriver.findElement(AndroidDriver.java:1)\n" +
                "        at org.openqa.selenium.remote.RemoteWebDriver.findElementByXPath(RemoteWebDriver.java:500)\n" +
                "        at io.appium.java_client.DefaultGenericMobileDriver.findElementByXPath(DefaultGenericMobileDriver.java:145)\n" +
                "        at io.appium.java_client.AppiumDriver.findElementByXPath(AppiumDriver.java:1)\n" +
                "        at io.appium.java_client.android.AndroidDriver.findElementByXPath(AndroidDriver.java:1)\n" +
                "        at org.openqa.selenium.By$ByXPath.findElement(By.java:361)\n" +
                "        at org.openqa.selenium.remote.RemoteWebDriver.findElement(RemoteWebDriver.java:355)\n" +
                "        at io.appium.java_client.DefaultGenericMobileDriver.findElement(DefaultGenericMobileDriver.java:63)\n" +
                "        at io.appium.java_client.AppiumDriver.findElement(AppiumDriver.java:1)\n" +
                "        at io.appium.java_client.android.AndroidDriver.findElement(AndroidDriver.java:1)\n" +
                "        at org.openqa.selenium.support.ui.ExpectedConditions.findElement(ExpectedConditions.java:899)\n" +
                "        at org.openqa.selenium.support.ui.ExpectedConditions.access$000(ExpectedConditions.java:41)\n" +
                "        at org.openqa.selenium.support.ui.ExpectedConditions$7.apply(ExpectedConditions.java:205)\n" +
                "        at org.openqa.selenium.support.ui.ExpectedConditions$7.apply(ExpectedConditions.java:201)\n" +
                "        at org.openqa.selenium.support.ui.FluentWait.until(FluentWait.java:238)\n" +
                "        ... 31 more";
    }
}
