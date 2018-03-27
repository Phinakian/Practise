import java.util.Comparator;

public class BiTree<E> {
	private BiTreeNode<E> root;
	protected Comparator<E> comparator;
	protected static class BiTreeNode<E> extends TreeNode<BiTreeNode<E>,E>{
		public BiTreeNode(E key, BiTreeNode<E> parent, BiTreeNode<E> leftChild, BiTreeNode<E> rightChild) {
			super(key,parent,leftChild,rightChild);
		}
	}
	public BiTree() {
		root = null;
		comparator = null;
	}
	public BiTree(E key) {
		root = new BiTreeNode<E>(key,null,null,null);
		comparator = null;
	}
	public BiTree(Comparator<E> comparator) {
		root = null;
		this.comparator = comparator;
	}
	@SuppressWarnings("unchecked")
	protected int compare(E o1,E o2) {
		if(comparator != null) {
			return comparator.compare(o1, o2);
		}
		return ((Comparable<E>)o1).compareTo(o2);
	}
	protected BiTreeNode<E> getNode(BiTreeNode<E> node, E key) {
		if(node == null) {
			return null;
		} else {
			BiTreeNode<E> tempNode = null;
			if(compare(key, node.key) == 0) {
				return node;
			}
			if(compare(key, node.key) < 0) {
				tempNode = getNode(node.leftChild,key);
			} 
			if(compare(key, node.key) > 0) {
				tempNode = getNode(node.rightChild,key);
			}
			return tempNode;
		}
	}
	protected BiTreeNode<E> getDescendantNode(BiTreeNode<E> node){
		if(node.rightChild != null) {
			node = node.rightChild;
			while(node.leftChild != null) {
				node = node.leftChild;
			}
			return node;
		} else {
			while(node.parent != null) {
				if(node == node.parent.leftChild) {
					return node.parent;
				}
				if(node == node.parent.rightChild) {
					node = node.parent;
				}
			}
			return null;
		}
	}
	protected <T extends TreeNode<T,E>> T addNote(T addedNode,T startNode) {
		if(getNode(root,addedNode.key) != null) {
			return null;
		} else {
			while(startNode != null) {
			    if(compare(addedNode.key, startNode.key) < 0) {
			    	if(startNode.leftChild != null) {
			    		startNode = startNode.leftChild;
			    	} else {
			    		addedNode.parent = startNode;
			    		startNode.leftChild = addedNode;
			    		return addedNode;
			    	}
			    } 
			    if(compare(addedNode.key, startNode.key) > 0) {
			    	if(startNode.rightChild != null) {
			    		startNode = startNode.rightChild;
			    	} else {
			    		addedNode.parent = startNode;
			    		startNode.rightChild = addedNode;
			    		return addedNode;
			    	}
			    }
			    if(compare(addedNode.key, startNode.key) == 0) {
			    	return null;
			    }
			}
		}
		return null;
	}
	public boolean isEmpty() {
		return root == null;
	}
	public boolean add(E key) {
		if (isEmpty()) {
			root = new BiTreeNode<E>(key,null,null,null);
			return true;
		} else {
			return addNote(new BiTreeNode<E>(key,null,null,null),root) != null;
		}
	}
	public boolean remove(E key) {
		BiTreeNode<E> removedNode = getNode(root,key);
		if(removedNode.leftChild == null && removedNode.rightChild == null) {
			if(removedNode == removedNode.parent.leftChild) {
				removedNode.parent.leftChild = null;
			}
			if(removedNode == removedNode.parent.rightChild) {
				removedNode.parent.rightChild = null;
			}
		}
		if(removedNode.leftChild != null && removedNode.rightChild == null || removedNode.rightChild != null && removedNode.leftChild == null) {
			if(removedNode.leftChild != null) {
				removedNode.leftChild.parent = removedNode.parent;
				if(compare(removedNode.leftChild.key, removedNode.parent.key) < 0) {
					removedNode.parent.leftChild = removedNode.leftChild;
				} else {
					removedNode.parent.rightChild = removedNode.leftChild;
				}
			} else {
				removedNode.rightChild.parent = removedNode.parent;
				if(compare(removedNode.rightChild.key, removedNode.parent.key) < 0) {
					removedNode.parent.leftChild = removedNode.rightChild;
				} else {
					removedNode.parent.rightChild = removedNode.rightChild;
				}
			}
		}
		if(removedNode.leftChild != null && removedNode.rightChild != null){
			BiTreeNode<E> desNode = getDescendantNode(removedNode);
			removedNode.key = desNode.key;
			if(desNode == desNode.parent.leftChild) {
				desNode.parent.leftChild = null;
			}
			if(desNode == desNode.parent.rightChild) {
				desNode.parent.rightChild = null;
			}
		}
		return false;
	}
}
