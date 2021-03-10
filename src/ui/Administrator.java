package ui;

public class Administrator extends User {
	private static String type = "ADMIN";
	private String username;
	private String password;
	
	public Administrator(String userN, String passW)
	{
		super(userN,passW);
	}
	
	public String getType()
	{
		return type;
	}
}
