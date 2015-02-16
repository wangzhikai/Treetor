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
package javax.util.concurrent.profilable;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.util.concurrent.profilable.messenger.FunctorType;
import javax.util.concurrent.profilable.messenger.GraphMessenger;
import javax.util.concurrent.profilable.messenger.MessageForGraph;

public abstract class ProfilableRunnable implements Runnable,Profilable  {
	protected final long profiableId ;
	public long getProfiableId() {
		return profiableId;
	}
	
	protected CountDownLatch startsignal;
	public ProfilableRunnable (CountDownLatch startsignal) {
		this.startsignal = startsignal;
		profiableId = net.heteroclinic.graph.Node.counter.incrementAndGet();
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
			ThreadIdProfilableIdMap.putAnEntry(Thread.currentThread().getId(), this.getProfiableId());
			GraphMessenger.messageque.put(
					new MessageForGraph(FunctorType.createATNodeforProfilable, this.getProfiableId(),Thread.currentThread().getId()));

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
