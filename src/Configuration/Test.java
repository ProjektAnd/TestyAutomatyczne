package Configuration;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test
{
	private static final List<User> USERS = Configuration.REGISTERED_ACCOUNTS;
	private String name;
	private int numberOfDevices;
	private List<TestDevice> devices;

	public Test(String testName , int numberOfDevices) throws MalformedURLException {
		name = testName;
		devices = initializeDevices(numberOfDevices);
	}

	private static List<TestDevice> initializeDevices(int numberOfDevices) throws MalformedURLException {
		List<TestDevice> devices = new ArrayList<>();

		for (int i = 0; i < numberOfDevices; i++)
		{
			User user = USERS.get(i);
			TestDevice device = new TestDevice(i, user);
			devices.add(device);
		}
		return devices;
	}

	public int getNumberOfDevices() {
		return numberOfDevices;
	}

	public String getName() {
		return name;
	}

	public TestDevice getDevice(int id)
	{
		return devices.get(id);
	}

	public List<TestDevice> getDevices()
	{
		return devices;
	}
}
