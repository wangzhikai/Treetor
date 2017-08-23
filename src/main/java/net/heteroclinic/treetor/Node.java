package net.heteroclinic.treetor;

import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Map;

public class Node<T> {
	protected Color fillcolor = new Color(111,111,111);
	
	protected Map<Long, Node<T>> subnodes = new LinkedHashMap<Long, Node<T>>();	
	public Map<Long, Node<T>> getSubnodes() {
		return subnodes;
	}

	protected long id = -1L;
	protected Node<T> parentnode = null;
	public Node<T> getParentnode() {
		return parentnode;
	}

	public void setParentnode(Node<T> parentnode) {
		this.parentnode = parentnode;
	}
	
	/**
	 * The caller of this function should check the integrity of the tree eg. no loop.
	 * @param child
	 */	
	public void addSubNode(long cid,Node<T> child) {
		if (child != null) {
			subnodes.put(cid, child);
		}
	}

	protected String label;
	protected T value;
	
	public T getValue() {
		return value;
	}

	// package visibility, only created through the tree it belongs to.
	Node() {
		
	}

	public Node(final T value, final long id) {
		// TODO Auto-generated constructor stub
		this.value = value;
		this.id = id;
	}
}
