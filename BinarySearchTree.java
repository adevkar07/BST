/** @author 
 *  Binary search tree (starter code)
 **/

package rxp170021;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;
import java.util.Scanner;

public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
    static class Entry<T> {
        T element;
        Entry<T> left, right;

        public Entry(T x, Entry<T> left, Entry<T> right) {
            this.element = x;
	        this.left = left;
	        this.right = right;
        }
    }
    
    Entry<T> root;
    int size;
    Stack<Entry<T>> s;

    public BinarySearchTree() {
	root = null;
	size = 0;
    }


    /** TO DO: Is x contained in tree?
     */
    public boolean contains(T x) 
    {
    	Entry<T> t = find(x);
    	if (t == null || t.element != x) {
    		return false;
    	}
    	else
    		return true;
    }

    /** TO DO: Is there an element that is equal to x in the tree?
     *  Element in tree that is equal to x is returned, null otherwise.
     */
    public T get(T x) 
    {   	
    	Entry<T> t = find(x);
    	if (t == null || t.element != x) {
    		return null;
    	}
    	else
    		return t.element;
    }

    /** TO DO: Add x to tree. 
     *  If tree contains a node with same key, replace element by x.
     *  Returns true if x is a new element added to tree.
     */
    public boolean add(T x) 
    {
    	if (size == 0)
    	{
    		root = new Entry<>(x, null, null);
    		size = 1;
    		return true;
    	}
    	else
    	{
    		Entry<T> t = find(x);
    		if (t.element == x) 
    		{
    			t.element = x;
    			return false;
    		}
    		else if (x.compareTo(t.element) < 0)
    		{
    			t.left = new Entry(x, null, null);
    		}
    		else
    		{
    			t.right = new Entry(x, null, null);
    		}
    		size++;
    	}
	    return true;
    }
    
    public Entry<T> find(T x) 
    {
    	s = new Stack<Entry<T>> ();
    	s.push(null);
    	return find(root, x);    	
    }
    
    //Find x within subtree rooted at X
    public Entry<T> find (Entry<T> t, T x) 
    {
    	if (t == null || t.element == x) 
    	{
    		return t;
    	}
    	while(true) 
    	{
    		if (x.compareTo(t.element) == 0)
    		{
    			break;
    		}
    		else if (x.compareTo(t.element) < 0) 
    		{
    			if (t.left == null) 
    			{
    				break;
    			}
    			else 
    			{
    				s.push(t);
    				t = t.left;
    			}
    		}
    		else if (t.right == null) {
    			break;
    		}
    		else {
    			s.push(t);
    			t = t.right;
    		}
    	}
    	return t;
    }

    /** TO DO: Remove x from tree. 
     *  Return x if found, otherwise return null
     */
    public T remove(T x) 
    {
    	if (root == null)
    	{
    		return null;
    	}
    	Entry<T> t = find(x);
    	if (t.element != x) {
    		return null;
    	}
    	T result = t.element;
    	if (t.left == null || t.right == null) {
    		splice(t);
    	}
    	else {
    		s.push(t);
    		Entry<T> minRight = find(t.right, t.element);
    		t.element = minRight.element;
    		splice(minRight);
    	}
    	size--;
	    return result;
    }
    
    public void splice(Entry<T> t)
    {
    	Entry<T> pt = s.peek();
    	Entry<T> c = (t.left == null) ? t.right : t.left;
    	if (pt == null)
    		root = c;
    	else if (pt.left == t) {
    		pt.left = c;
    	}else {
    		pt.right = c;
    	}
    }

    public T min() {
    	if (size == 0)
    		return null;
    	Entry<T> t = root;
    	while(t.left != null)
    	{
    		t = t.left;
    	}
	    return t.element;
    }

    public T max() {
    	if (size == 0)
    	{
    		return null;
    	}
    	Entry<T> t = root;
    	while(t.right != null) {
    		t = t.right;
    	}
        return t.element;
    }

    // TODO: Create an array with the elements using in-order traversal of tree
    public Comparable[] toArray() 
    {
	    Comparable[] arr = new Comparable[size];
	    int i = 0;
	    Stack<Entry<T>> s = new Stack<Entry<T>> ();
	    Entry<T> curr = root;
	    while(!s.empty() || curr != null) {	    	
	    	while(curr != null) {
	    		s.push(curr);
	    		curr = curr.left;	    		
	    	}
	    	curr = s.pop();
	    	arr[i] = ((Comparable)curr.element);
	    	i++;
	    	curr = curr.right;   	
	    }
	    /* write code to place elements in array here */
	    return arr;
    }


// Start of Optional problem 2

    /** Optional problem 2: Iterate elements in sorted order of keys
	Solve this problem without creating an array using in-order traversal (toArray()).
     */
    public Iterator<T> iterator() {
	return null;
    }

    
    // Optional problem 2.  Find largest key that is no bigger than x.  Return null if there is no such key.
    public T floor(T x) {
        return null;
    }

    // Optional problem 2.  Find smallest key that is no smaller than x.  Return null if there is no such key.
    public T ceiling(T x) {
        return null;
    }

    // Optional problem 2.  Find predecessor of x.  If x is not in the tree, return floor(x).  Return null if there is no such key.
    public T predecessor(T x) {
        return null;
    }

    // Optional problem 2.  Find successor of x.  If x is not in the tree, return ceiling(x).  Return null if there is no such key.
    public T successor(T x) {
        return null;
    }

// End of Optional problem 2

    public static void main(String[] args) {
	BinarySearchTree<Integer> t = new BinarySearchTree<>();
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
            int x = in.nextInt();
            if(x > 0) {
                System.out.print("Add " + x + " : ");
                t.add(x);
                t.printTree();
            } else if(x < 0) {
                System.out.print("Remove " + x + " : ");
                t.remove(-x);
                t.printTree();
            } else {
                Comparable[] arr = t.toArray();
                System.out.print("Final: ");
                for(int i=0; i<t.size; i++) {
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
                return;
            }           
        }
    }


    public void printTree() {
	System.out.print("[" + size + "]");
	printTree(root);
	System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
	if(node != null) {
	    printTree(node.left);
	    System.out.print(" " + node.element);
	    printTree(node.right);
	}
    }

}
/*
Sample input:
1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0

Output:
Add 1 : [1] 1
Add 3 : [2] 1 3
Add 5 : [3] 1 3 5
Add 7 : [4] 1 3 5 7
Add 9 : [5] 1 3 5 7 9
Add 2 : [6] 1 2 3 5 7 9
Add 4 : [7] 1 2 3 4 5 7 9
Add 6 : [8] 1 2 3 4 5 6 7 9
Add 8 : [9] 1 2 3 4 5 6 7 8 9
Add 10 : [10] 1 2 3 4 5 6 7 8 9 10
Remove -3 : [9] 1 2 4 5 6 7 8 9 10
Remove -6 : [8] 1 2 4 5 7 8 9 10
Remove -3 : [8] 1 2 4 5 7 8 9 10
Final: 1 2 4 5 7 8 9 10 

*/
