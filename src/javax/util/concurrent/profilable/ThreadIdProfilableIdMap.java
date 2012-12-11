package javax.util.concurrent.profilable;

import java.util.concurrent.ConcurrentHashMap;

//This is a way to implement thread safe singleton of some sort.
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
	// TODO May it also needs to remove threadid. But the question, is threadid unique in running instance of JVM?
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -5722173453512709290L;

}
