package net.heteroclinic.graphtest;

import java.util.Calendar;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.GraphOrientation;
import net.heteroclinic.graph.Node;
import net.heteroclinic.graph.RNode;
import net.heteroclinic.graph.TNode;

public class Test20121009 {

	/**
	 * @param args
	 */
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

		
		// TODO Auto-generated method stub
		new TNode(); //1/1
		new TNode();//2/6
		new TNode();//3/7
		new TNode();//4/8
		new TNode();//5/9
		new TNode();//6/10
		new TNode();//7/12
		new TNode();//8/13
		
		new RNode();//1/2
		new RNode();//2/3
		new RNode();//3/4
		new RNode();//4/5
		new RNode();//5/11
		
		Node.addNodeSubnodePair(1,9);
		Node.addNodeSubnodePair(1,10);
		Node.addNodeSubnodePair(1,11);
		Node.addNodeSubnodePair(1,12);
		
		Node.addNodeSubnodePair(9,2);
		Node.addNodeSubnodePair(9,3);
		
		Node.addNodeSubnodePair(10,4);
		
		Node.addNodeSubnodePair(11,5);
		Node.addNodeSubnodePair(11,6);
		
		Node.addNodeSubnodePair(5,13);
		
		Node.addNodeSubnodePair(13,7);
		Node.addNodeSubnodePair(13,8);
		Node.allnodes.get(1l).BFSGraphConstruction();

		//TEST TOP to bottom
		Node.allnodes.get(1l).image2dRender(
				"C:\\Users\\Graphics\\Desktop\\"
						+ Calendar.getInstance().getTimeInMillis() + ".png",
				GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");
		
		
		Node.removeNodeLeafSubnodePair(1,12);
		Node.allnodes.get(1l).BFSGraphConstruction();
		Node.allnodes.get(1l).image2dRender(
				"C:\\Users\\Graphics\\Desktop\\"
						+ Calendar.getInstance().getTimeInMillis() + ".png",
				GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");

	}

}
