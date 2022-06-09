package UseCases;

import Configuration.Automation;
import Configuration.Test;
import Configuration.TestDevice;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

public class PU4
{
	public static void performTest(Test test) throws InterruptedException, IOException {
		TestDevice device = test.getDevice(0);
		AndroidDriver<AndroidElement> driver = device.getDriver();

		Automation.Login(test);

		Automation.waitUntilViewChanges(driver);

		MobileElement el1 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.Button[3]");
		el1.click();

		Automation.waitUntilViewChanges(driver);

		Assert.assertEquals(driver.currentActivity() , ".ui.activity.UploadStoryActivity" );

		File assetDir = new File("C:\\Users\\PytonTygrysi\\Pictures", "../assets");

		File img = new File(assetDir.getCanonicalPath(), "20201226_012744.jpg");

		driver.pushFile("InternalStorage/DCIM/Camera" + "/" + img.getName(), img);

		MobileElement el2 = driver.findElementById("net.mankindenemy.diploma.project:id/rv_rounded_story");
		el2.click();
		MobileElement el3 = driver.findElementById("com.google.android.apps.photos:id/image");
		el3.click();
		MobileElement el4 = driver.findElementByAccessibilityId("Photo taken on Dec 26, 2020 1:41:28 PM");
		el4.click();
		MobileElement el5 = driver.findElementById("net.mankindenemy.diploma.project:id/et_edit_story");
		el5.sendKeys("Przykładowy opis posta 1.");
		MobileElement el6 = driver.findElementById("net.mankindenemy.diploma.project:id/share_story");
		el6.click();

		Automation.logout(test);
	}
}
