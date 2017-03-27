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
import javax.util.concurrent.profilable.messenger.GraphMessenger;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.ForestMinistry;



public class Test20170303_20121204_I2 {
	

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
		
		Bag.testresultfilepath = "";
		Bag.testunitname = "PFLB_BLOCK_UNBLOCK_SIMPLE";
		Bag.testresultfiletype = ".png";
		

		Bag.treeinteveralinforest = 1.0d * Bag.nodespace;
		
		// first pass set Bag.forestministryusepresetgraphborder false
		// second pass set true, change the values with ForestMinistry.initGraphBorder
		Bag.forestministryusepresetgraphborder =true;
		if ( Bag.forestministryusepresetgraphborder ) {
			Bag.forestministryupdategraphborder = false;
			
			ForestMinistry.initGraphBorder(-1,0.0,0.0,0.0,0.0,0.0,0.0,41.375,29.25);
		} else {
			Bag.forestministryupdategraphborder = true;
		}	
		
		
		CountDownLatch startSignal = new CountDownLatch(1);
		
		List<Future<?>> fl = new ArrayList<Future<?>>();
		ExecutorService exec = Executors.newCachedThreadPool();
		
		//TODO how can we guarantee the static locks' constructors' messages are passed? because the message queue is static. We can buffer messages even Messenger thread is not started. 
		fl.add(exec.submit(new GraphMessenger(startSignal)));
	
		fl.add(exec.submit(new TestBlockUnblock_blocker( startSignal)));
		fl.add(exec.submit(new TestBlockUnblock_blockee( startSignal)));


		TimeUnit.MILLISECONDS.sleep(4000);

		exec.shutdown();
		for (Future<?> f : fl)
			f.cancel(true);
		
		ForestMinistry.printStaticGraphBorder();

	}

}
