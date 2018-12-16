import java.util.ArrayList;

public class PerfectMatching {
	
	private BipartiteGraph graph;
	private ArrayList<Edge> perfectMatch;
	private boolean isPerfectMatch;
	
	private ArrayList<Node> unmatchedLeftNodes;
	private ArrayList<Node> unmatchedRightNodes;
	
	// main constructor, this is where we integrate 
	// all algorithms to find the perfect matching
	public PerfectMatching(BipartiteGraph graph){
		this.graph = graph;
		// first of all, let's try our luck to use
		// greedy algorithm to extract as many pairs of
		// matched nodes as possible
		GreedyMatching greedyMatch = new GreedyMatching(graph);
		greedyMatch.findGreedyMatching();
		perfectMatch = greedyMatch.getEdges();
		isPerfectMatch = true;
		greedyMatch.searchUnmatchedNodes();
		
		// store the unmatched nodes left and right, 
		// we are going to find the augmenting path between
		// nodes from different gruop
		unmatchedLeftNodes = greedyMatch.getUnmatchedLeftNodes();
		unmatchedRightNodes = greedyMatch.getUnmatchedRightNodes();
	}
	
	public void findPerfectMatching(){
		
		for(int i = 0; i < unmatchedLeftNodes.size(); ++i){
			for(int j = 0; j < unmatchedRightNodes.size(); ++j){
				
				// randomly choosing two unmatched nodes from
				// either side
				Node leftNode = unmatchedLeftNodes.get(i),
						rightNode = unmatchedRightNodes.get(j);
				
				// we apply our depth first search algorithm to find
				// all possible paths between these two nodes by brute
				// force
				AllPaths paths = new AllPaths(this.graph, leftNode, rightNode);
				ArrayList<ArrayList<Edge>> allPaths = paths.getAllPaths();
	
				ArrayList<Edge> edges = findAugmentPath(allPaths);
				
				boolean isAugPathFound = edges != null;
				
				if(isAugPathFound){
					// if we have found the augmenting path
					// by theory, we are guaranteed to match 
					// a new pair of nodes
					leftNode.setMark();
					rightNode.setMark();
					this.unmatchedLeftNodes.set(i,null);
					this.unmatchedRightNodes.set(j,null);
					// after we found a new pair
					// the perfect match edges need to be updated 
					updatePerfectMatch(edges);
					
				}
			}
		}
		
		for(int m = 0; m < this.unmatchedLeftNodes.size(); ++m){
			if(this.unmatchedLeftNodes.get(m) != null){
				this.isPerfectMatch = false;
			}
		}
	}
	
	
	public ArrayList<Edge> findAugmentPath(ArrayList<ArrayList<Edge>> allPaths){
    	for(int i = 0; i < allPaths.size(); ++i){
    		ArrayList<Edge> path = allPaths.get(i);
    		boolean isValidLength = path.size() > 1;
    		int count = 0;
    		if(isValidLength){
    			
    			for(int j = 1; j < path.size(); j+=2){
    				for(int k = 0; k < perfectMatch.size(); k++){
    					if(isSameEdge(path.get(j), perfectMatch.get(k))){
    						count++;
    					}
    				}
    			}
    		}
    		if(count == (path.size()-1)/2){
    			return path;
    		}
    	}
    	return null;
    }

	
	public void updatePerfectMatch(ArrayList<Edge> path){
		
		boolean isAugPath = path != null;
		if(isAugPath){
			for(int i = 0; i < path.size()-1; i+=2){
				for(int j = 0; j < perfectMatch.size(); ++j){	
					// the augmenting path should always have every
	    			// second edge that's the same as one of the perfect
	    			// matching edges. Thus, by theory, we add in the edges
	    			// that are different and delete the common edges
					if(isSameEdge(path.get(i+1), perfectMatch.get(j))){
						perfectMatch.remove(j);
					}
				}
				perfectMatch.add(path.get(i));
			}
		}
	}
	
	public boolean isSameEdge(Edge e1, Edge e2){
		Node e1n1 = e1.getEdge()[0];
		Node e1n2 = e1.getEdge()[1];
		Node e2n1 = e2.getEdge()[0];
		Node e2n2 = e2.getEdge()[1];
		
		if(e1n1 == e2n1 && e1n2 == e2n2){
			return true;
		}else if(e1n1 == e2n2 && e1n2 == e2n1){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isPerfectMatch(){
		return this.isPerfectMatch;
	}
	
	public void printPerfectlyMatchedNodes(){
		
		if(this.isPerfectMatch){
			System.out.println("Perfectly Matched Nodes:");
			for(int i = 0; i < this.perfectMatch.size(); ++i){
				Node n1 = this.perfectMatch.get(i).getEdge()[0];
				Node n2 = this.perfectMatch.get(i).getEdge()[1];
				System.out.printf("|%d| -> |%d|\n", n1.getNodeIdx(), n2.getNodeIdx() );
			}
			System.out.println();
		}else{
			System.out.println("Unmatched Nodes (No Perfect Matching):");
			
			for(int i = 0; i < this.unmatchedLeftNodes.size(); ++i){
				System.out.printf("[%d] ? [%d]\n", this.unmatchedLeftNodes.get(i).getNodeIdx(), 
						this.unmatchedRightNodes.get(i).getNodeIdx());
			}
			System.out.println();
			
			System.out.println("Matched Nodes:");
			for(int i = 0; i < this.perfectMatch.size(); ++i){
				Node n1 = this.perfectMatch.get(i).getEdge()[0];
				Node n2 = this.perfectMatch.get(i).getEdge()[1];
				System.out.printf("|%d| -> |%d|\n", n1.getNodeIdx(), n2.getNodeIdx() );
			}
			System.out.println();
		}
	}
}
