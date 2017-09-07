package net.heteroclinic.treetor;

import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.heteroclinic.graph.Edge;
import net.heteroclinic.graph.Geotype;
import net.heteroclinic.graph.Point3D;

public class Node<T> {
	protected Color fillcolor = new Color(111,111,111);
	protected Point3D position = new Point3D(0.0d,0.0d,0.0d);

	public void setPosition( double x,double y,double z) {
		position.setX(x) ;
		position.setY(y);
		position.setZ(z);
	}
	
	protected Map<Long, Node<T>> subnodes = new LinkedHashMap<Long, Node<T>>();	
	public Map<Long, Node<T>> getSubnodes() {
		return subnodes;
	}

	protected long id = -1L;
	public long getId() {
		return id;
	}

	protected Node<T> parentnode = null;
	public Node<T> getParentnode() {
		return parentnode;
	}

	public void setParentnode(Node<T> parentnode) {
		this.parentnode = parentnode;
	}
	public Point3D getPosition() {
		return position;
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
	public long getFamilyId () {
		if (parentnode == null)
			return 0;
		else 
			return parentnode.getId();
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
		this.value = value;
		this.id = id;
		this.label=value.toString();
	}
	public Node(final T value, final long id, final String label) {
		this.value = value;
		this.id = id;
		this.label=label;
	}

	public Geotype getGeotype() {
		return geotype;
	}
	
	public void setGeotype(Geotype geotype) {
		this.geotype = geotype;
	}
	protected Map <Long, Edge<T> >   localedges = new HashMap<Long, Edge<T> >();

	public void addALocalEdge ( Edge<T> edge) {
		localedges.put(edge.getId(), edge);
	}
	protected Geotype geotype = Geotype.Equator; // Equator, Northern, Southern
	protected Node<T> alignsubnode = null;

	public void assignSubnodesGeotype () {
		if (subnodes.size() <=0) {
			this.alignsubnode = null;
			return;
		}
		if (geotype ==  Geotype.Southern) {
			//assign all to 
			Iterator<Long> it = subnodes.keySet().iterator();
			List <Node<T>> tmplist = new LinkedList <Node<T> >();
			while (it.hasNext()) {
				Long k = it.next();
				Node<T> tmpn = subnodes.get(k);
				tmpn.setGeotype(Geotype.Southern);
				tmplist.add(tmpn);
			}
			if (tmplist.size() >0) {
				this.alignsubnode = tmplist.get(tmplist.size()/2);
			}
		} else if (geotype ==  Geotype.Northern) {
			Iterator<Long> it = subnodes.keySet().iterator();
			List <Node<T>> tmplist = new LinkedList <Node<T> >();
			while (it.hasNext()) {
				Long k = it.next();
				Node<T> tmpn = subnodes.get(k);
				tmpn.setGeotype(Geotype.Northern);
				tmplist.add(tmpn);
			}
			if (tmplist.size() >0) {
				this.alignsubnode = tmplist.get(tmplist.size()/2);
			}
		} else {
			int size = subnodes.size();
			if (size %2 == 0 ) {
				int split = size/2;
				Iterator<Long> it = subnodes.keySet().iterator();
				int itc = 0;
				while (it.hasNext()) {
					Long l = it.next();
					Node<T> n = subnodes.get(l);
					if (itc < split) {
						n.setGeotype(Geotype.Northern);
					} else {
						n.setGeotype(Geotype.Southern);
					}
					itc++;
				}
			} else {
				int split = size/2;
				Iterator<Long> it = subnodes.keySet().iterator();
				int itc = 0;
				while (it.hasNext()) {
					Long l = it.next();
					Node<T> n = subnodes.get(l);
					if (itc < split) {
						n.setGeotype(Geotype.Northern);
					} else if (itc == split) {
						
						n.setGeotype(Geotype.Equator);
					} else 
						n.setGeotype(Geotype.Southern);
					itc++;
				}
			}
		}
	}
}
