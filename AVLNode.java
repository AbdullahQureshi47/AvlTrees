
/**
 * @author Abdullah 
 * @Date 11/10/18
 * This class creates a node with a value given by the user
 *
 */
public class AVLNode {

	int key;
	AVLNode leftChild,rightChild;
	
	

	/**
	 * @return the leftChild
	 */
	public AVLNode getLeftChild() {
		return leftChild;
	}

	/**
	 * @return the rightChild
	 */
	public AVLNode getRightChild() {
		return rightChild;
	}

	public AVLNode(int d)
	{
		key=d;
	}
	
	/**
	 * @return the key
	 */
	public int getKey() {
		return key;
	}
	
}
