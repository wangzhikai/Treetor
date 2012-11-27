package javax.util.concurrent.profilable.test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import javax.util.concurrent.profilable.locks.ProfilableReentrantLock;


import sun.misc.Unsafe;

public class Test20121123 {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		ProfilableReentrantLock lock1 = new ProfilableReentrantLock();
		ProfilableReentrantLock lock2 = new ProfilableReentrantLock();
		List<Future<?>> fl = new ArrayList<Future<?>>();
		ExecutorService exec = Executors.newCachedThreadPool();
		fl.add(exec.submit(new ProfilableRuntype1(lock1, lock2)));
		fl.add(exec.submit(new ProfilableRuntype2(lock1, lock2)));

		TimeUnit.MILLISECONDS.sleep(10000);

		exec.shutdown();
		for (Future<?> f : fl)
			f.cancel(true);

	}

}
