
import java.util.ArrayList;
import java.util.Stack;

public class AllPaths {

	// the current path
    private Stack<Node> path  = new Stack<Node>();   
    // the set of nodes on the path
    private ArrayList<Node> nodesOnPath  = new ArrayList<Node>(); 
    private ArrayList<ArrayList<Edge>> allPaths = new ArrayList<ArrayList<Edge>>();
    
    public AllPaths(BipartiteGraph G, Node src, Node dst) {
        findAllPaths(G, src, dst);
    }

    // use Depth First Search to find all possible paths between two nodes
    private void findAllPaths(BipartiteGraph G, Node src, Node dst) {

        // add node v to current path from s
        path.push(src);
        nodesOnPath.add(src);

        // found path from s to t - currently prints in reverse order because of stack
        if (src.equals(dst)){
        	
        	ArrayList<Edge> edges = new ArrayList<Edge>(); 
        	for(int i = 0; i < path.size()-1; i++){
        		edges.add(new Edge(path.get(i), path.get(i+1)));	
        	}
        	// add all paths 
        	allPaths.add(edges);
        	
        }

        // consider all neighbors that would continue 
        // path with repeating a node
        else {
            for (Node n : G.getNeighbours(src)) {
                if (!nodesOnPath.contains(n)) findAllPaths(G, n, dst);
            }
        }

        // done exploring from src, so remove from path
        path.pop();
        nodesOnPath.remove(src);
    }
    
    public ArrayList<ArrayList<Edge>> getAllPaths(){
    	return this.allPaths;
    }
    
    public void printAllPaths(){
    	for(int i = 0; i < allPaths.size(); ++i){
    		for(int j = 0; j < allPaths.get(i).size(); ++j){
    			System.out.printf("(%d %d)", allPaths.get(i).get(j).getEdge()[0].getNodeIdx(),
    					allPaths.get(i).get(j).getEdge()[1].getNodeIdx());
    		}
    		System.out.println();
    	}
    }

}
