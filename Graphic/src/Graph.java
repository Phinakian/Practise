import java.util.ArrayList;
import java.util.Iterator;

public class Graph<E>{
	private ArrayList<Vertex<E>> vertexes;
	private int size;
	public Graph(E[] vex)
	{
		vertexes = new ArrayList<Vertex<E>>();
		size = vex.length;
		for(int i=0;i<vex.length;i++)
			vertexes.add(new Vertex<E>(vex[i]));
		
	}
	/*public Graph(E[] vex,int[][] arc)
	{
		vertexes = new Vertex<E>();
		for(int i=0;i<vex.length;i++)
			vertexes.data = vex[i];
		edges=arc;
	}*/
/*	public int firstAdjVex(int Vex)
	{
		if(Vex<0||Vex>size)
		{
			for(int i=0;i<size;i++)
				if(edges.GetNode(Vex).data.GetNode(i).data==1)
					return i;
			return -1;
		}
		else
		{
			System.out.println("Error");
			return -2;
		}
	}
	public int nextAdjVex(int Vex,int Arc)
	{
		if(Vex<0||Vex>size)
		{
			for(int i=Arc+1;i<size;i++)
				if(edges.GetNode(Vex).data.GetNode(i).data==1)
					return i;
			return -1;
		}
		else
		{
			System.out.println("Error");
			return -2;
		}
	}
	{
		this.vertexes.add(x);
		this.size++;
	}*/
/*	void addVex(E x,int pos)
	{
		this.vertexes.InsertNode(x,pos);
		this.edges.InsertNode(new LinkList<Integer>(),pos);
		for(int i=0;i<size;i++)
		{
			this.edges.GetNode(pos).data.InsertNode(0);
			this.edges.GetNode(i).data.InsertNode(0,pos);
		}
		this.size++;
		
	}*/
	public Vertex<E> getVex(int index) {
		return vertexes.get(index);
	}
	public void setVex(int index,Vertex<E> e) {
		
	}
	public boolean addVex(Vertex<E> x)
	{
		for(int i=0;i<x.connections.size();i++) {
			if((x.connections.get(i).start!=null)&&(x.connections.get(i).start!=x)) {
				x.connections.get(i).start.connections.add(x.connections.get(i));
			}
			if((x.connections.get(i).end!=null)&&(x.connections.get(i).end!=x)) {
				x.connections.get(i).end.connections.add(x.connections.get(i));
			}
		}
		this.vertexes.add(x);
		this.size++;
		return true;
	}
	public void removeVex(Vertex<E> x)
	{
		for(int i=0;i<x.connections.size();i++) {
			if(x.connections.get(i).end.equals(x)) {
				x.connections.get(i).start.connections.remove(x.connections.get(i));
			}
			if(x.connections.get(i).start.equals(x)) {
				x.connections.get(i).end.connections.remove(x.connections.get(i));
			}
		}
		this.vertexes.remove(x);
		this.size--;
	}
	public void addEdge(Vertex<E> src,Vertex<E> dest,int weight)
	{
		Edge<E> tempEdge = new Edge<E>(src,dest,weight);
		if(src.connections.contains(tempEdge)) {
			src.connections.remove(tempEdge);
		}
		if(dest.connections.contains(tempEdge)) {
			dest.connections.remove(tempEdge);
		}
		src.connections.add(tempEdge);
		dest.connections.add(tempEdge);
	}
	public void removeEdge(Vertex<E> src,Vertex<E> dest)
	{
		Edge<E> tempEdge = new Edge<E>(src,dest,0);
		src.connections.remove(tempEdge);
		dest.connections.remove(tempEdge);
	}
//	void DFSTraverse();
//	void BFSTraverse();
	@Override
	public String toString() {
		String str = "¶¥µã£º"+vertexes+"±ß£º";
		for(int i=0;i<size;i++)
		{
			str += vertexes.get(i).connections;
		}
		return str;
	}
}