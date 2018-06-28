class BinarySearchTree implements BSTInterface {
	protected   String item;
	protected BSTNode left, right;
	protected   BSTNode root;
    protected class BSTNode { 
        String item;
        BSTNode left, right;
          BSTNode(String item) {//Node inner class 
            this.item = item; 
            BSTNode left = null; 
            BSTNode right = null; 
        }
    }
   public BinarySearchTree() { // Binary Search tree constructor 
        root = null; 
    }
 	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}
	public void makeEmpty() {
		root = null;
	}
	public MyQueue inOrder(){
		MyQueue queue = new MyQueue(); 
		inOrderRecursive(root, queue);
		return queue;
	}
	public MyQueue preOrder(){
		MyQueue queue = new MyQueue(); 
		preOrderRecursive(root, queue);
		return queue;
	}
	public MyQueue postOrder(){
		  MyQueue queue = new MyQueue(); 
          postOrderRecursive(root, queue);
          return queue;
	}
	public boolean contains(String s){
	  return recursiveSearch(root, s);
	}
	public void put(String s){
           root = recursiveInsert(root, s);
	}
	public void delete(String s){
         root =recursiveRemove(root,s);
	}
	public void balanceBST(){
		 int count=0;
		MyQueue queue = inOrder();
		 MyQueue tempQueue = new MyQueue(); 
		   while(!queue.isEmpty()){
			queue.dequeue();
            tempQueue.enqueue(root);
			count ++;
		}
		String [] copy = new String[count];
		for(int i=0;i<count;i++){
			 while(!queue.isEmpty()){
			copy[i] = (String)tempQueue.peek();
			tempQueue.dequeue();
		  }
		}
		makeEmpty();
		int first = 0;
		int last = copy.length;
		root = balanceRecursive(copy, first, last-1);
	}
	// TODO: Fill this in and call it from contains()
	protected boolean recursiveSearch(BSTNode node, String s) {
		if (node == null) {
			return false;
		} else if (s.compareTo(node.item) == 0) {
			return true;
		} else if (s.compareTo(node.item) < 0) {
			return recursiveSearch(node.left, s);
} else { // s.compareTo(node.item) > 0
	return recursiveSearch(node.right, s);
}
	}
	// TODO: Fill this in and call it from put()
	protected BSTNode recursiveInsert(BSTNode node, String s){
		if (node == null) {
			node = new BSTNode(s);
		} else if (s.compareTo(node.item) < 0) {
			node.left = recursiveInsert(node.left,s);
		} else if (s.compareTo(node.item) > 0) {
			node.right = recursiveInsert(node.right,s);
                }   // else equal, don’t do anything to t.
          return node;
	}
	// TODO: Fill this in and call it from delete()
	protected BSTNode recursiveRemove(BSTNode node, String s) {
     if (node == null) { // node DNE in tree, do nothing
     } else if (s.compareTo(node.item) < 0) {//s​ is alphabetically before ​node.item​
     	node.left = recursiveRemove(node.left, s);
     } else if (s.compareTo(node.item) > 0) {//s​ is alphabetically after ​node.item​
     	node.right = recursiveRemove(node.right, s);
     } else if (s.compareTo(node.item)==0){ // String s​ equals node.item 
		node = deleteNode(node); 
}
return node;
	}
	// TODO: Fill this in and call it from recursiveRemove()
	protected BSTNode deleteNode(BSTNode node) {
     // handels four cases for removing a node 
		// case 1 no children 
		 if(node.right==null && node.left == null){
		 	node = null; // Leaf node just remove
		 }
		 // case 2 just a left child
		 else if(node.right == null && node.left!=null){
		 	node = node.left; 
		 }
		 // case 3 just a right child
		 else if(node.right != null && node.left==null){
		 	node = node.right; 
		 }
		 // case 4 two children with two children
		 else {
		String successor = getSmallest(node.right);
		node.item = successor;
      // Delete that successor.
		node.right = recursiveRemove(node.right, successor);
	}
	return node; 
	}

	// TODO: Fill this in and call it from deleteNode()
	protected String getSmallest(BSTNode node) {
	  String smallest = node.item;
     if (node.left == null) {
        return smallest;
          } 
          else {
          	while(node.left!=null){
            smallest = node.left.item; 
            node = node.left;
            return getSmallest(node); 
          	}
        }
       return smallest; 
   	}
	// TODO: Fill this in and call it from inOrder()
	protected void inOrderRecursive(BSTNode node, MyQueue queue) {
     if (node != null){
     	inOrderRecursive(node.left, queue); 
     	queue.enqueue(node.item);
        inOrderRecursive(node.right, queue); 
     }
	}


	// TODO: Fill this in and call it from preOrder()
	protected void preOrderRecursive(BSTNode node, MyQueue queue) {
  if (node != null){
  	   queue.enqueue(node.item);
     	inOrderRecursive(node.left, queue); 
        inOrderRecursive(node.right, queue); 
     }
	}

	// TODO: Fill this in and call it from postOrder()
	protected void postOrderRecursive(BSTNode node, MyQueue queue) {
    if (node != null){
         	inOrderRecursive(node.left, queue); 
        inOrderRecursive(node.right, queue); 
  	   queue.enqueue(node.item);
     }
	}

	// Prints out the tree structure, using indenting to show the different levels of the tree
	public void printTreeStructure() { 
		printTree(0, root);
	}

	// Recursive helper for printTreeStructure()
	protected void printTree(int depth, BSTNode node) {
		indent(depth);
		if (node != null) {
	    	System.out.println(node.item);
	    	printTree(depth + 1, node.left);
	    	printTree(depth + 1, node.right);
	 	} 
	 	else {
	  		System.out.println("null");
	  	}
	}

	// Indents with with spaces 
	protected void indent(int depth) {
		for(int i = 0; i < depth; i++)
			System.out.print("  "); // Indents two spaces for each unit of depth
	}
	// Extra Credit 

	// TODO: If doing the extra credit, fill this in and call it from balanceBST()
	 protected BSTNode balanceRecursive(String[] array, int first, int last) {
       // Base Case 
        if (first < last) {
            return null;
        }
        // Get the middle element and make it root 
        int mid = (first + last) / 2;
        BSTNode node = new BSTNode(array[mid]);
        // Recursively construct the left subtree and make it left child of root
        node.left = balanceRecursive(array, first, mid - 1);
        // Recursively construct the right subtree and make it right child of root 
        node.right = balanceRecursive(array, mid + 1, last);
        
        return node;
	} 
}