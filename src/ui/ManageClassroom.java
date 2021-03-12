package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ManageClassroom extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel,row1_p,row2_p,row3_p,row4_p,row5_p,row6_p,allTables_p;
	private JLabel title_l, prompt1_l,prompt2_l,password_l,gradeNum_l,classNum_l;
	private JLabel g1_l,g2_l,g3_l,g4_l,g5_l,g6_l;
	private JTable grade1_t,grade2_t,grade3_t,grade4_t,grade5_t,grade6_t;
	private JScrollPane grade1_sp,grade2_sp,grade3_sp,grade4_sp,grade5_sp,grade6_sp;
	private JTextField password_tf,gradeNum_tf,classNum_tf;
	private JButton update_b;
	private SQLConnector sql;
	private TheListener listener;
	
	public ManageClassroom(TheListener listener,SQLConnector sql)
	{
		this.sql = sql;
		this.listener = listener;
		initiateFrame();
		initiateAttributes();
		adjustElements();
		placeElements();
		
		
		getContentPane().add(mainPanel);
		
		pack();
		setVisible(true);
	}
	
	private void initiateFrame()
	{
		setTitle("APSLE - Manage Classrooms");
		setPreferredSize(new Dimension(650,600));
        setResizable(false);
        setLocation(400, 100);
	}
	
	private void initiateAttributes()
	{
		mainPanel = new JPanel();
		allTables_p = new JPanel();
		row1_p = new JPanel();
		row2_p = new JPanel();
		row3_p = new JPanel();
		row4_p = new JPanel();
		row5_p = new JPanel();
		row6_p = new JPanel();
		
		title_l = new JLabel("Manage Classrooms");
		prompt1_l = new JLabel("Click the row of the user you want to edit then enter the new");
		prompt2_l = new JLabel("info for that user below. Leave blank to keep existing info.");
		password_l = new JLabel("New Password:");
		gradeNum_l = new JLabel("New Grade Number:");
		classNum_l = new JLabel("New Class Number:");
		g1_l = new JLabel("Grade 1");
		g2_l = new JLabel("Grade 2");
		g3_l = new JLabel("Grade 3");
		g4_l = new JLabel("Grade 4");
		g5_l = new JLabel("Grade 5");
		g6_l = new JLabel("Grade 6");
		
		password_tf = new JTextField();
		gradeNum_tf = new JTextField();
		classNum_tf = new JTextField();
		
		update_b = new JButton("UPDATE");
		
		initialiseTables();
	}
	
	private void initialiseTables()
	{
		String[] columns = {"Username","User Type","Class Number"};
		String[][] userData = sql.getUserData();
		int g1=0;
		int g2=0;
		int g3=0;
		int g4=0;
		int g5=0;
		int g6=0;
		
		for (int i=0;i<userData.length;++i)
		{
			switch(userData[i][2])
			{
			case "1":
				g1++;
				break;
			case "2":
				g2++;
				break;
			case "3":
				g3++;
				break;
			case "4":
				g4++;
				break;
			case "5":
				g5++;
				break;
			case "6":
				g6++;
				break;
			}
		}
		
		String[][] g1Data = new String[g1][3];
		String[][] g2Data = new String[g2][3];
		String[][] g3Data = new String[g3][3];
		String[][] g4Data = new String[g4][3];
		String[][] g5Data = new String[g5][3];
		String[][] g6Data = new String[g6][3];
		
		g1=0;
		g2=0;
		g3=0;
		g4=0;
		g5=0;
		g6=0;
		
		for (int i=0;i<userData.length;++i)
		{
			switch(userData[i][2])
			{
			case "1":
				g1Data[g1][0] = userData[i][0];
				g1Data[g1][1] = userData[i][1];
				g1Data[g1][2] = userData[i][3];
				g1++;
				break;
			case "2":
				g2Data[g2][0] = userData[i][0];
				g2Data[g2][1] = userData[i][1];
				g2Data[g2][2] = userData[i][3];
				g2++;
				break;
			case "3":
				g3Data[g3][0] = userData[i][0];
				g3Data[g3][1] = userData[i][1];
				g3Data[g3][2] = userData[i][3];
				g3++;
				break;
			case "4":
				g4Data[g4][0] = userData[i][0];
				g4Data[g4][1] = userData[i][1];
				g4Data[g4][2] = userData[i][3];
				g4++;
				break;
			case "5":
				g5Data[g5][0] = userData[i][0];
				g5Data[g5][1] = userData[i][1];
				g5Data[g5][2] = userData[i][3];
				g5++;
				break;
			case "6":
				g6Data[g6][0] = userData[i][0];
				g6Data[g6][1] = userData[i][1];
				g6Data[g6][2] = userData[i][3];
				g6++;
				break;
			}
		}
		
		grade1_t = new JTable(new DefaultTableModel(g1Data,columns));
		grade2_t = new JTable(new DefaultTableModel(g2Data,columns));
		grade3_t = new JTable(new DefaultTableModel(g3Data,columns));
		grade4_t = new JTable(new DefaultTableModel(g4Data,columns));
		grade5_t = new JTable(new DefaultTableModel(g5Data,columns));
		grade6_t = new JTable(new DefaultTableModel(g6Data,columns));
		
		grade1_sp = new JScrollPane(grade1_t);
		grade2_sp = new JScrollPane(grade2_t);
		grade3_sp = new JScrollPane(grade3_t);
		grade4_sp = new JScrollPane(grade4_t);
		grade5_sp = new JScrollPane(grade5_t);
		grade6_sp = new JScrollPane(grade6_t);
	}
	
	private void placeElements()
	{
		row1_p.add(password_l);
		row1_p.add(Box.createRigidArea(new Dimension(100,0)));
		row1_p.add(gradeNum_l);
		row1_p.add(Box.createRigidArea(new Dimension(100,0)));
		row1_p.add(classNum_l);
		
		row2_p.add(password_tf);
		row2_p.add(Box.createRigidArea(new Dimension(10,0)));
		row2_p.add(gradeNum_tf);
		row2_p.add(Box.createRigidArea(new Dimension(10,0)));
		row2_p.add(classNum_tf);
		
		row3_p.add(g1_l);
		row3_p.add(Box.createRigidArea(new Dimension(150,0)));
		row3_p.add(g2_l);
		row3_p.add(Box.createRigidArea(new Dimension(150,0)));
		row3_p.add(g3_l);
		
		row4_p.add(grade1_sp);
		row4_p.add(grade2_sp);
		row4_p.add(grade3_sp);
		
		row5_p.add(g4_l);
		row5_p.add(Box.createRigidArea(new Dimension(150,0)));
		row5_p.add(g5_l);
		row5_p.add(Box.createRigidArea(new Dimension(150,0)));
		row5_p.add(g6_l);
		
		row6_p.add(grade4_sp);
		row6_p.add(grade5_sp);
		row6_p.add(grade6_sp);
		
		allTables_p.add(row3_p);
		allTables_p.add(row4_p);
		allTables_p.add(row5_p);
		allTables_p.add(row6_p);
		
		mainPanel.add(title_l);
		mainPanel.add(Box.createRigidArea(new Dimension(0,50)));
		mainPanel.add(prompt1_l);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
		mainPanel.add(prompt2_l);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
		mainPanel.add(row1_p);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
		mainPanel.add(row2_p);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
		mainPanel.add(update_b);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
		mainPanel.add(allTables_p);
		
		
		
	}
	
	private void adjustElements()
	{
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		allTables_p.setLayout(new BoxLayout(allTables_p,BoxLayout.PAGE_AXIS));
		row1_p.setLayout(new BoxLayout(row1_p,BoxLayout.LINE_AXIS));
		row2_p.setLayout(new BoxLayout(row2_p,BoxLayout.LINE_AXIS));
		row3_p.setLayout(new BoxLayout(row3_p,BoxLayout.LINE_AXIS));
		row4_p.setLayout(new BoxLayout(row4_p,BoxLayout.LINE_AXIS));
		row5_p.setLayout(new BoxLayout(row5_p,BoxLayout.LINE_AXIS));
		row6_p.setLayout(new BoxLayout(row6_p,BoxLayout.LINE_AXIS));
		
		update_b.addActionListener(listener);
	}

	public int thereIsSelection() {
		// TODO Auto-generated method stub
		int g1_sel = grade1_t.getSelectedRow();
		int g2_sel = grade2_t.getSelectedRow();
		int g3_sel = grade3_t.getSelectedRow();
		int g4_sel = grade4_t.getSelectedRow();
		int g5_sel = grade5_t.getSelectedRow();
		int g6_sel = grade6_t.getSelectedRow();
		
		if(g1_sel != -1)
		{
			return 1;
		}
		if(g2_sel != -1)
		{
			return 2;
		}
		if(g3_sel != -1)
		{
			return 3;
		}
		if(g4_sel != -1)
		{
			return 4;
		}
		if(g5_sel != -1)
		{
			return 5;
		}
		if(g6_sel != -1)
		{
			return 6;
		}
		return 0;
	}

	public String[][] getUpdateInfo(int gNum) {
		String[][] info = new String[2][3];
		int rowIndex;
		switch(gNum)
		{
		case 1:
			rowIndex = grade1_t.getSelectedRow(); 
			info[0][0] = grade1_t.getModel().getValueAt(rowIndex, 0).toString();
			info[0][1] = grade1_t.getModel().getValueAt(rowIndex, 1).toString();
			info[0][2] = grade1_t.getModel().getValueAt(rowIndex, 2).toString();
			info[1][0] = password_tf.getText();
			info[1][1] = gradeNum_tf.getText();
			info[1][2] = classNum_tf.getText();
			break;
		case 2:
			rowIndex = grade2_t.getSelectedRow(); 
			info[0][0] = grade2_t.getModel().getValueAt(rowIndex, 0).toString();
			info[0][1] = grade2_t.getModel().getValueAt(rowIndex, 1).toString();
			info[0][2] = grade2_t.getModel().getValueAt(rowIndex, 2).toString();
			info[1][0] = password_tf.getText();
			info[1][1] = gradeNum_tf.getText();
			info[1][2] = classNum_tf.getText();
			break;
		case 3:
			rowIndex = grade3_t.getSelectedRow(); 
			info[0][0] = grade3_t.getModel().getValueAt(rowIndex, 0).toString();
			info[0][1] = grade3_t.getModel().getValueAt(rowIndex, 1).toString();
			info[0][2] = grade3_t.getModel().getValueAt(rowIndex, 2).toString();
			info[1][0] = password_tf.getText();
			info[1][1] = gradeNum_tf.getText();
			info[1][2] = classNum_tf.getText();
			break;
		case 4:
			rowIndex = grade4_t.getSelectedRow(); 
			info[0][0] = grade4_t.getModel().getValueAt(rowIndex, 0).toString();
			info[0][1] = grade4_t.getModel().getValueAt(rowIndex, 1).toString();
			info[0][2] = grade4_t.getModel().getValueAt(rowIndex, 2).toString();
			info[1][0] = password_tf.getText();
			info[1][1] = gradeNum_tf.getText();
			info[1][2] = classNum_tf.getText();
			break;
		case 5:
			rowIndex = grade5_t.getSelectedRow(); 
			info[0][0] = grade5_t.getModel().getValueAt(rowIndex, 0).toString();
			info[0][1] = grade5_t.getModel().getValueAt(rowIndex, 1).toString();
			info[0][2] = grade5_t.getModel().getValueAt(rowIndex, 2).toString();
			info[1][0] = password_tf.getText();
			info[1][1] = gradeNum_tf.getText();
			info[1][2] = classNum_tf.getText();
			break;
		case 6:
			rowIndex = grade6_t.getSelectedRow(); 
			info[0][0] = grade6_t.getModel().getValueAt(rowIndex, 0).toString();
			info[0][1] = grade6_t.getModel().getValueAt(rowIndex, 1).toString();
			info[0][2] = grade6_t.getModel().getValueAt(rowIndex, 2).toString();
			info[1][0] = password_tf.getText();
			info[1][1] = gradeNum_tf.getText();
			info[1][2] = classNum_tf.getText();
			break;
		}
		return info;
	}
}