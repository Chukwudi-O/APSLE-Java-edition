package ui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AddUser extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel title_l = new JLabel("APSLE - Manage Users");
    private JLabel addUser_l = new JLabel("Add new User:");
    private JLabel username_l = new JLabel("Username");
    private JLabel password_l = new JLabel("Password");
    private JLabel classNum_l = new JLabel("Class Number");
    private JLabel gradeNum_l = new JLabel("Grade Number");
    private JTextField username_tf = new JTextField();
    private JTextField password_tf = new JTextField();
    private JTextField classNum_tf = new JTextField();
    private JTextField gradeNum_tf = new JTextField();
    private JRadioButton teacher_rb = new JRadioButton("Teacher");
    private JRadioButton student_rb = new JRadioButton("Student");
    private JButton add_b = new JButton("ADD");
    private ButtonGroup type_bg = new ButtonGroup();
    
    
    public AddUser(TheListener listener)
    {
    	this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
    	this.setPreferredSize(new Dimension(600, 275));
        
    	type_bg.add(teacher_rb);
        type_bg.add(student_rb);
        
        add_b.addActionListener(listener);
         
        this.add(title_l);
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(addUser_l);
        
        JPanel row1_p = new JPanel();
        row1_p.setLayout(new BoxLayout(row1_p,BoxLayout.LINE_AXIS));
        row1_p.add(Box.createRigidArea(new Dimension(50,0)));
        row1_p.add(username_l);
        row1_p.add(Box.createRigidArea(new Dimension(50,0)));
        row1_p.add(password_l);
        row1_p.add(Box.createRigidArea(new Dimension(50,0)));
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row1_p);
        
        JPanel row2_p = new JPanel();
        row2_p.setLayout(new BoxLayout(row2_p,BoxLayout.LINE_AXIS));
        row2_p.add(Box.createRigidArea(new Dimension(50,0)));
        row2_p.add(username_tf);
        row2_p.add(Box.createRigidArea(new Dimension(50,0)));
        row2_p.add(password_tf);
        row2_p.add(Box.createRigidArea(new Dimension(50,0)));
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row2_p);
        
        JPanel row3_p = new JPanel();
        row3_p.setLayout(new BoxLayout(row3_p,BoxLayout.LINE_AXIS));
        row3_p.add(Box.createRigidArea(new Dimension(50,0)));
        row3_p.add(teacher_rb);
        row3_p.add(Box.createRigidArea(new Dimension(50,0)));
        row3_p.add(student_rb);
        row3_p.add(Box.createRigidArea(new Dimension(50,0)));
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row3_p);
        
        JPanel row4_p = new JPanel();
        row4_p.setLayout(new BoxLayout(row4_p,BoxLayout.LINE_AXIS));
        row4_p.add(Box.createRigidArea(new Dimension(50,0)));
        row4_p.add(gradeNum_l);
        row4_p.add(Box.createRigidArea(new Dimension(50,0)));
        row4_p.add(classNum_l);
        row4_p.add(Box.createRigidArea(new Dimension(50,0)));
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row4_p);
        
        JPanel row5_p = new JPanel();
        row5_p.setLayout(new BoxLayout(row5_p,BoxLayout.LINE_AXIS));
        row5_p.add(Box.createRigidArea(new Dimension(50,0)));
        row5_p.add(gradeNum_tf);
        row5_p.add(Box.createRigidArea(new Dimension(50,0)));
        row5_p.add(classNum_tf);
        row5_p.add(Box.createRigidArea(new Dimension(50,0)));
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(row5_p);
        
        this.add(Box.createRigidArea(new Dimension(0,20)));
        this.add(add_b);
        this.add(Box.createRigidArea(new Dimension(0,20)));
    }
    
    
    public String getActiveRB()
    {
    	if (teacher_rb.isSelected())
    	{
    		return "teacher";
    	}else if(student_rb.isSelected())
    	{
    		return "student";
    	}
    	return "none";
    }
    
    public boolean checkTextFields()
    {
    	
    	if(username_tf.getText().equals("") || password_tf.getText().equals(""))
    	{
    		return false;
    	}
    	if(classNum_tf.getText().equals("") || gradeNum_tf.getText().equals(""))
    	{
    		return false;
    	}
    	
    	return true;
    }
    
    public String[] getNewUserInfo()
	{
		String[] newUserInfo = {username_tf.getText(),password_tf.getText(),getActiveRB(),
								gradeNum_tf.getText(),classNum_tf.getText()};
		return newUserInfo;
	}

}
