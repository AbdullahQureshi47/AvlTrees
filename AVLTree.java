import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {
	
	AVLNode root;
	int height;

	
	/**
	 * @return the root
	 */
	public AVLNode getRoot() {
		return root;
	}


	/**
	 * @param root the root to set
	 */
	public void setRoot(AVLNode root) {
		this.root = root;
	}


	/**
	 * @param value The value added to the node
	 * @param node The node that the new node is added to.
	 * @return AVLNode 
	 */
	public AVLNode insert(int value,AVLNode node)
	{
		if(node==null)
		return new AVLNode(value);
		
		if(value<node.key)
			node.leftChild=this.insert(value, node.leftChild);
		else if (value>=node.key)
			node.rightChild=this.insert(value, node.rightChild);
			
		// If during insertion the node became unbalanced then the following statements perform
		// a set of rotations to balance the tree
		
		//Right Rotation
		if(this.balanceFactor(node)==2 && this.balanceFactor(node.rightChild)==1)
			return this.leftRotation(node);
		//Left Rotation
		if(this.balanceFactor(node)==-2 && this.balanceFactor(node.leftChild)==-1)
			return this.rightRotation(node);
		//RightLeft rotation
		if(this.balanceFactor(node)==2 && this.balanceFactor(node.rightChild)==-1)
			 {node.rightChild=this.rightRotation(node.rightChild);
			return this.leftRotation(node);}
		//LeftRight rotation	
		if(this.balanceFactor(node)==-2 && this.balanceFactor(node.leftChild)==1)
			{node.leftChild=this.leftRotation(node.leftChild);
			return this.rightRotation(node);}
		
		return node;
			
	
	}
	
	/**
	 * @return the maximum path from the current to the leaf node
	 */
	public int getHeight(AVLNode node) {
		
		if(node==null)
			return -1;
		else
		{
			int leftH=this.getHeight(node.leftChild);
			int rightH = this.getHeight(node.rightChild);
		//Returns the maximum height
		if(leftH>rightH)
		return (leftH+1);
		else
			return (rightH+1);
	}
	}
	
	/**
	 * @param node
	 * @return The difference in the heights of the left subtree and the right subtree.
	 */
	public int balanceFactor(AVLNode node)
	{ if (node==null)
		return 0;
	else
		return this.getHeight(node.rightChild)-this.getHeight(node.leftChild);
	}
	
	/**
	 * @param node
	 * @return AVLNode
	 * A function that right rotates the node
	 */
	public AVLNode rightRotation(AVLNode node)
	{
		AVLNode newRoot=node.leftChild;
		AVLNode RCLCOOR=newRoot.getRightChild();

		// Now we perform the rotation
		newRoot.rightChild=node;
		node.leftChild=RCLCOOR;
		
		root=newRoot;
		
		
		return newRoot;
		
		
	}
	
	/**
	 * @param node
	 * @return AVLNode
	 * A function that left rotates the node
	 */
	public AVLNode leftRotation(AVLNode node)
	
	{
		AVLNode newRoot=node.rightChild;
		AVLNode LCRCDOOR=newRoot.getLeftChild();
		
		//Now we perform the rotations
		newRoot.leftChild=node;
		node.rightChild=LCRCDOOR;
		
		root=newRoot;
		
		return newRoot;
	}
	/** Traverses the tree in breadth-first order.
	 *Taken from the binaryLab class
    @param action an object that will act on all the nodes in the tree
*/
	public void breadthFirstTraversal(NodeVisitor action)
	{
		Queue<Object> queue = new LinkedList<Object>();
		queue.add(this.root);
		while( ! queue.isEmpty() )
		{
			AVLNode myNode = (AVLNode)queue.remove();
			if ( !(myNode==null) )
            {
    			action.visit(myNode.getKey());  			
    			queue.add(myNode.leftChild);
    			queue.add(myNode.rightChild);
            }
		}
	}	

	public static void main(String args[])
	{
		AVLTree myTree=new AVLTree();
		
		//Add all these elements to the tree
		myTree.root=myTree.insert(10, myTree.root);
		myTree.root=myTree.insert(5, myTree.root);
		myTree.root=myTree.insert(13, myTree.root);
		myTree.root=myTree.insert(4, myTree.root);
		myTree.root=myTree.insert(8, myTree.root);
		myTree.root=myTree.insert(9, myTree.root);
		myTree.root=myTree.insert(3, myTree.root);
		
		
		NodeVisitor printer=new PrintAction();
		
		myTree.breadthFirstTraversal(printer);
		System.out.println("Depth");
		System.out.println(myTree.getHeight(myTree.root));
		
	}
}
