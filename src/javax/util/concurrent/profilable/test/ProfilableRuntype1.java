package javax.util.concurrent.profilable.test;

import java.util.concurrent.TimeUnit;

import javax.util.concurrent.profilable.locks.ProfilableReentrantLock;
//import java.util.concurrent.locks.ReentrantLock;

public class ProfilableRuntype1 implements Runnable{
	ProfilableReentrantLock lock1;
	ProfilableReentrantLock lock2;
	long delay ;

	public ProfilableRuntype1(ProfilableReentrantLock lock1, ProfilableReentrantLock lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
		this.delay = 500l;
	}
	public ProfilableRuntype1(ProfilableReentrantLock lock1, ProfilableReentrantLock lock2, long delay) {
		this.lock1 = lock1;
		this.lock2 = lock2;
		this.delay = delay;
	}

	@Override
	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(delay);
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
