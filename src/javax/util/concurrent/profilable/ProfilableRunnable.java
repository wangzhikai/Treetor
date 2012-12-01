package javax.util.concurrent.profilable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.util.concurrent.profilable.messenger.FunctorType;
import javax.util.concurrent.profilable.messenger.GraphMessenger;
import javax.util.concurrent.profilable.messenger.MessageForGraph;

public abstract class ProfilableRunnable implements Runnable  {
	protected CountDownLatch startsignal;
	public ProfilableRunnable (CountDownLatch startsignal) {
		this.startsignal = startsignal;
	}
	protected volatile boolean halted = false;

	public boolean isHalted() {
		return halted;
	}

	public void setHalted(boolean halted) {
		this.halted = halted;
	}
	
	public abstract void subContract  ();

	public void run() {
		try {
			GraphMessenger.messageque.put(
					new MessageForGraph(FunctorType.createATNode, Thread.currentThread().getId()));
			startsignal.await();
			subContract ();
//			while (!Thread.interrupted() && !halted) {
//			}
		} catch (InterruptedException e) {
			// e.printStackTrace();

		} finally {
			if (halted) {
				System.out.println("Thread halted!");
			} else if (Thread.interrupted()){
				System.out.println("Thread interupted!");
			} else {
				System.out.println("Thread stopped by itself!");
			}
		}
	}
}
