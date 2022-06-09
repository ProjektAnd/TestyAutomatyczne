package Configuration;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Automation
{

	static final String loadingScreenActivity = Configuration.LOADING_SCREEN_ACTIVITY;

	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

		Logger logger = Configuration.initializeLogger();

		// Test test = new Test("PU13", 1);
		// Test test = new Test("PU1", 1);
		// Test test = new Test("PU9", 1);
		Test test = new Test("PU26", 2);

		runTest(test, logger);

	}

	public static void runTest(Test test, Logger logger) throws NoSuchMethodException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {

		String testName = test.getName();
		Class<?> targetClass = Class.forName("UseCases." + testName);
		Method method = targetClass.getDeclaredMethod("performTest", Test.class);
		method.invoke(targetClass , test);


		logger.log(Level.INFO, "Test " + testName + " has been completed");

	}

	public static void logout(Test test)  {
		for (TestDevice device : test.getDevices()) {

			AndroidDriver<AndroidElement> driver = device.getDriver();

			MobileElement el1 = driver.findElementById("net.mankindenemy.diploma.project:id/bt_settings_menu");
			el1.click();

			MobileElement el2 = driver.findElementById("net.mankindenemy.diploma.project:id/bt_log_out");
			el2.click();

		}
	}

	public static void login(TestDevice device, String email , String password) throws InterruptedException {
		AndroidDriver<AndroidElement> driver = device.getDriver();

		Automation.waitUntilViewChanges(driver);

		Assert.assertEquals(driver.currentActivity() , ".ui.activity.LoginActivity");

		MobileElement el1 = driver.findElementById("net.mankindenemy.diploma.project:id/et_email_address");
		el1.sendKeys(email);

		MobileElement el2 = driver.findElementById("net.mankindenemy.diploma.project:id/et_password");
		el2.sendKeys(password);

		MobileElement el3 = driver.findElementById("net.mankindenemy.diploma.project:id/bt_login");
		el3.click();

		Automation.waitUntilViewChanges(driver);

		Assert.assertEquals(driver.currentActivity() , ".ui.activity.MainActivity" );
	}

	public static void Login(Test test) throws InterruptedException {
		for (TestDevice device : test.getDevices()) {

			login(device , device.getEmail() , device.getPassword());
		}
	}


	public static void waitUntilViewChanges(AndroidDriver<AndroidElement> driver) throws InterruptedException {

		String previousActivity = driver.currentActivity() , activityName = driver.currentActivity();
		while(activityName.equals(previousActivity) || activityName.equals(loadingScreenActivity)){
			TimeUnit.MILLISECONDS.sleep(100);
			activityName = driver.currentActivity();
		}
	}

	public static void waitUntilViewChanges(AndroidDriver<AndroidElement> driverA , AndroidDriver<AndroidElement> driverB) throws InterruptedException {

		String previousActivity = driverA.currentActivity() , activityName = driverA.currentActivity();
		while(activityName.equals(previousActivity) || activityName.equals(loadingScreenActivity)){
			TimeUnit.MILLISECONDS.sleep(100);
			activityName = driverA.currentActivity();
		}

		previousActivity = activityName;
		activityName = driverB.currentActivity();

		while(!activityName.equals(previousActivity)){
			TimeUnit.MILLISECONDS.sleep(100);
			activityName = driverB.currentActivity();
		}
	}

	public static void waitHalfsecond() throws InterruptedException {

			TimeUnit.MILLISECONDS.sleep(500);
	}

}
