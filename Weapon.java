/*
 * Ανέστης Καϊμακαμίδης ΑΕΜ: 9627 τηλέφωνο: 6980613638 webmail: anestisk@ece.auth.gr
 * Κωνσταντίνος Καλαμάρας ΑΕΜ:9716 τηλέφωνο: 6974965296 webmail: kkalamar@ece.auth.gr
 */
public class Weapon {

	int id;
	int x;
	int y;
	int playerId;
	String type;
	/*
	 * id: weapon id
	 * x: position on X-axis
	 * y: position on Y-axis
	 * playerId: player that can pick/own this weapon
	 * type: weapon type (Sword, Bow, Pistol)
	 */
	
	// Constructors
	Weapon(Weapon a){
		this.id = a.id;
		this.x = a.x;
		this.playerId = a.playerId;
		this.y = a.y;
		this.type = a.type;
		
	}
	Weapon(int id,int x,int y,int playerId,String type){
		this.id = id;
		this.x = x;
		this.playerId = playerId;
		this.y = y;
		this.type = type;
		
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

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
