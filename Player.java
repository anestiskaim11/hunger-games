/*
 * Ανέστης Καϊμακαμίδης ΑΕΜ: 9627 τηλέφωνο: 6980613638 webmail: anestisk@ece.auth.gr
 * Κωνσταντίνος Καλαμάρας ΑΕΜ:9716 τηλέφωνο: 6974965296 webmail: kkalamar@ece.auth.gr
 */
public class Player {

	int id;
	String name;
	Board board;
	int score;
	int x;
	int y;
	Weapon bow;
	Weapon pistol;
	Weapon sword;
	
	/*
	 * id: player id
	 * name: player name
	 * board: game board
	 * score: player's score
	 * x: position on X-axis
	 * y: position on Y-axis
	 * bow: bow picked
	 * pistol: pistol picked
	 * sword: sword picked
	 */
	
	//Constructors
	Player(){
		this.id = 0;
		this.name = null;
		this.score = 0;
		this.x = 0;
		this.y = 0;
		this.board = null;
		this.bow = null;
		this.pistol = null;
		this.sword = null;
	}

	public Player(int id, String name, Board board, int score, int x, int y, Weapon bow, Weapon pistol, Weapon sword) {
		this.id = id;
		this.name = name;
		this.board = board;
		this.score = score;
		this.x = x;
		this.y = y;
		this.bow = bow;
		this.pistol = pistol;
		this.sword = sword;
	}

	// Getters/Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Weapon getBow() {
		return bow;
	}

	public void setBow(Weapon bow) {
		this.bow = bow;
	}

	public Weapon getPistol() {
		return pistol;
	}

	public void setPistol(Weapon pistol) {
		this.pistol = pistol;
	}

	public Weapon getSword() {
		return sword;
	}

	public void setSword(Weapon sword) {
		this.sword = sword;
	}
	/*
	 * getRandomMove function:
	 * variables:
	 * 		move: the new position of the player that is returned
	 * 		a,b: the step a player makes on X and Y axis
	 */
	int[] getRandomMove(){
		int[] move=new int[2];
		int a = 0,b = 0,c = 0;
		do {
			//c between 1 and 8 eight possible moves
			c = (int) (Math.random() *8 ) + 1;
			/*
			 * if player is on x = 1, y = 1, x = -1, y = -1 line and want to pass through x = 0 and y = 0 lines
			 * we should add or subtract 2 instead of one
			 */
			switch(c) {
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
			
		}while(this.x + a > board.getM()/2 || this.x + a < (-board.getM())/2 
				|| this.y + b > board.getN()/2 || this.y + b < (-board.getN())/2);
		//stop when the step is inside the board 
		move[0]= this.x + a;
		move[1]= this.y + b;
		//return the new position
		return move;
		
	}
	/*
	 * move function:
	 * variables:
	 * 		numbers: returned list containing new x, new y, weapons picked, food picked, traps passed
	 * 		newMove: gets the return value of makeRandomMove
	 * 
	 */
	int[] move() {
		int[] numbers = new int[5];
		int[] newMove = new int[2];
		newMove = getRandomMove();
		//make move
		this.x = newMove[0];
		this.y = newMove[1];
		int wc = 0, fc = 0, tc = 0;
		//check if the new position of the player is equal to an object's position
		for(int i = 0; i < board.getW(); i++) {
			if(board.getWeapons()[i].getX() == this.x && board.getWeapons()[i].getY() == this.y && board.getWeapons()[i].getPlayerId() == this.getId()) {
				wc++;
				//pick weapon
				if(board.getWeapons()[i].getType() == "Sword") {
					//System.out.println(this.getName() + " picked a Sword");
					this.sword = new Weapon(board.getWeapons()[i]);
				}
				else if(board.getWeapons()[i].getType() == "Bow") {
					//System.out.println(this.getName() + " picked a Bow");
					this.bow = new Weapon(board.getWeapons()[i]);
				}
				else if(board.getWeapons()[i].getType() == "Pistol") {
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
				//delete food from the board
				board.getFood()[i].setX(0);
				board.getFood()[i].setY(0);
				
			}
		}
		for(int i = 0; i < board.getT(); i++) {	
			if(board.getTraps()[i].getX() == this.x && board.getTraps()[i].getY() == this.y ) {
				tc++;
				//System.out.println(this.getName() + " stepped on a trap: " + board.getTraps()[i].getType());
				//lose trap points if you can't face it
				if(!((board.getTraps()[i].getType() == "Animal" && this.getBow() != null) || (board.getTraps()[i].getType() == "Rope" && this.getSword() != null))){
					this.setScore(this.getScore() + board.getTraps()[i].getPoints());
				}
			}
		}
		//return the numbers list
		numbers[0] = newMove[0];
		numbers[1] = newMove[1];
		numbers[2] = wc;
		numbers[3] = fc;
		numbers[4] = tc;
		return numbers;
		
		
		
	}
	
}


