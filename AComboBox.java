
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
/*
 * Ανέστης Καϊμακαμίδης ΑΕΜ: 9627 τηλέφωνο: 6980613638 webmail: anestisk@ece.auth.gr
 * Κωνσταντίνος Καλαμάρας ΑΕΜ:9716 τηλέφωνο: 6974965296 webmail: kkalamar@ece.auth.gr
 */

public class AComboBox extends JComboBox<String> implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String [] options;
	String currentname;
	//constructor
	AComboBox(String [] options){
		this.options = options;
		this.addItem(options[0]);
		this.addItem(options[1]);
		this.addItem(options[2]);
		this.addItem(options[3]);
		this.addActionListener(this);
	}
	//event
	public void actionPerformed(ActionEvent e) {
		@SuppressWarnings("unchecked")
		JComboBox<String> cb = (JComboBox<String>)e.getSource();
	    currentname = (String)cb.getSelectedItem();
	    
	    
	}
	//getters/setters
	public String getCurrentname() {
		return currentname;
	}
	public void setCurrentname(String currentname) {
		this.currentname = currentname;
	}
	
}
