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
