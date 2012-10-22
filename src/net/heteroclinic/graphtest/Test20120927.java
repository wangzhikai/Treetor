package net.heteroclinic.graphtest;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.GraphOrientation;
import net.heteroclinic.graph.Node;

public class Test20120927  extends Test{


	// TO construct a binary tree
	public static void main(String[] args) {
		Bag.tierdistance = 10.0d;
		Bag.nodesize = 1.6d;
		Bag.nodespace = 1.3d * Bag.nodesize;

		Bag.familydistance = 2.0d * Bag.nodespace;
		Bag.graphboder = 3.0d * Bag.nodespace;
		Bag.OneDoubleequalpixels = 15;
		Bag.fontsize = 14;
		Bag.fontwidth = 8; // not accurate.
		Bag.edgetrim = 1.5d; // ratio
		
		Bag.testresultfilepath = "C:\\Users\\Graphics\\Desktop\\treetortest\\";
		Bag.testunitname = "CONSTR-OREINT";
		Bag.testresultfiletype = ".png";
		
		System.out.println(Test.getAResultFilename());

	

		int nodeslimit =getPower(  2,8);
		Node n = new Node();
		Random rand = new Random(Calendar.getInstance().getTimeInMillis()
				% 111335);
		

		for (int i = 1; i < nodeslimit; i++) {
			n = new Node();
		}
		

		
		LinkedList <Long >  llleft= new LinkedList<Long> ();
		LinkedList <Long > llright = new LinkedList<Long> ();
		for (int i = 0; i< nodeslimit; i++) {
			llleft.add((long) (i+1));
		}
		long count = 1;
		llright.add(1l);
		llleft.remove(1l);
		while (llright.size() < nodeslimit ) {
			int index1 = rand.nextInt(llleft.size());
			
			int index2 = rand.nextInt(llright.size());
			
			long parentnodeid = llright.get(index2);
			
			long childnodeid = llleft.get(index1);
			
			Node.addNodeSubnodePair(parentnodeid, childnodeid);
			llright.add(childnodeid);
			
			llleft.remove(index1);

			count++;
		}
		System.out.println("Tree constructed after "+count+" ops.");
		
		//TODO ?? both case I and case II biased to north?
		
		
		Node.allnodes.get(1l).BFSGraphConstruction();

		
		//TEST TOP to bottom
		Node.allnodes.get(1l).image2dRender(Test.getAResultFilename(),
				GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");
		
		Node.allnodes.get(1l).image2dRender(Test.getAResultFilename(),
				GraphOrientation.RighttoLeft);
		System.out.println("Picture drawn.");

		
		Node.allnodes.get(1l).image2dRender(Test.getAResultFilename(),
				GraphOrientation.LefttoRight);
		System.out.println("Picture drawn.");

		Node.allnodes.get(1l).image2dRender(Test.getAResultFilename(),
				GraphOrientation.BottomtoTop);
		System.out.println("Picture drawn.");	
		//Node.allnodes.get(1l).BFSprint();
	}	
	
	
	//PNG works for getPower(  2,12);
	public static void main_test(String[] args) {
		Bag.tierdistance = 8.0d;
		Bag.nodesize = 1.6d;
		Bag.nodespace = 1.3d * Bag.nodesize;

		Bag.familydistance = 2.0d * Bag.nodespace;
		Bag.graphboder = 3.0d * Bag.nodespace;
		Bag.OneDoubleequalpixels = 15;
		Bag.fontsize = 14;
		Bag.fontwidth = 8; // not accurate.
		Bag.edgetrim = 1.5d; // ratio

	

		int nodeslimit =getPower(  2,12);
		Node n = new Node();
		Random rand = new Random(Calendar.getInstance().getTimeInMillis()
				% 111335);
		
//		//Case I this will make the smaller number nodes dense
//		for (int i = 1; i < nodeslimit; i++) {
//			int pnode = rand.nextInt(Node.allnodes.size());
//			n = new Node();
//
//			Node.addNodeSubnodePair(pnode + 1, n.getId());
//		}
		
		
		//int powerladder = 1;
		//Case II this will make the smaller number nodes dense
		for (int i = 1; i < nodeslimit; i++) {
			//int pnode = rand.nextInt(Node.allnodes.size());
			n = new Node();

			//Node.addNodeSubnodePair(pnode + 1, n.getId());
		}
		

		
		LinkedList <Long >  llleft= new LinkedList<Long> ();
		LinkedList <Long > llright = new LinkedList<Long> ();
		for (int i = 0; i< nodeslimit; i++) {
			llleft.add((long) (i+1));
		}
		long count = 1;
		llright.add(1l);
		llleft.remove(1l);
		while (llright.size() < nodeslimit ) {
			int index1 = rand.nextInt(llleft.size());
			
			int index2 = rand.nextInt(llright.size());
			
			long parentnodeid = llright.get(index2);
			
			long childnodeid = llleft.get(index1);
			
			Node.addNodeSubnodePair(parentnodeid, childnodeid);
			llright.add(childnodeid);
			
			llleft.remove(index1);

			count++;
		}
		System.out.println("Tree constructed after "+count+" ops.");
		
		//TODO ?? both case I and case II biased to north?
		
		
		Node.allnodes.get(1l).BFSGraphConstruction();
		Node.allnodes.get(1l).image2dRender(
				"C:\\Users\\Graphics\\Desktop\\"
						+ Calendar.getInstance().getTimeInMillis() + ".png",
				GraphOrientation.LefttoRight);
		System.out.println("Picture drawn.");
		//Node.allnodes.get(1l).BFSprint();
	}


}
