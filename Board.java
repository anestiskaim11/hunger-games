/*
 * Ανέστης Καϊμακαμίδης ΑΕΜ: 9627 τηλέφωνο: 6980613638 webmail: anestisk@ece.auth.gr
 * Κωνσταντίνος Καλαμάρας ΑΕΜ:9716 τηλέφωνο: 6974965296 webmail: kkalamar@ece.auth.gr
 */
public class Board implements Cloneable{

	int N,M;
	int W,F,T;
	int[][] weaponAreaLimits=new int[4][2];
	int[][] foodAreaLimits=new int[4][2];
	int[][] trapAreaLimits=new int[4][2];
	Weapon[] weapons;
	Food[] food;
	Trap[] traps;
	
	/*
	 * N,M: board dimensions
	 * W,F,T: number of weapons, food and traps respectively
	 * weaponAreaLimits, foodAreaLimits, trapAreaLimits: boards with the outside boarders of each object group
	 * weapons: list of weapons in the game
	 * food: list of food in the game
	 * traps: list of traps in the game
	 */
	
	// Constructors
	Board() {
		this.N = 0;
		this.M = 0;
		this.W = 0;
		this.F = 0;
		this.T = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 2; j++) {
				this.weaponAreaLimits[i][j] = 0;
				this.foodAreaLimits[i][j] = 0;
				this.trapAreaLimits[i][j]=0;
			}
		}
		this.weapons=null;
		this.food=null;
		this.traps=null;
	}

	Board(int n, int m, int w, int f, int t) {
		if(n == m) {
			this.N = n;
			this.M = m;
			this.W = w;
			this.F = f;
			this.T = t;
			this.weapons = new Weapon[W];
			this.food = new Food[F];
			this.traps = new Trap[T];
			
		}
		else System.out.println("M and N must be equal!");
		
	}
	
	Board(Board a){
		this.N = a.N;
		this.M = a.M;
		this.W = a.W;
		this.F = a.F;
		this.T = a.T;
		for(int i = 0; i < this.W; i++) this.weapons[i] = a.weapons[i];
		for(int i = 0; i < this.F; i++) this.food[i] = a.food[i];
		for(int i = 0; i < this.T; i++) this.traps[i] = a.traps[i];
	}
	
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
	}  
	// Getters/Setters
	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int getM() {
		return M;
	}

	public void setM(int m) {
		M = m;
	}

	public int getW() {
		return W;
	}

	public void setW(int w) {
		W = w;
	}

	public int getF() {
		return F;
	}

	public void setF(int f) {
		F = f;
	}

	public int getT() {
		return T;
	}

	public void setT(int t) {
		T = t;
	}

	public int[][] getWeaponAreaLimits() {
		return weaponAreaLimits;
	}

	public void setWeaponAreaLimits(int[][] weaponAreaLimits) {
		this.weaponAreaLimits = weaponAreaLimits;
	}

	public int[][] getFoodAreaLimits() {
		return foodAreaLimits;
	}

	public void setFoodAreaLimits(int[][] foodAreaLimits) {
		this.foodAreaLimits = foodAreaLimits;
	}

	public int[][] getTrapAreaLimits() {
		return trapAreaLimits;
	}

	public void setTrapAreaLimits(int[][] trapAreaLimits) {
		this.trapAreaLimits = trapAreaLimits;
	}

	public Weapon[] getWeapons() {
		return weapons;
	}

	public void setWeapons(Weapon[] weapons) {
		this.weapons = weapons;
	}

	public Food[] getFood() {
		return food;
	}

	public void setFood(Food[] food) {
		this.food = food;
	}

	public Trap[] getTraps() {
		return traps;
	}

	public void setTraps(Trap[] traps) {
		this.traps = traps;
	}
	/*
	 * createRandomWeapon function:
	 * creates the weapon list 
	 * variables:
	 * 		type1: weapon type
	 * 		x,y: the position of the weapon created that is generated randomly inside the boarders
	 * 		oldxy: a list of the positions of all the weapons that have already been placed on the board, so that
	 * 		there are no weapons on the same place
	 * 		ok: variable that controls the do-while loop checking if the random x,y position has already been given
	 *		to another weapon
	 */
	public void createRandomWeapon() {
		String type1 = null;
		int x, y, randtype1 = 0, randtype2 = 0, randtype3 = 0;;
		int oldxy[][] = new int[this.W][2];
		boolean ok = false;
		for(int i = 0; i < this.W; i++) {
			for(int j = 0; j < 2; j++) oldxy[i][j] = 0;
		}
		//loop through the weapon list to create all the weapons
		for(int i = 2; i < this.W + 2; i++) {
			do {
				ok = false;
				//to generate x,y positions we use the formula index = (random between 0 and (upperlimit - lowerlimit)) + lowerlimit
				x = (int) (Math.random()* (this.getWeaponAreaLimits()[2][0] - this.getWeaponAreaLimits()[0][0] + 1)) + this.getWeaponAreaLimits()[0][0];
				y = (int) (Math.random()* (this.getWeaponAreaLimits()[2][1] - this.getWeaponAreaLimits()[0][1] + 1)) + this.getWeaponAreaLimits()[0][1];
				//check if this place is already taken
				for(int k = 0; k < this.W; k++) {
					if(x == oldxy[k][0] && y == oldxy[k][1]) {
						ok = true;
						break;
					}
				}
			} while(x == 0 || y == 0 || ok);
			//remember the place given to the new weapon
			for(int k = 0; k < this.W; k++) {
				if(oldxy[k][0] == 0 && oldxy[k][1] == 0) {
					oldxy[k][0] = x;
					oldxy[k][1] = y;
					break;
				}
			}
			//determine its type
			//change type every 2 loops so that each player has the same number of every type of weapon on board
			//make sure every 6 weapons there are a sword, a bow and a pistol for each player
			if(i % 6 == 2) randtype1 = (int) (Math.random()* 3);
			else if(i % 6 == 4) {
				do{
					randtype2 = (int) (Math.random()* 3);
					
				}while(randtype2 == randtype1);
			}
			else if(i % 6 == 0) {
				do{
					randtype3 = (int) (Math.random()* 3);
					
				}while(randtype3 == randtype1 || randtype3 == randtype2);
			}
			
			switch((((i/2) % 3 == 1)?randtype1:0) + (((i/2) % 3 == 2)?randtype2:0) + (((i/2) % 3 == 0)?randtype3:0)) {
				case 0: type1 = "Bow";
						break;
				case 1: type1 = "Pistol";
						break;
				case 2: type1 = "Sword";
						break;
			}
			//create the weapon with weaponid: i/2 and playerid = (i % 2) + 1 (i = 2,3,...,W+2) 
			//so we get weaponid: 1,1,2,2...W/2,W/2 and playerid: 1,1,2,2,1,1...
			this.weapons[i - 2] = new Weapon(i / 2, x, y, (i % 2) + 1, type1);
		}
		
	}
	/*
	 * createRandomTrap function:
	 * creates the trap list 
	 * variables:
	 * 		type1: trap type
	 * 		x,y: the position of the trap created that is generated randomly inside the boarders
	 * 		oldxy: a list of the positions of all the traps that have already been placed on the board, so that
	 * 		there are no traps on the same place
	 * 		ok: variable that controls the do-while loop checking if the random x,y position has already been given
	 *		to another trap
	 *		points: point-loss
	 */
	public void createRandomTrap() {
		String type1 = null;
		int x, y, point, randtype = 0;
		boolean ok  = false;
		int oldxy[][] = new int [this.T][2];
		for(int i = 0; i < this.T; i++) {
			for(int j = 0; j < 2; j++) oldxy[i][j] = 0;
		}
		//loop through the trap list to create all the traps
		for(int i = 0; i < this.T; i++) {
			//points between -10 and -1
			point = (int) (Math.random()* 10) - 10;
			do {
				ok = false;
				//to generate x,y positions we use the formula index = (random between 0 and (upperlimit - lowerlimit)) + lowerlimit
				x = (int) (Math.random()* (this.getTrapAreaLimits()[2][0] - this.getTrapAreaLimits()[0][0] + 1)) + this.getTrapAreaLimits()[0][0];
				y = (int) (Math.random()* (this.getTrapAreaLimits()[2][1] - this.getTrapAreaLimits()[0][1] + 1)) + this.getTrapAreaLimits()[0][1];
				//check if this place is already taken
				for(int k = 0; k < this.T; k++) {
					if(x == oldxy[k][0] && y == oldxy[k][1]) {
						ok = true;
						break;
					}
				}
			} while((x <= this.getFoodAreaLimits()[2][0] && x >= this.getFoodAreaLimits()[0][0] && y >= this.getFoodAreaLimits()[0][1] && y <= this.getFoodAreaLimits()[2][1]) || ok || x == 0 || y == 0);
			//x,y must be outside food area limits
			//remember the place given to the new trap
			for(int k = 0; k < this.T; k++) {
				if(oldxy[k][0] == 0 && oldxy[k][1] == 0) {
					oldxy[k][0] = x;
					oldxy[k][1] = y;
					break;
				}
			}
			//determine its type
			if(i % 2 == 0) randtype = (int) (Math.random()* 2);
			else if(i % 2 == 1) randtype = 1 - randtype;
			switch(randtype) {
				case 0: type1 = "Rope";
						break;
				case 1: type1 = "Animal";
						break;
			}
			//create trap 
			this.traps[i] = new Trap(i + 1, x, y, point, type1);
		}
		
	}
	
	/*
	 * createRandomFood function:
	 * creates the food list 
	 * variables:
	 * 		x,y: the position of the food created that is generated randomly inside the boarders
	 * 		oldxy: a list of the positions of all the food that have already been placed on the board, so that
	 * 		there are no food on the same place
	 * 		ok: variable that controls the do-while loop checking if the random x,y position has already been given
	 *		to another food object
	 *		points: point-gain
	 */
	public void createRandomFood() {
		int x,y,point;
		boolean ok = false;
		int oldxy[][] = new int [this.F][2];
		for(int i = 0; i < this.F; i++) {
			for(int j = 0; j < 2; j++) oldxy[i][j] = 0;
		}
		//loop through the food list to create all the food objects
		for(int i = 0; i < this.F; i++) {
			//points between 1 and 10
			point = (int) (Math.random()* 10) + 1;
			do {
				ok = false;
				//to generate x,y positions we use the formula index = (random between 0 and (upperlimit - lowerlimit)) + lowerlimit
				x = (int) (Math.random()* (this.getFoodAreaLimits()[2][0] - this.getFoodAreaLimits()[0][0] + 1)) + this.getFoodAreaLimits()[0][0];
				y = (int) (Math.random()* (this.getFoodAreaLimits()[2][1] - this.getFoodAreaLimits()[0][1] + 1)) + this.getFoodAreaLimits()[0][1];
				//check if this place is already taken
				for(int k = 0; k < this.F; k++) {
					if(x == oldxy[k][0] && y == oldxy[k][1]) {
						ok = true;
						break;
					}
				}
			} while((x <= this.getWeaponAreaLimits()[2][0] && x >= this.getWeaponAreaLimits()[0][0] && y >= this.getWeaponAreaLimits()[0][1] && y <= this.getWeaponAreaLimits()[2][1]) || ok || x== 0 || y == 0);
			//x,y must be outside weapon area limits
			//remember the place given to the new food object
			for(int k = 0; k < this.F; k++) {
				if(oldxy[k][0] == 0 && oldxy[k][1] == 0) {
					oldxy[k][0] = x;
					oldxy[k][1] = y;
					break;
				}
			}
			//create food object
			this.food[i] = new Food(i + 1, x, y, point);
		}
		
	}
	//createBoard function: calls createRandomWeapon, createRandomFood and createRandomTrap functions
	public void createBoard() {
		this.createRandomWeapon();
		this.createRandomFood();
		this.createRandomTrap();
	}
	
	/*
	 * resizeBoard function:
	 * resizes board by changing N and M
	 * old board: 		N/2
	 * 			-M/2			M/2
	 * 					-N/2
	 * new board:
	 * 					N/2 - 1
	 * 			-M/2 + 1		M/2 - 1
	 * 					-N/2 + 1
	 * so the lines x = M/2, x = -M/2, y = N/2, y = -N/2 are deleted
	 * if no player is on the board boarders
	 * 			
	 */
	public void resizeBoard(Player p1,Player p2) {
		if(p1.getY() != this.getN()/2 && p1.getX() != this.getM()/2 && p1.getY() != (-this.getN())/2 && p1.getX() != (-this.getM())/2 && p2.getY() != this.getN()/2 && p2.getX() != this.getM()/2 && p2.getY() != (-this.getN())/2 && p2.getX() != (-this.getM())/2) {
			this.N = this.N - 2;
			this.M = this.M - 2;
		}
	}
	/*
	 * getStringRepresentation function:
	 * this function represents the game board each round
	 * variables:
	 * 		board: the string[][] returned containing the board
	 * 		ok: boolean variable controlling the for loop(when an object is printed the "___" string must not be printed)
	 * 		x,y: transform the coordinates to board indexes
	 * 
	 */
	public String[][] getStringRepresentation(Player p1, Player p2){
		String[][] board = new String[this.N][this.M];
		boolean ok = false;
		int x,y;
		for(int i = (-this.N /2); i <= this.N/2; i++) { 
			for(int j=(-this.M)/2 ; j <= this.M /2; j++) { 
				//ignore x = 0 and y = 0 lines
				if(i == 0 || j == 0) continue;
				//transformation
				x = j + this.M/2 - ((j > 0) ? 1 : 0); 
				y = i + this.N/2 - ((i > 0) ? 1 : 0); 
				//if there is a player there print P1 or P2
				//for player representation
				if(p1.getX() == j && p1.getY() == i){
					//System.out.print(" P1");
					board[y][x] = " P1";
					continue;
				}
				if(p2.getX() == j && p2.getY() == i){
					//System.out.print(" P2");
					board[y][x] = " P2";
					continue;
				}
				//loop through objects to see their position
				//if there is an object on the current i,j print it
				for(int k = 0; k < this.F; k++) {
					if(this.food[k].getX() == j && this.food[k].getY() == i) {
							board[y][x] = ((this.food[k].getId() < 10) ? " ":"") + "F" + this.food[k].getId();
							ok = true;
					}
				}
				for(int k = 0; k < this.T; k++) {
					if(this.traps[k].getX() == j && this.traps[k].getY() == i) {
						board[y][x] = ((this.traps[k].getId() < 10) ? " ":"") + "T" + this.traps[k].getId();
						ok = true;
					}
				}
				for(int k = 0; k < this.W; k++) {
					if(this.weapons[k].getX() == j && this.weapons[k].getY() == i) {
						board[y][x] = "W" + this.weapons[k].getPlayerId() + this.weapons[k].getId();
						ok = true;
					}
				}
				//if we have printed an object or a player continue
				if(ok) {
					ok = false;
					continue;
				}
				//else print "___"
				else board[y][x] = " _ ";
			}
		}
		//return the string board
		return board;
		
	}
	
	
	
}
