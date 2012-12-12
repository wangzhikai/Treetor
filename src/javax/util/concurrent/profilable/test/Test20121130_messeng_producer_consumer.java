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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.util.concurrent.profilable.messenger.FunctorType;
import javax.util.concurrent.profilable.messenger.GraphMessenger;
import javax.util.concurrent.profilable.messenger.MessageForGraph;

class Producer implements Runnable {
	protected CountDownLatch startsignal;
	protected long sleeptime;
	public Producer(FunctorType eft, CountDownLatch startsignal,long sleeptime) {
		this.startsignal = startsignal;
		this.eft = eft;
		this.sleeptime =  sleeptime;
	}

	protected FunctorType eft;

	protected volatile boolean halted = false;

	public boolean isHalted() {
		return halted;
	}

	public void setHalted(boolean halted) {
		this.halted = halted;
	}

	@Override
	public void run() {
		
		try {
			//TimeUnit.MILLISECONDS.sleep(sleeptime);
			startsignal.await();
			while (!Thread.interrupted() && !halted) {
				MessageForGraph msg = null;
				switch (eft) {
				case ARGTHREE:
					msg = new MessageForGraph(FunctorType.ARGTHREE, 331, 372,
							373);
					break;
				case ARGTWO:
					msg = new MessageForGraph(FunctorType.ARGTWO, 21, 22);
					break;
				case ARGONE:
					msg = new MessageForGraph(FunctorType.ARGONE, 15);
					break;
				}
				GraphMessenger.messageque.put(msg);
				
				TimeUnit.MILLISECONDS.sleep(sleeptime);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();

		} finally {
			if (halted) {
				System.out.println(eft + " thread halted!");
			} else {
				System.out.println(eft + " thread interupted!");
			}
		}
	}
}

public class Test20121130_messeng_producer_consumer {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		// int[] numbers = { 2, 3, 4, 5 };
		// int[] numbersClone = (int[]) numbers.clone();
		// System.out.println(numbers[2]);
		// System.out.println(numbersClone[2]);
		// numbersClone[2]= 0;
		// System.out.println(numbers[2]);
		// System.out.println(numbersClone[2]);

		CountDownLatch startSignal = new CountDownLatch(1);
		
		List<Future<?>> fl = new ArrayList<Future<?>>();
		ExecutorService exec = Executors.newCachedThreadPool();
		
		fl.add(exec.submit(new GraphMessenger(startSignal)));
	
		fl.add(exec.submit(new Producer(FunctorType.ARGTHREE, startSignal,10)));
		fl.add(exec.submit(new Producer(FunctorType.ARGTWO, startSignal,7)));
		fl.add(exec.submit(new Producer(FunctorType.ARGONE, startSignal,3)));

		TimeUnit.MILLISECONDS.sleep(10000);

		exec.shutdown();
		for (Future<?> f : fl)
			f.cancel(true);


		// ARGTHREE(3),
		// ARGTWO(2),
		// ARGONE(1);

	}

}
