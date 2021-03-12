package ui;

import java.awt.Dimension;

import javax.swing.*;

public class ManageUsers extends JFrame{
	private AddUser addUser_p;
	private DeleteUser deleteUser_p;
	private JPanel panel;

	public ManageUsers(TheListener listener, SQLConnector sql)
	{
		initiateFrame();
		
		panel = new JPanel();
		addUser_p = new AddUser(listener);
		deleteUser_p = new DeleteUser(listener,sql);
		
		panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(addUser_p);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(deleteUser_p);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		
		getContentPane().add(panel);
		pack();
        setVisible(true);
	}
	
	private void initiateFrame()
	{
		setPreferredSize(new Dimension(600,600));
        setResizable(false);
        setLocation(500, 100);
	}
	
	public boolean checkTextFields()
	{
		return addUser_p.checkTextFields();
	}
	
	public String[] getNewUserInfo()
	{
		return addUser_p.getNewUserInfo();
	}
	
	public String[] getUserData()
	{
		return deleteUser_p.getUserData();
	}
}
