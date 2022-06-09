package Configuration;

public class User
{
	private static final String defaultAccountPassword = Configuration.DEFAULT_ACCOUNT_PASSWORD;
	private String email;
	private String password;

	public User(String em)
	{
		 email = em;
		 password = defaultAccountPassword;
	}

	public User(String em, String pw)
	{
		email = em;
		password = pw;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}
