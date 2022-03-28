import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
 * Ανέστης Καϊμακαμίδης ΑΕΜ: 9627 τηλέφωνο: 6980613638 webmail: anestisk@ece.auth.gr
 * Κωνσταντίνος Καλαμάρας ΑΕΜ:9716 τηλέφωνο: 6974965296 webmail: kkalamar@ece.auth.gr
 */

public class TilePanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int []TileId;
	BoardPanel bp;
	BufferedImage sword, pistol, bow, animal, rope, food, playerA, playerB;
	
	//Constructor
	/*
	 * Constructs a tile taking as arguments its position (x,y), the Board Panel in which it is located, the Board that
	 * contains information about the objects of the game and the two players of the game
	 */
	TilePanel(int TileIdx, int TileIdy, BoardPanel bp, Board b, Player p1, Player p2) throws IOException{
		super(new GridBagLayout());
		TileId = new int[2];
		this.TileId[0] = TileIdx;
		this.TileId[1] = TileIdy;
		this.bp = bp;
		//create grid by coloring borders for each tile
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(20,20));
		//load images
		sword = ImageIO.read(ClassLoader.getSystemResource("sword.png"));
		pistol = ImageIO.read(ClassLoader.getSystemResource("pistol.png"));
		bow = ImageIO.read(ClassLoader.getSystemResource("bow.png"));
		food = ImageIO.read(ClassLoader.getSystemResource("food.png"));
		animal = ImageIO.read(ClassLoader.getSystemResource("animal.png"));
		rope = ImageIO.read(ClassLoader.getSystemResource("rope.png"));
		playerA = ImageIO.read(ClassLoader.getSystemResource("PlayerA.png"));
		playerB = ImageIO.read(ClassLoader.getSystemResource("PlayerB.png"));
		//color areas
		if(TileId[0] <= b.getWeaponAreaLimits()[2][0] && TileId[0] >= b.getWeaponAreaLimits()[0][0] && TileId[1] >= b.getWeaponAreaLimits()[0][1] && TileId[1] <= b.getWeaponAreaLimits()[2][1])
			this.setBackground(Color.WHITE);
		else if(TileId[0] <= b.getFoodAreaLimits()[2][0] && TileId[0] >= b.getFoodAreaLimits()[0][0] && TileId[1] >= b.getFoodAreaLimits()[0][1] && TileId[1] <= b.getFoodAreaLimits()[2][1])
			this.setBackground(Color.decode("#9E9E9E"));
		else if(TileId[0] <= b.getTrapAreaLimits()[2][0] && TileId[0] >= b.getTrapAreaLimits()[0][0] && TileId[1] >= b.getTrapAreaLimits()[0][1] && TileId[1] <= b.getTrapAreaLimits()[2][1])
			this.setBackground(Color.decode("#83F2F6"));
		else this.setBackground(Color.decode("#EEDADA"));
		//for each tile load the correct image
		//food representation
		for(int i = 0; i < b.getF(); i++) {
			if(TileId[0] == b.getFood()[i].getX() && TileId[1] == b.getFood()[i].getY()) {
				this.add(new JLabel(new ImageIcon(food)));
				JLabel points = new JLabel("+" + b.getFood()[i].getPoints());
				points.setFont(points.getFont().deriveFont(5.0f));
				this.add(points);
			}
		}
		//weapon representation
		for(int i = 0; i < b.getW(); i++) {
			if(TileId[0] == b.getWeapons()[i].getX() && TileId[1] == b.getWeapons()[i].getY() && b.getWeapons()[i].getType() == "Sword") {
				this.add(new JLabel(new ImageIcon(sword)));
			}
			else if(TileId[0] == b.getWeapons()[i].getX() && TileId[1] == b.getWeapons()[i].getY() && b.getWeapons()[i].getType() == "Pistol") {
				this.add(new JLabel(new ImageIcon(pistol)));
			}
			else if(TileId[0] == b.getWeapons()[i].getX() && TileId[1] == b.getWeapons()[i].getY() && b.getWeapons()[i].getType() == "Bow") {
				this.add(new JLabel(new ImageIcon(bow)));
			}
		}
		//trap representation
		for(int i = 0; i < b.getT(); i++) {
			if(TileId[0] == b.getTraps()[i].getX() && TileId[1] == b.getTraps()[i].getY() && b.getTraps()[i].getType() == "Rope") {
				this.add(new JLabel(new ImageIcon(rope)));
				JLabel points = new JLabel("-" + b.getFood()[i].getPoints());
				points.setFont(points.getFont().deriveFont(5.0f));
				this.add(points);
			}
			else if(TileId[0] == b.getTraps()[i].getX() && TileId[1] == b.getTraps()[i].getY() && b.getTraps()[i].getType() == "Animal") {
				this.add(new JLabel(new ImageIcon(animal)));
				JLabel points = new JLabel("-" + b.getFood()[i].getPoints());
				points.setFont(points.getFont().deriveFont(5.0f));
				this.add(points);
			}
		}
		//player representation
		if(TileId[0] == p1.getX() && TileId[1] == p1.getY()) this.add(new JLabel(new ImageIcon(playerA)));
		if(TileId[0] == p2.getX() && TileId[1] == p2.getY()) this.add(new JLabel(new ImageIcon(playerB)));
		
		
	}

}
