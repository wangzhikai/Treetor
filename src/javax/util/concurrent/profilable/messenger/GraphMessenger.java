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

package javax.util.concurrent.profilable.messenger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.ForestMinistry;
import net.heteroclinic.graph.GraphBorder;
import net.heteroclinic.graph.GraphOrientation;
import net.heteroclinic.graph.Test;

public class GraphMessenger implements Runnable {
	public static BlockingQueue<MessageForGraph> messageque = new LinkedBlockingQueue<MessageForGraph>();
	public static volatile boolean stopped = false;
	protected volatile boolean halted = false;

	public boolean isHalted() {
		return halted;
	}

	public void setHalted(boolean halted) {
		this.halted = halted;
	}

	protected CountDownLatch startsignal;

	public GraphMessenger(CountDownLatch startsignal) {
		this.startsignal = startsignal;

	}

	@Override
	public void run() {
		try {
			startsignal.countDown();
			boolean reconstructgraph = true;
			while (!Thread.interrupted() && !halted ) {

				MessageForGraph msg = messageque.take();
				// System.out.println("hi");
				System.out.println("MSG: "+msg);
				switch (msg.eft) {
				case removeNodeSubnodePairforProfilable :
					ForestMinistry.removeNodeSubnodePairforProfilable(msg.getVarat(0), msg.getVarat(1), msg.getVarat(2));
					reconstructgraph = true;
					break;
				case createARNodeforProfilable :
					//assert(msg.vargs.length == msg.eft.getLenreq());
					ForestMinistry.createARNodeforProfilable (msg.getVarat(0),msg.getVarat(1));
					reconstructgraph = false;
					break;
				case setNodeSubnodePairforProfilable:
					ForestMinistry.setNodeSubnodePairforProfilable(msg.getVarat(0), msg.getVarat(1));
					reconstructgraph = true;
					break;
				case createATNodeforProfilable:
					ForestMinistry.createATNodeforProfilable(msg.getVarat(0), msg.getVarat(1));
					reconstructgraph = true;
					break;
				case ARGTHREE:
//					msg = new MessageForGraph(FunctorType.ARGTHREE, 331, 372,
//							373);
					reconstructgraph = false;
					break;
				case ARGTWO:
//					msg = new MessageForGraph(FunctorType.ARGTWO, 21, 22);
					reconstructgraph = false;
					break;
				case ARGONE:
//					msg = new MessageForGraph(FunctorType.ARGONE, 15);
					reconstructgraph = false;
					break;
				default:
					System.out.println("Unkown msg receveived in GraphMessenger");

				}
				if (reconstructgraph){
					GraphBorder base_graphborder = null;
					if ( Bag.forestministryusepresetgraphborder ) {
						 ForestMinistry.constructForest();
						base_graphborder =  ForestMinistry.getGraphborder();
					} else {
						base_graphborder = ForestMinistry.constructForest();
					}
					
					ForestMinistry.forest2drender(Test.getAResultFilename(),base_graphborder, GraphOrientation.ToptoBottom);

					 
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("GraphMessenger thread interupted 1!");

		} finally {
			if (halted) {
				System.out.println("GraphMessenger thread halted!");
			} else {
				System.out.println("GraphMessenger thread interupted 2!");
			}
		}

	}

}
