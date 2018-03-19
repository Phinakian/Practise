import java.util.ArrayList;

public class Vertex<E> {
	public E data;
	public ArrayList<Edge<E>> connections;
	public Vertex(E data) {
		this.data = data;
		connections = new ArrayList<Edge<E>>();
	}
	@Override
	public String toString() {
		return "["+data+"]";
	}
}
