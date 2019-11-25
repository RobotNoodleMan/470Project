
public class RegisteredUser extends User {
	
	protected String name;
	protected String password;
	
	public RegisteredUser()
	{
		super();
	}
	
	public void setName(String n)
	{
		name = n;
	}
	public void setPassword(String p)
	{
		password = p;
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
