
public class Edge {
	
	private Node n1, n2;
	
	public Edge(Node n1, Node n2){
		this.n1 = n1;
		this.n2 = n2;
	}
	
	public Node[] getEdge(){
		Node[] edge = {n1,n2};
		return edge;
	}
	
}
