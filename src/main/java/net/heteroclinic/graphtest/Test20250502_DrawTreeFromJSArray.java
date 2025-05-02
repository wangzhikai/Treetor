package net.heteroclinic.graphtest;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Random;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.GraphOrientation;
import net.heteroclinic.graph.Node;
import net.heteroclinic.graph.Test;
import net.heteroclinic.graph.ForestMinistry;
import net.heteroclinic.graph.GraphBorder;
import net.heteroclinic.graph.GraphOrientation;
import net.heteroclinic.graph.Nodetype;

public class Test20250502_DrawTreeFromJSArray extends Test{


	// TO construct a binary tree
	public static void main(String[] args) {

		
		Bag.tierdistance = 10.0d;
		Bag.nodesize = 1.6d;
		Bag.nodespace = 1.3d * Bag.nodesize;

		Bag.familydistance = 2.0d * Bag.nodespace;
		Bag.boderspace = 3.0d * Bag.nodespace;
		Bag.OneDoubleequalpixels = 15;
		Bag.fontsize = 14;
		Bag.fontwidth = 8; // not accurate.
		Bag.edgetrim = 1.5d; // ratio
		
		if (args.length > 0) {
            Bag.testresultfilepath = args[0];
        } else {
            Bag.testresultfilepath = "./"; // Default path
        }
		Bag.testunitname = "JSArrayTree";
		Bag.testresultfiletype = ".png";
		

		ForestMinistry.createANode(Nodetype.TNODE);
		ForestMinistry.createANode(Nodetype.TNODE);
		ForestMinistry.setNodeSubnodePair(1l, 2l);
		ForestMinistry.addAVirtaulEdge(1l, 2l);
		GraphBorder base_graphborder = ForestMinistry.constructForest();

		ForestMinistry.constructForest(base_graphborder);
		ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");
	}	
	
	



}
