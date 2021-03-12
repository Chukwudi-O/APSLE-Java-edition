package ui;

public class User {
	private String username;
	private String password;
	
	public User(String userN, String passW)
	{
		username = userN;
		password = passW;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public String getPassword()
	{
		return this.password;
	}
	public String getType()
	{
		return "user";
	}
	
	public int getGradeNum()
	{
		return 0;
	}
	
	public int getClassNum()
	{
		return 0;
	}
}
