package net.heteroclinic.graphtest;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.ForestMinistry;
import net.heteroclinic.graph.GraphBorder;
import net.heteroclinic.graph.GraphOrientation;
import net.heteroclinic.graph.Node;
import net.heteroclinic.graph.RNode;
import net.heteroclinic.graph.TNode;
import net.heteroclinic.graph.Test;

public class Test20121025 extends Test {


	public static void main(String[] args) {
		Bag.tierdistance = 10.0d;
		Bag.nodesize = 2.5d;
		Bag.nodespace = 1.3d * Bag.nodesize;

		Bag.familydistance = 2.0d * Bag.nodespace;
		Bag.boderspace = 3.0d * Bag.nodespace;
		Bag.treeinteveralinforest = 1.0d * Bag.nodespace;
		Bag.OneDoubleequalpixels = 15;
		Bag.fontsize = 14;
		Bag.fontwidth = 8; // not accurate.
		Bag.edgetrim = 1.5d; // ratio

		Bag.testresultfilepath = "C:\\Users\\Graphics\\Desktop\\treetortest\\";
		Bag.testunitname = "TEST_FOREST_CONSTRUCTION";
		Bag.testresultfiletype = ".png";
		

		int i = 0; 
		for (i = 0; i< 16; i++)
			ForestMinistry.createANode();
		
		ForestMinistry.setNodeSubnodePair(1l, 2l);
		ForestMinistry.setNodeSubnodePair(1l, 3l);
		ForestMinistry.setNodeSubnodePair(2l, 4l);
		ForestMinistry.setNodeSubnodePair(2l, 5l);
		ForestMinistry.setNodeSubnodePair(4l, 14l);
		ForestMinistry.setNodeSubnodePair(4l, 15l);
		ForestMinistry.setNodeSubnodePair(4l, 16l);

		ForestMinistry.setNodeSubnodePair(6l, 7l);
		ForestMinistry.setNodeSubnodePair(6l, 8l);
		
		ForestMinistry.setNodeSubnodePair(9l, 10l);
		ForestMinistry.setNodeSubnodePair(9l, 11l);
	
		ForestMinistry.setNodeSubnodePair(10l, 12l);
		ForestMinistry.setNodeSubnodePair(10l, 13l);
		ForestMinistry.print ();
		GraphBorder base_graphborder = ForestMinistry.constructForest();
		
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.LefttoRight);
		System.out.println("Picture drawn.");
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.RighttoLeft);
		System.out.println("Picture drawn.");
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.BottomtoTop);
		System.out.println("Picture drawn.");
		
	}

}
