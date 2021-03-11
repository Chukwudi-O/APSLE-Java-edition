package ui;
import java.sql.*;
import java.util.ArrayList;

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

	public void addNewUser(String[] newUserInfo) 
	{
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
	
	public String[][] getUserData()
	{
		ArrayList<String[]> data = new ArrayList<String[]>();
		String[][] none = {};
		try
		{
			PreparedStatement stmt_t = conn.prepareStatement("SELECT * FROM teacherlogins");
			PreparedStatement stmt_s = conn.prepareStatement("SELECT * FROM studentlogins");
			
			ResultSet res_t = stmt_t.executeQuery();
			ResultSet res_s = stmt_s.executeQuery();
			
			
			int i=0;
			while(res_t.next())
			{
				String[] tempData = {res_t.getString("username"),"Teacher",String.valueOf(res_t.getInt("gradeNumber")),String.valueOf(res_t.getInt("classNumber"))};
				data.add(tempData);
				++i;
			}
			
			while(res_s.next())
			{
				String[] tempData = {res_s.getString("username"),"Student",String.valueOf(res_s.getInt("gradeNumber")),String.valueOf(res_s.getInt("classNumber"))};
				data.add(tempData);
				++i;
			}
			
			String[][] allData = new String[data.size()][4];
			
			for (i=0;i<data.size();++i)
			{
				allData[i][0] = data.get(i)[0];
				allData[i][1] = data.get(i)[1];
				allData[i][2] = data.get(i)[2];
				allData[i][3] = data.get(i)[3];
			}
			
			return allData;
			
			
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		return none;
	}

	public void DeleteUser(String[] userToDelete) {
		try
		{
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM "+userToDelete[1].toLowerCase()+"logins WHERE username = ?");
			stmt.setString(1, userToDelete[0]);
			stmt.execute();
			System.out.println(userToDelete[0]+" was deleted successfully.");
		} catch(Exception e)
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