package ui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TheListener listener;
	private User currentUser;

	public HomeFrame(User currUser,TheListener l)
	{
		listener = l;
		currentUser = currUser;
		
		setTitle("APSLE - "+currUser.getType()+" Home");
		setPreferredSize(new Dimension(500,500));
        setResizable(false);
        setLocation(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		switch(currUser.getType())
		{
		case "ADMIN":
			loadAdminHome();
			break;
		case "TEACHER":
			loadTeacherHome();
			break;
		case "STUDENT":
			loadStudentHome();
			break;
		}
		
		pack();
		setVisible(true);
	}
	
	private void loadStudentHome() {
		JLabel title_l = new JLabel("Student HOME");
		JLabel welcome_l = new JLabel("Welcome "+currentUser.getUsername()+", click the subject you would like to interact with.");
		JLabel classroom_l = new JLabel("You are in Grade "+String.valueOf(currentUser.getGradeNum())+" Class "+String.valueOf(currentUser.getClassNum()));
		
		JButton math_b = new JButton("MATH");
		JButton english_b = new JButton("ENGLISH");
		JButton science_b = new JButton("SCIENCE");
		
		math_b.addActionListener(listener);
		english_b.addActionListener(listener);
		science_b.addActionListener(listener);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		
		mainPanel.add(title_l);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(welcome_l);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(classroom_l);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(math_b);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(english_b);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(science_b);
		
		add(mainPanel);
	}

	private void loadTeacherHome() {
		JLabel title_l = new JLabel("TEACHER HOME");
		JLabel welcome_l = new JLabel("Welcome "+currentUser.getUsername()+", click the subject you would like to manage.");
		JLabel classroom_l = new JLabel("You are in Grade "+String.valueOf(currentUser.getGradeNum())+" Class "+String.valueOf(currentUser.getClassNum()));
		
		JButton math_b = new JButton("MATH");
		JButton english_b = new JButton("ENGLISH");
		JButton science_b = new JButton("SCIENCE");
		
		math_b.addActionListener(listener);
		english_b.addActionListener(listener);
		science_b.addActionListener(listener);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		
		mainPanel.add(title_l);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(welcome_l);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(classroom_l);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(math_b);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(english_b);
		mainPanel.add(Box.createVerticalStrut(20));
		mainPanel.add(science_b);
		
		add(mainPanel);
	}

	private void loadAdminHome()
	{
		JLabel title_l = new JLabel("ADMIN HOME");
		JLabel welcome_l = new JLabel("Welcome "+currentUser.getUsername()+", what would you like to do today?");
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
    	
    	add(outerPanel);
	}
}

