/*
 * jdfr: Java Dead-lock Free Runtime (the goal of this project)
 * 
 * Author Zhikai Wang/www.heteroclinic.net 
 * 
 * Please keep this header.
 *
 * 2012
 * 
 */
package javax.util.concurrent.profilable.test;

import java.util.concurrent.locks.LockSupport;

public class Test20121123_selfInterrupt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//This method is wrong.
		System.out.println("pt 1");
		Thread.currentThread().interrupt();
		System.out.println("pt 2");
		Object o = new Object();

		 LockSupport.park(500000l);
		 System.out.println("pt 3");
	}

}
