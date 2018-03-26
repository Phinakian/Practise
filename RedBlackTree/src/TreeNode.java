
public class TreeNode<T,E> {
	E key;
	protected T parent;
	protected T leftChild;
	protected T rightChild;
	public TreeNode(E key, T parent, T leftChild, T rightChild) {
		this.key = key;
		this.parent = parent;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	@Override
	public String toString() {
		return key.toString();
	}
}
