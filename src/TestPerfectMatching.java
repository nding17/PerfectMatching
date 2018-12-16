
public class TestPerfectMatching {

	public static void main(String[] args) {
	
		
		Node n0 = new Node(0, new int[] {8,9,10,12}); 
		Node n1 = new Node(1, new int[] {9,11});
		Node n2 = new Node(2, new int[] {9,10,11});
		Node n3 = new Node(3, new int[] {10});
		Node n4 = new Node(4, new int[] {11,12,14});
		Node n5 = new Node(5, new int[] {12,13});
		Node n6 = new Node(6, new int[] {10,12,15});
		Node n7 = new Node(7, new int[] {14,15});
		Node n8 = new Node(8, new int[] {0});
		Node n9 = new Node(9, new int[] {0,1,2});
		Node n10 = new Node(10, new int[] {0,2,3,6});
		Node n11 = new Node(11, new int[] {1,2,4});
		Node n12 = new Node(12, new int[] {0,4,5,6});
		Node n13 = new Node(13, new int[] {5});
		Node n14 = new Node(14, new int[] {4,7});
		Node n15 = new Node(15, new int[] {6,7});
		
		
		
		Node[] leftNodes = {n0,n1,n2,n3,n4,n5,n6,n7};
		Node[] rightNodes = {n8,n9,n10,n11,n12,n13,n14,n15};
		
		
		/*
		Node n0 = new Node(0, new int[] {8,10}); 
		Node n1 = new Node(1, new int[] {6,7,11});
		Node n2 = new Node(2, new int[] {6,9,10});
		Node n3 = new Node(3, new int[] {8,10,11});
		Node n4 = new Node(4, new int[] {7,9,11});
		Node n5 = new Node(5, new int[] {7,8,9});
		Node n6 = new Node(6, new int[] {1,2});
		Node n7 = new Node(7, new int[] {1,4,5});
		Node n8 = new Node(8, new int[] {0,3,5});
		Node n9 = new Node(9, new int[] {2,4,5});
		Node n10 = new Node(10, new int[] {0,2,3});
		Node n11 = new Node(11, new int[] {1,3,4});
		
		Node[] leftNodes = {n0,n1,n2,n3,n4,n5};
		Node[] rightNodes = {n6,n7,n8,n9,n10,n11};
		*/
		
		/*
		Node n0 = new Node(0, new int[] {2,3}); 
		Node n1 = new Node(1, new int[] {2,3});
		Node n2 = new Node(2, new int[] {0,1});
		Node n3 = new Node(3, new int[] {0,1});
		
		Node[] leftNodes = {n0,n1};
		Node[] rightNodes = {n2,n3};
		*/
		
		BipartiteGraph graph = new BipartiteGraph(leftNodes, rightNodes);
		
		PerfectMatching perfectMatch = new PerfectMatching(graph);
		perfectMatch.findPerfectMatching();
		perfectMatch.printPerfectlyMatchedNodes();
		
	}

}
