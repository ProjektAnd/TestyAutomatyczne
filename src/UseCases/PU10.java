package UseCases;

import Configuration.Automation;
import Configuration.Test;
import Configuration.TestDevice;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

public class PU10
{
	public static void performTest(Test test) throws InterruptedException {

		TestDevice firstDevice = test.getDevice(0);
		TestDevice secondDevice = test.getDevice(1);
		AndroidDriver<AndroidElement> firstDriver = firstDevice.getDriver();
		AndroidDriver<AndroidElement> secondDriver = secondDevice.getDriver();

		Automation.Login(test);

		// [I] Otwieranie widoków z konwersacjami
		MobileElement el1_a =  firstDriver.findElementById("net.mankindenemy.diploma.project:id/bt_chat");
		el1_a.click();
		MobileElement el1_b =  secondDriver.findElementById("net.mankindenemy.diploma.project:id/bt_chat");
		el1_b.click();
		MobileElement el2_a =  firstDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]");
		el2_a.click();
		MobileElement el2_b =  secondDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[5]");
		el2_b.click();

		Automation.waitUntilViewChanges(firstDriver , secondDriver);

		// [II.a] Wysyłanie pierwszej wiadomości.
		MobileElement el3_a =  firstDriver.findElementById("net.mankindenemy.diploma.project:id/et_chat_input");
		el3_a.sendKeys("Wiadomosc testowa nr. 1");
		MobileElement el4_a =  firstDriver.findElementById("net.mankindenemy.diploma.project:id/bt_send_message");
		el4_a.click();
		// [II.b] Wysyłanie drugiej wiadomosci.
		MobileElement el3_b =  secondDriver.findElementById("net.mankindenemy.diploma.project:id/et_chat_input");
		el3_b.sendKeys("Wiadomosc testowa nr. 2");
		MobileElement el4_b =  secondDriver.findElementById("net.mankindenemy.diploma.project:id/bt_send_message");
		el4_b.click();
		// [II.c] Wysyłanie trzeciej wiadomosci.
		MobileElement el5_a =  firstDriver.findElementById("net.mankindenemy.diploma.project:id/et_chat_input");
		el5_a.sendKeys("Wiadomosc testowa nr. 3");
		MobileElement el6_a =  firstDriver.findElementById("net.mankindenemy.diploma.project:id/bt_send_message");
		el6_a.click();

		// [III] Pobieranie calej historii czatu z wiadomosciami
		AndroidElement chat = firstDriver.findElement(By.tagName("rv_chat"));
		List<MobileElement> messages = chat.findElements(By.tagName("text"));
		int messagesCount = messages.size();
		String firstMessage = messages.get(messagesCount - 3).getText();
		String secondMessage = messages.get(messagesCount - 2).getText();
		String thirdMessage = messages.get(messagesCount - 1).getText();

		// [IV] Sprawdzanie czy wiadomosci zostaly otrzymane
		Assert.assertEquals(firstMessage  , "Wiadomosc testowa nr. 1" );
		Assert.assertEquals(secondMessage  , "Wiadomosc testowa nr. 2" );
		Assert.assertEquals(thirdMessage  , "Wiadomosc testowa nr. 3" );

		firstDriver.navigate().back();
		secondDriver.navigate().back();

		Automation.waitUntilViewChanges(firstDriver , secondDriver);

		Automation.logout(test);
	}
}
