package javax.util.concurrent.profilable;

import java.util.concurrent.ConcurrentHashMap;

public class ThreadIdProfilableIdMap extends ConcurrentHashMap<Long, Long> {
	//Thread.CurrentThread.id, profilableId
	protected static ThreadIdProfilableIdMap tables = new ThreadIdProfilableIdMap();
	protected ThreadIdProfilableIdMap() {
	}
	public static boolean putAnEntry (long tid, long profid) {
		if (tables.contains(tid))
			return false;
		else {
			return (tables.put(tid, profid) != null);
		}
	}
	public static long getProfilableIdByThreadId (long threadid) {
		if (tables.containsKey(threadid))
			return tables.get(threadid);
		else
			return -1l;
	}
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5722173453512709290L;

}
