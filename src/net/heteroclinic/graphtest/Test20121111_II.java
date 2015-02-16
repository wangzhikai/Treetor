package net.heteroclinic.graphtest;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.ForestMinistry;
import net.heteroclinic.graph.GraphBorder;
import net.heteroclinic.graph.GraphOrientation;
import net.heteroclinic.graph.Nodetype;
import net.heteroclinic.graph.Test;


public class Test20121111_II extends net.heteroclinic.graph.Test {
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
		
		//Bag.testresultfilepath = "C:\\Users\\Graphics\\Desktop\\treetortest\\";
		Bag.testresultfilepath = "/home/zhikai/Desktop/treetortest/";
		Bag.testunitname = "LIVELOCK_TEST";
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
		ForestMinistry.createANode(Nodetype.TNODE);
		
		ForestMinistry.createANode(Nodetype.RNODE,-1l);
		ForestMinistry.createANode(Nodetype.RNODE,-1l);
		ForestMinistry.createANode(Nodetype.RNODE,-1l);
		ForestMinistry.createANode(Nodetype.RNODE,-1l);
		
		
		ForestMinistry.setNodeSubnodePair(1l, 3l);
		ForestMinistry.setNodeSubnodePair(2l, 5l);
		
		ForestMinistry.setNodeSubnodePair(1l, 4l);
		ForestMinistry.setNodeSubnodePair(2l, 6l);
		
		
		ForestMinistry.addAVirtaulEdge(1l, 5l);
		ForestMinistry.setNodeSubnodePair(5l, 1l);
		ForestMinistry.removeAVirtualEdge(1l, 5l);
//		
//
		ForestMinistry.addAVirtaulEdge(2l, 3l);
		ForestMinistry.removeNodeSubnodePair(2l, 5l,3);
		
		ForestMinistry.removeAVirtualEdge(2l, 3l);
		
		ForestMinistry.removeNodeSubnodePair(5l, 1l,1);
		ForestMinistry.setNodeSubnodePair(1l, 5l);
		
		ForestMinistry.addAVirtaulEdge(2l, 5l);
		
		ForestMinistry.setNodeSubnodePair(5l, 2l);
		ForestMinistry.removeAVirtualEdge(2l, 5l);
		
		
		ForestMinistry.addAVirtaulEdge(1l, 6l);
		
		ForestMinistry.removeNodeSubnodePair(1l, 5l,3);
		ForestMinistry.removeNodeSubnodePair(5l, 2l,1);
		ForestMinistry.setNodeSubnodePair(2l, 5l);

		ForestMinistry.removeAVirtualEdge(1l, 6l);
		

		ForestMinistry.addAVirtaulEdge(1l, 5l);
		
		//ForestMinistry.print();
		GraphBorder base_graphborder = ForestMinistry.constructForest();
		//ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		//System.out.println("Picture drawn.");
		
		ForestMinistry.zero();

		

		ForestMinistry.createANode(Nodetype.TNODE);
		ForestMinistry.createANode(Nodetype.TNODE);
		
		ForestMinistry.constructForest(base_graphborder);
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");
		
		ForestMinistry.createANode(Nodetype.RNODE,-1l);
		ForestMinistry.createANode(Nodetype.RNODE,-1l);
		ForestMinistry.createANode(Nodetype.RNODE,-1l);
		ForestMinistry.createANode(Nodetype.RNODE,-1l);
		
		
		ForestMinistry.setNodeSubnodePair(1l, 3l);
		ForestMinistry.setNodeSubnodePair(2l, 5l);

		ForestMinistry.constructForest(base_graphborder);
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");
		
		
		ForestMinistry.setNodeSubnodePair(1l, 4l);
		ForestMinistry.setNodeSubnodePair(2l, 6l);

		ForestMinistry.constructForest(base_graphborder);
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");

		ForestMinistry.addAVirtaulEdge(1l, 5l);

		ForestMinistry.constructForest(base_graphborder);
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");

		
		ForestMinistry.setNodeSubnodePair(5l, 1l);
		ForestMinistry.removeAVirtualEdge(1l, 5l);

		ForestMinistry.constructForest(base_graphborder);
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");

		
		ForestMinistry.addAVirtaulEdge(2l, 3l);
		
		ForestMinistry.constructForest(base_graphborder);
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");

		
		ForestMinistry.removeNodeSubnodePair(2l, 5l,3);
		ForestMinistry.removeAVirtualEdge(2l, 3l);
		ForestMinistry.removeNodeSubnodePair(5l, 1l,1);
		ForestMinistry.setNodeSubnodePair(1l, 5l);
		
		ForestMinistry.constructForest(base_graphborder);
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");

		
		ForestMinistry.addAVirtaulEdge(2l, 5l);
		
		ForestMinistry.constructForest(base_graphborder);
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");

		
		ForestMinistry.setNodeSubnodePair(5l, 2l);
		ForestMinistry.removeAVirtualEdge(2l, 5l);
		
		ForestMinistry.constructForest(base_graphborder);
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");
		

		ForestMinistry.addAVirtaulEdge(1l, 6l);
		
		ForestMinistry.constructForest(base_graphborder);
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");

		
		ForestMinistry.removeNodeSubnodePair(1l, 5l,3);
		ForestMinistry.removeNodeSubnodePair(5l, 2l,1);
		ForestMinistry.setNodeSubnodePair(2l, 5l);
		ForestMinistry.removeAVirtualEdge(1l, 6l);
		
		ForestMinistry.constructForest(base_graphborder);
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");		

		
		ForestMinistry.addAVirtaulEdge(1l, 5l);

		ForestMinistry.constructForest(base_graphborder);
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");
		
//
//		ForestMinistry.createANode(Nodetype.TNODE);
//		ForestMinistry.createANode(Nodetype.TNODE);
//
//		ForestMinistry.constructForest(base_graphborder);
//		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
//		System.out.println("Picture drawn.");
//
//		ForestMinistry.createANode(Nodetype.RNODE);
//		ForestMinistry.createANode(Nodetype.RNODE);
//		ForestMinistry.setNodeSubnodePair(1l, 3l);
//		ForestMinistry.setNodeSubnodePair(2l, 4l);
//		
//		ForestMinistry.constructForest(base_graphborder);
//		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
//		System.out.println("Picture drawn.");
		
		
//		ForestMinistry.createANode(Nodetype.RNODE);
//		ForestMinistry.createANode(Nodetype.RNODE);
//		ForestMinistry.setNodeSubnodePair(1l, 5l);
//		ForestMinistry.setNodeSubnodePair(2l, 6l);
//		
//		ForestMinistry.constructForest(base_graphborder);
//		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
//		System.out.println("Picture drawn.");

	}
}
