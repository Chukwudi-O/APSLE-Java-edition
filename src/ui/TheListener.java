package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TheListener implements ActionListener {

	User currentUser;
	SQLConnector sql;
	UIManager uimanager;
	
	public TheListener(UIManager ui,SQLConnector sqlconnector)
	{
		uimanager = ui;
		sql = sqlconnector;
	}
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("LOGIN"))
		{
			String user = uimanager.getUserName();
			String pass = uimanager.getPassWord();
			String userType = "nonUser";
			if (user.equals("") || pass.equals(""))
			{
				System.out.println("Login unsucessful");
			} else
			{
				if(uimanager.verifyCredentials(user,pass).equals("admin"))
				{
					userType = "admin";
				}else if(uimanager.verifyCredentials(user,pass).equals("teacher"))
				{
					userType = "teacher";
				}else if(uimanager.verifyCredentials(user,pass).equals("student"))
				{
					userType = "student";
				}else if(uimanager.verifyCredentials(user,pass).equals("nonUser"))
				{
					System.out.println("Unsuccessful login. Check your credentials again.");
				}	
			}
			
			if(!userType.equals("nonUser"))
			{
				
				if (userType.equals("admin"))
				{
					currentUser = new Administrator(user,pass);
					
				}else
				{
					int[] classroom = sql.getClassroomNumbers(user, pass, userType);
					if (userType.equals("teacher")) {
						currentUser = new Teacher(user,pass,classroom[0],classroom[1]);
					} else
					{
						currentUser = new Student(user,pass,classroom[0],classroom[1]);
					}
				}
				uimanager.loadHomePage(currentUser);
			}
		}else if (e.getActionCommand().equals("MANAGE USERS"))
		{
			System.out.println("Managing users");
			uimanager.loadManageUsers(currentUser);
		}else if (e.getActionCommand().equals("MANAGE CLASSROOMS"))
		{
			System.out.println("Managing classrooms");
		}else if (e.getActionCommand().equals("EXIT"))
		{
			uimanager.exitApp();
		}else if (e.getActionCommand().equals("ADD"))
			if(!uimanager.isTextFieldEmpty())
			{
				String[] newUserInfo = uimanager.getNewUserInfo();
				if(!newUserInfo[2].equals("none"))
				{
					try
					{
						if (Integer.parseInt(newUserInfo[3]) < 7 && Integer.parseInt(newUserInfo[4]) < 7)
						{
							sql.addNewUser(newUserInfo);
						}
					} catch (Exception ex)
					{
						System.out.println("Please enter a number less than 7 for class and grade number.");
					}
					
				}
			}
		
	} 

}
