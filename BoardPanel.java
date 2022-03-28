import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;
/*
 * Ανέστης Καϊμακαμίδης ΑΕΜ: 9627 τηλέφωνο: 6980613638 webmail: anestisk@ece.auth.gr
 * Κωνσταντίνος Καλαμάρας ΑΕΜ:9716 τηλέφωνο: 6974965296 webmail: kkalamar@ece.auth.gr
 */

public class BoardPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TilePanel tiles[][];
	Board b;
	Player[] p;
	//constructor
	BoardPanel(Board b, Player p1, Player p2) throws IOException{
		//grid layout taking dimensions from board class
		super(new GridLayout(b.getN(),b.getM()));
		this.setPreferredSize(new Dimension(500,500));
		this.b = b;
		p = new Player[2];
		this.p[0] = p1;
		this.p[1] = p2;		
		tiles = new TilePanel[b.getN()][b.getM()];
		//create tiles
		for(int i = -(b.getN()/2); i <= b.getN()/2; i++) {
			for(int j = -(b.getM()/2); j <= b.getM()/2; j++) {
				if(i == 0 || j == 0) continue;
				TilePanel tile = new TilePanel(j, i, this, b, p1, p2);
				this.tiles[j + b.getM()/2 - ((j > 0) ? 1 : 0)][i + b.getN()/2 - ((i > 0) ? 1 : 0)] = tile;
				this.add(tile);
			}
		}
		validate();
	}
	

}
