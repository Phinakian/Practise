
public class Vertex<E> {
	public E data;
	public EdgeList<E> connections;
	public Vertex(E data) {
		this.data = data;
		connections = new EdgeList<E>();
	}
	@Override
	public String toString() {
		return "["+data+"]";
	}
}
