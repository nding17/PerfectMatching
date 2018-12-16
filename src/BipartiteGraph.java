import java.util.ArrayList;

public class BipartiteGraph {
	
	private ArrayList<Node> leftNodes;
	private ArrayList<Node> rightNodes;
	private ArrayList<Node> allNodes;
	
	// construct the bipartite graph based on the left and 
	// right side nodes 
	public BipartiteGraph(Node[] leftNodes, Node[] rightNodes){
		this.leftNodes = new ArrayList<Node>();
		this.rightNodes = new ArrayList<Node>();
		this.allNodes = new ArrayList<Node>();
		
		for(int i = 0; i < leftNodes.length; ++i){
			this.leftNodes.add(leftNodes[i]);
			this.allNodes.add(leftNodes[i]);
		}
		
		for(int j = 0; j < rightNodes.length; ++j){
			this.rightNodes.add(rightNodes[j]);
			this.allNodes.add(rightNodes[j]);
		}
	}
	
	public ArrayList<Node> getLeftNodes(){
		return this.leftNodes;
	}
	
	public ArrayList<Node> getRightNodes(){
		return this.rightNodes;
	}
	
	public ArrayList<Node> getAllNodes(){
		return this.allNodes;
	}
	
	public Node getLeftNodeAt(int i){
		return leftNodes.get(i);
	}
	
	public Node getRightNodeAt(int i){
		return rightNodes.get(i);
	}
	
	public Node getNodeAt(int i){
		return allNodes.get(i);
	}
	
	// transform indices to Node
	// find all the adjacent nodes based on a given node
	public ArrayList<Node> getNeighbours(Node node){
		ArrayList<Node> neighbours = new ArrayList<Node>();
		ArrayList<Integer> adjNodes = node.getAdjNodesIdx();
		for(int i = 0; i < adjNodes.size(); ++i){
			for(int j = 0; j < this.allNodes.size(); ++j){
				if(this.allNodes.get(j).getNodeIdx() == adjNodes.get(i)){
					neighbours.add(this.allNodes.get(j));
				}
			}
		}
		return neighbours;
	}
	
	public boolean isPossibleForPerfectMatch(){
		return leftNodes.size() == rightNodes.size();
	}

}
