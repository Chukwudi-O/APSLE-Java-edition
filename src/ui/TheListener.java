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
		switch(e.getActionCommand())
		{
		case "LOGIN":
			String user = uimanager.getUserName();
			String pass = uimanager.getPassWord();
			if (user.equals("") || pass.equals(""))
			{
				System.out.println("Login unsucessful");
			} else
			{
				if(uimanager.verifyCredentials(user,pass).equals("admin"))
				{
					currentUser = new Administrator(user,pass);
					uimanager.loadHomePage(currentUser);
				}else if(uimanager.verifyCredentials(user,pass).equals("teacher"))
				{
					int[] classroom = sql.getClassroomNumbers(user, pass, "teacher");
					currentUser = new Teacher(user,pass,classroom[0],classroom[1]);
					uimanager.loadHomePage(currentUser);
				}else if(uimanager.verifyCredentials(user,pass).equals("student"))
				{
					int[] classroom = sql.getClassroomNumbers(user, pass, "student");
					currentUser = new Student(user,pass,classroom[0],classroom[1]);
					uimanager.loadHomePage(currentUser);
				}else if(uimanager.verifyCredentials(user,pass).equals("nonUser"))
				{
					System.out.println("Unsuccessful login. Check your credentials again.");
				}	
			}
			break;
			
		case "MANAGE USERS":
			uimanager.loadManageUsers(currentUser);
			break;
			
		case "MANAGE CLASSROOMS":
			uimanager.loadManageClassrooms();
			break;
			
		case "EXIT":
			uimanager.exitApp();
			break;
			
		case "ADD":
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
							uimanager.resetFrame("ManageUsers");
						}
					} catch (Exception ex)
					{
						System.out.println("Please enter a number less than 7 for class and grade number.");
					}
					
				}
			}
			break;
			
		case "DELETE":
			String [] userToDelete = uimanager.getUserToDelete();
			if(userToDelete.length != 0)
			{
				sql.DeleteUser(userToDelete);
				uimanager.resetFrame("ManageUsers");
			} else
			{
				System.out.println("That user does not exist");
			}
			break;
			
		case "UPDATE":
			uimanager.updateUsers();
			break;
		case "MATH":
			uimanager.loadSubject("math");
			break;
		case "ENGLISH":
			uimanager.loadSubject("english");
			break;
		case "SCIENCE":
			uimanager.loadSubject("science");
			break;
		case "UPLOAD MATERIALS":
			uimanager.searchFile("material");
			break;
		case "UPLOAD ASSIGNMENTS":
			uimanager.searchFile("assignment");
			break;
		case "ApproveSelection":
			uimanager.uploadFile();
			break;
		case "CancelSelection":
			uimanager.cancelUpload();
			break;
		default:
				System.out.println(e.getActionCommand());
		}
	}
	

}
