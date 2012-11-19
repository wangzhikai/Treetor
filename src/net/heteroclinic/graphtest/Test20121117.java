package net.heteroclinic.graphtest;

import java.util.LinkedHashSet;

import net.heteroclinic.graph.DirectedTuple;

public class Test20121117 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//To test Directed Tuple
		DirectedTuple dt1 = new DirectedTuple (1l,2l);
		DirectedTuple dt2 = new DirectedTuple (2l,1l);
		DirectedTuple dt3 = new DirectedTuple (1l,2l);
		DirectedTuple dt4 = new DirectedTuple (1l,3l);
		
		System.out.println(dt1.equals(dt3));// true 
		System.out.println(dt1.equals(dt2));// false
		System.out.println(dt1.equals(dt4));// false
		
		LinkedHashSet<DirectedTuple> lhs = new LinkedHashSet<DirectedTuple>();
		lhs.add(dt1);
		lhs.add(dt2);
		lhs.add(dt3);
		lhs.add(dt4);
		System.out.println(lhs.contains(dt1));//true
		lhs.remove(dt3);
		System.out.println(lhs.contains(dt1));//false
		

	}

}
