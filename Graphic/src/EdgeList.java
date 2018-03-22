import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class EdgeList<E> implements List<Edge<E>> {
	private int size = 0;
	private int modCount = 0;
	Node<E> head;
	Node<E> last;
	
	private static class Node<E> {
		Edge<E> edge;
		Node<E> next;
		Node<E> prev;
		public Node(Edge<E> edge,Node<E> prev,Node<E> next) {
			this.edge = edge;
			this.prev = prev;
			this.next = next;
		}
		
		@Override
		public String toString() {
			return edge + ",";
		}
	}
	
	private class EdgeIterator implements ListIterator<Edge<E>> {
		private Node<E> lastNode;
		private Node<E> cursor;
        private int nextIndex;
        private int expectedModCount = modCount;
        
		public EdgeIterator() {
			this.nextIndex = 0;
			this.lastNode = null;
			this.cursor = head;
		}
		
		public EdgeIterator(int index) {
			this.nextIndex = index;
			this.lastNode = null;
			this.cursor = getNode(index);
		}
		
		@Override
		public boolean hasNext() {
			return nextIndex < size;
		}
		
		@Override
		public Edge<E> next() {
			if(modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			if(hasNext()) {
				lastNode = cursor;
				cursor = cursor.next;
				nextIndex++;
				return lastNode.edge;
			} else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public boolean hasPrevious() {
			return nextIndex > 0;
		}
		
		@Override
		public Edge<E> previous() {
			if(modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			if(hasPrevious()) {
				if(cursor == null) {
					cursor = last;
				} else {
					cursor = cursor.prev;
				}
				lastNode = cursor;
				nextIndex--;
				return lastNode.edge;
			} else {
				throw new NoSuchElementException();
			}
		}
		
		@Override
		public int nextIndex() {
			return nextIndex;
		}
		
		@Override
		public int previousIndex() {
			return nextIndex - 1;
		}
		
		@Override
		public void remove() {
			if(modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			if(cursor.prev == null) {
				head = lastNode.next;
				lastNode.next.prev = null;
				cursor = lastNode.next;
			} else if(cursor.next == null) {
				last = cursor;
				lastNode.prev.next = null;
				cursor = lastNode.prev;
				nextIndex--;
			} else {
				lastNode.next.prev = lastNode.prev;
				lastNode.prev.next = lastNode.next;
				cursor = lastNode.prev;
				nextIndex--;
			}
			lastNode = null;
			size--;		
			modCount++;
			expectedModCount++;
		}
		
		@Override
		public void set(Edge<E> e) {
			if(lastNode == null) {
                throw new IllegalStateException();
			}
			if(modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
            lastNode.edge = e;
		}
		
		@Override
		public void add(Edge<E> e) {
			if(modCount != expectedModCount) {
				throw new ConcurrentModificationException();
			}
			Node<E> newNode = new Node<E>(e,cursor.prev,cursor);
			if(cursor.prev == null) {
				head = newNode;
			} else {
				cursor.prev.next = newNode;
			}
			cursor.prev = newNode;
			lastNode = null;
			size++;
			modCount++;
			expectedModCount++;
		}	
	}
	
	private Node<E> getNode(int index) {
		Node<E> node = head;
		if(index < size && index > 0) {
			for(int i = 0; i < index; i++) {
				node = node.next;
			}	
		} else {
			throw new IndexOutOfBoundsException();
		}
		return node;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}
	
	@Override
	public Iterator<Edge<E>> iterator() {
		return new EdgeIterator();
	}
	
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		int i = 0;
		Node<E> node = head;
		while(node != null) {
			result[i] = node.edge;
			i++;
			node = node.next;
		}
		return result;
	}
	
	@Override
	public <T> T[] toArray(T[] a) {
		//Unimplemented
		return null;
	}
	
	@Override
	public boolean add(Edge<E> edge) {
		if(head == null) {
			head = new Node<E>(edge,null,null);
			last = head;
		} else {
			Node<E> tempNode = head;
			while(tempNode.next != null) {
				tempNode = tempNode.next;
			}
			tempNode.next = new Node<E>(edge,tempNode,null);
			last = tempNode.next;
		}
		size++;
		modCount++;
		return true;
	}
	
	@Override
	public boolean remove(Object o) {
		if(o == null) {
			throw new NullPointerException();
		} else {
			if(isEmpty()) {
				return false;
		    } else {
		    	Node<E> tempNode;
			    for(tempNode = head;tempNode.next != null;tempNode = tempNode.next) {
				    if(tempNode.edge.equals(o)) {
					    break;
			        }
		        }
		        if(tempNode.next != null)
		        {
			        tempNode.next.prev = tempNode.prev;
			        tempNode.prev.next = tempNode.next;
			        size--;
			        modCount++;
			        return true;
		        } else if(tempNode.edge.equals(o)) {
		    	    last = tempNode.prev;
			        tempNode.prev.next = null;
			        size--;
			        modCount++;
			        return true;
		        } else {
		         	return false;
		        }
		    }
		   
		}
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		Object[] arr = c.toArray();
		for(int i = 0; i < arr.length; i++) {
			if(indexOf(arr[i]) == -1) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean addAll(Collection<? extends Edge<E>> c) {
		return addAll(size, c);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(int index, Collection<? extends Edge<E>> c) {
		Object[] arr = c.toArray();
		if(arr.length == 0) {
			return false;
		}
		if(index == size) {
			for(int i = 0; i < arr.length; i++) {
				add((Edge<E>)arr[i]);
			}
		} else {
			for(int i = 0; i < arr.length; i++) {
				add(index + i,(Edge<E>)arr[i]);
			}
		}
		return true;
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		for(Node<E> node = head; node.next != null; node = node.next) {
			if(c.contains(node)) {
				node.next.prev = node.prev;
				node.prev.next = node.next;
		        size--;
		        modCount++;
			}
		}
		return true;
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		for(Node<E> node = head; node.next != null; node = node.next) {
			if(!c.contains(node)) {
				node.next.prev = node.prev;
				node.prev.next = node.next;
		        size--;
		        modCount++;
			}
		}
		return true;
	}
	
	@Override
	public void clear() {
		Node<E> node = head;
		while (node != null) {
            Node<E> next = node.next;
            node.edge = null;
            node.next = null;
            node.prev = null;
            node = next;
        }
        head = last = null;
        size = 0;
        modCount++;
	}
	
	@Override
	public Edge<E> get(int index) {
		return getNode(index).edge;
	}
	
	@Override
	public Edge<E> set(int index, Edge<E> edge) {
		Node<E> oldNode = getNode(index);
		getNode(index).edge = edge;
		return oldNode.edge;
	}
	
	@Override
	public void add(int index, Edge<E> edge) {
		if(index == size) {
			add(edge);
		} else {
			Node<E> node = getNode(index);
			Node<E> newNode = new Node<E>(edge,node.prev,node);
			node.prev.next = newNode;
			node.prev = newNode;
			size++;
			modCount++;
			}
	}
	
	@Override
	public Edge<E> remove(int index) {
		Node<E> deleteNode;
		if(index == size) {
			deleteNode = last;
			last.prev.next = null;
			last = last.prev;
		} else if(index == 0) {
			deleteNode = head;
			head.next.prev = null;
			head = head.next;
		} else {
			deleteNode = getNode(index);
			deleteNode.prev.next = deleteNode.next;
			deleteNode.next.prev = deleteNode.prev;
		}
		size--;
		modCount++;
		return deleteNode.edge;
	}
	
	@Override
	public int indexOf(Object o) {
		if(o == null) {
			throw new NullPointerException();
		} else {
			if(!isEmpty()) {
				int index = 0;
				Node<E> tempNode;
				for (tempNode = head; tempNode.next != null; tempNode = tempNode.next) {
					if(tempNode.edge.equals(o)) {
						return index;
					}
					index++;
				}
			}
			return -1;
		}
	}
	
	@Override
	public int lastIndexOf(Object o) {
		if(o == null) {
			throw new NullPointerException();
		} else {
			int index = size;
			Node<E> tempNode;
			for(tempNode = last;tempNode.prev != null;tempNode = tempNode.prev) {
				if(tempNode.edge.equals(o)) {
					return index;
				}
				index--;
			}
			return -1;
		}
	}
	
	@Override
	public ListIterator<Edge<E>> listIterator() {
		return new EdgeIterator();
	}
	
	@Override
	public ListIterator<Edge<E>> listIterator(int index) {
		return new EdgeIterator(index);
	}
	
	@Override
	public List<Edge<E>> subList(int fromIndex, int toIndex) {
		EdgeList<E> result = new EdgeList<E>();
		for(int i = fromIndex;i < toIndex;i++) {
			result.add(getNode(i).edge);
		}
		return result;
	}
	@Override
	public String toString() {
		return super.toString();
	}
	
}
