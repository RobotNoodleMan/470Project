package entities;

public class RegisteredUser extends User {
	
	protected String name;
	protected String password;
	private boolean recieveNotifications;
	
	public RegisteredUser()
	{
		super();
		setRecieveNotifications(false);
		
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
		setRecieveNotifications(n);
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPassword()
	{
		return password;
	}

	public boolean isRecieveNotifications() {
		return recieveNotifications;
	}

	public void setRecieveNotifications(boolean recieveNotifications) {
		this.recieveNotifications = recieveNotifications;
	}

}
