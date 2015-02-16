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


public enum FunctorType {
	removeNodeSubnodePairforProfilable(3),
	createATNodeforProfilable(2),
	createARNodeforProfilable(2),
	//createARNode (1),
	//createATNode(1),
	createADanglingRNode(1),
	setNodeSubnodePairforProfilable(2),
	//setNodeSubnodePair(2),
    ARGTHREE(3),
    ARGTWO(2),
    ARGONE(1);
    private int lenreq = 0;
    public int getLenreq() {
        return lenreq;
    }
    FunctorType (int lenreq) {
        this.lenreq = lenreq;
    }
    public boolean verifyArglength (int p) throws IllegalFunctorArgumentException {
        if (p == lenreq)
            return true;
        else
            throw new IllegalFunctorArgumentException(""+this+" needs " + lenreq +" arguments. Caller gives "+  p+".");
    }

    public String toString () {
    	return name() + ":"+lenreq;
    }
}
