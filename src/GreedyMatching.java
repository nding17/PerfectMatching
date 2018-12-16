import java.util.ArrayList;

public class GreedyMatching {
	
	private BipartiteGraph graph;
	private ArrayList<Edge> edges;
	private ArrayList<Node> unmatchedLeftNodes;
	private ArrayList<Node> unmatchedRightNodes;
	
	
	public GreedyMatching(BipartiteGraph graph){
		this.graph = graph;
		edges = new ArrayList<Edge>();
		unmatchedLeftNodes = new ArrayList<Node>();
		unmatchedRightNodes = new ArrayList<Node>();
	}
	
	// greedy algorithm for finding a perfect matching
	// this is what we want to do first, we want to try
	// our luck until we have exhausted our matching
	public void findGreedyMatching(){
		
		if(graph.isPossibleForPerfectMatch()){
			for(int i = 0; i < graph.getLeftNodes().size(); ++i){
				
				Node leftNode = graph.getLeftNodeAt(i);
				ArrayList<Integer> leftAdjNodes = leftNode.getAdjNodesIdx();
				
				for(int j = 0; j < leftAdjNodes.size(); ++j){
					int adjNode = leftAdjNodes.get(j);
					for(int k = 0; k < graph.getRightNodes().size(); ++k){		
						Node rightNode = graph.getRightNodeAt(k);
						
						if(adjNode == rightNode.getNodeIdx() && 
								!leftNode.checkIsMarked() 
								&& !rightNode.checkIsMarked()){
							
							leftNode.setMark();
							rightNode.setMark();
							Edge newEdge = new Edge(leftNode, rightNode);
							edges.add(newEdge);
							break;
						}
					}
				}
			}
		}
	}
	
	// since all matched nodes are marked
	// those unmarked nodes must be unmatched nodes then
	public void searchUnmatchedNodes(){
		
		ArrayList<Node> leftNodes = graph.getLeftNodes();
		ArrayList<Node> rightNodes = graph.getRightNodes();
		
		for(int i = 0; i < leftNodes.size(); ++i){
			if(!leftNodes.get(i).checkIsMarked()){
				unmatchedLeftNodes.add(leftNodes.get(i));
			}
			
			if(!rightNodes.get(i).checkIsMarked()){
				unmatchedRightNodes.add(rightNodes.get(i));
			}
		}
		
	}
	
	public ArrayList<Node> getUnmatchedLeftNodes(){
		return this.unmatchedLeftNodes;
	}
	
	public ArrayList<Node> getUnmatchedRightNodes(){
		return this.unmatchedRightNodes;
	}
	
	public ArrayList<Edge> getEdges(){
		return this.edges;
	}
	
}
