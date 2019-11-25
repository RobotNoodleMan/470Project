
public class RegisteredUser extends User {
	
	protected String name;
	protected String password;
	protected boolean recieveNotifications;
	
	public RegisteredUser()
	{
		super();
		recieveNotifications = false;
		
	}
	
	public void setName(String n)
	{
		name = n;
	}
	public void setPassword(String p)
	{
		password = p;
	}
	public void setNotifcation(boolean n)
	{
		recieveNotifications = n;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPassword()
	{
		return password;
	}

}
