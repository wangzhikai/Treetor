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

class IllegalFunctorArgumentException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 6163942080839435224L;
    protected String str = "Reason not given";
    public IllegalFunctorArgumentException(String str) {
        this.str = str;
    }
    public String toString () {
        return str;
    }
}

enum FunctorArglengthReq {
    ARGTWO(2),
    ARGONE(1);
    private int lenreq = 0;
    public int getLenreq() {
        return lenreq;
    }
    FunctorArglengthReq (int lenreq) {
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

class FunctorAdapter {
    public static int divert (FunctorArglengthReq lenreq, long ... vargs) throws IllegalFunctorArgumentException {
        int result = -1;
        switch (lenreq) {
        case ARGTWO:
            FunctorArglengthReq.ARGTWO.verifyArglength(vargs.length) ;
            System.out.println( "OK");
            
            break;
        case ARGONE:
            FunctorArglengthReq.ARGONE.verifyArglength(vargs.length) ;
            System.out.println( "OK");
            break;
        }
        
        return result;
    }
}

public class Test20121128 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            FunctorAdapter.divert(FunctorArglengthReq.ARGONE, 1);
        } catch (IllegalFunctorArgumentException e) {
            System.out.println(e);
            //e.printStackTrace();
        }
        try {
            FunctorAdapter.divert(FunctorArglengthReq.ARGONE, 1,2);
        } catch (IllegalFunctorArgumentException e) {
            System.out.println(e);
            //e.printStackTrace();
        }
        try {
            FunctorAdapter.divert(FunctorArglengthReq.ARGONE, 1,2,3);
        } catch (IllegalFunctorArgumentException e) {
            System.out.println(e);
            //e.printStackTrace();
        }
        try {
            FunctorAdapter.divert(FunctorArglengthReq.ARGTWO, 1);
        } catch (IllegalFunctorArgumentException e) {
            System.out.println(e);
            //e.printStackTrace();
        }
        try {
            FunctorAdapter.divert(FunctorArglengthReq.ARGTWO,1, 2,3);
        } catch (IllegalFunctorArgumentException e) {
            System.out.println(e);
            //e.printStackTrace();
        }
        try {
            FunctorAdapter.divert(FunctorArglengthReq.ARGTWO, 1,2);
        } catch (IllegalFunctorArgumentException e) {
            System.out.println(e);
            //e.printStackTrace();
        }

    }

}