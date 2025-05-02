package net.heteroclinic.graphtest;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.GraphOrientation;
import net.heteroclinic.graph.Node;
import net.heteroclinic.graph.Test;

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
            50, 
            30, 70, 
            20, 40, 60, 80, 
            10, 25, 35, 45, null, 65, 75, 90, 
            null, null, null, 27, null, null, null, 47, null, null, null, null, null, 85, null, null,
            null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, 99
        };

        // Create nodes and establish parent-child relationships
        for (int i = 0; i < a.length; i++) {
            if (a[i] != null) {
                // Create the node
                Node currentNode = Node.getOrCreateNode((long) i + 1);
                currentNode.setValue(a[i]);

                // Add left child
                int leftIndex = 2 * i + 1;
                if (leftIndex < a.length && a[leftIndex] != null) {
                    Node leftChild = Node.getOrCreateNode((long) leftIndex + 1);
                    leftChild.setValue(a[leftIndex]);
                    Node.addNodeSubnodePair(currentNode.getId(), leftChild.getId());
                }

                // Add right child
                int rightIndex = 2 * i + 2;
                if (rightIndex < a.length && a[rightIndex] != null) {
                    Node rightChild = Node.getOrCreateNode((long) rightIndex + 1);
                    rightChild.setValue(a[rightIndex]);
                    Node.addNodeSubnodePair(currentNode.getId(), rightChild.getId());
                }
            }
        }

        // Render the tree
        Node.allnodes.get(1L).BFSGraphConstruction();
        Node.allnodes.get(1L).image2dRender(Test.getAResultFilename(), GraphOrientation.ToptoBottom);
        System.out.println("Tree rendered successfully!");
    }
}