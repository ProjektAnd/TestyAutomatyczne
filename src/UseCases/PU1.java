package UseCases;

import Configuration.Test;
import Configuration.TestDevice;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import Configuration.Automation;

public class PU1
{
	public static void performTest(Test test) throws InterruptedException {
		TestDevice device = test.getDevice(0);
		AndroidDriver<AndroidElement> driver = device.getDriver();

		Automation.waitUntilViewChanges(driver);

		Assert.assertEquals(driver.currentActivity() , ".ui.activity.LoginActivity");

		MobileElement el1 = driver.findElementById("net.mankindenemy.diploma.project:id/et_email_address");
		el1.sendKeys("l.stepien@enemyofmankind.com");

		MobileElement el2 = driver.findElementById("net.mankindenemy.diploma.project:id/et_password");
		el2.sendKeys("test11");

		MobileElement el3 = driver.findElementById("net.mankindenemy.diploma.project:id/bt_login");
		el3.click();

		Automation.waitUntilViewChanges(driver);

		Assert.assertEquals(driver.currentActivity() , ".ui.activity.MainActivity" );

		Automation.logout(test);
	}
}
