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

public class Runtype1 implements Runnable{
	ReentrantLock lock1;
	ReentrantLock lock2;

	public Runtype1(ReentrantLock lock1, ReentrantLock lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
	}

	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lock1.lock(); // will wait until this thread gets the lock
		System.out.println("pt 1");
		try {
			// critical section
			System.out.println("pt 2");

		} finally {
			// releasing the lock so that other threads can get notifies
			System.out.println("pt 3");
			lock1.unlock();
			System.out.println("pt 4");
		}
		System.out.println("pt 5");

	}

}
