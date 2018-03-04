// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp
import java.util.Stack;               // for Stack class
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

////////////////////////////////////////////////////////////////
class TreeBSTApp2
   {
////////////////////////////////////////////////////////////////
static class Node
   {
   public int iData;              // data item (key)
   public double dData;           // data item
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child

   public void displayNode()      // display ourself
      {
      System.out.print('{');
      System.out.print(iData);
      System.out.print(", ");
      System.out.print(dData);
      System.out.print("} ");
      }
   }  // end class Node
////////////////////////////////////////////////////////////////
static class DTree
   {
   private Node root;             // first node of tree

// -------------------------------------------------------------
   public DTree()                  // constructor
      { root = null; }            // no nodes in tree yet

	/**
 	Returns the number of nodes in the tree with key less than the given key.
 	If there is no such node, 0 should be returned.
	*/
	public int numOfNodesLessThanKey(int key) {
		if(!isNull(find(key)))
			return countLess(root, key);
		return 0;
	}

	public int countLess(Node node,int key){
		if(isNull(node))
			return 0;
		if(node.iData < key)
			return 1 + countLess(node.leftChild,key) + countLess(node.rightChild,key);
		return countLess(node.leftChild, key) + countLess(node.rightChild, key);
	}
	/**
 	Returns the total sum values of all leave nodes' double values in the tree.
 	If the tree is empty, 0.0 should be returned.
	*/
	public double sumAllLeavesValuesofBST() {
		if(!isNull(root))
			return sumLeaf(root);
		return 0.0;
	}
	
	public double sumLeaf(Node node){
		if(isNull(node))
			return 0.0;
		if(isNull(node.leftChild) && isNull(node.rightChild))
			return node.dData;
		return sumLeaf(node.leftChild) + sumLeaf(node.rightChild);
	}

	public boolean isNull(Node node){
		return node==null;
	}
	
	public Node findParent(Node node){
		Node current = root;
		Node parent = root;
		while(current!=node){
			parent = current;
			if(node.iData < current.iData)
				current = current.leftChild;
			else
				current = current.rightChild;
		}
		return parent;
	}
// -------------------------------------------------------------
   public Node find(int key)      // find node with given key
      {                           // (assumes non-empty tree)
      Node current = root;               // start at root
      while(current.iData != key)        // while no match,
         {
         if(key < current.iData)         // go left?
            current = current.leftChild;
         else                            // or go right?
            current = current.rightChild;
         if(current == null)             // if no child,
            return null;                 // didn't find it
         }
      return current;                    // found it
      }  // end find()
// -------------------------------------------------------------
	/*
	This insert function will insert the key in the 
	BST tree. Then, it will store the dd in that newly inserted node
	
	*/
   public void insert(int key, double dd)
      {
	   Node node = new Node();
	   node.iData = key;
	   node.dData = dd;
	   if(isNull(root))
		   root = node;
	   else{
		   Node current = root;
		   Node parent = root;
		   while(current!=null){
			   parent = current;
			   if(key < current.iData)
				   current = current.leftChild;
			   else
				   current = current.rightChild;
		   }
		   if(key < parent.iData)
			   parent.leftChild = node;
		   else
			   parent.rightChild = node;
	   }
      }  // end insert()
// -------------------------------------------------------------
	// This function delete the node with the given key. 
	// It is guaranteed that the function will never be called on the empty tree
	// If the key is found and removed, the true value should be returned.
	// Otherwise, return false.
	
   public boolean delete(int key) // delete node with given key
      {                           // (assumes non-empty list)
	   Node node = find(key);
	   if(!isNull(node)){
		   Node parent = findParent(node);
		   boolean leftChild = !isNull(node.leftChild);
		   boolean rightChild = !isNull(node.rightChild);
		   //no child
		   if(!leftChild && !rightChild){
			   if(node==root)
				   root = null;
			   else if(parent.leftChild == node)
				   parent.leftChild = null;
			   else
				   parent.rightChild = null;
		   }
		   else if(leftChild && !rightChild){
			   if(node==root)
				   root = root.leftChild;
			   else if(parent.leftChild == node)
				   parent.leftChild = node.leftChild;
			   else
				   parent.rightChild = node.leftChild;
		   }
		   else if(!leftChild && rightChild){
			   if(node==root)
				   root = root.rightChild;
			   else if(parent.leftChild == node)
				   parent.leftChild = node.rightChild;
			   else
				   parent.rightChild = node.rightChild;
		   }
		   else{
			   Node temp = node.rightChild;
			   while(temp!=null){
				   if(isNull(temp.leftChild)){
					   delete(temp.iData);
					   node.iData = temp.iData;
					   node.dData = temp.dData;
				   }
				   temp = temp.leftChild;
			   }
		   }
		   return true;
	   }
	   else
		   return false;
      }  // end delete()
// -------------------------------------------------------------
// -------------------------------------------------------------
   public void displayTree()
      {
      Stack globalStack = new Stack();
      globalStack.push(root);
      int nBlanks = 32;
      boolean isRowEmpty = false;
      System.out.println(
      "......................................................");
      while(isRowEmpty==false)
         {
         Stack localStack = new Stack();
         isRowEmpty = true;

         for(int j=0; j<nBlanks; j++)
            System.out.print(' ');

         while(globalStack.isEmpty()==false)
            {
            Node temp = (Node)globalStack.pop();
            if(temp != null)
               {
               System.out.print(temp.iData);
               localStack.push(temp.leftChild);
               localStack.push(temp.rightChild);

               if(temp.leftChild != null ||
                                   temp.rightChild != null)
                  isRowEmpty = false;
               }
            else
               {
               System.out.print("--");
               localStack.push(null);
               localStack.push(null);
               }
            for(int j=0; j<nBlanks*2-2; j++)
               System.out.print(' ');
            }  // end while globalStack not empty
         System.out.println();
         nBlanks /= 2;
         while(localStack.isEmpty()==false)
            globalStack.push( localStack.pop() );
         }  // end while isRowEmpty is false
      System.out.println(
      "......................................................");
      }  // end displayTree()
// -------------------------------------------------------------
   }  // end class Tree
   public static void main(String[] args) throws IOException
      {
      DTree theTree;
      BufferedReader br = new BufferedReader (
                               new InputStreamReader(System.in));
      String line = br.readLine();
      int numTestCase = Integer.parseInt(line); 
      StringTokenizer tokens;
      int ndex, oNum, cdex, ldex, key;
      double dFin;
      boolean ttOutput;
      Node tNode;
      for(ndex = 0; ndex < numTestCase; ndex++){
        line = br.readLine();
        tokens = new StringTokenizer(line, " \t\r\n");
        oNum = Integer.parseInt(tokens.nextToken());
        theTree = new DTree();
        for(cdex = 0; cdex < oNum; cdex++){
            line = br.readLine();
            tokens = new StringTokenizer(line, " \t\r\n");
            ldex = Integer.parseInt(tokens.nextToken());                        
            switch(ldex){
                case 0: key = Integer.parseInt(tokens.nextToken());// key
                    dFin = Double.parseDouble(tokens.nextToken()); // value
                    theTree.insert(key, dFin); // insert(key, value)
                    break;
                case 1: key = Integer.parseInt(tokens.nextToken()); // key
                    ttOutput = theTree.delete(key); // delete(key)
                    if(ttOutput){
                        System.out.println("Deletion of " + key + " is successful.");
                    }else{
                        System.out.println("Deletion of " + key + " is not successful.");
                    }
                    break;
                case 2: key = Integer.parseInt(tokens.nextToken());// key
                    tNode = theTree.find(key);
                    if(tNode == null){
                        System.out.println("the tree does not have " + key);
                    }else{
                        System.out.println(tNode.iData + " has " + tNode.dData);
                    }
                    break;
                case 3: key = Integer.parseInt(tokens.nextToken());// key
                	key = theTree.numOfNodesLessThanKey(key);
                    System.out.println("Size of the subtree is " + key);
                    break;
                case 4: dFin = theTree.sumAllLeavesValuesofBST();
                    System.out.println("Total leave values of the tree is " + dFin);
                    break;
                default: // print
                    theTree.displayTree();
            }
        }
      }
    }  // end main
   }  // end class TreeApp
////////////////////////////////////////////////////////////////