package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DeleteUser extends JPanel {
	
	

	public DeleteUser(int lengthOfList) {
		
		setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 200));

        JTable mainTable = new JTable(20, 3);
        
        mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollpane = new JScrollPane(mainTable);
        add(scrollpane, BorderLayout.CENTER);
	}

}
