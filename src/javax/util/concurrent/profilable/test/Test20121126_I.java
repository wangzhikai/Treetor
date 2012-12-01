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
		ProfilableReentrantLock lock1 = new ProfilableReentrantLock();
		ProfilableReentrantLock lock2 = new ProfilableReentrantLock();
		List<Future<?>> fl = new ArrayList<Future<?>>();
		ExecutorService exec = Executors.newCachedThreadPool();
		fl.add(exec.submit(new ProfilableRuntype1(lock1, lock2,500)));
		fl.add(exec.submit(new ProfilableRuntype2(lock1, lock2,30000)));

		TimeUnit.MILLISECONDS.sleep(1000000);

		exec.shutdown();
		for (Future<?> f : fl)
			f.cancel(true);

	}

}
