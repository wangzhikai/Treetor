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

import javax.util.concurrent.profilable.LockerRoom;
import javax.util.concurrent.profilable.ProfilableRunnable;
import javax.util.concurrent.profilable.locks.ProfilableReentrantLock;
import javax.util.concurrent.profilable.messenger.FunctorType;
import javax.util.concurrent.profilable.messenger.GraphMessenger;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.ForestMinistry;

class Testrun_II extends ProfilableRunnable{

	public Testrun_II(CountDownLatch startsignal) {
		super(startsignal);
	}

	@Override
	public void subContract()  {
		try {
			
			LockerRoom.samplelocks[0].lock();
			LockerRoom.samplelocks[1].lock();
			LockerRoom.samplelocks[2].lock();
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
//		catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		finally {
			LockerRoom.samplelocks[0].unlock();
			LockerRoom.samplelocks[1].unlock();
			LockerRoom.samplelocks[2].unlock();
		}
	}
	
}

public class Test20121201_II {
//	public static ProfilableReentrantLock [] locks =
//			new ProfilableReentrantLock [10];
//	static {
//		for (int i = 0; i<locks.length; i++) {
//			locks[i]  = new ProfilableReentrantLock();
//		}
//	}

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		Bag.tierdistance = 10.0d;
		Bag.nodesize = 2.5d;
		Bag.nodespace = 1.3d * Bag.nodesize;

		Bag.familydistance = 2.0d * Bag.nodespace;
		Bag.boderspace = 3.0d * Bag.nodespace;
		Bag.OneDoubleequalpixels = 15;
		Bag.fontsize = 14;
		Bag.fontwidth = 8; // not accurate.
		Bag.edgetrim = 1.5d; // ratio
		
		Bag.testresultfilepath = "C:\\Users\\Graphics\\Desktop\\treetortest\\";
		Bag.testunitname = "PFLB_JUST_LOCK_UNLOCK";
		Bag.testresultfiletype = ".png";
		

		Bag.treeinteveralinforest = 1.0d * Bag.nodespace;
		
		
		// first pass set Bag.forestministryusepresetgraphborder false
		// second pass set true, change the values with ForestMinistry.initGraphBorder
		Bag.forestministryusepresetgraphborder =true;
		if ( Bag.forestministryusepresetgraphborder ) {
			Bag.forestministryupdategraphborder = false;
			
			ForestMinistry.initGraphBorder(-1,0.0,0.0,0.0,0.0,0.0,0.0,31.375,26.0);
		} else {
			Bag.forestministryupdategraphborder = true;
		}
		
		CountDownLatch startSignal = new CountDownLatch(1);
		
		List<Future<?>> fl = new ArrayList<Future<?>>();
		ExecutorService exec = Executors.newCachedThreadPool();
		
		fl.add(exec.submit(new GraphMessenger(startSignal)));
	
		fl.add(exec.submit(new Testrun_II( startSignal)));


		TimeUnit.MILLISECONDS.sleep(2000);

		exec.shutdown();
		for (Future<?> f : fl)
			f.cancel(true);
		
		ForestMinistry.printStaticGraphBorder();

	}

}
