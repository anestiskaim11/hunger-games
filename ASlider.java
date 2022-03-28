import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/*
 * Ανέστης Καϊμακαμίδης ΑΕΜ: 9627 τηλέφωνο: 6980613638 webmail: anestisk@ece.auth.gr
 * Κωνσταντίνος Καλαμάρας ΑΕΜ:9716 τηλέφωνο: 6974965296 webmail: kkalamar@ece.auth.gr
 */
public class ASlider extends JSlider implements ChangeListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int value;
	//constructor
	ASlider(){
		super(ASlider.HORIZONTAL, 0, 20, 0);
		this.setMajorTickSpacing(20);
		value = 0;
	}
	//getters/setters
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	//event
	public void stateChanged(ChangeEvent e) {
		this.value = this.getValue();
	}
	

}
