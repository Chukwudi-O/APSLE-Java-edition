package ui;
//import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UIManager {
	
	private JFrame mainFrame;
	private JFrame homeFrame;
	private JFrame manageUsers;
	private SQLConnector sql;
	private JTextField user_tf,pass_tf;
	private JButton login_b;
	private TheListener listener;
	private AddUser addUser_p;
	private DeleteUser deleteUser_p;
	private User currentUser;
	 
	
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
		//mainFrame.dispatchEvent(new WindowEvent(mainFrame,WindowEvent.WINDOW_CLOSING));
		currentUser = currUser;
		mainFrame.setVisible(false);
		homeFrame = new JFrame("APSLE - "+currUser.getType()+" Home");
		homeFrame.setPreferredSize(new Dimension(500,500));
        homeFrame.setResizable(false);
        homeFrame.setLocation(500, 250);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        if(currUser.getType().equals("ADMIN"))
        {
        	
        	JLabel title_l = new JLabel("APSLE ADMIN HOME");
        	JLabel welcome_l = new JLabel("Welcome "+currUser.getUsername()+", what would you like to do today?");
        	JButton manageUsers_b = new JButton("MANAGE USERS");
        	JButton manageClassrooms_b = new JButton("MANAGE CLASSROOMS");
        	JButton logout_b = new JButton("EXIT");
        	
        	manageUsers_b.addActionListener(listener);
        	manageClassrooms_b.addActionListener(listener);
        	logout_b.addActionListener(listener);
        	
        	
        	JPanel homePanel = new JPanel();
        	homePanel.setLayout(new BoxLayout(homePanel,BoxLayout.PAGE_AXIS));
        	homePanel.add(Box.createRigidArea(new Dimension(0,50)));
        	homePanel.add(title_l);
        	homePanel.add(Box.createRigidArea(new Dimension(0,50)));
        	homePanel.add(welcome_l);
        	homePanel.add(Box.createRigidArea(new Dimension(0,50)));
        	homePanel.add(manageUsers_b);
        	homePanel.add(Box.createRigidArea(new Dimension(0,50)));
        	homePanel.add(manageClassrooms_b);
        	homePanel.add(Box.createRigidArea(new Dimension(0,50)));
        	homePanel.add(logout_b);
        	homePanel.add(Box.createRigidArea(new Dimension(0,50)));
        	
        	JPanel outerPanel = new JPanel();
        	outerPanel.setLayout(new BoxLayout(outerPanel,BoxLayout.LINE_AXIS));
        	outerPanel.add(Box.createRigidArea(new Dimension(100,0)));
        	outerPanel.add(homePanel);
        	outerPanel.add(Box.createRigidArea(new Dimension(100,0)));
        	
        	homeFrame.getContentPane().add(outerPanel);
        	
        } else if (currUser.getType().equals("TEACHER"))
        {
        	System.out.println("Teacher Login successful");
        }else
        {
        	System.out.println("Student Login successful");
        }
        
        homeFrame.pack();
        homeFrame.setVisible(true);
	}
	
	public void loadManageUsers(User currUser)
	{
		manageUsers = new JFrame("APSLE - Manage Users");
		JPanel panel = new JPanel();
		addUser_p = new AddUser(listener);
		deleteUser_p = new DeleteUser(listener,sql);
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(addUser_p);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(deleteUser_p);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		
		manageUsers.setPreferredSize(new Dimension(600,600));
        //manageUsers.setResizable(false);
        manageUsers.setLocation(500, 100);   
        manageUsers.getContentPane().add(panel);
        manageUsers.pack();
        manageUsers.setVisible(true);
        
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
		return !addUser_p.checkTextFields();
	}

	public String[] getNewUserInfo()
	{
		return addUser_p.getNewUserInfo();
	}


	public String[] getUserToDelete() {
		
		return deleteUser_p.getUserData();
	}


	public void resetManageUsers() {
		
		manageUsers.dispatchEvent(new WindowEvent(manageUsers,WindowEvent.WINDOW_CLOSING));
		loadManageUsers(currentUser);
	}
}
