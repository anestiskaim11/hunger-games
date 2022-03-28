import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Ανέστης Καϊμακαμίδης ΑΕΜ: 9627 τηλέφωνο: 6980613638 webmail: anestisk@ece.auth.gr
 * Κωνσταντίνος Καλαμάρας ΑΕΜ:9716 τηλέφωνο: 6974965296 webmail: kkalamar@ece.auth.gr
 */
public class Game {

	int round;
	
	// round: game round
	
	// Constructors
	Game(){
		this.round=0;
	}
	
	Game(int round){
		this.round=round;
	}
	
	// Getters/Setters	
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
	//main function
	public static void main(String[] args) throws IOException, InterruptedException{
		//initiate objects
		boolean ok = false;
		int movescoreA = 0, movescoreB = 0, pscoreA = 15, pscoreB = 15;
		Game a = new Game(1);
		Board b = new Board(20, 20, 6, 10, 8);
		JFrame window = new JFrame("Game");
		int [][] sw = {{-2,-2}, {2,-2}, {2,2}, {-2,2}}; //this list must be {{-,-}, {+,-}, {+,+}, {-,+}} in order to work properly
		b.setWeaponAreaLimits(sw);
		int[][] sf = {{-3,-3},{3,-3},{3,3},{-3,3}}; //this list must be {{-,-}, {+,-}, {+,+}, {-,+}} in order to work properly
		b.setFoodAreaLimits(sf);
		int[][] st = {{-4,-4},{4,-4},{4,4},{-4,4}}; //this list must be {{-,-}, {+,-}, {+,+}, {-,+}} in order to work properly
		b.setTrapAreaLimits(st);
		//create JFrame
		window.setVisible(true);
		window.setSize(1100,700);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//panel1 contains the buttons and the game speed slider
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridBagLayout());
		panel1.setSize(100,700);
		//panel2 contains playerA info
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridBagLayout());
		panel2.setSize(700,300);
		//panel3 contains playerB info
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridBagLayout());
		panel3.setSize(700,300);
		//panel4 contains game round info
		JPanel panel4 = new JPanel();
		panel4.setLayout(new GridBagLayout());
		panel4.setSize(100,700);
		//create the two option boxes
		String [] options = { "-", "Random Player", "Heuristic Player" , "MinMax Player"};
		AComboBox box1 = new AComboBox(options);
		box1.setSelectedIndex(0);
		
		AComboBox box2 = new AComboBox(options);
		box2.setSelectedIndex(0);
		//create the objects for panels 1, 2 and 3
		JLabel label1 = new JLabel("Player A");
		JLabel label2 = new JLabel("Move Score = " + movescoreA);
		JLabel label3 = new JLabel("Total Score = " + pscoreA);
		JLabel label4 = new JLabel("Player B");
		JLabel label5 = new JLabel("Move Score = " + movescoreB);
		JLabel label6 = new JLabel("Total Score = " + pscoreB);
		AButton generate_board = new AButton("Generate Board");
		AButton play = new AButton("Play");
		AButton quit = new AButton("Quit");
		ASlider speed = new ASlider();
		JLabel label7 = new JLabel("Game Speed");
		JLabel label8 = new JLabel("Round: " + a.getRound());
		BufferedImage pistolicon = ImageIO.read(ClassLoader.getSystemResource("pistol.png"));
		BufferedImage swordicon = ImageIO.read(ClassLoader.getSystemResource("sword.png"));
		BufferedImage bowicon = ImageIO.read(ClassLoader.getSystemResource("bow.png"));
		BufferedImage PlayerAIcon = ImageIO.read(ClassLoader.getSystemResource("PlayerABig.png"));
		BufferedImage PlayerBIcon = ImageIO.read(ClassLoader.getSystemResource("PlayerBBig.png"));
		JLabel sword1 = new JLabel(new ImageIcon(swordicon));
		JLabel pistol1 = new JLabel(new ImageIcon(pistolicon));
		JLabel bow1 = new JLabel(new ImageIcon(bowicon));
		JLabel sword2 = new JLabel(new ImageIcon(swordicon));
		JLabel pistol2 = new JLabel(new ImageIcon(pistolicon));
		JLabel bow2 = new JLabel(new ImageIcon(bowicon));
		JLabel label9 = new JLabel(new ImageIcon(PlayerAIcon));
		JLabel label10 = new JLabel(new ImageIcon(PlayerBIcon));
		
		//arrange objects on panel 2
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 1;
		c1.gridy = 1;
		panel2.add(label9,c1);
		c1.gridx = 0;
		c1.gridy = 2;
		panel2.add(pistol1,c1);
		c1.gridx = 1;
		c1.gridy = 2;
		panel2.add(sword1,c1);
		c1.gridx = 2;
		c1.gridy = 2;
		panel2.add(bow1,c1);
		c1.gridx = 1;
		c1.gridy = 3;
		panel2.add(label1,c1);
		c1.gridx = 1;
		c1.gridy = 4;
		panel2.add(label2,c1);
		c1.gridx = 1;
		c1.gridy = 5;
		panel2.add(label3,c1);
		c1.gridx = 1;
		c1.gridy = 6;
		panel2.add(box1,c1);
		//arrange objects on panel 3
		GridBagConstraints c2 = new GridBagConstraints();
		c2.gridx = 1;
		c2.gridy = 1;
		panel3.add(label10,c2);
		c2.gridx = 0;
		c2.gridy = 2;
		panel3.add(pistol2,c2);
		c2.gridx = 1;
		c2.gridy = 2;
		panel3.add(sword2,c2);
		c2.gridx = 2;
		c2.gridy = 2;
		panel3.add(bow2,c2);
		c2.gridx = 1;
		c2.gridy = 3;
		panel3.add(label4,c2);
		c2.gridx = 1;
		c2.gridy = 4;
		panel3.add(label5,c2);
		c2.gridx = 1;
		c2.gridy = 5;
		panel3.add(label6,c2);
		c2.gridx = 1;
		c2.gridy = 6;
		panel3.add(box2,c2);
		pistol1.setEnabled(false);
		bow1.setEnabled(false);
		sword1.setEnabled(false);
		pistol2.setEnabled(false);
		bow2.setEnabled(false);
		sword2.setEnabled(false);
		//arrange objects on panel 1
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		panel1.add(label7, c);
		c.gridx = 1;
		c.gridy = 1;
		panel1.add(speed, c);
		c.gridx = 0;
		c.gridy = 2;
		panel1.add(generate_board, c);
		c.gridx = 1;
		c.gridy = 2;
		panel1.add(play, c);
		c.gridx = 2;
		c.gridy = 2;
		panel1.add(quit, c);
		//object for panel 4
		panel4.add(label8);
		//add panels to the JFrame
		window.add(panel1, BorderLayout.SOUTH);
		window.add(panel2, BorderLayout.WEST);
		window.add(panel3, BorderLayout.EAST);
		window.add(panel4, BorderLayout.NORTH);
		window.validate();
		//do not enable generate board button until the user has picked 2 types of players
		while(box1.getCurrentname() == "-" ||  box2.getCurrentname() == "-") {
			generate_board.setEnabled(false);
			window.revalidate();
		}
		generate_board.setEnabled(true);
		//if the user hits the button generate board continue
		while(!generate_board.isHit()) System.out.print("");
		//create objects
		HeuristicPlayer.r = 3;
		MinMaxPlayer.r = Integer.MAX_VALUE;
		b.createBoard();
		Player p1 = new Player();
		Player p2 = new Player();
		switch(box1.getCurrentname()) {
			case "Random Player":
				p1 = new Player (1,"Player1",b,15,10,10,null,null,null);
				break;
			case "Heuristic Player":
				p1 = new HeuristicPlayer(1,"Player1",b,15,10,10,null,null,null);
				break;
			case "MinMax Player":
				p1 = new MinMaxPlayer(1,"Player1",b,15,10,10,null,null,null,p2);
				break;
		}
		switch(box2.getCurrentname()) {
			case "Random Player":
				p2 = new Player (2,"Player2",b,15,10,10,null,null,null);
				break;
			case "Heuristic Player":
				p2 = new HeuristicPlayer(2,"Player2",b,15,10,10,null,null,null);
				break;
			case "MinMax Player":
				p2 = new MinMaxPlayer(2,"Player2",b,15,10,10,null,null,null,p1);
				break;
		}		
		String[][] s = new String[b.getN()][b.getM()];
		//create board panel
		BoardPanel bp = new BoardPanel(b, p1, p2);
		window.add(bp, BorderLayout.CENTER);
		bp.revalidate();
		bp.repaint();
		//start when play button is hit
		while(!play.isHit()) System.out.print("");
		//game ends when board becomes 4X4
		while(b.getM() > 4 && b.getN() > 4) {
			System.out.println("It's round : " + a.round);
			//delay up to 5 seconds depending on user input on the JSlider
			TimeUnit.MILLISECONDS.sleep(2500 - speed.getValue()*125);
			//update round label
			label8.setText("Round: " + a.getRound());
			//resize board every 3 rounds
			if(a.getRound() % 3 == 0 ) {
				b.resizeBoard(p1, p2);
			}
			//make move
			//type cast for each player option
			switch(box1.getCurrentname()) {
				case "Random Player":
					p1.move();
					break;
				case "Heuristic Player":
					HeuristicPlayer ph1 = (HeuristicPlayer) p1;
					ph1.move(p2);
					p1 = ph1;
					break;
				case "MinMax Player":
					MinMaxPlayer pm1 = (MinMaxPlayer) p1;
					pm1.getNextMove(p1.getX(), p1.getY(), p2.getX(), p2.getY());
					p1 = pm1;
					break;
			}
			//update board
			bp = new BoardPanel(b, p1, p2);
			window.add(bp, BorderLayout.CENTER);
			//check if the player has any weapons
			if(p1.getPistol() != null) pistol1.setEnabled(true);
			if(p1.getSword() != null) sword1.setEnabled(true);
			if(p1.getBow() != null) bow1.setEnabled(true);
			bp.revalidate();
			bp.repaint();
			//System.out.println("P1-> " + p1.getX() + "," + p1.getY());
			//update player A info
			movescoreA = p1.getScore() - pscoreA;
			pscoreA = p1.getScore();
			label2.setText("Move Score = " + movescoreA);
			label3.setText("Player Score = " + pscoreA);
			//check if p2 can kill p1
			if(HeuristicPlayer.kill(p2, p1, 2)) {
				s = b.getStringRepresentation(p1, p2);
				//print board
				for(int i = 0; i < b.getN(); i++) {
					for(int j = 0; j < b.getM(); j++) {
						System.out.print(s[i][j]);
					}
					System.out.println();
				}
				System.out.println("P2 has killed P1");
				JOptionPane.showMessageDialog(null, "Player B has killed Player A");
				ok = true;
				break;
			}
			//delay
			TimeUnit.MILLISECONDS.sleep(2500 - speed.getValue()*125);
			//player B move
			switch(box2.getCurrentname()) {
				case "Random Player":
					p2.move();
					break;
				case "Heuristic Player":
					HeuristicPlayer ph2 = (HeuristicPlayer) p2;
					ph2.move(p1);
					p2 = ph2;
					break;
				case "MinMax Player":
					MinMaxPlayer pm2 = (MinMaxPlayer) p2;
					pm2.getNextMove(p2.getX(), p2.getY(), p1.getX(), p1.getY());
					p2 = pm2;
					break;
			}
			//update board and weapon property
			bp = new BoardPanel(b, p1, p2);
			window.add(bp, BorderLayout.CENTER);
			if(p2.getPistol() != null) pistol2.setEnabled(true);
			if(p2.getSword() != null) sword2.setEnabled(true);
			if(p2.getBow() != null) bow2.setEnabled(true);
			bp.revalidate();
			bp.repaint();
			//update player B info
			movescoreB = p2.getScore() - pscoreB;
			pscoreB = p2.getScore();
			label5.setText("Move Score = " + movescoreB);
			label6.setText("Player Score = " + pscoreB);
			//System.out.println("P2-> " + p2.getX() + "," + p2.getY());
			//check if p1 can kill p2
			if(HeuristicPlayer.kill(p1, p2, 2)) {
				s = b.getStringRepresentation(p1, p2);
				//print board
				for(int i = 0; i < b.getN(); i++) {
					for(int j = 0; j < b.getM(); j++) {
						System.out.print(s[i][j]);
					}
					System.out.println();
				}
				System.out.println("P1 has killed P2");
				JOptionPane.showMessageDialog(null, "Player A has killed Player B");
				ok = true;
				break;
			}
			s = b.getStringRepresentation(p1, p2);
			//print board
			for(int i = 0; i < b.getN(); i++) {
				for(int j = 0; j < b.getM(); j++) {
					System.out.print(s[i][j]);
				}
				System.out.println();
			}
			System.out.println();
			//quit button
			if(quit.isHit()) System.exit(0);
			if(p1.getScore() < 0) {
				System.out.println("Player 1 score is below 0. Player 2 has won!");
				JOptionPane.showMessageDialog(null, "Player A score is below 0. Player B has won!");
				break;
			}
			if(p2.getScore() < 0) {
				System.out.println("Player 2 score is below 0. Player 1 has won!");
				JOptionPane.showMessageDialog(null, "Player B score is below 0. Player A has won!");
				break;
			}
			//System.out.println("P1 score: " + p1.getScore());
			//System.out.println("P2 score: " + p2.getScore());
			//increase round
			a.setRound(a.getRound() + 1);
		}
		if(!ok){
			System.out.println("P1 score: " + p1.getScore());
			System.out.println("P2 score: " + p2.getScore());
			if(p1.getScore() != p2.getScore()) {
				System.out.println("Player " + (p1.getScore() > p2.getScore() ? "A":"B") + " wins!");
				JOptionPane.showMessageDialog(null, "Player" + (p1.getScore() > p2.getScore() ? "A":"B") + " wins!");	
			}
			else {
				System.out.println("It's a tie");
				JOptionPane.showMessageDialog(null, "It's a tie");	
			}
		}
		System.exit(0);
	}

}
