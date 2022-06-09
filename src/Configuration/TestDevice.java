package Configuration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.util.List;

public class TestDevice
{
	private static final List<String> androidDevices = Configuration.GetAndroidDevices();

	private String emulatorName;
	private User user;
	private AndroidDriver<AndroidElement> androidDriver;

	public TestDevice(int deviceId, User us) throws MalformedURLException {
		emulatorName = androidDevices.get(deviceId);
		user = us;
		DesiredCapabilities dc = Configuration.initializeDesiredCapabilities(emulatorName);
		androidDriver = Configuration.InitializeDriver(dc);
	}

	public String getDeviceName()
	{
		return emulatorName;
	}

	public String getPassword()
	{
		return user.getPassword();
	}

	public String getEmail()
	{
		return user.getEmail();
	}

	public AndroidDriver<AndroidElement> getDriver() {
		return androidDriver;
	}
}
