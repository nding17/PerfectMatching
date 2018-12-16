import java.util.ArrayList;

public class Node {
	
	private ArrayList<Integer> adjacentNodes;
	private int node;
	private boolean isMarked;
	
	public Node(int node, int[] adjNodes){
		this.node = node;
		this.adjacentNodes = new ArrayList<Integer>();
		this.isMarked = false;
		for(int i = 0; i < adjNodes.length; ++i){
			this.adjacentNodes.add(adjNodes[i]);
		}
	}
	
	// get the indices of all the neighboring nodes 
	public ArrayList<Integer> getAdjNodesIdx(){
		return this.adjacentNodes;
	}
	
	public int getNodeIdx(){
		return this.node;
	}
	
	public void setMark(){
		isMarked = true;
	}
	
	public boolean checkIsMarked(){
		return this.isMarked;
	}
}
