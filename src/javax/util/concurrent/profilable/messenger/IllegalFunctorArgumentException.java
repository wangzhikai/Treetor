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

public class IllegalFunctorArgumentException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8562784624543257212L;
    protected String str = "Reason not given";
    public IllegalFunctorArgumentException(String str) {
        this.str = str;
    }
    public String toString () {
        return str;
    }

}
