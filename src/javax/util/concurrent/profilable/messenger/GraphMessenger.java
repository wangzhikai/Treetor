package javax.util.concurrent.profilable.messenger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import net.heteroclinic.graph.ForestMinistry;
import net.heteroclinic.graph.GraphBorder;
import net.heteroclinic.graph.GraphOrientation;
import net.heteroclinic.graph.Test;

public class GraphMessenger implements Runnable {
	public static BlockingQueue<MessageForGraph> messageque = new LinkedBlockingQueue<MessageForGraph>();
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
			while (!Thread.interrupted() && !halted) {

				MessageForGraph msg = messageque.take();
				// System.out.println("hi");
				System.out.println("MSG: "+msg);

				switch (msg.eft) {
				case createATNode:
					ForestMinistry.createATNode(msg.getVarat(0));
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
					GraphBorder base_graphborder = ForestMinistry.constructForest();
					
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
