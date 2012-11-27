package javax.util.concurrent.profilable.test;

import java.util.concurrent.locks.LockSupport;

public class Test20121123_selfInterrupt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("pt 1");
		Thread.currentThread().interrupt();
		System.out.println("pt 2");
		Object o = new Object();

		 LockSupport.park(500000l);
		 System.out.println("pt 3");
	}

}
