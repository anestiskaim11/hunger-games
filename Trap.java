/*
 * Ανέστης Καϊμακαμίδης ΑΕΜ: 9627 τηλέφωνο: 6980613638 webmail: anestisk@ece.auth.gr
 * Κωνσταντίνος Καλαμάρας ΑΕΜ:9716 τηλέφωνο: 6974965296 webmail: kkalamar@ece.auth.gr
 */
public class Trap {

	int id;
	int x;
	int y;
	int points;
	String type;
	
	/*
	 * id : trap id
	 * x: position on X-axis
	 * y: position on Y-axis
	 * points: points a player loses passing through this trap(between 1 and 10)
	 * type: trap type(Animal, Rope)
	 */
	
	// Constructors
	public Trap(int id, int x, int y, int points, String type) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.points = points;
		this.type = type;
	}
	
	public Trap(Trap a) {
		this.id = a.id;
		this.x = a.x;
		this.y = a.y;
		this.points = a.points;
		this.type = a.type;
	}

	// Getters/Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
}
