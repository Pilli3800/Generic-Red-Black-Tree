package redblacktree;

import exception.ExceptionIsEmpty;

// CONSTRUCTION: with no parameters
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x (unimplemented)
// boolean contains( x )  --> Return true if x is found
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print all items

/**
 * Implements a red-black tree. Note that all "matching" is based on the
 * compareTo method.
 * 
 * Source: Data Structures and Problem Solving Using Java
 * 
 * @author Mark Allen Weiss
 */
public class RedBlackTree<T extends Comparable<? super T>> {

	private RedBlackNode<T> header;
	private RedBlackNode<T> nullNode;

	private static final int BLACK = 1; // BLACK must be 1
	private static final int RED = 0;

	// Used in insert routine and its helpers
	private RedBlackNode<T> current;
	private RedBlackNode<T> parent;
	private RedBlackNode<T> grand;
	private RedBlackNode<T> great;

	public RedBlackTree() {
		nullNode = new RedBlackNode<T>(null);
		nullNode.left = nullNode.right = nullNode;
		header = new RedBlackNode<T>(null);
		header.left = header.right = nullNode;
	}

	private final int compare(T item, RedBlackNode<T> t) {
		if (t == header)
			return 1;
		else
			return item.compareTo(t.element);
	}

	public void insert(T item) {
		current = parent = grand = header;
		nullNode.element = item;

		while (compare(item, current) != 0) {
			great = grand;
			grand = parent;
			parent = current;
			current = compare(item, current) < 0 ? current.left : current.right;

			// Check if two red children; fix if so
			if (current.left.color == RED && current.right.color == RED)
				handleReorient(item);
		}

		// Insertion fails if already present
		if (current != nullNode)
			return;
		current = new RedBlackNode<T>(item, nullNode, nullNode);

		// Attach to parent
		if (compare(item, parent) < 0)
			parent.left = current;
		else
			parent.right = current;
		handleReorient(item);
	}

	public void remove(T x) {
		throw new UnsupportedOperationException();
	}

	public T findMin() throws Exception {
		if (isEmpty())
			throw new ExceptionIsEmpty("Red Black Tree Empty");

		RedBlackNode<T> itr = header.right;

		while (itr.left != nullNode)
			itr = itr.left;

		return itr.element;
	}

	public T findMax() throws Exception {
		if (isEmpty())
			throw new ExceptionIsEmpty("Red Black Tree Empty");

		RedBlackNode<T> itr = header.right;

		while (itr.right != nullNode)
			itr = itr.right;

		return itr.element;
	}

	public boolean contains(T x) {
		nullNode.element = x;
		current = header.right;

		for (;;) {
			if (x.compareTo(current.element) < 0)
				current = current.left;
			else if (x.compareTo(current.element) > 0)
				current = current.right;
			else if (current != nullNode)
				return true;
			else
				return false;
		}
	}

	public void makeEmpty() {
		header.right = nullNode;
	}

	public void printTree() {
		printTree(header.right);
	}

	private void printTree(RedBlackNode<T> t) {
		if (t != nullNode) {
			printTree(t.left);
			System.out.print("[Color: " + t.color + "] " + t.element + " - ");
			printTree(t.right);
		}
	}

	public boolean isEmpty() {
		return header.right == nullNode;
	}

	private void handleReorient(T item) {
		// Do the color flip
		current.color = RED;
		current.left.color = BLACK;
		current.right.color = BLACK;

		if (parent.color == RED) // Have to rotate
		{
			grand.color = RED;
			if ((compare(item, grand) < 0) != (compare(item, parent) < 0))
				parent = rotate(item, grand); // Start dbl rotate
			current = rotate(item, great);
			current.color = BLACK;
		}
		header.right.color = BLACK; // Make root black
	}

	private RedBlackNode<T> rotate(T item, RedBlackNode<T> parent) {
		if (compare(item, parent) < 0)
			return parent.left = compare(item, parent.left) < 0 ? rotateWithLeftChild(parent.left) : // LL
					rotateWithRightChild(parent.left); // LR
		else
			return parent.right = compare(item, parent.right) < 0 ? rotateWithLeftChild(parent.right) : // RL
					rotateWithRightChild(parent.right); // RR
	}

	private static <T> RedBlackNode<T> rotateWithLeftChild(RedBlackNode<T> k2) {
		RedBlackNode<T> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		return k1;
	}

	private static <T> RedBlackNode<T> rotateWithRightChild(RedBlackNode<T> k1) {
		RedBlackNode<T> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		return k2;
	}

	private static class RedBlackNode<T> {

		T element; // The data in the node
		RedBlackNode<T> left; // Left child
		RedBlackNode<T> right; // Right child
		int color; // Color

		// Constructors
		RedBlackNode(T theElement) {
			this(theElement, null, null);
		}

		RedBlackNode(T theElement, RedBlackNode<T> lt, RedBlackNode<T> rt) {
			element = theElement;
			left = lt;
			right = rt;
			color = RedBlackTree.BLACK;
		}

	}

}
