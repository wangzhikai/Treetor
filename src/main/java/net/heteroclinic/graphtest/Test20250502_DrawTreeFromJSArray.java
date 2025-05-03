package net.heteroclinic.graphtest;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.ForestMinistry;
import net.heteroclinic.graph.GraphBorder;
import net.heteroclinic.graph.GraphOrientation;
import net.heteroclinic.graph.Node;
import net.heteroclinic.graph.Test;
import net.heteroclinic.graph.Nodetype;
import java.util.Map;
import java.util.HashMap;

public class Test20250502_DrawTreeFromJSArray extends Test {

    public static void main(String[] args) {
        // Set up Bag properties
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

        // Node.js array representing the tree
        Integer[] a = {
    //0 
    50, 
    //1 
    30, 70, 
    //3 
    20, 40, 60, 80, 
    //7 
    10, 25, 35, 45,        null, 65, 75, 90, 
    //15 
    null, null, null,27,   null, null, null,47,   null,null, null, null,  null, 85, null, null,
    //31 for 85 i = 28, 99 = 2*28+2 = 58
      null, null, null, null, 
      // 35
      null, null, null, null, 
      // 39
      null, null, null, null, 
      // 43
      null, null, null, null, 
      
      // 47
      null, null, null, null, 
      // 51
      null, null, null, null, 
      // 55
      null, null, null, 99 
        };

        // Map to store non-null nodes
        //Node[] nodes = new Node[a.length];


        // Create the root node
		//// create nodes at level 1
		for (int i =0; i< a.length;i++) {
			System.out.println("a = "+a[i]);
		}
        // if (a[0] != null) {
        //     ForestMinistry.createANode(Nodetype.TNODE,(long) a[0]);
		// 	map.put(0,map.size()+1);
        //     //nodes[0].setValue(a[0]);
        // } else {
        //     System.out.println("Root node is null. Cannot construct tree.");
        //     return;
        // }
		// ForestMinistry.createANode(Nodetype.TNODE,(long) a[0]);
		// map.put(0,map.size()+1);
		// ForestMinistry.createANode(Nodetype.TNODE,(long) a[1]);
		// map.put(1,map.size()+1);

		// System.out.println(map.get(0));
		// System.out.println(map.get(1));
		// ForestMinistry.setNodeSubnodePair(map.get(0), map.get(1));
		// ForestMinistry.addAVirtaulEdge(map.get(0), map.get(1));


		// Node n = new Node("333");
		// Node n2= new Node("44");
		// Node n3= new Node("555");
        // Node.addNodeSubnodePair(1l, 2l);
		// Node.addNodeSubnodePair(1l, 3l);
		// Node.allnodes.get(1l).BFSGraphConstruction();

		int totalLevel = (int) Math.ceil(Math.log(a.length + 1)/Math.log(2.0));
		System.out.println("totalLevel = "+ totalLevel + " a.length ="+ a.length);
		Map<Integer,Long> noneEmptyNodes = new HashMap<Integer,Long>();

		// create nodes at level 1
		if (a[0]!=null) {
			Node nn = new Node(""+a[0]);
			noneEmptyNodes.put(0,nn.getId());
		} else {
			System.out.println("Root node is null. Cannot construct tree.");
            return;
		}

		// Node nn = new Node(""+a[1]);
		// noneEmptyNodes.put(1,nn.getId());
		// Node.addNodeSubnodePair(1l, 2l);
		for (int l = 2; l<= totalLevel; l++) {        
			// create nodes at current level
			// connect parent level down to current level
			//! for ( var j = 2**(l-1)-1 ; j<= 2**l-2; j++) {
			for (int j =(int) Math.pow(2, l - 1) - 1; j <= Math.min((int) Math.pow(2, l) - 2, a.length - 1); j++) {
				if (a[j]!= null) {
					Node nn = new Node(""+a[j]);
					noneEmptyNodes.put(j,nn.getId());
					int pi = (int) Math.floor((j-1)/2);
					Long pn = noneEmptyNodes.get(pi);
					if (pn == null ) {
						System.out.println("Root node is null. Cannot construct tree." +"j ="+ j  +"a[j] ="+a[j] + " pi "+ pi);
						return;
					}
					// TODO if is a right node and parent has no left, add the parent a dummy left
					if ( j % 2== 0 && a[j-1] == null) {
						Node nn2 = new Node("");
						noneEmptyNodes.put(j-1,nn2.getId());
						Node.addNodeSubnodePair(pn, nn2.getId());
					}
					Node.addNodeSubnodePair(pn, nn.getId());
				}
			}
		}

		
		Node.allnodes.get(1l).BFSGraphConstruction();
		






		//TEST TOP to bottom
		Node.allnodes.get(1l).image2dRender(Test.getAResultFilename(),
				GraphOrientation.ToptoBottom);
		System.out.println("Picture drawn.");




 
    }
}