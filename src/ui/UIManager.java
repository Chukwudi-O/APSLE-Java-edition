package ui;
//import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UIManager {
	
	private JFrame mainFrame;
	private HomeFrame homeFrame;
	private SQLConnector sql;
	private JTextField user_tf,pass_tf;
	private JButton login_b;
	private TheListener listener;
	private ManageClassroom manageClassroom;
	private ManageUsers manageUsers;
	private User currentUser;
	private Subject selectedSubject;
	 
	
	public UIManager()
	{
		sql = new SQLConnector();
		listener = new TheListener(this,sql);
	}
	
	
	public void loadLoginPage()
	{
		mainFrame = new JFrame("APSLE - Login");
		mainFrame.setPreferredSize(new Dimension(500,500));
        mainFrame.setResizable(false);
        mainFrame.setLocation(500, 250);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.PAGE_AXIS));
        centerPanel.add(Box.createRigidArea(new Dimension(0,100)));
        
        JLabel user_l = new JLabel("Username");
        user_tf = new JTextField();
        centerPanel.add(user_l);
        centerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        centerPanel.add(user_tf);
        centerPanel.add(Box.createRigidArea(new Dimension(0,90)));
        
        JLabel pass_l = new JLabel("Password");
        pass_tf = new JTextField();
        centerPanel.add(pass_l);
        centerPanel.add(Box.createRigidArea(new Dimension(0,10)));
        centerPanel.add(pass_tf);
        centerPanel.add(Box.createRigidArea(new Dimension(0,90)));
        
        
        
        login_b = new JButton("LOGIN");
        login_b.addActionListener(listener);
        JPanel subpanel = new JPanel();
        subpanel.setLayout(new BoxLayout(subpanel,BoxLayout.LINE_AXIS));
        subpanel.add(Box.createRigidArea(new Dimension(50,0)));
        subpanel.add(login_b);
        subpanel.add(Box.createRigidArea(new Dimension(50,0)));
        centerPanel.add(subpanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0,50)));
        
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
        panel.add(Box.createRigidArea(new Dimension(50,0)));
        panel.add(centerPanel);
        panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
        
        mainFrame.getContentPane().add(panel);
        
        mainFrame.pack();
        mainFrame.setVisible(true);
	}

	public String verifyCredentials(String username,String password)
	{
		boolean isValid = sql.checkCredential(username, password, "admin");
		
		if (isValid)
		{
			return "admin";
		}
		
		isValid = sql.checkCredential(username, password, "teacher");
		
		if (isValid)
		{
			return "teacher";
		}
		
		isValid = sql.checkCredential(username, password, "student");
		
		if (isValid)
		{
			return "student";
		}
		
		return "nonUser";
	}
	
	public void loadHomePage(User currUser)
	{
		mainFrame.setVisible(false);
		homeFrame = new HomeFrame(currUser,listener);
		currentUser = currUser;
	}
	
	
	public void loadManageUsers(User currUser)
	{
		manageUsers = new ManageUsers(listener,sql);
	}
	
	public String getUserName()
	{
		return user_tf.getText();
	}
	
	public String getPassWord()
	{
		return pass_tf.getText();
	}
	
	public void exitApp()
	{
		mainFrame.dispatchEvent(new WindowEvent(mainFrame,WindowEvent.WINDOW_CLOSING));
	}
	
	public boolean isTextFieldEmpty()
	{
		return !manageUsers.checkTextFields();
	}

	public String[] getNewUserInfo()
	{
		return manageUsers.getNewUserInfo();
	}


	public String[] getUserToDelete() {
		
		return manageUsers.getUserData();
	}


	public void loadManageClassrooms() {
		manageClassroom = new ManageClassroom(listener,sql);
	}


	public void updateUsers() {
		int selection = manageClassroom.thereIsSelection();
		if(selection != 0)
		{
			String[][] updateInfo = manageClassroom.getUpdateInfo(selection);
			sql.updateUserInfo(updateInfo);
			resetFrame("ManageClassrooms");
		}
		
	}
	
	public void resetFrame(String frameType)
	{
		switch (frameType)
		{
		case "ManageUsers":
			manageUsers.dispatchEvent(new WindowEvent(manageUsers,WindowEvent.WINDOW_CLOSING));
			loadManageUsers(currentUser);
			break;
		case "ManageClassrooms":
			manageUsers.dispatchEvent(new WindowEvent(manageUsers,WindowEvent.WINDOW_CLOSING));
			loadManageUsers(currentUser);
			break;
		}
	}


	public void loadSubject(String subj) {
		selectedSubject = new Subject(listener,sql,subj,currentUser);
	}


	public void searchFile(String type) {
		selectedSubject.promptFile(type);
	}


	public void uploadFile() {
		selectedSubject.uploadFile();
		selectedSubject.dispatchEvent(new WindowEvent(selectedSubject,WindowEvent.WINDOW_CLOSING));
		
	}


	public void cancelUpload() {
		selectedSubject.cancelUpload();
	}
}
