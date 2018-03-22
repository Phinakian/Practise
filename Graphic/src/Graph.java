import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class Graph<E> {
	private ArrayList<Vertex<E>> vertexes;
	private int size;
	private HashSet<Vertex<E>> visitedVex;

	public Graph(E[] vex) {
		vertexes = new ArrayList<Vertex<E>>();
		size = vex.length;
		for (int i = 0; i < vex.length; i++)
			vertexes.add(new Vertex<E>(vex[i]));
		visitedVex = new HashSet<Vertex<E>>();
	}

	/*
	 * public Graph(E[] vex,int[][] arc) { vertexes = new Vertex<E>(); for(int
	 * i=0;i<vex.length;i++) vertexes.data = vex[i]; edges=arc; }
	 */
	public Vertex<E> firstAdjVex(Vertex<E> vex) {
		if (vertexes.contains(vex)) {
			for(Edge<E> edge : getVex(vex).connections) {
				if (edge.start.equals(vex)) {
					return edge.end;
				}
			}
			return null;
		} else {
			System.out.println("Error");
			return null;
		}
	}

	public Vertex<E> nextAdjVex(Vertex<E> vex, Vertex<E> adjVex) {
		if (vertexes.contains(vex)) {
			ListIterator<Edge<E>> iterator = getVex(vex).connections.listIterator();
			while (iterator.hasNext()) {
				if (iterator.next().equals(new Edge<E>(vex,adjVex,0))) {
					try {
						while (!iterator.next().start.equals(vex));
						return iterator.previous().end;
					} catch (NoSuchElementException e) {
						return null;
					}
				}
			}
			
		} else {
			System.out.println("Error");
			return null;
		}
		return null;
	}
	public Vertex<E> getVex(int index) {
		return vertexes.get(index);
	}

	public Vertex<E> getVex(Vertex<E> vex) {
		ListIterator<Vertex<E>> iterator = vertexes.listIterator();
		while(iterator.hasNext()) {
			if(iterator.next().equals(vex)) {
				return iterator.previous();
			}
		}
		return null;
	}

	public void setVex(int index, Vertex<E> e) {

	}

	public boolean addVex(Vertex<E> x) {
		for (int i = 0; i < x.connections.size(); i++) {
			if ((x.connections.get(i).start != null) && (x.connections.get(i).start != x)) {
				x.connections.get(i).start.connections.add(x.connections.get(i));
			}
			if ((x.connections.get(i).end != null) && (x.connections.get(i).end != x)) {
				x.connections.get(i).end.connections.add(x.connections.get(i));
			}
		}
		this.vertexes.add(x);
		this.size++;
		return true;
	}

	public void removeVex(Vertex<E> x) {
		for (int i = 0; i < x.connections.size(); i++) {
			if (x.connections.get(i).end.equals(x)) {
				x.connections.get(i).start.connections.remove(x.connections.get(i));
			}
			if (x.connections.get(i).start.equals(x)) {
				x.connections.get(i).end.connections.remove(x.connections.get(i));
			}
		}
		this.vertexes.remove(x);
		this.size--;
	}

	public void addEdge(Vertex<E> src, Vertex<E> dest, int weight) {
		Edge<E> tempEdge = new Edge<E>(src, dest, weight);
		if (src.connections.contains(tempEdge)) {
			src.connections.remove(tempEdge);
		}
		if (dest.connections.contains(tempEdge)) {
			dest.connections.remove(tempEdge);
		}
		src.connections.add(tempEdge);
		dest.connections.add(tempEdge);
	}

	public void removeEdge(Vertex<E> src, Vertex<E> dest) {
		Iterator<Edge<E>> iterator = src.connections.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().end.equals(dest)) {
				iterator.remove();
			}
		}
		iterator = dest.connections.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().start.equals(src)) {
				iterator.remove();
			}
		}
	}

	public void depthFirstSearchTraverse(Vertex<E> vex) {
		Vertex<E> adjVex;
		System.out.println(vex);
		visitedVex.add(vex);
		adjVex = firstAdjVex(vex);
		while (adjVex != null) {
			if (!visitedVex.contains(adjVex)) {
				depthFirstSearchTraverse(adjVex);
			}
			adjVex = nextAdjVex(vex, adjVex);
		}
	}

	// void breadthFirstSearchTraverse();
	@Override
	public String toString() {
		String str = "¶¥µã£º" + vertexes + "±ß£º";
		for (int i = 0; i < size; i++) {
			str += vertexes.get(i).connections;
		}
		return str;
	}

}