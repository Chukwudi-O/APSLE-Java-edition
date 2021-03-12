package ui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DeleteUser extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel title_l,search_l;
	private JButton delete_b;
	private JTextField search_tf;
	private JScrollPane scrollpane;
	private JTable mainTable;
	private String[][] userData;

	public DeleteUser(TheListener listener, SQLConnector sql) {
		
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(600, 300));

        title_l = new JLabel("Delete User:");
        search_l = new JLabel("Enter the username of the user you want to delete then click DELETE");
        search_tf = new JTextField();
        delete_b = new JButton("DELETE");
        
        delete_b.addActionListener(listener);
        
        String[] columns = {"Username","User Type","Grade Number","Class Number"};
        userData = sql.getUserData();
        DefaultTableModel model = new DefaultTableModel(userData,columns);
        
        mainTable = new JTable(model);
       
        
        search_tf.setPreferredSize(new Dimension(200, 25));
        search_tf.setMaximumSize( search_tf.getPreferredSize() );
        
        add(title_l);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(search_l);
        add(Box.createRigidArea(new Dimension(0,10)));
        
        JPanel subpanel1_p = new JPanel();
        subpanel1_p.setLayout(new BoxLayout(subpanel1_p,BoxLayout.LINE_AXIS));
        subpanel1_p.add(Box.createRigidArea(new Dimension(20,0)));
        subpanel1_p.add(search_tf);
        subpanel1_p.add(Box.createRigidArea(new Dimension(10,0)));
        subpanel1_p.add(delete_b);
        
        scrollpane = new JScrollPane(mainTable);
      
        add(subpanel1_p);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(scrollpane);
	}

	public String[] getUserData() {
		String search = search_tf.getText();
		String[] none = {};
		
		if(search.equals(""))
		{
			return none;
		}
		
		for(int i=0;i<userData.length;++i)
		{
			if (userData[i][0].equals(search))
			{
				String[] data = {search,userData[i][1]};
				return data;
			}
		}
		return none;
	}

	

}
