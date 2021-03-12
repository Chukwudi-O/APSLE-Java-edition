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

	public void updateUserInfo(String[][] updateInfo) {
		String userN = updateInfo[0][0];
		String userT = updateInfo[0][1];
		String userclassN = updateInfo[0][2];
		String newPass = updateInfo[1][0];
		String newGrade = updateInfo[1][1];
		String newClass = updateInfo[1][2];
		
		if(!newPass.equals(""))
		{
			if(!newGrade.equals(""))
			{
				if(!newClass.equals(""))
				{
					try
					{
						PreparedStatement stmt = conn.prepareStatement("UPDATE "+userT.toLowerCase()+"logins SET password = ? ,gradeNumber = ?,classNumber = ? WHERE username = ? AND classNumber = ?");
						stmt.setString(1, newPass);
						stmt.setString(2, newGrade);
						stmt.setString(3, newClass);
						stmt.setString(4, userN);
						stmt.setString(5, userclassN);
						stmt.execute();
					} catch(Exception e)
					{
						e.printStackTrace();
					}
				}else 
				{
					try
					{
						PreparedStatement stmt = conn.prepareStatement("UPDATE "+userT.toLowerCase()+"logins SET password = ? ,gradeNumber = ? WHERE username = ? AND classNumber = ?");
						stmt.setString(1, newPass);
						stmt.setString(2, newGrade);
						stmt.setString(3, userN);
						stmt.setString(4, userclassN);
						stmt.execute();
					} catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}else
			{
				if(!newClass.equals(""))
				{
					try
					{
						PreparedStatement stmt = conn.prepareStatement("UPDATE "+userT.toLowerCase()+"logins SET password = ?, classNumber = ? WHERE username = ? AND classNumber = ?");
						stmt.setString(1, newPass);
						stmt.setString(2, newClass);
						stmt.setString(3, userN);
						stmt.setString(4, userclassN);
						stmt.execute();
					} catch(Exception e)
					{
						e.printStackTrace();
					}
				}else
				{
					try
					{
						PreparedStatement stmt = conn.prepareStatement("UPDATE "+userT.toLowerCase()+"logins SET password = ? WHERE username = ? AND classNumber = ?");
						stmt.setString(1, newPass);
						stmt.setString(2, userN);
						stmt.setString(3, userclassN);
						stmt.execute();
					} catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
			
		}else
		{
			if(!newGrade.equals(""))
			{
				if(!newClass.equals(""))
				{
					try
					{
						PreparedStatement stmt = conn.prepareStatement("UPDATE "+userT.toLowerCase()+"logins SET gradeNumber = ?, classNumber = ? WHERE username = ? AND classNumber = ?");
						stmt.setString(1, newGrade);
						stmt.setString(2, newClass);
						stmt.setString(3, userN);
						stmt.setString(4, userclassN);
						stmt.execute();
					} catch(Exception e)
					{
						e.printStackTrace();
					}
				}else
				{
					try
					{
						PreparedStatement stmt = conn.prepareStatement("UPDATE "+userT.toLowerCase()+"logins SET gradeNumber = ? WHERE username = ? AND classNumber = ?");
						stmt.setString(1, newGrade);
						stmt.setString(2, userN);
						stmt.setString(3, userclassN);
						stmt.execute();
					} catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}else
			{
				if(!newClass.equals(""))
				{
					try
					{
						PreparedStatement stmt = conn.prepareStatement("UPDATE "+userT.toLowerCase()+"logins SET classNumber = ? WHERE username = ? AND classNumber = ?");
						stmt.setString(1, newClass);
						stmt.setString(2, userN);
						stmt.setString(3, userclassN);
						stmt.execute();
					} catch(Exception e)
					{
						e.printStackTrace();
					}
				}else
				{
					System.out.println("Nothing was entered");
				}
			}
		}
		
	}

	public void teacherUpload(String[] uploadInfo) {
		try
		{
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO teacheruploads (fileName,fileExtension,"+
								"dateUploaded,subject,gradeNumber,classNumber,typeOfUpload,uploadedBy) VALUES ("+
								"?,?,?,?,?,?,?,?) ");
			stmt.setString(1, uploadInfo[0]);
			stmt.setString(2, uploadInfo[1]);
			stmt.setString(3, uploadInfo[2]);
			stmt.setString(4, uploadInfo[3]);
			stmt.setInt(5, Integer.parseInt(uploadInfo[4]));
			stmt.setInt(6, Integer.parseInt(uploadInfo[5]));
			stmt.setString(7, uploadInfo[6]);
			stmt.setString(8, uploadInfo[7]);
			
			stmt.execute();
		} catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	public ArrayList<String[]> getMaterials(String subject, int gradeNum, int classNum) {
		ArrayList<String[]> materialInfo = new ArrayList<String[]>();
		try
		{
			PreparedStatement stmt = conn.prepareStatement("SELECT fileName,fileExtension FROM teacheruploads WHERE subject = ? AND gradeNumber = ? AND classNumber = ? AND typeOfUpload = ?");
			stmt.setString(1, subject);
			stmt.setInt(2, gradeNum);
			stmt.setInt(3, classNum);
			stmt.setString(4, "material");
			ResultSet res = stmt.executeQuery();
			
			materialInfo = new ArrayList<String[]>();
			while(res.next())
			{
				String[] temp = {res.getString("fileName"),res.getString("fileExtension")};
				materialInfo.add(temp);
			}
			return materialInfo;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return materialInfo;
	}

	public ArrayList<String[]> getAssignments(String subject, int gradeNum, int classNum) {
		ArrayList<String[]> assignmentInfo = new ArrayList<String[]>();
		try
		{
			PreparedStatement stmt = conn.prepareStatement("SELECT fileName,fileExtension FROM teacheruploads WHERE subject = ? AND gradeNumber = ? AND classNumber = ? AND typeOfUpload = ?");
			stmt.setString(1, subject);
			stmt.setInt(2, gradeNum);
			stmt.setInt(3, classNum);
			stmt.setString(4, "assignment");
			ResultSet res = stmt.executeQuery();
			
			assignmentInfo = new ArrayList<String[]>();
			int i=0;
			while(res.next())
			{
				String[] temp = {res.getString("fileName"),res.getString("fileExtension")};
				assignmentInfo.add(temp);
				i++;
			}
			return assignmentInfo;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return assignmentInfo;
	}

	public void removeUpload(String[] fileInfo) {
		try
		{
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM teacheruploads WHERE subject = ? AND gradeNumber = ? AND classNumber = ? AND fileName = ?");
			stmt.setString(1,fileInfo[0]);
			stmt.setInt(2,Integer.parseInt(fileInfo[1]));
			stmt.setInt(3,Integer.parseInt(fileInfo[2]));
			stmt.setString(4,fileInfo[3]);
			stmt.execute();
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