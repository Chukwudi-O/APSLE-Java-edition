package ui;

public class Teacher extends User {
	private static String type = "TEACHER";
	private String username;
	private String password;
	private int gradeNumber;
	private int classNumber;
	
	public Teacher(String userN, String passW, int gradeNum, int classNum)
	{
		super(userN,passW);
		gradeNumber = gradeNum;
		classNumber = classNum;
	}
	
	public String getType()
	{
		return type;
	}
	
	public int getGradeNum()
	{
		return gradeNumber;
	}
	
	public int getClassNum()
	{
		return classNumber;
	}
}
