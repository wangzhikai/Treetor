/**
 * @author Zhikai
 * We no longer use node to represent a tree to avoid using the static structure to store all nodes.
 */
package net.heteroclinic.treetor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;

import net.heteroclinic.graph.Bag;
import net.heteroclinic.graph.Edge;
import net.heteroclinic.graph.Geotype;
import net.heteroclinic.graph.GraphBorder;
import net.heteroclinic.graph.Tier;


public class Tree<T> {
	//protected Node<T> root;
	protected long rootId = -1;
	protected Map<Long, Node<T>> allSubNodes = new LinkedHashMap<Long, Node<T>>();
	protected AtomicLong counter = new AtomicLong(0L);
	public static final long addNodeFailure = -1l;
	
	public Tree() {
		super();
	}	
	
	/**
	 * Not thread safe
	 * @param rootValue
	 * @return
	 */	
	public Long createAndAddRoot( T rootValue) {
		if (rootValue == null)
			return addNodeFailure;
		long id = counter.incrementAndGet();
		rootId = id;
		Node<T> n = new Node<T>(rootValue,id);
		allSubNodes.put(id,  n);
		return id;
	}
	//TODO add node
	/**
	 * The first parameter is the parent id, the id must exist.
	 * The second parameter is the child node's value, it should not exist.
	 * The following conditions must be full-filled to create a new root:
	 * - the first parameter is not a positive integer/long value
	 * - the tree has zero nodes
	 * There is no check needed if the value already exist. We build a render tree
	 * but not search tree or by any means ordered tree.
	 * Return the new node id, if the return id is less or equal to zero, the
	 * addition of new node is a failure.
	 */
	public Long addANewNode (final Long parentNodeId, T newNodeValue ) {
		if (parentNodeId == null || parentNodeId<0l || 
				!allSubNodes.keySet().contains(parentNodeId) ) 
		{
			return -1l;
		}
		long id = counter.incrementAndGet();
		Node<T> p = allSubNodes.get(parentNodeId);
		Node<T> n = new Node<T>(newNodeValue,id);
		allSubNodes.put(id,  n);
		n.setParentnode(p);
		p.addSubNode(id, n);
		return id;
	}
	
	//TODO create a tree manually for test
	public static void main(String [] args) {
		Tree<Integer> t1 = new Tree<Integer>();
		long rid = t1.createAndAddRoot(5);
		
		long n3 = t1.addANewNode(rid,3);
		
		t1.addANewNode(n3,1 );
		t1.addANewNode(rid,2);
		
		long n7 = t1.addANewNode(rid,7);
		t1.addANewNode(n7,6 );
		long n15 = t1.addANewNode(n7,15 );	
		
		t1.addANewNode(n15,8);
		t1.addANewNode(n15,9);
		t1.addANewNode(n15,10);
		t1.addANewNode(n15,19);
		t1.printBFS();
		/*
5 
3 2 7 
1 6 15 
8 9 10 19 

		 */
	}
	
	//BFS print
	public void printBFS () {
		Map<Long, Node<T>> currentLayer = new LinkedHashMap<Long, Node<T>>();
		//List<T> currentLayer = new ArrayList<T>();
		currentLayer.put(rootId, allSubNodes.get(rootId));
		while (currentLayer.size()>0) {
			Map<Long, Node<T>>  tmpLayer = new LinkedHashMap<Long, Node<T>>();
			for (Long k : currentLayer.keySet()) {
				Node<T> n = currentLayer.get(k);
				System.out.print(n.getValue() + " ");
				if (n.getSubnodes().size()>0) {
					for (Long ks: n.getSubnodes().keySet()) {
						tmpLayer.put(ks, n.getSubnodes().get(ks));
					}
				}
			}
			System.out.println();
			currentLayer = tmpLayer;
			
			
		}
	}
	protected Bag bag ;
	
	public Bag getBag() {
		return bag;
	}

	public void setBag(Bag bag) {
		this.bag = bag;
	}
	protected Map<Long, Node<T>> nodestorender = new HashMap<Long, Node<T>>();
	public Map<Long, Node<T>> getNodestorender() {
		return nodestorender;
	}
	protected Map<Long, Edge<T>> edgestorender = new HashMap<Long, Edge<T>>();
	
	protected Map <Long, Tier > tierstorage = new TreeMap<Long, Tier >();	
	
	static public int BFSConstructgetEquatorIndex (int southernnodescount, int northernnodescount, int ttl) {
		int result = -1;
		if  (( southernnodescount+ northernnodescount) != ttl)  {
			result = ttl - southernnodescount - 1;
		}
		return result;
	}

	
	public void BFSGraphConstruction () {
		nodestorender.clear();
		edgestorender.clear();
		
		Map<Long, Node<T>> stack = new LinkedHashMap<Long, Node<T>> ();
		Node<T> n = this.getRootNode();
		n.setPosition(0.0d, 0.0d, 0.0d);
		n.setGeotype(Geotype.Equator);
		
		nodestorender.put(n.getId(), n);
		long level = 0l;
		Tier tier = new Tier(level);
		tier.setBound(-1.0d * bag.nodespace/2.0d, 1.0d * bag.nodespace/2.0d, -1.0d * bag.nodespace/2.0d, 
				1.0d * bag.nodespace/2.0d, 0.0d, 0.0d );
		tierstorage.put(tier.getLevel(), tier);
		stack.put(n.getId(),n);
		Map<Long, Node<T>> tmpstack = new LinkedHashMap<Long, Node<T>> ();
		Map<Long, Node<T>> stackref = null;
		int southernnodescount = 0;
		int northernnodescount = 0;
		List <Node<T>> tmplist = new LinkedList <Node<T> >();
		while (stack.size() > 0 ) {
			level = level + 1;
			tmpstack.clear();
			// first pass count southern count, northern nodes count, decide equator index.
			Iterator<Long> it = stack.keySet().iterator();
			southernnodescount = 0;
			northernnodescount = 0;
			tmplist = new LinkedList <Node<T> >();
			//int i = 0 ;
			while (it.hasNext()) {
				Node<T> tn = stack.get(it.next());
				tn.assignSubnodesGeotype();
				Map<Long, Node<T>> subnodes = tn.getSubnodes();
				Iterator<Long> it2 = subnodes.keySet().iterator();
				while (it2.hasNext()) {
					Node<T> tmpn = subnodes.get(it2.next());
					if (tmpn.getGeotype() == Geotype.Southern) {
						southernnodescount++;
					}
					if (tmpn.getGeotype() == Geotype.Northern) {
						northernnodescount++;
					}

					Edge<T> e = new Edge<T>(tn, tmpn, tmpn.getId());
					tn.addALocalEdge(e);
					
					//tmpn.setPosition(level *1.0d * bag.tierdistance, i * bag.nodespace,0.0d);
					nodestorender.put(tmpn.getId(),tmpn);
					edgestorender.put(e.getId(), e);
					tmplist.add(tmpn);
					//i++;
				}
				tmpstack.putAll(subnodes);
			}
			
			//SECOND PASS INPUT: southernnodescount northernnodescount tmplist
			tier = new Tier(level);
			tier.setBound(0.0d ,level *1.0d * bag.tierdistance , 0.0d , 0.0d , 0.0d, 0.0d );
			tierstorage.put(tier.getLevel(), tier);
			if (tmplist.size() > 0) {
				int equatorindex = BFSConstructgetEquatorIndex (southernnodescount, northernnodescount,tmplist.size());
				if (-1 != equatorindex ) {// there is equator at this level
					// set equator node pos
					Node<T> startingnode = tmplist.get(equatorindex);
					startingnode.setPosition(level *1.0d * bag.tierdistance, 0.0d,0.0d);
					
					
					long startfamilyid =startingnode.getFamilyId();
					long lastfamilyid = startfamilyid;
					// grow south branch
					{
						//for (int cursor = equatorindex - 1; cursor>= 0; cursor--) {
						for (int cursor = equatorindex + 1; cursor<tmplist.size(); cursor++) {
							Node<T> currentnode = tmplist.get(cursor);
							long currentfamilyid = currentnode.getFamilyId();
							boolean familychanged = false;
							if (currentfamilyid != lastfamilyid) {
								// process 
								familychanged = true;
								lastfamilyid =currentfamilyid ;
							} 
							double proposed_y1 =  currentnode.getParentnode().getPosition().getY();
							double proposed_y2 =  tier.getYmax();
							if (familychanged) {
								proposed_y2 = proposed_y2+  bag.familydistance;
							} else {
								proposed_y2 = proposed_y2+  bag.nodespace;
							}
							currentnode.setPosition(level *1.0d * bag.tierdistance, (proposed_y1>proposed_y2)?proposed_y1:proposed_y2, 0.0d);
							tier.setYmax(currentnode.getPosition().getY());
						}
					}
					lastfamilyid = startfamilyid;
					// grow north branch
					{
						//for (int cursor = equatorindex + 1; cursor<tmplist.size(); cursor++) {
						for (int cursor = equatorindex - 1; cursor>= 0; cursor--) {
							Node<T> currentnode = tmplist.get(cursor);
							long currentfamilyid = currentnode.getFamilyId();
							boolean familychanged = false;
							if (currentfamilyid != lastfamilyid) {
								// process 
								familychanged = true;
								lastfamilyid =currentfamilyid ;
							} 
							double proposed_y1 =  currentnode.getParentnode().getPosition().getY();
							double proposed_y2 =  tier.getYmin();
							if (familychanged) {
								proposed_y2 = proposed_y2- bag.familydistance;
							} else {
								proposed_y2 = proposed_y2- bag.nodespace;
							}
							currentnode.setPosition(level *1.0d * bag.tierdistance, (proposed_y1<proposed_y2)?proposed_y1:proposed_y2, 0.0d);
							tier.setYmin(currentnode.getPosition().getY());
						}
					}
				} else {// No equator at this level

					
					//BEG original
					int northstartindex = -2;
					int southstartindex =  - 1;
					if (northernnodescount > 0 ) {
						northstartindex = northernnodescount -1;
						long lastfamilyid = -1;
						for (int cursor = northstartindex; cursor>=0 ; cursor--) {
							Node<T> currentnode = tmplist.get(cursor);
							
							long currentfamilyid = currentnode.getFamilyId();
							boolean familychanged = false;
							if (currentfamilyid != lastfamilyid) {
								// process 
								familychanged = true;
								//lastfamilyid =currentfamilyid ;
							} 
							double proposed_y1 =  currentnode.getParentnode().getPosition().getY();
							double proposed_y2 =  tier.getYmin();
							if (familychanged) {
								if ( cursor != northstartindex)
									proposed_y2 = proposed_y2 - bag.familydistance;
								else  {
									if ( southernnodescount  > 0 ) {
										southstartindex = northernnodescount;
										//northstartindex
										long southstartfamilyid = tmplist.get(southstartindex).getFamilyId();
										long northstartfamilyid = tmplist.get(northstartindex).getFamilyId();
										if (southstartfamilyid == northstartfamilyid ) {
											proposed_y2 = proposed_y2 - bag.nodespace/2.0d;
										} else {
											proposed_y2 = proposed_y2 -  bag.familydistance/2.0d;
										}
									} else {
										proposed_y2 = proposed_y2 - bag.nodespace/2.0d;
									}
								}
							} else {
								proposed_y2 = proposed_y2 - bag.nodespace;
							}
							lastfamilyid =currentfamilyid ;
							currentnode.setPosition(level *1.0d * bag.tierdistance, (proposed_y1<proposed_y2)?proposed_y1:proposed_y2, 0.0d);
							tier.setYmin(currentnode.getPosition().getY());
						}
					}
					// grow south branch
					if (southernnodescount > 0 ) {
						long lastfamilyid = -1;
						
						if ( northernnodescount > 0 ) {
							southstartindex = northernnodescount;
						} else 
							southstartindex = 0;
						//System.out.println("southstartindex :" + southstartindex+" " +tmplist.get(southstartindex));
						for (int cursor = southstartindex; cursor< tmplist.size(); cursor++) {
							Node<T> currentnode = tmplist.get(cursor);
							long currentfamilyid = currentnode.getFamilyId();
							boolean familychanged = false;
							if (currentfamilyid != lastfamilyid) {
								// process 
								familychanged = true;
								
							} 
							double proposed_y1 =  currentnode.getParentnode().getPosition().getY();
							double proposed_y2 =  tier.getYmax();
							if (familychanged) {
								// proposed_y2 = proposed_y2+
								// bag.familydistance;
								if (cursor != southstartindex)
									proposed_y2 = proposed_y2
											+ bag.familydistance;
								// else
								// proposed_y2 = proposed_y2 +
								// bag.familydistance/2.0d;
								else {
									if (northernnodescount > 0) {
										northstartindex = northernnodescount - 1;
										// northstartindex
										long southstartfamilyid = tmplist.get(
												southstartindex).getFamilyId();
										long northstartfamilyid = tmplist.get(
												northstartindex).getFamilyId();
										if (southstartfamilyid == northstartfamilyid) {
											proposed_y2 = proposed_y2
													+ bag.nodespace / 2.0d;
										} else {
											proposed_y2 = proposed_y2
													+ bag.familydistance / 2.0d;
										}
									} else {
										proposed_y2 = proposed_y2
												+ bag.nodespace / 2.0d;
									}
								}
							} else {
								proposed_y2 = proposed_y2 + bag.nodespace;
							}
							
							lastfamilyid =currentfamilyid ;
							currentnode.setPosition(level *1.0d * bag.tierdistance, (proposed_y1>proposed_y2)?proposed_y1:proposed_y2, 0.0d);
							tier.setYmax(currentnode.getPosition().getY());
						}
					}
				}//if (-1 == equatorindex )
			}//if (tmplist.size() > 0)
			stackref = tmpstack;
			tmpstack = stack;
			stack = stackref;			
		}
		updateGraphborder ();
	}

	
	protected GraphBorder graphborder = new GraphBorder(-1);
	public GraphBorder getGraphborder() {
		return graphborder;
	}
	public void updateGraphborder () {
		Iterator<Long> it  = tierstorage.keySet().iterator();
		Tier tier = null; 
		while (it.hasNext()) {
			Long k = it.next();
			tier = tierstorage.get(k);
			graphborder.setXmin(tier.getXmin()<graphborder.getXmin()?tier.getXmin(): graphborder.getXmin());
			graphborder.setYmin(tier.getYmin()<graphborder.getYmin()?tier.getYmin(): graphborder.getYmin());
			graphborder.setZmin(tier.getZmin()<graphborder.getZmin()?tier.getZmin(): graphborder.getZmin());
			graphborder.setXmax(tier.getXmax()>graphborder.getXmax()?tier.getXmax(): graphborder.getXmax());
			graphborder.setYmax(tier.getYmax()>graphborder.getYmax()?tier.getYmax(): graphborder.getYmax());
			graphborder.setZmax(tier.getZmax()>graphborder.getZmax()?tier.getZmax(): graphborder.getZmax());
		}
		graphborder.setWidth(graphborder.getXmax() - graphborder.getXmin());
		graphborder.setHeight(graphborder.getYmax() - graphborder.getYmin());
	}	
	public Node<T> getRootNode() {
		if (this.allSubNodes.size()>0) {
			return allSubNodes.get(1l);
		} else {
			return null;
		}
	}

	//TODO Render
	//TODO stress test 

}
