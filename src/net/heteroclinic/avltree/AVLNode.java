/**
 * @author zhikai
 * Reference https://en.wikipedia.org/wiki/AVL_tree last retrieved on Mar 2, 2017
 */
package net.heteroclinic.avltree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.util.concurrent.profilable.ThreadIdProfilableIdMap;
import javax.util.concurrent.profilable.messenger.FunctorType;
import javax.util.concurrent.profilable.messenger.GraphMessenger;
import javax.util.concurrent.profilable.messenger.MessageForGraph;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.ForestMinistry;

public class AVLNode {
	
	public static volatile boolean ready = false;
	
	public AVLNode () {
		profiableId = net.heteroclinic.graph.Node.counter.incrementAndGet();
		
	}
	protected final long profiableId ;

	
	public long getProfiableId() {
		return profiableId;
	}
	public static void setShow(boolean show) {
		AVLNode.show = show;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	public static volatile boolean show = false;
	protected List<String> messages = new ArrayList<String>();
	protected AVLNode parent;
	public AVLNode parent() {
		return parent;
	}
	public AVLNode getParent() {
		return parent;
	}

	public void setParent(AVLNode parent) {
		this.parent = parent;
	}

	protected AVLNode left;
	protected AVLNode right;
	public AVLNode getLeft() {
		return left;
	}
	public void setLeft(AVLNode left) {
		this.left = left;
	}
	public AVLNode getRight() {
		return right;
	}
	public void setRight(AVLNode right) {
		this.right = right;
	}

	protected int value;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public static AVLNode parent(final AVLNode avln) {
		return avln.getParent();
	}
	public static AVLNode right_child(final AVLNode avln) {
		return avln.getRight();
	}
	
	public static int Height(final AVLNode avln) {
		return avln.getHeight();
		
	}
	
	public int getHeight() {

			int l = 0;
			int r = 0;
			if (this.getLeft()!= null) {
				l = 1 + this.getLeft().getHeight();
			}
			if (this.getRight()!= null) {
				r = 1 + this.getRight().getHeight();
			}
			return l>r?l:r;
			
	}
	//"BalanceFactor(N) := –Height(LeftSubtree(N)) + Height(RightSubtree(N))"
	public static int BalanceFactor(final AVLNode N) {
		return -1 * Height(N.getLeft()) + Height(N.getRight());		
	}
	
	public static AVLNode left_child(final AVLNode avln) {
		return avln.getLeft();
	}
	
	
	
	public static AVLNode addNewNode(final AVLNode parent, final int value) throws InterruptedException {
		AVLNode result = null;
		result = new AVLNode();
		result.setValue(value);
		if (show ) {
			GraphMessenger.messageque.put(
				new MessageForGraph(FunctorType.createATNodeforProfilable, result.getProfiableId(),result.getValue()));
		}
		if (parent == null) {
			return result;
		} else {
			parent.addNewNode(result);
			return result;
		}
	}
	
	protected void addNewNode(final AVLNode newn) throws InterruptedException {
		if (getValue() > newn.getValue() ) {
			if (getLeft()!= null) {
				getLeft().addNewNode( newn);
			} else {
				setLeft(newn);
				newn.setParent(this);
				if (show) {
	    			GraphMessenger.messageque.put(
	    					new MessageForGraph(FunctorType.setNodeSubnodePairforProfilable, 
	    							
	    							this.getProfiableId(),newn.getProfiableId()));
				}
			}
		} else {
			if (getRight() != null) {
				getRight().addNewNode(newn);
			} else {
				setRight(newn);
				newn.setParent(this);
				if (show) {
	    			GraphMessenger.messageque.put(
	    					new MessageForGraph(FunctorType.setNodeSubnodePairforProfilable, 
	    							
	    							this.getProfiableId(),newn.getProfiableId()));
				}
			}
		}
	}
	
	public static void inOrderPrint (final AVLNode parent) {
		if (parent.getLeft()!= null) {
			inOrderPrint (parent.getLeft());
		}
		System.out.print(parent.getValue()+" ");
		if (parent.getRight()!= null) {
			inOrderPrint (parent.getRight());
		}
	}
	
	public static void testInOrderPrint (boolean run) throws InterruptedException {
		if (!run) {
			return;
		}
		AVLNode.show = false;
		AVLNode root =  addNewNode(null, 9);
		addNewNode(root,5);
		addNewNode(root,17);
		addNewNode(root,3);
		addNewNode(root,7);
		addNewNode(root,11);
		addNewNode(root,21);
		addNewNode(root,1);
		addNewNode(root,4);
		addNewNode(root,6);
		addNewNode(root,8);
		addNewNode(root,10);
		addNewNode(root,13);
		addNewNode(root,18);
		addNewNode(root,23);
		inOrderPrint (root);
		//1 3 4 5 6 7 8 9 10 11 13 17 18 21 23 
	}
	
	public static void testVisualizeUnbalanced(boolean run) throws InterruptedException {
		if (!run) {
			return;
		}
		AVLNode.show = true;
		AVLNode root =  addNewNode(null, 9);
		addNewNode(root,5);
		addNewNode(root,17);
		addNewNode(root,3);
		addNewNode(root,7);
		addNewNode(root,11);
		addNewNode(root,21);
		addNewNode(root,1);
		addNewNode(root,4);
		addNewNode(root,6);
		addNewNode(root,8);
		addNewNode(root,10);
		addNewNode(root,13);
		addNewNode(root,18);
		addNewNode(root,23);
		addNewNode(root,33);
		addNewNode(root,43);
		AVLNode.ready = true;

	}
	
	public static void testInOrderPrint2(boolean run) throws InterruptedException {
		if (!run) {
			return;
		}
		AVLNode root =  addNewNode(null, 9);
		addNewNode(root,5);
		addNewNode(root,17);
		addNewNode(root,3);
		addNewNode(root,7);
		addNewNode(root,11);
		addNewNode(root,21);
		addNewNode(root,1);
		addNewNode(root,4);
		addNewNode(root,6);
		addNewNode(root,8);
		addNewNode(root,10);
		addNewNode(root,13);
		addNewNode(root,18);
		addNewNode(root,23);
		addNewNode(root,33);
		addNewNode(root,43);

		inOrderPrint (root);
		//1 3 4 5 6 7 8 9 10 11 13 17 18 21 23 33 43 
	}
	
	public static void main(String [] args) throws InterruptedException {
		{
			boolean testThisBlock = false;
			testInOrderPrint (testThisBlock);
			System.out.println();
		}
		{
			boolean testThisBlock = false;
			testInOrderPrint2 (testThisBlock);
			System.out.println();
		}
		{
			// TODO Auto-generated method stub
			Bag.tierdistance = 10.0d;
			Bag.nodesize = 2.5d;
			Bag.nodespace = 1.3d * Bag.nodesize;

			Bag.familydistance = 2.0d * Bag.nodespace;
			Bag.boderspace = 3.0d * Bag.nodespace;
			Bag.OneDoubleequalpixels = 15;
			Bag.fontsize = 14;
			Bag.fontwidth = 8; // not accurate.
			Bag.edgetrim = 1.5d; // ratio
			
			//Bag.testresultfilepath = "C:\\Users\\Graphics\\Desktop\\treetortest\\";
			Bag.testresultfilepath = "";
			Bag.testunitname = "AVLTree";
			Bag.testresultfiletype = ".png";
			

			Bag.treeinteveralinforest = 1.0d * Bag.nodespace;
			
			
			
			// first pass set Bag.forestministryusepresetgraphborder false
			// second pass set true, change the values with ForestMinistry.initGraphBorder
			Bag.forestministryusepresetgraphborder =false;
			if ( Bag.forestministryusepresetgraphborder ) {
				Bag.forestministryupdategraphborder = false;
				
				//ForestMinistry.initGraphBorder(-1,0.0,0.0,0.0,0.0,0.0,0.0,51.375,39.0);
				ForestMinistry.initGraphBorder(-1,0.0,0.0,0.0,0.0,0.0,0.0,51.375,39.0);
			} else {
				Bag.forestministryupdategraphborder = true;
			}
			
			
			
			
			CountDownLatch startSignal = new CountDownLatch(1);
			
			List<Future<?>> fl = new ArrayList<Future<?>>();
			ExecutorService exec = Executors.newCachedThreadPool();
			
			fl.add(exec.submit(new GraphMessenger(startSignal)));
			
			testVisualizeUnbalanced(true);
			
			while (!AVLNode.ready && GraphMessenger.messageque.size()>0) {
				TimeUnit.MILLISECONDS.sleep(1000);
			}
			
			for (Future<?> f : fl)
				f.cancel(true);
			
			GraphMessenger.stopped = true;
			exec.shutdown();
		
			ForestMinistry.printStaticGraphBorder();

		
		}
	}
	
	
	
//	public static AVLNode rotate_RightLeft(AVLNode X,AVLNode Z) {
//	     // Z is by 2 higher than its sibling
//		AVLNode Y = left_child(Z); // Inner child of Z
//	     // Y is by 1 higher than sibling
//		AVLNode t3 = right_child(Y);
//	     //left_child(Z) = t3;
//		Z.setLeft(t3);
//	     if (t3 != null) {
//	         //parent(t3) = Z;
//	    	 t3.setParent(Z);
//	     }
//	     //right_child(Y) = Z;
//	     Y.setParent(Z);
//	     //parent(Z) = Y;
//	     Z.setParent(Y);
//	     
//	     AVLNode t2 = left_child(Y);
//	     //right_child(X) = t2;
//	     X.setRight(t2);
//	     if (t2 != null) {
//	         //parent(t2) = X;
//	    	 t2.setParent(X);
//	     }
//	     //left_child(Y) = X;
//	     Y.setLeft(X);
//	     //parent(X) = Y;
//	     X.setParent(Y);
//	 
//	     // 1st case, BalanceFactor(Y) > 0, happens with insertion or deletion:
//	     if (BalanceFactor(Y) > 0) { // t3 was higher
//	         //BalanceFactor(X) = –1;  // t1 now higher
//	         assertBalanceFactor (X, -1);
//	         //BalanceFactor(Z) = 0;
//	         assertBalanceFactor (X, 0);
//	     } else // 2nd case, BalanceFactor(Y) == 0, only happens with deletion, not insertion:
//	     if (BalanceFactor(Y) == 0) {
//	         //BalanceFactor(X) = 0;
//	         assertBalanceFactor (X, 0);
//	         //BalanceFactor(Z) = 0;
//	         assertBalanceFactor (Z, 0);
//	     } else // 3rd case happens with insertion or deletion:
//	     {                           // t2 was higher
//	         //BalanceFactor(X) = 0;
//	         assertBalanceFactor (X, 0);
//	         //BalanceFactor(Z) = +1;  // t4 now higher
//	         assertBalanceFactor (Z, 1);
//	     }
//	     //BalanceFactor(Y) = 0;
//	     assertBalanceFactor (Y, 0);
//	     return Y; // return new root of rotated subtree
//	 }
//
//	public static void assertBalanceFactor (final AVLNode avln, final int f) {
//		if (BalanceFactor(avln)!= f) {
//			throw new RuntimeException("assertBalanceFactor failed");
//		}
//	}
//	
//	
//	public static AVLNode  rotate_Left(AVLNode X,AVLNode Z) {
//	     // Z is by 2 higher than its sibling
//		AVLNode t23 = left_child(Z); // Inner child of Z
//	     //right_child(X) = t23;
//		X.setRight(t23);
//	     if (t23 != null) {
//	         //parent(t23) = X;
//	    	 t23.setParent(X);
//	     }
//	 
//	     //left_child(Z) = X;
//	     Z.setLeft(X);
//	     //parent(X) = Z;
//	     X.setParent(Z);
//	 
//	     // 1st case, BalanceFactor(Z) == 0, only happens with deletion, not insertion:
//	     if (BalanceFactor(Z) == 0) { // t23 has been of same height as t4
//	         //BalanceFactor(X) = +1;   // t23 now higher
//	         assertBalanceFactor (X, 1);
//	         //BalanceFactor(Z) = –1;   // t4 now lower than X
//	         assertBalanceFactor (Z, -1);
//	     } else // 2nd case happens with insertion or deletion:
//	     {
//	         //BalanceFactor(X) = 0;
//	         assertBalanceFactor (X, 0);
//	         //BalanceFactor(Z) = 0;
//	         assertBalanceFactor (Z, 0);
//	     }
//	 
//	     return Z; // return new root of rotated subtree
//	 }
//
//	//TODO insert
//	//this function is copied from Wikipedia, auxiliary fucntions added.
//	//"The height of the subtree rooted by Z has increased by 1. It is already in AVL shape."
//	//"By inserting the new node Z as a child of node X the height of that subtree Z increases from 0 to 1."
//	public void insert(AVLNode Z) {
//		AVLNode N ;
//	 for (AVLNode X = parent(Z); X != null; X = parent(Z)) { // Loop (possibly up to the root)
//	     // BalanceFactor(X) has not yet been updated!
//	     if (Z == right_child(X)) { // The right subtree increases
//	         if (BalanceFactor(X) > 0) { // X is right-heavy
//	             // ===> the temporary BalanceFactor(X) == +2
//	             // ===> rebalancing is required.
//	        	 AVLNode G = parent(X); // Save parent of X around rotations
//	             if (BalanceFactor(Z) < 0)  {    // Right Left Case     (see figure 5)
//	            	 N = rotate_RightLeft(X,Z); // Double rotation: Right(Z) then Left(X)
//	             }
//	             else      {                     // Right Right Case    (see figure 4)
//	            	 N = rotate_Left(X,Z);      // Single rotation Left(X)
//	             // After rotation adapt parent link
//	             }
//	         }
//	         else {
//	             if (BalanceFactor(X) < 0) {
//	                 //BalanceFactor(X) = 0; // Z’s height increase is absorbed at X.
//	                 assertBalanceFactor (X, 0);
//	                 break; // Leave the loop
//	             }
//	             //BalanceFactor(X) = +1;
//	             assertBalanceFactor (X,1);
//	             // Height(X) increases by 1
//	             continue;
//	         }
//	     }
//	     else { // Z == left_child(X): the left subtree increases
//	         if (BalanceFactor(X) < 0) { // X is left-heavy
//	             // ===> the temporary BalanceFactor(X) == –2
//	             // ===> rebalancing is required.
//	        	 AVLNode G = parent(X); // Save parent of X around rotations
//	        	 
//	             if (BalanceFactor(Z) > 0)      // Left Right Case
//	                 N = rotate_LeftRight(X,Z); // Double rotation: Left(Z) then Right(X)
//	             else                           // Left Left Case
//	                 N = rotate_Right(X,Z);     // Single rotation Right(X)
//	             // After rotation adapt parent link
//	         }
//	         else {
//	             if (BalanceFactor(X) > 0) {
//	                 BalanceFactor(X) = 0; // Z’s height increase is absorbed at X.
//	                 break; // Leave the loop
//	             }
//	             BalanceFactor(X) = –1;
//	             // Height(X) increases by 1
//	             continue;
//	         }
//	     }
//	     // After a rotation adapt parent link:
//	     // N is the new root of the rotated subtree
//	     // Height does not change: Height(N) == old Height(X)
//	     parent(N) = G;
//	     if (G != null) {
//	         if (X == left_child(G))
//	             left_child(G) = N;
//	         else
//	             right_child(G) = N;
//	         break;
//	     }
//	     else {
//	         tree->root = N; // N is the new root of the total tree
//	         break;
//	     }
//	 
//	     // There is no fall thru, only break; or continue;
//	 }
//	 // Unless loop is left via break, the height of the total tree increases by 1.
//	}
//
//	//TODO delete
	

}
