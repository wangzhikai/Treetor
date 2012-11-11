package net.heteroclinic.graphtest;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.ForestMinistry;
import net.heteroclinic.graph.GraphBorder;
import net.heteroclinic.graph.GraphOrientation;
import net.heteroclinic.graph.Node;
import net.heteroclinic.graph.Test;


public class Test20121028 extends Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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

		Bag.testresultfilepath = "C:\\Users\\Graphics\\Desktop\\treetortest\\";
		Bag.testunitname = "TEST_FOREST_CONSTRUCTION";
		Bag.testresultfiletype = ".png";
		

		//int i = 0; 
		for (int i = 1; i <13; i++) {
			 new Node();
		}

		
		Node.addNodeSubnodePair(1l, 2l);
		Node.addNodeSubnodePair(1l, 3l);
		Node.addNodeSubnodePair(2l, 4l);
		Node.addNodeSubnodePair(2l, 5l);

		Node.allnodes.get(1l).BFSGraphConstruction();

		Node.allnodes.get(1l).image2dRender(Test.getAResultFilename(),
				GraphOrientation.LefttoRight);
		Node.allnodes.get(1l).getGraphborder().print();
		
		System.out.println("Picture drawn.");
		



	}

}
