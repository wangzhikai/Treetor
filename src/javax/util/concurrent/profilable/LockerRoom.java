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

import javax.util.concurrent.profilable.locks.ProfilableReentrantLock;

public class LockerRoom {
	//LockerRoom.samplelocks
	public static ProfilableReentrantLock[] samplelocks = new ProfilableReentrantLock[10];
	static {
		for (int i = 0; i < samplelocks.length; i++) {
			samplelocks[i] = new ProfilableReentrantLock();
		}
	}
}
