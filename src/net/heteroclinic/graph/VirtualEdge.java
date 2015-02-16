/*
 * The author Zhikai Wang/www.heteroclinic.net reserves all rights of this file.
 * (c) 2012  Zhikai Wang/www.heteroclinic.net
 *  
 */

package net.heteroclinic.graph;

public class VirtualEdge extends Edge {

	public VirtualEdge(Node point1, Node point2, long id) {
		super(point1, point2, id);
		this.start = point1.getId();
		this.end = point2.getId();
	}
	public static final long HASHSHIFT = 1000000l;
	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

	protected long start;
	protected long end;

	public int hashCode() {
		// return (int)(value ^ (value >>> 32));
		return (int) (start * HASHSHIFT + end);
	}

//	public DirectedTuple(long start, long end) {
//		this.start = start;
//		this.end = end;
//	}

	public boolean equals(Object obj) {
		if (obj instanceof VirtualEdge) {
			//return value == ((Long) obj).longValue();
			VirtualEdge tmpobj = (VirtualEdge)obj;
			return (start == tmpobj.getStart()) && (end == tmpobj.getEnd());
		}
		return false;
	}
}
