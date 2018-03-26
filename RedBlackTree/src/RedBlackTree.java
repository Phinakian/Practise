
public class RedBlackTree<E> extends BiTree<E> {
	private RBNode<E> root;
	private enum Color {
		red,black;
	}
	private static class RBNode<E> extends TreeNode<RBNode<E>,E> {
		Color color = Color.black;
		public RBNode(E key, RBNode<E> parent, RBNode<E> leftChild, RBNode<E> rightChild, Color color) {
			super(key,parent,leftChild,rightChild);
			this.color = color;
		}
	}
	public RedBlackTree() {
		super();
		root = null;
	}
	private void rotateLeft(RBNode<E> rotatedNode) {
		if(rotatedNode.parent != null) {
			if(rotatedNode == rotatedNode.parent.leftChild) {
				rotatedNode.parent.leftChild = rotatedNode.rightChild;
			} else {
				rotatedNode.parent.rightChild = rotatedNode.rightChild;
			}
		}
		rotatedNode.rightChild.parent = rotatedNode.parent;
		rotatedNode.parent = rotatedNode.rightChild;
		rotatedNode.rightChild = rotatedNode.parent.leftChild;
		if(rotatedNode.rightChild != null) {
		    rotatedNode.rightChild.parent = rotatedNode;
		}
		rotatedNode.parent.leftChild = rotatedNode;
	}
	private void rotateRight(RBNode<E> rotatedNode) {
		if(rotatedNode.parent != null) {
			if(rotatedNode == rotatedNode.parent.leftChild) {
				rotatedNode.parent.leftChild = rotatedNode.leftChild;
			} else {
				rotatedNode.parent.rightChild = rotatedNode.leftChild;
			}
		}
		rotatedNode.leftChild.parent = rotatedNode.parent;
		rotatedNode.parent = rotatedNode.leftChild;
		rotatedNode.leftChild = rotatedNode.parent.rightChild;
		if(rotatedNode.leftChild != null) {
			rotatedNode.leftChild.parent = rotatedNode;
		}
		rotatedNode.parent.rightChild = rotatedNode;
	}
	public boolean isEmpty() {
		return root == null;
	}
	public boolean add(E key) {
		RBNode<E> addedNode = new RBNode<E>(key,null,null,null,Color.red);
		if (isEmpty()) {
			root = new RBNode<E>(key,null,null,null,Color.black);
			return true;
		} else {
			if((addedNode = addNote(addedNode,root)) != null) {
				if(addedNode.parent.color == Color.black) {
					return true;
				} else {
					if(addedNode.parent == addedNode.parent.parent.leftChild) {
						if(addedNode.parent.parent.leftChild == null || addedNode.parent.parent.rightChild.color == Color.black) {
							if(addedNode == addedNode.parent.leftChild) {
								Color tempColor = addedNode.parent.parent.color;
								addedNode.parent.parent.color = addedNode.parent.color;
								addedNode.parent.color = tempColor;
								rotateRight(addedNode.parent.parent);
							} else {
								Color tempColor = addedNode.color;
								rotateLeft(addedNode.parent);
								addedNode.color = addedNode.parent.color;
								addedNode.parent.color = tempColor;
								rotateRight(addedNode.parent);
							}
						} else {
							addedNode.parent.parent.leftChild.color = Color.black;
							addedNode.parent.parent.rightChild.color = Color.black;
							addedNode.parent.parent.color = Color.red;
						}
					}
					if(addedNode.parent == addedNode.parent.parent.rightChild) {
						if(addedNode.parent.parent.leftChild == null || addedNode.parent.parent.leftChild.color == Color.black) {
							if(addedNode == addedNode.parent.rightChild) {
								Color tempColor = addedNode.parent.parent.color;
								addedNode.parent.parent.color = addedNode.parent.color;
								addedNode.parent.color = tempColor;
								rotateLeft(addedNode.parent.parent);
							} else {
								Color tempColor = addedNode.color;
								rotateRight(addedNode.parent);
								addedNode.color = addedNode.parent.color;
								addedNode.parent.color = tempColor;
								rotateLeft(addedNode.parent);
							}
						} else {
							addedNode.parent.parent.rightChild.color = Color.black;
							addedNode.parent.parent.leftChild.color = Color.black;
							addedNode.parent.parent.color = Color.red;
						}
					}
				}
				return true;
			} else {
				return false;
			}
		}
	}
}
