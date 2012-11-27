package javax.util.concurrent.profilable.test;

import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.ReentrantLock;

import javax.util.concurrent.profilable.locks.ProfilableReentrantLock;

public class ProfilableRuntype2 implements Runnable {
	ProfilableReentrantLock lock1;
	ProfilableReentrantLock lock2;

	public ProfilableRuntype2(ProfilableReentrantLock lock1, ProfilableReentrantLock lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
	}

	@Override
	public void run() {
		lock1.lock(); // will wait until this thread gets the lock
		try {
			// critical section
			try {
				TimeUnit.MILLISECONDS.sleep(3000);
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
