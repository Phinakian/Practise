
public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer[] arr = {8,9,6,5,4,3,2}; 
		Graph<Integer> graph = new Graph<Integer>(arr);
		graph.addVex(new Vertex<Integer>(6));
		graph.addEdge(graph.getVex(4), graph.getVex(2), 4);
		graph.addEdge(graph.getVex(7), graph.getVex(5), 4);
		graph.addEdge(graph.getVex(5), graph.getVex(6), 4);
		graph.addEdge(graph.getVex(0), graph.getVex(5), 4);
		graph.addEdge(graph.getVex(1), graph.getVex(5), 4);
		graph.addEdge(graph.getVex(5), graph.getVex(2), 4);
		graph.addEdge(graph.getVex(5), graph.getVex(3), 4);
		graph.addEdge(graph.getVex(5), graph.getVex(4), 4);
		graph.addEdge(graph.getVex(2), graph.getVex(5), 4); 
		graph.depthFirstSearchTraverse(graph.getVex(0));
//		System.out.println(graph.firstAdjVex(graph.getVex(5)));
//		System.out.println(graph.nextAdjVex(graph.getVex(5),graph.firstAdjVex(graph.getVex(5))));
//		System.out.println(graph.nextAdjVex(graph.getVex(5),graph.getVex(3)));
//		System.out.println(graph.getVex(2));
		EdgeList<Integer> test = new EdgeList<Integer>();
		test.add(new Edge<Integer>(graph.getVex(4), graph.getVex(2), 4));
		test.add(new Edge<Integer>(graph.getVex(7), graph.getVex(5), 4));
		test.add(new Edge<Integer>(graph.getVex(5), graph.getVex(6), 4));
//		test.add(new Edge<Integer>(graph.getVex(0), graph.getVex(5), 4));
//		test.add(new Edge<Integer>(graph.getVex(1), graph.getVex(5), 4));
//		test.remove(2);
		EdgeList<Integer> a = new EdgeList<Integer>();
		a.add(new Edge<Integer>(graph.getVex(3), graph.getVex(6), 4));
		a.add(new Edge<Integer>(graph.getVex(0), graph.getVex(5), 4));
		a.add(new Edge<Integer>(graph.getVex(1), graph.getVex(5), 4));
		test.addAll(1, a);
		test.add(new Edge<Integer>(graph.getVex(1), graph.getVex(5), 4));
		System.out.println(test);
	}

}
