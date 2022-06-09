package Configuration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Configuration
{

	static final String LOADING_SCREEN_ACTIVITY = ".ui.activity.SplashActivity";
	static final String DEFAULT_ACCOUNT_PASSWORD = "test11";

	static final List<String> ANDROID_DEVICES = Arrays.asList("emulator-5554" , "emulator-5556");
	static final List<User> REGISTERED_ACCOUNTS = Arrays.asList(
		 new User("l.stepien@enemyofmankind.com" , "test11"),
		 new User("e1@e.com" , "test11"),
		 new User("e2@e.com" , "test11") );

	static final String APLICATION_PACKAGE = "net.mankindenemy.diploma.project";
	static final String APLICATION_ACTIVITY = ".ui.activity.SplashActivity";
	static final String SERVER_HUB_URL = "http://25.61.123.51:4723/wd/hub";

	public static DesiredCapabilities initializeDesiredCapabilities(String deviceName )
	{
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("deviceName" , deviceName);
		dc.setCapability("platformName" , Platform.ANDROID);
		dc.setCapability("appPackage", APLICATION_PACKAGE );
		dc.setCapability("appActivity" , APLICATION_ACTIVITY);
		dc.setCapability("onReset" , true);
		dc.setCapability("autoGrantPermissions", true);
		return dc;
	}

	public static AndroidDriver<AndroidElement> InitializeDriver(DesiredCapabilities dc) throws MalformedURLException {
		return InitializeDriver(SERVER_HUB_URL, dc);
	}

	public static AndroidDriver<AndroidElement> InitializeDriver(String url, DesiredCapabilities dc) throws MalformedURLException {
		return new AndroidDriver<>(new URL(url) , dc);
	}

	public static Logger initializeLogger()
	{
		return Logger.getLogger(Automation.class.getName());
	}

	public static List<String> GetAndroidDevices()
	{
		return ANDROID_DEVICES;
	}
}
