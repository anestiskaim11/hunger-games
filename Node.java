/*
 * Ανέστης Καϊμακαμίδης ΑΕΜ: 9627 τηλέφωνο: 6980613638 webmail: anestisk@ece.auth.gr
 * Κωνσταντίνος Καλαμάρας ΑΕΜ:9716 τηλέφωνο: 6974965296 webmail: kkalamar@ece.auth.gr
 */
import java.util.ArrayList;

public class Node {

	Node parent;
	ArrayList <Node> children;
	int nodeDepth;
	int[] nodeMove;
	Board nodeBoard;
	double nodeEvaluation;
	
	//Constructors
	Node(){
		parent = null;
		children = new ArrayList <Node>();
		nodeDepth = 0;
		nodeMove = null;
		nodeBoard = null;
		nodeEvaluation = 0;
	};
	
	Node(Node parent,ArrayList <Node> children,
			int nodeDepth,int[] nodeMove,
			Board nodeBoard,double nodeEvaluation){
		this.parent = parent;
		this.nodeEvaluation = nodeEvaluation;
		this.nodeBoard = nodeBoard;
		this.children = children;
		this.nodeDepth = nodeDepth;
		this.nodeMove = new int[3];
		for(int i = 0; i < 3; i++) this.nodeMove[i] = nodeMove[i];
	}
	
	//Getters/Setters
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public ArrayList<Node> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	public int getNodeDepth() {
		return nodeDepth;
	}
	public void setNodeDepth(int nodeDepth) {
		this.nodeDepth = nodeDepth;
	}
	public int[] getNodeMove() {
		return nodeMove;
	}
	public void setNodeMove(int[] nodeMove) {
		this.nodeMove = nodeMove;
	}
	public Board getNodeBoard() {
		return nodeBoard;
	}
	public void setNodeBoard(Board nodeBoard) {
		this.nodeBoard = nodeBoard;
	}
	public double getNodeEvaluation() {
		return nodeEvaluation;
	}
	public void setNodeEvaluation(double nodeEvaluation) {
		this.nodeEvaluation = nodeEvaluation;
	}
	
	
	
	
	
	
}
