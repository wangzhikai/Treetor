/*
 * The author Zhikai Wang/www.heteroclinic.net reserves all rights of this file.
 * (c) 2012  Zhikai Wang/www.heteroclinic.net
 *  
 */

package net.heteroclinic.graph;

public class Edge { 
	//protected AtomicLong counter = new  AtomicLong(0L);
	protected long id = -1l;
	
	public long getId() {
		return id;
	}

	public Node getPoint1() {
		return point1;
	}
	public Node getPoint2() {
		return point2;
	}

	protected Node point1;
	protected Node point2;
	// id is the subnode's id
	public Edge(Node point1,Node point2,long id) {
		this.point1 = point1;
		this.point2 = point2;
		//id = counter.incrementAndGet();
		this.id = id;
	}
}
