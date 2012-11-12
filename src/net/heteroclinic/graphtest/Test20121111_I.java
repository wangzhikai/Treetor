package net.heteroclinic.graphtest;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.ForestMinistry;
import net.heteroclinic.graph.GraphBorder;
import net.heteroclinic.graph.GraphOrientation;
import net.heteroclinic.graph.Node;
import net.heteroclinic.graph.Nodetype;
import net.heteroclinic.graph.RNode;
import net.heteroclinic.graph.TNode;
import net.heteroclinic.graph.Test;


public class Test20121111_I extends net.heteroclinic.graph.Test {

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
		Bag.testunitname = "RTNODE_FOREST";
		Bag.testresultfiletype = ".png";
		

		Bag.treeinteveralinforest = 1.0d * Bag.nodespace;
		
//		// TODO Auto-generated method stub
//		new Node(); //1/1
//		new Node();//2/6
//
//		
//		new Node();//1/2
//		new Node();//2/3
//		new Node();//3/4
//		new Node();//4/5
		ForestMinistry.createANode(Nodetype.TNODE);
		
		ForestMinistry.createANode(Nodetype.RNODE);
		ForestMinistry.createANode(Nodetype.RNODE);
		ForestMinistry.createANode(Nodetype.RNODE);
		ForestMinistry.createANode(Nodetype.RNODE);
		
		ForestMinistry.createANode(Nodetype.TNODE);
		
		ForestMinistry.setNodeSubnodePair(1l, 2l);
		ForestMinistry.setNodeSubnodePair(1l, 3l);
		ForestMinistry.setNodeSubnodePair(6l, 4l);
		ForestMinistry.setNodeSubnodePair(6l, 5l);
		ForestMinistry.addAVirtaulEdge(1l, 4l);
		
		ForestMinistry.print();
		
		GraphBorder base_graphborder = ForestMinistry.constructForest();
		
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");

		
		

	}

}
