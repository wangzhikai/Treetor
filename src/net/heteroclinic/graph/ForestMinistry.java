package net.heteroclinic.graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;

public class ForestMinistry {
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
	static public Node createANode () {
		return createANode (Nodetype.DEFAULT);
	}
	
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
		return 1;
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
		gb.setWidth(ttl_w);
		gb.setHeight(ttl_h);
		
		it = trees_under_direct_administration.keySet().iterator();
		
		double h_remain =ttl_h;
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
}
