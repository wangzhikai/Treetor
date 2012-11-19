package net.heteroclinic.graph;

public class DirectedTuple {
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

	public DirectedTuple(long start, long end) {
		this.start = start;
		this.end = end;
	}

	public boolean equals(Object obj) {
		if (obj instanceof DirectedTuple) {
			//return value == ((Long) obj).longValue();
			DirectedTuple tmpobj = (DirectedTuple)obj;
			return (start == tmpobj.getStart()) && (end == tmpobj.getEnd());
		}
		return false;
	}
}
