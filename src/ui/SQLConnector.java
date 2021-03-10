package ui;
import java.sql.*;

public class SQLConnector {
	private String url = "jdbc:mysql://localhost:3306";
	private String user = "root";
	private String password = "";
	private String database = "apsle";
	private Connection conn;
	
	public SQLConnector() {
		initiateConnection();
	}
	
	private void initiateConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
			
			Statement stt = conn.createStatement();
			stt.execute("USE "+database);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public boolean checkCredential(String username, String pass,String userType)
	{
		try
		{
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM "+userType+"logins WHERE username = ? AND password = ?");
			stmt.setString(1, username);
			stmt.setString(2, pass);
			ResultSet res = stmt.executeQuery();
			if (res.next())
				{
					return true;
				}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public int[] getClassroomNumbers(String userN, String passW,String userType)
	{
		int[] classroom = {0,0};
		try
		{
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM "+userType+"logins WHERE username = ? AND password = ?");
			stmt.setString(1, userN);
			stmt.setString(2, passW);
			ResultSet res = stmt.executeQuery();
	
			if (res.next())
			{
				classroom[0] = res.getInt("gradeNumber");
				classroom[1] = res.getInt("classNumber");
			}
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return classroom;
	}

	public void addNewUser(String[] newUserInfo) {
		try
		{
			if (newUserInfo[2].equals("teacher"))
			{
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO teacherlogins "+
										"(username,password,classNumber,gradeNumber) VALUES (?,?,?,?)");
				stmt.setString(1,newUserInfo[0]);
				stmt.setString(2,newUserInfo[1]);
				stmt.setInt(3,Integer.parseInt(newUserInfo[4]));
				stmt.setInt(4,Integer.parseInt(newUserInfo[3]));
				
				stmt.execute();
				System.out.println("Teacher added successfully");
			}else if (newUserInfo[2].equals("student"))
			{
				PreparedStatement stmt = conn.prepareStatement("INSERT INTO studentlogins "+
										"(username,password,classNumber,gradeNumber) VALUES (?,?,?,?)");
				stmt.setString(1,newUserInfo[0]);
				stmt.setString(2,newUserInfo[1]);
				stmt.setString(3,newUserInfo[4]);
				stmt.setString(4,newUserInfo[3]);
				
				stmt.execute();
				System.out.println("Student added successfully");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}

//stt.execute("CREATE DATABASE IF NOT EXISTS testing");

/*
stt.execute("DROP TABLE IF EXISTS people");
stt.execute("CREATE TABLE people (" +
		"id BIGINT NOT NULL AUTO_INCREMENT,"
		+ "fname VARCHAR(25),"
		+ "lname VARCHAR(25),"
		+ "PRIMARY KEY(id)"
		+")");

stt.execute("INSERT INTO people (fname, lname) VALUES"+
		"('Chevil', 'Ojuro')");

ResultSet res = stt.executeQuery("SELECT * FROM people WHERE lname = 'Ojuro'");

while (res.next())
{
	System.out.println(res.getString("fname") + " " + res.getString("lname"));
}

PreparedStatement prep = con.prepareStatement("SELECT * FROM people WHERE lname = ?");
prep.setString(1, "Mitchell");

res = prep.executeQuery();
*/