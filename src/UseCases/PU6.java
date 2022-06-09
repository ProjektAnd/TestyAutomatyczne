package UseCases;

import Configuration.Automation;
import Configuration.Test;
import Configuration.TestDevice;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;

import java.io.IOException;

public class PU6
{
	public static void performTest(Test test) throws InterruptedException, IOException {
		TestDevice device = test.getDevice(0);
		AndroidDriver<AndroidElement> driver = device.getDriver();

		Automation.Login(test);

		MobileElement searchUsersButton = driver.findElementById("net.mankindenemy.diploma.project:id/bt_settings_menu");

		Assert.assertEquals(searchUsersButton.getText() , "Search friends");

		MobileElement el1 = driver.findElementById("net.mankindenemy.diploma.project:id/bt_settings_menu");
		el1.click();
		MobileElement el2 = driver.findElementById("net.mankindenemy.diploma.project:id/bt_language");

		Automation.waitUntilViewChanges(driver);

		Assert.assertEquals(driver.currentActivity() , ".ui.activity.LanguageActivity" );

		el2.click();
		MobileElement el3 = driver.findElementById("android:id/text1");
		el3.click();
		MobileElement el4 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
		el4.click();

		Automation.waitUntilViewChanges(driver);
		searchUsersButton = driver.findElementById("net.mankindenemy.diploma.project:id/bt_settings_menu");

		Assert.assertEquals(driver.currentActivity() , ".ui.activity.MainActivity" );
		Assert.assertEquals(searchUsersButton.getText() , "Szukaj użytkowników");

		MobileElement el5 = (MobileElement) driver.findElementById("net.mankindenemy.diploma.project:id/bt_settings_menu");
		el5.click();
		MobileElement el6 = (MobileElement) driver.findElementById("net.mankindenemy.diploma.project:id/bt_language");
		el6.click();

		Automation.waitUntilViewChanges(driver);

		Assert.assertEquals(driver.currentActivity() , ".ui.activity.LanguageActivity" );


		MobileElement el7 = (MobileElement) driver.findElementById("android:id/text1");
		el7.click();
		MobileElement el8 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]");
		el8.click();

		Automation.waitUntilViewChanges(driver);
		searchUsersButton = driver.findElementById("net.mankindenemy.diploma.project:id/bt_settings_menu");

		Assert.assertEquals(driver.currentActivity() , ".ui.activity.MainActivity" );
		Assert.assertEquals(searchUsersButton.getText() , "Search friends");

		Automation.logout(test);
	}
}
