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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.util.concurrent.profilable.locks.ProfilableReentrantLock;

public class Test20121126_I {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO
		// this class can be used for a case for being blocked.
		// set debug point  ProfilableRuntype1 run () lock1.lock(); // will wait until this thread gets the lock
		ProfilableReentrantLock lock1 = new ProfilableReentrantLock();
		ProfilableReentrantLock lock2 = new ProfilableReentrantLock();
		List<Future<?>> fl = new ArrayList<Future<?>>();
		ExecutorService exec = Executors.newCachedThreadPool();
		fl.add(exec.submit(new ProfilableRuntype1(lock1, lock2,500)));
		fl.add(exec.submit(new ProfilableRuntype2(lock1, lock2,3000000)));

		TimeUnit.MILLISECONDS.sleep(10000000);

		exec.shutdown();
		for (Future<?> f : fl)
			f.cancel(true);

	}

}
