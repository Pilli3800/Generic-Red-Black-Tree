package redblacktree;

public class RedBlackTreeTest {

	// Test program
	public static void main(String[] args) throws Exception {
		RedBlackTree<Integer> t = new RedBlackTree<Integer>();
		t.insert(1);
		t.insert(2);
		t.insert(40);
		t.insert(50);
		t.insert(10);
		t.insert(61);
		t.insert(41);
		t.insert(86);
		t.insert(89);
		t.insert(9);
		t.printTree();
	}

}
