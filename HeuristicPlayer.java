/*
 * Ανέστης Καϊμακαμίδης ΑΕΜ: 9627 τηλέφωνο: 6980613638 webmail: anestisk@ece.auth.gr
 * Κωνσταντίνος Καλαμάρας ΑΕΜ:9716 τηλέφωνο: 6974965296 webmail: kkalamar@ece.auth.gr
 */
import java.util.ArrayList;
import java.util.HashMap;

public class HeuristicPlayer extends Player {
	
	ArrayList <Integer[]> path;
	static int r;
	
	//Constructors
	HeuristicPlayer(){
		super();
		this.path = new ArrayList <Integer[]> ();
	}
	
	HeuristicPlayer(int id, String name, Board board, int score, int x, int y, Weapon bow, Weapon pistol, Weapon sword){
		super(id,name,board,score,x,y,bow,pistol,sword);
		this.path =  new ArrayList <Integer[]> ();
	}
	/*
	 * decode function:
	 * 		takes the dice and returns the move on X and Y axis
	 * 		a: Move on X axis
	 * 		b: Move on Y axis
	 * 		returns the array {a,b}
	 * 		We have to be careful with the moves that cross the lines x = 0 and y = 0
	 * 		the dice encodes these moves:
	 * 		8   1   2
	 * 		7	P	3
	 * 		6   5   4
	 */
	int [] decode(int dice){
		int a = 0, b = 0;
		int [] result = new int [2];
		switch(dice) {
		case 1:
			a = 0;
			b = -1 - ((this.y == 1) ? 1:0);
			break;
		case 2:
			a = 1 + ((this.x == -1) ? 1:0);
			b = -1 -((this.y == 1) ? 1:0);
			break;
		case 3:
			a = 1 + ((this.x == -1) ? 1:0);
			b = 0;
			break;
		case 4:
			a = 1 + ((this.x == -1) ? 1:0);		
			b = 1 + ((this.y == -1) ? 1:0);		
			break;
		case 5:
			a = 0;
			b = 1 + ((this.y == -1) ? 1:0);
			break;
		case 6:
			a = -1 - ((this.x == 1) ? 1:0);
			b = 1 + ((this.y == -1) ? 1:0);
			break;
		case 7:
			a = -1 - ((this.x == 1) ? 1:0);
			b = 0;
			break;
		case 8:
			a = -1 - ((this.x == 1) ? 1:0);
			b = -1 - ((this.y == 1) ? 1:0);
			break;
		}
		result[0] = a;
		result[1] = b;
		return result;
	}
	/*
	 * playerDistance function
	 * 		calculates how many moves away the other player is
	 * 		dx: distance on X axis
	 * 		dy: distance on Y axis
	 * 		if we cross lines x = 0 and y = 0 we should subtract 1
	 * 		distance = max(dx, dy)
	 * 		if distance is greater than the space that can be seen the function returns -1
	 */
	float playerDistance(Player p) {
		float result ;
		int dx = p.getX() - this.x;
		int dy = p.getY() - this.y;
		dx = (this.x * p.getX() < 0) ? (Math.abs(dx) - 1):(Math.abs(dx));
		dy = (this.y * p.getY() < 0) ? (Math.abs(dy) - 1):(Math.abs(dy));
		result = (dx > dy) ? dx : dy;
		return (result <= HeuristicPlayer.r) ? result : -1;
	}
	
	/*
	 *   foodDistance function: 
	 *   	calculates the same way the distance between a possible place and a food object
	 *   	if food object is greater than the space that can be seen the function returns -1 
	 */
	int foodDistance(Food f,int dice) {
		int result ;
		int dx = f.getX() - (this.x + decode(dice)[0]);
		int dy = f.getY() - (this.y + decode(dice)[1]);
		dx = ((this.x + decode(dice)[0]) * f.getX() < 0) ? (Math.abs(dx) - 1):(Math.abs(dx));
		dy = ((this.y + decode(dice)[1]) * f.getY() < 0) ? (Math.abs(dy) - 1):(Math.abs(dy));
		result = (dx > dy) ? dx : dy;
		return (result <= HeuristicPlayer.r) ? result : -1;
	}
	/*
	 *   weaponDistance function: 
	 *   	calculates the same way the distance between a possible place and a weapon object
	 *   	if weapon object is greater than the space that can be seen the function returns -1 
	 */
	int weaponDistance(Weapon w,int dice) {
		int result ;
		int dx = w.getX() - (this.x + decode(dice)[0]);
		int dy = w.getY() - (this.y + decode(dice)[1]);
		dx = ((this.x + decode(dice)[0]) * w.getX() < 0) ? (Math.abs(dx) - 1):(Math.abs(dx));
		dy = ((this.y + decode(dice)[1]) * w.getY() < 0) ? (Math.abs(dy) - 1):(Math.abs(dy));
		result = (dx > dy) ? dx : dy;
		return (result <= HeuristicPlayer.r) ? result : -1;
	}
	/*
	 *   trapDistance function: 
	 *   	calculates the same way the distance between a possible place and a trap object
	 *   	if trap object is greater than the space that can be seen the function returns -1 
	 */
	int trapDistance(Trap t,int dice) {
		int result ;
		int dx = t.getX() - (this.x + decode(dice)[0]);
		int dy = t.getY() - (this.y + decode(dice)[1]);
		dx = ((this.x + decode(dice)[0]) * t.getX() < 0) ? (Math.abs(dx) - 1):(Math.abs(dx));
		dy = ((this.y + decode(dice)[1]) * t.getY() < 0) ? (Math.abs(dy) - 1):(Math.abs(dy));
		result = (dx > dy) ? dx : dy;
		return (result <= HeuristicPlayer.r) ? result : -1;
	}
	
	/*
	 *  evaluate function:
	 *  	gainWeapons: weight to gain a bow or a sword
	 *  	gainPistol: weight to gain a pistol
	 *  	avoidTraps: weight to avoid traps
	 *  	gainPoints: weight to gain Points
	 *  	forceKill: weight to kill the other player
	 *  	avoidPlayer: weight to avoid the other player
	 *  	beclose: weight to be close to the other player and wait to make a mistake
	 *  	gotocenter: weight to go to the center of the board
	 *  	
	 *  	this function evaluates the moves depending on the distance and the weight of each possible move from every visible object
	 */
	double evaluate(int dice , Player p) {
		double gainWeapons = 1.5, gainPistol = 5, avoidTraps = 0.5, gainPoints = 1.0, forceKill = 10.0, avoidPlayer = -100.0, beclose = 3.0, gotocenter = 1.0;
		int a = decode(dice)[0], b = decode(dice)[1];
		a = this.x + a;
		b = this.y + b;
		double result = 0;
		int c = 0, d = 0;
		if(a * p.getX() < 0) c = 1;		//c and d variables that help find the correct distance
		if(b * p.getY() < 0) d = 1;		//we subtract 1 on these two cases
		//if(this.id == 2) {
			if(this.getBow() != null && this.getSword() != null) avoidTraps = 0; //if we have the bow and the sword we don't have to avoid traps
			if(p.getPistol() != null && this.getPistol() == null && this.playerDistance(p) >= 0) {	//if we don't have the pistol and the opponent has it
				gainPistol = 50;	//in this case we should gain the pistol and stay on the center
				gotocenter = 30;
				//if we are going to die avoid player
				if((float)Math.sqrt(((Math.abs(a - p.getX()) - c) * (Math.abs(a - p.getX()) - c)) + ((Math.abs(b - p.getY()) - d) * (Math.abs(b - p.getY()) - d))) <= 2) {
					result = result + avoidPlayer;
				}
			}
			if(p.getPistol() != null && this.getPistol() != null && this.playerDistance(p) >= 0) { //if we both have the pistol
				gainPistol = 5;	//reset weights
				gotocenter = 1;
				//if we are going to die avoid player
				if((float)Math.sqrt(((Math.abs(a - p.getX()) - c) * (Math.abs(a - p.getX()) - c)) + ((Math.abs(b - p.getY()) - d) * (Math.abs(b - p.getY()) - d))) < 2  && this.playerDistance(p) >= 0) {
					result = result + avoidPlayer;
				}
				//stay close to kill him if he makes a mistake
				if((float)Math.sqrt(((Math.abs(a - p.getX()) - c) * (Math.abs(a - p.getX()) - c)) + ((Math.abs(b - p.getY()) - d) * (Math.abs(b - p.getY()) - d))) == 2 && this.playerDistance(p) >= 0) {
					result = result + beclose ;
				}
			}
			//if we have the pistol and he doesn't
			if(p.getPistol() == null && this.getPistol() != null) {
				if((float)Math.sqrt(((Math.abs(a - p.getX()) - c) * (Math.abs(a - p.getX()) - c)) + ((Math.abs(b - p.getY()) - d) * (Math.abs(b - p.getY()) - d))) <= 2 && this.playerDistance(p) >= 0) {
					result = result + forceKill;	//kill him
					if(Math.abs(p.getX()) == board.getM()/2 && Math.abs(a) == board.getM()/2 - 1 && b == p.getY()) result += 100;	//helps make the right move to kill him
					if(Math.abs(p.getY()) == board.getN()/2 && Math.abs(a) == board.getN()/2 - 1 && b == p.getX()) result += 100;			
				}
			}
			//}
			//calculate the effect of all visible foods
			for(int i = 0; i < board.getF(); i++) {
				if(foodDistance(board.getFood()[i],dice) >= 0 && foodDistance(board.getFood()[i],dice) <= HeuristicPlayer.r - 1) {
					result = result + gainPoints * board.getFood()[i].getPoints() * (1 / (double) (10 * foodDistance(board.getFood()[i],dice) + 1) );
				}
			}
			//calculate the effect of all visible traps
			for(int i = 0; i < board.getT(); i++) {
				if(trapDistance(board.getTraps()[i],dice) >= 0 && (trapDistance(board.getTraps()[i],dice) <= HeuristicPlayer.r - 1)) {
					result = result + avoidTraps * board.getTraps()[i].getPoints() * (1 / (double) (600000 * trapDistance(board.getTraps()[i],dice) + 1) );
				}
			}
			//calculate the effect of all visible weapons
			for(int i = 0; i < board.getW(); i++) {
				if(weaponDistance(board.getWeapons()[i],dice) >= 0 && weaponDistance(board.getWeapons()[i],dice) <= HeuristicPlayer.r - 1 && board.getWeapons()[i].getPlayerId() == this.getId()) {
					if(board.getWeapons()[i].getType() == "Pistol") {
						result = result + gainPistol * (1 / (10 * weaponDistance(board.getWeapons()[i],dice) + 1) );
					}
					else result = result + gainWeapons * (1 / (double) (10 * weaponDistance(board.getWeapons()[i],dice) + 1) );
				}
			}
			//go to center
			if((Math.abs(this.x) >= 3 || Math.abs(this.y) >= 3) && (Math.abs(a) < Math.abs(this.x) && Math.abs(b) < Math.abs(this.y))) result = result + gotocenter;
			if((Math.abs(this.x) >= 3 || Math.abs(this.y) >= 3) && (Math.abs(a) < Math.abs(this.x) || Math.abs(b) < Math.abs(this.y))) result = result + gotocenter/2;
		//simple player
		/*if(this.id == 1) {
			gainWeapons = 2;
			gainPistol = 2;
			avoidTraps = 0.3;
			gainPoints = 0.4;
			forceKill = 1.0;
			gotocenter = 2;
			for(int i = 0; i < board.getF(); i++) {
				if(foodDistance(board.getFood()[i],dice) == 0) {
					result = result + gainPoints * board.getFood()[i].getPoints() ;
				}
			}
			for(int i = 0; i < board.getT(); i++) {
				if(trapDistance(board.getTraps()[i],dice) == 0) {
					result = result + avoidTraps * board.getTraps()[i].getPoints() ;
				}
			}
			for(int i = 0; i < board.getW(); i++) {
				if(weaponDistance(board.getWeapons()[i],dice) == 0 && board.getWeapons()[i].getPlayerId() == this.getId()) {
					if(board.getWeapons()[i].getType() == "Pistol") {
						result = result + gainPistol ;
					}
					else result = result + gainWeapons ;
				}
			}
			if(p.getPistol() == null && this.getPistol() != null) {
				if((float)Math.sqrt(((Math.abs(a - p.getX()) - c) * (Math.abs(a - p.getX()) - c)) + ((Math.abs(b - p.getY()) - d) * (Math.abs(b - p.getY()) - d))) <= 3) {
					result = result + forceKill;
				}
			}
			if((Math.abs(this.x) >= 3 || Math.abs(this.y) >= 3) && (Math.abs(a) < Math.abs(this.x) && Math.abs(b) < Math.abs(this.y))) result = result + gotocenter;
		}*/
		return result;
		
	}
	/*
	 * kill function:
	 * 		defines if a player can kill the other player
	 * 		a player can kill the other if their distance < d and he owns a pistol
	 * 		returns true if the first player can kill the second
	 */
	static boolean kill(Player player1 , Player player2 , float d) {
		int c = 0, e = 0;
		if(player1.getX() * player2.getX() < 0) c = 1;
		if(player1.getY() * player2.getY() < 0) e = 1;
		float distance = (float)Math.sqrt(((Math.abs(player1.getX() - player2.getX()) - c) * (Math.abs(player1.getX() - player2.getX()) - c)) + ((Math.abs(player1.getY() - player2.getY()) - e) * (Math.abs(player1.getY() - player2.getY()) - e)));
		if(distance < d && player1.getPistol() != null) return true;
		else return false;
	}
	/*
	 * move function:
	 * 		loops through all possible moves  calls evaluate function and decides the best move
	 */
	int[] move(Player p){
		HashMap <Integer,Double> moves = new HashMap<Integer,Double> ();
		double max = Double.NEGATIVE_INFINITY;
		int a = 0, b = 0, x = 0, y = 0, code = 0, wc = 0, tc = 0, fc = 0, points = 0;
		Integer[] newPos = new Integer[5];
		int[] newPosition = new int[2];
		for(int i = 8; i > 0; i--) {
			a = decode(i)[0];
			b = decode(i)[1];
			if(this.x + a > board.getM()/2 || this.x + a < (-board.getM())/2 
				|| this.y + b > board.getN()/2 || this.y + b < (-board.getN())/2) continue; //if the move is out of bounds continue
			if(evaluate(i,p) > max) { //find max evaluation value
				max = evaluate(i,p);
				code = i;
				x = a;
				y = b;
			}
			//System.out.println(i + " " + evaluate(i,p));
			moves.put(i, evaluate(i, p));	//store all possible moves in the hashmap
		}
		//make move
		this.x = this.x + x;
		this.y = this.y + y;
		for(int i = 0; i < board.getW(); i++) {
			if(board.getWeapons()[i].getX() == this.x && board.getWeapons()[i].getY() == this.y && board.getWeapons()[i].getPlayerId() == this.getId()) {
				//pick weapon
				if(board.getWeapons()[i].getType() == "Sword") {
					wc = 1;
					//System.out.println(this.getName() + " picked a Sword");
					this.sword = new Weapon(board.getWeapons()[i]);
				}
				else if(board.getWeapons()[i].getType() == "Bow") {
					wc = 2;
					//System.out.println(this.getName() + " picked a Bow");
					this.bow = new Weapon(board.getWeapons()[i]);
				}
				else if(board.getWeapons()[i].getType() == "Pistol") {
					wc = 3;
					//System.out.println(this.getName() + " picked a Pistol");
					this.pistol = new Weapon(board.getWeapons()[i]);
				}
				//delete weapon from the board
				board.getWeapons()[i].setX(0);
				board.getWeapons()[i].setY(0);
				
			}
		}
		for(int i = 0; i < board.getF(); i++) {
			if(board.getFood()[i].getX() == this.x && board.getFood()[i].getY() == this.y ) {
				fc++;
				//get food points
				this.setScore(this.getScore() + board.getFood()[i].getPoints());
				points = board.getFood()[i].getPoints();
				//delete food from the board
				board.getFood()[i].setX(0);
				board.getFood()[i].setY(0);
				
			}
		}
		for(int i = 0; i < board.getT(); i++) {	
			if(board.getTraps()[i].getX() == this.x && board.getTraps()[i].getY() == this.y ) {
				tc++;
				points = board.getTraps()[i].getPoints();
				//System.out.println(this.getName() + " stepped on a trap: " + board.getTraps()[i].getType());
				//lose trap points if you can't face it
				if(!((board.getTraps()[i].getType() == "Animal" && this.getBow() != null) || (board.getTraps()[i].getType() == "Rope" && this.getSword() != null))){
					this.setScore(this.getScore() + board.getTraps()[i].getPoints());
				}
			}
		}
		//store the move to the ArrayList path
		newPos[0] = code; //code of the move
		newPos[1] = points; //points gained
		newPos[2] = fc; //food counter
		newPos[3] = tc; //trap counter
		newPos[4] = wc; //weapon counter (1 for sword, 2 for bow, 3 for pistol)
		path.add(newPos);
		newPosition[0] = this.x;
		newPosition[1] = this.y;
		return newPosition; //return the new position
		
		
	}
	/*
	 * statistics function:
	 * 		displays statistics of each round
	 * 		round = ArrayList size
	 */
	void statistics() {
		System.out.println("ο παίκτης στο round " + path.size() + " έθεσε το ζάρι ίσο με " + path.get(path.size() - 1)[0]);
		if(path.get(path.size() - 1)[4] == 1) System.out.print(", μάζεψε ένα όπλο τύπου σπαθί");
		else if(path.get(path.size() - 1)[4] == 2) System.out.print(", μάζεψε ένα όπλο τύπου τόξο");
		else if(path.get(path.size() - 1)[4] == 3) System.out.print(", μάζεψε ένα όπλο τύπου πιστόλι");
		else System.out.print(", δεν μάζεψε όπλα");
		System.out.print(" και κέρδισε " + path.get(path.size() - 1)[1] + " πόντους");
	}
	
}
