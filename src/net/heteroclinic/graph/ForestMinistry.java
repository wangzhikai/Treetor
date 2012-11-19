package net.heteroclinic.graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.BasicStroke; 
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
//import javax.swing.*;

import javax.imageio.ImageIO;
//import java.awt.geom.*;
public class ForestMinistry {
	static public final Color VITUALEDGECOLOR = new Color(111,111,111);
	static public final Stroke VITUALEDGESTROKE = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
	//static protected Map<Long, VirtualEdge> virtualedges = new HashMap<Long, VirtualEdge>();
	static protected LinkedHashSet<VirtualEdge> virtualedges = new LinkedHashSet<VirtualEdge>();
	static protected Map<Long, Node> hangingnodes = new HashMap<Long, Node>();
	public static void addAVirtaulEdge (long fromid, long toid ) {
		Node nfrom = Node.allnodes.get(fromid);
		Node nto = Node.allnodes.get(toid);
		VirtualEdge ve = new VirtualEdge (nfrom, nto, nto.getId());
		//System.out.println("  "+ ve.getId()+ "\t" +ve);
		//virtualedges.put(ve.getId(), ve);
		virtualedges.add(ve);
		
	}
	public static void removeAVirtualEdge (long fromid, long toid) {
		Node nfrom = Node.allnodes.get(fromid);
		Node nto = Node.allnodes.get(toid);
		VirtualEdge ve = new VirtualEdge (nfrom, nto, nto.getId());
		virtualedges.remove(ve);
	}
	
	
	
	public static void drawAVirtualEdge (Graphics graphics,VirtualEdge ve,
			GraphBorder base_graphborder,
			GraphOrientation go) {
		Point3D pt1 = ve.getPoint1().getPosition();
		Point3D pt2 = ve.getPoint2().getPosition();
		Graphics2D g2d = (Graphics2D) graphics;
		Stroke oldstroke = g2d.getStroke();
		
		Point3D renderoffset1  = ve.getPoint1().getRenderoffset();
		Point3D renderoffset2  = ve.getPoint2().getRenderoffset();
		
		Point3D pt3 = new Point3D (pt1.getX()+renderoffset1.getX() + Bag.nodesize/Bag.edgetrim,
				pt1.getY()+renderoffset1.getY(),
				pt1.getZ()+renderoffset1.getZ()
				); 
		Point3D pt4 = new Point3D (pt2.getX()+renderoffset2.getX() - Bag.nodesize/Bag.edgetrim,
				pt2.getY()+renderoffset2.getY(),
				pt2.getZ()+renderoffset2.getZ()
				); 		
		
		
		g2d.setStroke(VITUALEDGESTROKE);
		
//		PixelPoint2D p2d = mapAPointTotheForestPixelWorld(base_graphborder, pt,go);
		//PixelPoint2D p2d1 = mapAPointTothePixelWorld(base_graphborder, pt3,go);
		PixelPoint2D p2d1 = Node.mapAPointTotheForestPixelWorld(base_graphborder, pt3,go);
		//PixelPoint2D p2d2 = mapAPointTothePixelWorld(base_graphborder, pt4,go);
		PixelPoint2D p2d2 = Node.mapAPointTotheForestPixelWorld(base_graphborder, pt4,go);
		g2d.drawLine(p2d1.getX(), p2d1.getY(),p2d2.getX(), p2d2.getY());
		
		g2d.setStroke(oldstroke);
		//graphics.drawLine(p2d1.getX(), p2d1.getY(),p2d2.getX(), p2d2.getY());
	}
	//TODO oct 27, 2012
	//! LinkedHashMap of supernodes static
	//! setNodeSubnodePair
	// ForestConstruction
	// Node.renderAsMemberoftheForest
	// ForestRender
	// Test case I
	// Decimate a node
	// Remove an edge
	// Test case II

	static protected Map<Long, Node> trees_under_direct_administration = new LinkedHashMap<Long, Node>();
//	TNODE,
//	RNODE,
//	DEFAULT
	static public Node createANode (Nodetype nt) {
		Node r = null;
		switch (nt) {
		case TNODE:
			r = new TNode();
			break;
		case RNODE:
			r = new RNode();
			break;
		default:
			r = new Node();
			;
		}
		trees_under_direct_administration.put(r.getId(), r);
		return r;
	}
	static public Node createANode (Nodetype nt,long pid) {
		Node r = null;
		switch (nt) {
		case TNODE:
			r = new TNode();
			break;
		case RNODE:
			r = new RNode();
			break;
		default:
			r = new Node();
			;
		}
		//trees_under_direct_administration.put(r.getId(), r);
		if (pid <= 0l) {
			hangingnodes.put(r.getId(), r);
		} else {
			setNodeSubnodePair(pid, r.getId());
			
		}
		return r;
	}
	static public Node createANode () {
		return createANode (Nodetype.DEFAULT);
	}
//	static public int removeNodeSubnodePair(long parentnodeid,
//			long childnodeid) {
//		int r = Node.removeNodeLeafSubnodePair(parentnodeid, childnodeid);
//		
//		return r;
//		
//	}
	
	static public int setNodeSubnodePair(long parentnodeid,
			long childnodeid) {
//		return Node.addNodeSubnodePair( parentnodeid,
//				childnodeid);
		//System.out.println("["+parentnodeid+","+childnodeid+"]" );
		//System.out.println("Node.addNodeSubnodePair("+ parentnodeid+","+childnodeid+");");
		Node parent = Node.allnodes.get(parentnodeid);
		if (parent == null)
			return -1;
		Node child = Node.allnodes.get(childnodeid);
		if (child.getParentnode() != null)
			return -4;
		parent.addAChild(child);
		child.setParentnode(parent);
		//trees_under_direct_administration.remove(child);
		trees_under_direct_administration.remove(child.getId());
		if (hangingnodes.containsKey(childnodeid))
			hangingnodes.remove(childnodeid);
		return 1;
	}
	static public void  zero ( ) {
		//Iterator<Long> it = trees_under_direct_administration.keySet().iterator();
		Node.zero();
		virtualedges.clear();
		hangingnodes.clear();
//		while (it.hasNext()) {
//			Long l = it.next();
//			Node r = trees_under_direct_administration.get(l);
//			//r.BFSGraphConstruction();
//			.zero();
//		}
	}
	static public GraphBorder constructForest(GraphBorder gb) {
		//GraphBorder gb = new GraphBorder(-1l);
		Iterator<Long> it = trees_under_direct_administration.keySet().iterator();
		while (it.hasNext()) {
			Long l = it.next();
			Node r = trees_under_direct_administration.get(l);
			r.BFSGraphConstruction();
		}
		it = trees_under_direct_administration.keySet().iterator();
		double ttl_w = 0.0d;
		double ttl_h = 0.0d;
		while (it.hasNext()) {
			Long l = it.next();
			Node r = trees_under_direct_administration.get(l);
//			ttl_w += r.getGraphborder().getWidth();
//			ttl_h = (ttl_h > r.getGraphborder().getHeight()) ? ttl_h:r.getGraphborder().getHeight();
			ttl_w  = (ttl_w > r.getGraphborder().getWidth()) ? ttl_w:r.getGraphborder().getWidth();
			ttl_h += r.getGraphborder().getHeight();
			if (it .hasNext()) {
				ttl_h += Bag.treeinteveralinforest;
			}
		}
		//gb.setWidth(ttl_w);
		//gb.setHeight(ttl_h);
		
		it = trees_under_direct_administration.keySet().iterator();
		
		//double h_remain =ttl_h;
		double h_remain =gb.getHeight();
		h_remain -= Bag.boderspace;
		while (it.hasNext()) {
			Long l = it.next();
			Node r = trees_under_direct_administration.get(l);
			double offsetx =r.getPosition().getX(); 
//					-1.0d * ttl_w /2.0d 
//					+ w_used + Math.abs(r.getGraphborder().getXmin());
			double offsety = r.getPosition().getY()
				 + h_remain  - Math.abs(r.getGraphborder().getYmax());
			if (it .hasNext()) {
				offsety += Bag.treeinteveralinforest;
			}
			double offsetz = r.getPosition().getZ();
			r.setRenderoffset(offsetx, offsety, offsetz);
			Map<Long, Node> nodestorender = r.getNodestorender();
			
			Iterator<Long> it2 = nodestorender.keySet().iterator();
			while (it2.hasNext()) {
				Long l2 = it2.next();
				Node r2 = nodestorender.get(l2);
				r2.setRenderoffset(offsetx, offsety, offsetz);
			}
			//System.out.println(r.getId() +" id : w h " + r.getGraphborder().getWidth() + " "+r.getGraphborder().getHeight());
			//System.out.println("h_remain "+h_remain);
			h_remain-= r.getGraphborder().getHeight();
			if (it .hasNext()) {
				h_remain -= Bag.treeinteveralinforest;
			}
		}
		return gb;
	}
	static public GraphBorder constructForest() {
		GraphBorder gb = new GraphBorder(-1l);
		Iterator<Long> it = trees_under_direct_administration.keySet().iterator();
		while (it.hasNext()) {
			Long l = it.next();
			Node r = trees_under_direct_administration.get(l);
			r.BFSGraphConstruction();
		}
		it = trees_under_direct_administration.keySet().iterator();
		double ttl_w = 0.0d;
		//double ttl_h = 0.0d;
		double ttl_h = Bag.boderspace;
		while (it.hasNext()) {
			Long l = it.next();
			Node r = trees_under_direct_administration.get(l);
//			ttl_w += r.getGraphborder().getWidth();
//			ttl_h = (ttl_h > r.getGraphborder().getHeight()) ? ttl_h:r.getGraphborder().getHeight();
			ttl_w  = (ttl_w > r.getGraphborder().getWidth()) ? ttl_w:r.getGraphborder().getWidth();
			ttl_h += r.getGraphborder().getHeight();
			if (it .hasNext()) {
				ttl_h += Bag.treeinteveralinforest;
			}
		}
		ttl_w += Bag.boderspace;
		gb.setWidth(ttl_w);
		ttl_h += Bag.boderspace;
		gb.setHeight(ttl_h);
		
		it = trees_under_direct_administration.keySet().iterator();
		
		double h_remain =ttl_h;
		h_remain -= Bag.boderspace;
		while (it.hasNext()) {
			Long l = it.next();
			Node r = trees_under_direct_administration.get(l);
			double offsetx =r.getPosition().getX(); 
//					-1.0d * ttl_w /2.0d 
//					+ w_used + Math.abs(r.getGraphborder().getXmin());
			double offsety = r.getPosition().getY()
				 + h_remain  - Math.abs(r.getGraphborder().getYmax());
			if (it .hasNext()) {
				offsety += Bag.treeinteveralinforest;
			}
			double offsetz = r.getPosition().getZ();
			//r.setRenderoffset(offsetx, offsety, offsetz);
			Map<Long, Node> nodestorender = r.getNodestorender();
			
			Iterator<Long> it2 = nodestorender.keySet().iterator();
			while (it2.hasNext()) {
				Long l2 = it2.next();
				Node r2 = nodestorender.get(l2);
				r2.setRenderoffset(offsetx, offsety, offsetz);
			}
			System.out.println(r.getId() +" id : w h " + r.getGraphborder().getWidth() + " "
			+r.getGraphborder().getHeight());
			System.out.println("h_remain "+h_remain);
			h_remain-= r.getGraphborder().getHeight();
			if (it .hasNext()) {
				h_remain -= Bag.treeinteveralinforest;
			}
		}
		return gb;
	}
	static public void forest2drender ( String fn,
			GraphBorder base_graphborder,
			GraphOrientation go) {
		
		//updateGraphborder ();
		
		//int width=512;
		//int height=512;
		base_graphborder.print();
		int img_w = Node.calculateImageWidth (base_graphborder, go);
		int img_h = Node.calculateImageHeight (base_graphborder, go);
		System.out.println("img_w:"+img_w+"\t"+ "img_h:"+img_h);

		//String mcap = "testcapcha";
		//Color background = new Color(204,204,204);
		Color fillgraph = new Color(255,255,255);

		Color paintcolor = new Color(0,0,0);

		//Font fnt=new Font("SansSerif",1,14);
		Font font=new Font("Monospaced",1,Bag.fontsize);
		

		BufferedImage img =new BufferedImage(img_w,img_h,BufferedImage.TYPE_INT_RGB);
		//BufferedImage img =new BufferedImage(img_w,img_h,BufferedImage.T);

		Graphics graphics = img.createGraphics();

		graphics.setColor(fillgraph);

		graphics.fillRect(0,0,img_w,img_h);

		graphics.setColor(paintcolor);
		graphics.setFont(font);
		
		
		Iterator<Long> it = trees_under_direct_administration.keySet().iterator();
		while (it.hasNext()) {
			Node tn = trees_under_direct_administration.get(it.next());
			//tn.node2drender( base_graphborder,graphics, go);
			tn.renderAsMemberoftheForest(base_graphborder, graphics, go);
		}
		
		//RENDER VIRTUAL EDGES 
		
		//Iterator<Long> vit = virtualedges.keySet().iterator();
		Iterator<VirtualEdge> vit = virtualedges.iterator();
		while (vit.hasNext()) {
//			ForestMinistry.drawAVirtualEdge(graphics,
//					virtualedges.get(vit.next()), base_graphborder, go);
			ForestMinistry.drawAVirtualEdge(graphics,
					vit.next(), base_graphborder, go);
		}
		
		
		File f = new File(fn);
		OutputStream os;
		try {
			os = new FileOutputStream(f);
			//ImageIO.write(img,"jpeg",os);
			ImageIO.write(img,"png",os);
			//ImageIO.w
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static public void print () {
		Iterator<Long> it = trees_under_direct_administration.keySet().iterator();
		while (it.hasNext()) {
			Node tn = trees_under_direct_administration.get(it.next());
			System.out.println();
			tn.BFSprint();
		}
		
	}
	//optor = 1, child starting a new tree, 0 remove, else hanging 
	public static int removeNodeSubnodePair(long parentnodeid, long childnodeid, int optor) {
		// TODO Auto-generated method stub
		//int r = Node.removeNodeLeafSubnodePair(parentnodeid, childnodeid);
		//int r = Node.removeNodeLeafSubnodePair(l, m,b);
		Node parent = Node.allnodes.get(parentnodeid);
		if (parent == null)
			return -1;
		Node child = Node.allnodes.get(childnodeid);
		if (child.getParentnode() == null)
			return -4;
////		//parent.addAChild(child);
////		//child.setParentnode(parent);
//		if (child.getSubnodes().size()  > 0) {
//			//return -3; // you can not remove non-leaf node with this function
//			hangingnodes.remove(child.getId());
//		
//		} else {
//			if (! keepchild)
//				Node.allnodes.remove(child.getId());
//		}
		parent.getSubnodes().remove(child.getId());
		child.setParentnode(null);
		if (optor == 1) {
			trees_under_direct_administration.put(child.getId(),child);
			hangingnodes.remove(child.getId());
		} else if (optor == 0) {
			Node.allnodes.remove(child.getId());
			hangingnodes.remove(child.getId());
		} else {
			hangingnodes.put(child.getId(),child);
		}
		//Node.allnodes.remove(child);
		
		
		return 1;
		

		
	}
}
