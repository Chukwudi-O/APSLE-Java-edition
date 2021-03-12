package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileSystemView;

public class Subject extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TheListener listener;
	private SQLConnector sql;
	private User currentUser;
	private JFileChooser upload;
	private JFrame uploadFrame;
	private String currentSubject;
	private ButtonGroup bg;
	private ArrayList<JRadioButton> rbList;

	public Subject(TheListener l,SQLConnector conn,String sub,User currUser)
	{
		listener = l;
		sql = conn;
		currentUser = currUser;
		currentSubject = sub;
		bg = new ButtonGroup();
		rbList = new ArrayList<JRadioButton>();
		
		setTitle("APSLE - "+currentUser.getType()+" - "+sub);
		setPreferredSize(new Dimension(500,500));
        setResizable(false);
        setLocation(500, 250);
		
		switch(sub)
		{
		case "math":
			loadMath();
			break;
		case "english":
			loadEnglish();
			break;
		case "science":
			loadScience();
			break;
		}
		
		pack();
		setVisible(true);
	}

	private void loadScience() {
		JLabel subjTitle = new JLabel("Science");
		JLabel materials = new JLabel("Learning Material:");
		JLabel assignments = new JLabel("Assignments");
		
		if (currentUser.getType().equals("TEACHER"))
		{	
			JButton uploadMaterials = new JButton("UPLOAD MATERIALS");
			JButton uploadAssignments = new JButton("UPLOAD ASSIGNMENTS");
			uploadMaterials.addActionListener(listener);
			uploadAssignments.addActionListener(listener);
			
			ArrayList<String[]> materialInfo = sql.getMaterials("SCIENCE",currentUser.getGradeNum(),currentUser.getClassNum());
			ArrayList<String[]> assignmentInfo = sql.getAssignments("SCIENCE",currentUser.getGradeNum(),currentUser.getClassNum());
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
			panel.add(subjTitle);
			panel.add(Box.createVerticalStrut(20));
			panel.add(materials);
			panel.add(Box.createVerticalStrut(10));
			panel.add(uploadMaterials);
			
			for(int i=0;i<materialInfo.size();++i)
			{
				String text = materialInfo.get(i)[0];
				
				JRadioButton temp_rb = new JRadioButton(text);
				bg.add(temp_rb);
				
				panel.add(Box.createVerticalStrut(10));
				panel.add(temp_rb);
				rbList.add(temp_rb);
			}
			
			panel.add(Box.createVerticalStrut(50));
			panel.add(assignments);
			panel.add(Box.createVerticalStrut(20));
			panel.add(uploadAssignments);
			
			for(int i=0;i<assignmentInfo.size();++i)
			{
				String text = assignmentInfo.get(i)[0];
				
				JRadioButton temp_rb = new JRadioButton(text);
				bg.add(temp_rb);
				
				panel.add(Box.createVerticalStrut(15));
				panel.add(temp_rb);
				rbList.add(temp_rb);
			}
			
			JButton remove = new JButton("REMOVE");
			remove.addActionListener(this);
			
			panel.add(Box.createVerticalStrut(30));
			panel.add(remove);
			getContentPane().add(panel);
		}else
		{
			ArrayList<String[]> materialInfo = sql.getMaterials("SCIENCE",currentUser.getGradeNum(),currentUser.getClassNum());
			ArrayList<String[]> assignmentInfo = sql.getAssignments("SCIENCE",currentUser.getGradeNum(),currentUser.getClassNum());
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
			panel.add(subjTitle);
			panel.add(Box.createVerticalStrut(20));
			panel.add(materials);
			
			for(int i=0;i<materialInfo.size();++i)
			{
				String text = materialInfo.get(i)[0];
				
				JRadioButton temp_rb = new JRadioButton(text);
				bg.add(temp_rb);
				
				panel.add(Box.createVerticalStrut(10));
				panel.add(temp_rb);
				rbList.add(temp_rb);
			}
			
			panel.add(Box.createVerticalStrut(50));
			panel.add(assignments);
			
			for(int i=0;i<assignmentInfo.size();++i)
			{
				String text = assignmentInfo.get(i)[0];
				
				JRadioButton temp_rb = new JRadioButton(text);
				bg.add(temp_rb);
				
				panel.add(Box.createVerticalStrut(15));
				panel.add(temp_rb);
				rbList.add(temp_rb);
			}
			
			JButton download = new JButton("DOWNLOAD");
			download.addActionListener(this);
			
			panel.add(Box.createVerticalStrut(30));
			panel.add(download);
			getContentPane().add(panel);
		}
	}

	private void loadEnglish() {
		JLabel subjTitle = new JLabel("English");
		JLabel materials = new JLabel("Learning Material:");
		JLabel assignments = new JLabel("Assignments");
		
		if (currentUser.getType().equals("TEACHER"))
		{	
			JButton uploadMaterials = new JButton("UPLOAD MATERIALS");
			JButton uploadAssignments = new JButton("UPLOAD ASSIGNMENTS");
			uploadMaterials.addActionListener(listener);
			uploadAssignments.addActionListener(listener);
			
			ArrayList<String[]> materialInfo = sql.getMaterials("ENGLISH",currentUser.getGradeNum(),currentUser.getClassNum());
			ArrayList<String[]> assignmentInfo = sql.getAssignments("ENGLISH",currentUser.getGradeNum(),currentUser.getClassNum());
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
			panel.add(subjTitle);
			panel.add(Box.createVerticalStrut(20));
			panel.add(materials);
			panel.add(Box.createVerticalStrut(10));
			panel.add(uploadMaterials);
			
			for(int i=0;i<materialInfo.size();++i)
			{
				String text = materialInfo.get(i)[0];
				
				JRadioButton temp_rb = new JRadioButton(text);
				bg.add(temp_rb);
				
				panel.add(Box.createVerticalStrut(10));
				panel.add(temp_rb);
				rbList.add(temp_rb);
			}
			
			panel.add(Box.createVerticalStrut(50));
			panel.add(assignments);
			panel.add(Box.createVerticalStrut(20));
			panel.add(uploadAssignments);
			
			for(int i=0;i<assignmentInfo.size();++i)
			{
				String text = assignmentInfo.get(i)[0];
				
				JRadioButton temp_rb = new JRadioButton(text);
				bg.add(temp_rb);
				
				panel.add(Box.createVerticalStrut(15));
				panel.add(temp_rb);
				rbList.add(temp_rb);
			}
			
			JButton remove = new JButton("REMOVE");
			remove.addActionListener(this);
			
			panel.add(Box.createVerticalStrut(30));
			panel.add(remove);
			getContentPane().add(panel);
		}else
		{
			ArrayList<String[]> materialInfo = sql.getMaterials("ENGLISH",currentUser.getGradeNum(),currentUser.getClassNum());
			ArrayList<String[]> assignmentInfo = sql.getAssignments("ENGLISH",currentUser.getGradeNum(),currentUser.getClassNum());
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
			panel.add(subjTitle);
			panel.add(Box.createVerticalStrut(20));
			panel.add(materials);
			
			for(int i=0;i<materialInfo.size();++i)
			{
				String text = materialInfo.get(i)[0];
				
				JRadioButton temp_rb = new JRadioButton(text);
				bg.add(temp_rb);
				
				panel.add(Box.createVerticalStrut(10));
				panel.add(temp_rb);
				rbList.add(temp_rb);
			}
			
			panel.add(Box.createVerticalStrut(50));
			panel.add(assignments);
			
			for(int i=0;i<assignmentInfo.size();++i)
			{
				String text = assignmentInfo.get(i)[0];
				
				JRadioButton temp_rb = new JRadioButton(text);
				bg.add(temp_rb);
				
				panel.add(Box.createVerticalStrut(15));
				panel.add(temp_rb);
				rbList.add(temp_rb);
			}
			
			JButton download = new JButton("DOWNLOAD");
			download.addActionListener(this);
			
			panel.add(Box.createVerticalStrut(30));
			panel.add(download);
			getContentPane().add(panel);
		}
	}

	private void loadMath() {
		JLabel subjTitle = new JLabel("Math");
		JLabel materials = new JLabel("Learning Material:");
		JLabel assignments = new JLabel("Assignments");
		
		
		if (currentUser.getType().equals("TEACHER"))
		{	
			JButton uploadMaterials = new JButton("UPLOAD MATERIALS");
			JButton uploadAssignments = new JButton("UPLOAD ASSIGNMENTS");
			uploadMaterials.addActionListener(listener);
			uploadAssignments.addActionListener(listener);
			
			ArrayList<String[]> materialInfo = sql.getMaterials("MATH",currentUser.getGradeNum(),currentUser.getClassNum());
			ArrayList<String[]> assignmentInfo = sql.getAssignments("MATH",currentUser.getGradeNum(),currentUser.getClassNum());
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
			panel.add(subjTitle);
			panel.add(Box.createVerticalStrut(20));
			panel.add(materials);
			panel.add(Box.createVerticalStrut(10));
			panel.add(uploadMaterials);
			
			for(int i=0;i<materialInfo.size();++i)
			{
				String text = materialInfo.get(i)[0];
				
				JRadioButton temp_rb = new JRadioButton(text);
				bg.add(temp_rb);
				
				panel.add(Box.createVerticalStrut(10));
				panel.add(temp_rb);
				rbList.add(temp_rb);
			}
			
			panel.add(Box.createVerticalStrut(50));
			panel.add(assignments);
			panel.add(Box.createVerticalStrut(20));
			panel.add(uploadAssignments);
			
			for(int i=0;i<assignmentInfo.size();++i)
			{
				String text = assignmentInfo.get(i)[0];
				
				JRadioButton temp_rb = new JRadioButton(text);
				bg.add(temp_rb);
				
				panel.add(Box.createVerticalStrut(15));
				panel.add(temp_rb);
				rbList.add(temp_rb);
			}
			
			JButton remove = new JButton("REMOVE");
			remove.addActionListener(this);
			
			panel.add(Box.createVerticalStrut(30));
			panel.add(remove);
			getContentPane().add(panel);
		}else
		{
			ArrayList<String[]> materialInfo = sql.getMaterials("MATH",currentUser.getGradeNum(),currentUser.getClassNum());
			ArrayList<String[]> assignmentInfo = sql.getAssignments("MATH",currentUser.getGradeNum(),currentUser.getClassNum());
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
			panel.add(subjTitle);
			panel.add(Box.createVerticalStrut(20));
			panel.add(materials);
			
			for(int i=0;i<materialInfo.size();++i)
			{
				String text = materialInfo.get(i)[0];
				
				JRadioButton temp_rb = new JRadioButton(text);
				bg.add(temp_rb);
				
				panel.add(Box.createVerticalStrut(10));
				panel.add(temp_rb);
				rbList.add(temp_rb);
			}
			
			panel.add(Box.createVerticalStrut(50));
			panel.add(assignments);
			
			for(int i=0;i<assignmentInfo.size();++i)
			{
				String text = assignmentInfo.get(i)[0];
				
				JRadioButton temp_rb = new JRadioButton(text);
				bg.add(temp_rb);
				
				panel.add(Box.createVerticalStrut(15));
				panel.add(temp_rb);
				rbList.add(temp_rb);
			}
			
			JButton download = new JButton("DOWNLOAD");
			download.addActionListener(this);
			
			panel.add(Box.createVerticalStrut(30));
			panel.add(download);
			getContentPane().add(panel);
		}
	}

	public void promptFile(String type) {
		uploadFrame = new JFrame(type);
		uploadFrame.setPreferredSize(new Dimension(500,500));
		uploadFrame.setResizable(false);
		uploadFrame.setLocation(500, 250);
		upload = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		upload.addActionListener(listener);
		
		uploadFrame.getContentPane().add(upload);
		uploadFrame.pack();
		uploadFrame.setVisible(true);
		
	}

	public void uploadFile() {
		File file = upload.getSelectedFile();
		String fileFullName = file.getName();
		int index = fileFullName.indexOf(".");
		String fileName =  fileFullName.substring(0,index);
		String fileExt = fileFullName.substring(index+1);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();  
		String dateUploaded = dtf.format(now);
		String[] uploadInfo = {fileName,fileExt,dateUploaded,currentSubject.toUpperCase(),String.valueOf(currentUser.getGradeNum()),
								String.valueOf(currentUser.getClassNum()),uploadFrame.getTitle(),currentUser.getUsername()};
		
		sql.teacherUpload(uploadInfo);
		uploadFrame.dispatchEvent(new WindowEvent(uploadFrame,WindowEvent.WINDOW_CLOSING));
	}

	public void cancelUpload() {
		uploadFrame.dispatchEvent(new WindowEvent(uploadFrame,WindowEvent.WINDOW_CLOSING));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("REMOVE"))
		{
			for (int i=0;i<rbList.size();++i)
			{
				if (rbList.get(i).isSelected())
				{
					String[] lst = {currentSubject,String.valueOf(currentUser.getGradeNum()),
							String.valueOf(currentUser.getClassNum()),rbList.get(i).getText()};
					sql.removeUpload(lst);
					new Subject(listener,sql,currentSubject,currentUser);
					this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
				}
			}
		}else if(e.getActionCommand().equals("DOWNLOAD"))
		{
			for (int i=0;i<rbList.size();++i)
			{
				if (rbList.get(i).isSelected())
				{
					String[] lst = {currentSubject,String.valueOf(currentUser.getGradeNum()),
							String.valueOf(currentUser.getClassNum()),rbList.get(i).getText()};
					System.out.println(lst[3]+" was downloaded.");
					new Subject(listener,sql,currentSubject,currentUser);
					this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING));
				}
			}
		}
	}
	
}
