
public class Edge<E> {
	public Vertex<E> start,end;
	public int weight;
	public Edge(Vertex<E> start,Vertex<E> end,int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		return (this.start==((Edge<E>)obj).start)&&(this.end==((Edge<E>)obj).end);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+start+"-->"+end+"]";
	}
}
