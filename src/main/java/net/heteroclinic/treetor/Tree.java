/**
 * @author Zhikai
 * We no longer use node to represent a tree to avoid using the static structure to store all nodes.
 */
package net.heteroclinic.treetor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Tree<T> {
	//protected Node<T> root;
	protected long rootId = -1;
	protected Map<Long, Node<T>> allSubNodes = new LinkedHashMap<Long, Node<T>>();
	protected AtomicLong counter = new AtomicLong(0L);
	public static final long addNodeFailure = -1l;
	
	public Tree() {
		super();
	}	
	
	/**
	 * Not thread safe
	 * @param rootValue
	 * @return
	 */	
	public Long createAndAddRoot( T rootValue) {
		if (rootValue == null)
			return addNodeFailure;
		long id = counter.incrementAndGet();
		rootId = id;
		Node<T> n = new Node<T>(rootValue,id);
		allSubNodes.put(id,  n);
		return id;
	}
	//TODO add node
	/**
	 * The first parameter is the parent id, the id must exist.
	 * The second parameter is the child node's value, it should not exist.
	 * The following conditions must be full-filled to create a new root:
	 * - the first parameter is not a positive integer/long value
	 * - the tree has zero nodes
	 * There is no check needed if the value already exist. We build a render tree
	 * but not search tree or by any means ordered tree.
	 * Return the new node id, if the return id is less or equal to zero, the
	 * addition of new node is a failure.
	 */
	public Long addANewNode (final Long parentNodeId, T newNodeValue ) {
		if (parentNodeId == null || parentNodeId<0l || 
				!allSubNodes.keySet().contains(parentNodeId) ) 
		{
			return -1l;
		}
		long id = counter.incrementAndGet();
		Node<T> p = allSubNodes.get(parentNodeId);
		Node<T> n = new Node<T>(newNodeValue,id);
		allSubNodes.put(id,  n);
		n.setParentnode(p);
		p.addSubNode(id, n);
		return id;
	}
	
	//TODO create a tree manually for test
	public static void main(String [] args) {
		Tree<Integer> t1 = new Tree<Integer>();
		long rid = t1.createAndAddRoot(5);
		
		long n3 = t1.addANewNode(rid,3);
		
		t1.addANewNode(n3,1 );
		t1.addANewNode(rid,2);
		
		long n7 = t1.addANewNode(rid,7);
		long n6 = t1.addANewNode(n7,6 );
		long n15 = t1.addANewNode(n7,15 );	
		
		t1.addANewNode(n15,8);
		t1.addANewNode(n15,9);
		t1.addANewNode(n15,10);
		t1.addANewNode(n15,19);
		t1.printBFS();
		/*
5 
3 2 7 
1 6 15 
8 9 10 19 

		 */
		
		
		
	}
	//TODO BFS print
	
	public void printBFS () {
		Map<Long, Node<T>> currentLayer = new LinkedHashMap<Long, Node<T>>();
		//List<T> currentLayer = new ArrayList<T>();
		currentLayer.put(rootId, allSubNodes.get(rootId));
		while (currentLayer.size()>0) {
			Map<Long, Node<T>>  tmpLayer = new LinkedHashMap<Long, Node<T>>();
			for (Long k : currentLayer.keySet()) {
				Node<T> n = currentLayer.get(k);
				System.out.print(n.getValue() + " ");
				if (n.getSubnodes().size()>0) {
					for (Long ks: n.getSubnodes().keySet()) {
						tmpLayer.put(ks, n.getSubnodes().get(ks));
					}
				}
			}
			System.out.println();
			currentLayer = tmpLayer;
			
			
		}
	}
	//TODO Render
	//TODO stress test 

}
