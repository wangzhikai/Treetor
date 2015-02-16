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

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Runtype2 implements Runnable {
	ReentrantLock lock1;
	ReentrantLock lock2;

	public Runtype2(ReentrantLock lock1, ReentrantLock lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
	}

	@Override
	public void run() {
		lock1.lock(); // will wait until this thread gets the lock
		try {
			// critical section
			try {
				TimeUnit.MILLISECONDS.sleep(300000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		} finally {
			// releasing the lock so that other threads can get notifies
			lock1.unlock();
			System.out.println("Type 2 released");
		}

	}

}
