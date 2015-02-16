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

public class MessageForGraph {
	protected long [] vargs;
	protected  FunctorType eft;
	public MessageForGraph( FunctorType eft, long ... vargs ) {
		//numbers = vargs.
		this.vargs = (long[]) vargs.clone();
		this.eft = eft;
		 
	}
	public String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append(eft.toString());
		int i = 0;
		for (; i< vargs.length; i++) {
			sb.append(" "+ vargs[i]);
		}
		return sb.toString();
	}
	public long getVarat (int index) {
		return vargs[index];
	}
}
