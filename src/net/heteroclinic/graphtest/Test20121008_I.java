package net.heteroclinic.graphtest;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.GraphOrientation;
import net.heteroclinic.graph.Node;
import net.heteroclinic.graph.RNode;
import net.heteroclinic.graph.TNode;
import net.heteroclinic.graph.Test;

public class Test20121008_I extends Test{

	public static void main(String[] args) {
		Bag.tierdistance = 10.0d;
		Bag.nodesize = 2.5d;
		Bag.nodespace = 1.3d * Bag.nodesize;

		Bag.familydistance = 2.0d * Bag.nodespace;
		Bag.boderspace = 3.0d * Bag.nodespace;
		Bag.OneDoubleequalpixels = 15;
		Bag.fontsize = 14;
		Bag.fontwidth = 8; // not accurate.
		Bag.edgetrim = 1.5d; // ratio

		Bag.testresultfilepath = "C:\\Users\\Graphics\\Desktop\\treetortest\\";
		Bag.testunitname = "TEST_RNODE_TREE";
		Bag.testresultfiletype = ".png";

	

		int nodeslimit =getPower(  2,8);
		Node n = new Node();
		Random rand = new Random(Calendar.getInstance().getTimeInMillis()
		 		% 111335);
		

		
		for (int i = 1; i < nodeslimit; i++) {
			n = new RNode();
			n.setFillcolor(255, 110, 110);

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
//		Node.allnodes.get(1l).image2dRender(
//				"C:\\Users\\Graphics\\Desktop\\"
//						+ Calendar.getInstance().getTimeInMillis() + ".png",
//				GraphOrientation.LefttoRight);
//		System.out.println("Picture drawn.");
		
		//TEST TOP to bottom
		Node.allnodes.get(1l).image2dRender(Test.getAResultFilename(),
				GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");
		
//		Node.allnodes.get(1l).image2dRender(
//				"C:\\Users\\Graphics\\Desktop\\"
//						+ Calendar.getInstance().getTimeInMillis() + ".png",
//				GraphOrientation.RighttoLeft);
//		System.out.println("Picture drawn.");
//
//		
//		Node.allnodes.get(1l).image2dRender(
//				"C:\\Users\\Graphics\\Desktop\\"
//						+ Calendar.getInstance().getTimeInMillis() + ".png",
//				GraphOrientation.LefttoRight);
//		System.out.println("Picture drawn.");
//
//		Node.allnodes.get(1l).image2dRender(
//				"C:\\Users\\Graphics\\Desktop\\"
//						+ Calendar.getInstance().getTimeInMillis() + ".png",
//				GraphOrientation.BottomtoTop);
//		System.out.println("Picture drawn.");	
		//Node.allnodes.get(1l).BFSprint();
	}	
	
}
