package net.heteroclinic.graphtest;

import java.util.Calendar;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.GraphBorder;
import net.heteroclinic.graph.GraphOrientation;
import net.heteroclinic.graph.Node;
import net.heteroclinic.graph.RNode;
import net.heteroclinic.graph.TNode;
import net.heteroclinic.graph.Test;

public class Test20121017 extends Test{

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
		Bag.testunitname = "TEST_RTNODE_TREE";
		Bag.testresultfiletype = ".png";

		
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
		Node.allnodes.get(1l).updateGraphborder();
		GraphBorder base_graphborder  = Node.allnodes.get(1l).getGraphborder();
		//System.out.println( Node.allnodes.get(1l).getGraphborder().getWidth() + "\t"+ Node.allnodes.get(1l).getGraphborder().getHeight());
		
		Node.zero(); 
		
		
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
		Node.allnodes.get(1l).BFSGraphConstruction();
		Node.allnodes.get(1l).image2dRenderWithoutUpdateBorder(Test.getAResultFilename(),base_graphborder,GraphOrientation.LefttoRight);

		
		
		Node.addNodeSubnodePair(1,10);
		Node.allnodes.get(1l).BFSGraphConstruction();
		Node.allnodes.get(1l).image2dRenderWithoutUpdateBorder(Test.getAResultFilename(), base_graphborder,GraphOrientation.LefttoRight);


		
		Node.addNodeSubnodePair(1,11);
		Node.allnodes.get(1l).BFSGraphConstruction();
		Node.allnodes.get(1l).image2dRenderWithoutUpdateBorder(Test.getAResultFilename(),base_graphborder, GraphOrientation.LefttoRight);


		Node.addNodeSubnodePair(1,12);
		Node.allnodes.get(1l).BFSGraphConstruction();
		Node.allnodes.get(1l).image2dRenderWithoutUpdateBorder(Test.getAResultFilename(),base_graphborder, GraphOrientation.LefttoRight);


		
		Node.addNodeSubnodePair(9,2);
		Node.allnodes.get(1l).BFSGraphConstruction();
		Node.allnodes.get(1l).image2dRenderWithoutUpdateBorder(Test.getAResultFilename(),base_graphborder, GraphOrientation.LefttoRight);


		Node.addNodeSubnodePair(9,3);
		Node.allnodes.get(1l).BFSGraphConstruction();
		Node.allnodes.get(1l).image2dRenderWithoutUpdateBorder(Test.getAResultFilename(),base_graphborder, GraphOrientation.LefttoRight);


		Node.addNodeSubnodePair(10,4);
		Node.allnodes.get(1l).BFSGraphConstruction();
		Node.allnodes.get(1l).image2dRenderWithoutUpdateBorder(Test.getAResultFilename(),base_graphborder, GraphOrientation.LefttoRight);

		
		Node.addNodeSubnodePair(11,5);
		Node.allnodes.get(1l).BFSGraphConstruction();
		Node.allnodes.get(1l).image2dRenderWithoutUpdateBorder(Test.getAResultFilename(),base_graphborder, GraphOrientation.LefttoRight);

		Node.addNodeSubnodePair(11,6);
		Node.allnodes.get(1l).BFSGraphConstruction();
		Node.allnodes.get(1l).image2dRenderWithoutUpdateBorder(Test.getAResultFilename(),base_graphborder, GraphOrientation.LefttoRight);

		
		Node.addNodeSubnodePair(5,13);
		Node.allnodes.get(1l).BFSGraphConstruction();
		Node.allnodes.get(1l).image2dRenderWithoutUpdateBorder(Test.getAResultFilename(),base_graphborder, GraphOrientation.LefttoRight);

		
		Node.addNodeSubnodePair(13,7);
		Node.allnodes.get(1l).BFSGraphConstruction();
		Node.allnodes.get(1l).image2dRenderWithoutUpdateBorder(Test.getAResultFilename(),base_graphborder, GraphOrientation.LefttoRight);

		Node.addNodeSubnodePair(13,8);
		Node.allnodes.get(1l).BFSGraphConstruction();
		Node.allnodes.get(1l).image2dRenderWithoutUpdateBorder(Test.getAResultFilename(),base_graphborder, GraphOrientation.LefttoRight);
	}

}
