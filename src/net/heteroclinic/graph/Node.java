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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.imageio.ImageIO;


public class Node   {
	protected Color fillcolor = new Color(111,111,111);
	public static GraphBorder graphborder = new GraphBorder(-1);

//	public GraphBorder getGraphborder() {
//		return graphborder;
//	}
//	public void setGraphborder(GraphBorder graphborder) {
//		this.graphborder = graphborder;
//	}
	public static void zero () {
		Node.allnodes.clear();
		Node.counter = new AtomicLong (0l);
		RNode.rnodecount = new AtomicLong (0l);
		TNode.tnodecount = new AtomicLong (0l);
	}
	public Color getFillcolor() {
		return fillcolor;
	}
	public void setFillcolor(Color fillcolor) {
		this.fillcolor = fillcolor;
	}
	public void setFillcolor(int r, int g , int b) {
		this.fillcolor = new Color(r,g,b);
		}
	public void node2drender ( Graphics graphics,GraphOrientation go) {
		Point3D pt = this.getPosition();
		PixelPoint2D p2d = mapAPointTothePixelWorld(pt,go);
		this.drawNodeShape(graphics, p2d);
		this.drawNodeLabel(graphics, p2d);
	}
	public void drawNodeShape(Graphics graphics,PixelPoint2D p2d) {
		int ovalsize = (int) (Bag.nodesize * ((double)Bag.OneDoubleequalpixels)) ;
		drawAlignedOval(graphics,p2d,ovalsize);
	}
	public void drawNodeLabel(Graphics graphics,PixelPoint2D p2d) {
		drawAlignedString (graphics,p2d,this.toString());
	}
	//, int width_pixelworld, int  height_pixelworld
	public static PixelPoint2D mapAPointTothePixelWorld (Point3D p3d, GraphOrientation go ) {
		double x = p3d.getX();
		double y = p3d.getY();
		PixelPoint2D p2d = null;
		if (go == GraphOrientation.LefttoRight) {// only map y
			//double ttllen = graphborder.getYmax() - graphborder.getYmin();
			double newy = y - graphborder.getYmin();
			int intx = (int)((x + Bag.boderspace)* (1.0d *Bag.OneDoubleequalpixels));
			int inty = (int)((newy + Bag.boderspace)* (1.0d *Bag.OneDoubleequalpixels));
			p2d = new PixelPoint2D(intx,inty);
		}
		if (go == GraphOrientation.RighttoLeft) {// only map y
			//double ttllen = graphborder.getYmax() - graphborder.getYmin();
			double newy = y - graphborder.getYmin();
			double newx = graphborder.getXmax() - x ;

			int intx = (int)((newx + Bag.boderspace)* (1.0d *Bag.OneDoubleequalpixels));
			
			int inty = (int)((newy + Bag.boderspace)* (1.0d *Bag.OneDoubleequalpixels));
			p2d = new PixelPoint2D(intx,inty);
		}
		if (go == GraphOrientation.ToptoBottom) {// only map y
			double newx = y - graphborder.getYmin();
			double newy = x;
			int intx = (int)((newx + Bag.boderspace)* (1.0d *Bag.OneDoubleequalpixels));
			int inty = (int)((newy + Bag.boderspace)* (1.0d *Bag.OneDoubleequalpixels));
			p2d = new PixelPoint2D(intx,inty);

		}
		if (go == GraphOrientation.BottomtoTop) {// only map y
			double newx = y - graphborder.getYmin();
			double newy = graphborder.getXmax() - x ;
			int intx = (int)((newx + Bag.boderspace)* (1.0d *Bag.OneDoubleequalpixels));
			int inty = (int)((newy + Bag.boderspace)* (1.0d *Bag.OneDoubleequalpixels));
			p2d = new PixelPoint2D(intx,inty);
		}
		return p2d;
	}
	public static void updateGraphborder () {
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
	
	protected Node alignsubnode = null;
	
	
	public static Map<Long, Node> nodestorender = new HashMap<Long, Node>();
	public static Map<Long, Edge> edgestorender = new HashMap<Long, Edge>();
	static protected Map <Long, Tier > tierstorage = new TreeMap<Long, Tier >();
	
	protected  Map <Long, Edge >   localedges = new HashMap<Long, Edge >();
	public void addALocalEdge ( Edge edge) {
		localedges.put(edge.getId(), edge);
	}

	protected Point3D position = new Point3D(0.0d,0.0d,0.0d);
	public Point3D getPosition() {
		return position;
	}
	public void setPosition(Point3D position) {
		//this.position = position;
		this.position.setX(position.getX());
		this.position.setY(position.getY());
		this.position.setZ(position.getZ());
	}
	public void setPosition( double x,double y,double z) {
		position.setX(x) ;
		position.setY(y);
		position.setZ(z);
	}
	
	protected Geotype geotype = Geotype.Equator; // Equator, Northern, Southern
	public Geotype getGeotype() {
		return geotype;
	}
	public void setGeotype(Geotype geotype) {
		this.geotype = geotype;
	}
//	public Geotype caculateGeotype () {
//		if (this.getParentnode() == null)
//			geotype = Geotype.Equator;
//		else {
//			switch (this.getParentnode().geotype) {
//			case Equator:
//				
//				break;
//			case Northern:
//				geotype = Geotype.Northern;
//				break;
//			case Southern:
//				geotype = Geotype.Southern;
//				break;
//			}
//		}
//		
//		
//		return geotype;
//	}
	public void assignSubnodesGeotype () {
		if (subnodes.size() <=0) {
			this.alignsubnode = null;
			return;
		}
		if (geotype ==  Geotype.Southern) {
			//assign all to 
			Iterator<Long> it = subnodes.keySet().iterator();
			List <Node> tmplist = new LinkedList <Node >();
			while (it.hasNext()) {
				Long k = it.next();
				Node tmpn = subnodes.get(k);
				tmpn.setGeotype(Geotype.Southern);
				tmplist.add(tmpn);
			}
			if (tmplist.size() >0) {
				this.alignsubnode = tmplist.get(tmplist.size()/2);
			}
		} else if (geotype ==  Geotype.Northern) {
			Iterator<Long> it = subnodes.keySet().iterator();
			List <Node> tmplist = new LinkedList <Node >();
			while (it.hasNext()) {
				Long k = it.next();
				Node tmpn = subnodes.get(k);
				tmpn.setGeotype(Geotype.Northern);
				tmplist.add(tmpn);
			}
			if (tmplist.size() >0) {
				this.alignsubnode = tmplist.get(tmplist.size()/2);
			}
		} else {
			int size = subnodes.size();
			if (size %2 == 0 ) {
				int split = size/2;
				Iterator<Long> it = subnodes.keySet().iterator();
				int itc = 0;
				while (it.hasNext()) {
					Long l = it.next();
					Node n = subnodes.get(l);
					if (itc < split) {
						n.setGeotype(Geotype.Northern);
					} else {
						n.setGeotype(Geotype.Southern);
					}
					itc++;
				}
			} else {
				int split = size/2;
				Iterator<Long> it = subnodes.keySet().iterator();
				int itc = 0;
				while (it.hasNext()) {
					Long l = it.next();
					Node n = subnodes.get(l);
					if (itc < split) {
						n.setGeotype(Geotype.Northern);
					} else if (itc == split) {
						
						n.setGeotype(Geotype.Equator);
					} else 
						n.setGeotype(Geotype.Southern);
					itc++;
				}
			}
//			Iterator<Long> it = subnodes.keySet().iterator();
//			List <Node> tmplist = new LinkedList <Node >();
//			while (it.hasNext()) {
//				Long k = it.next();
//				Node tmpn = subnodes.get(k);
//				//tmpn.setGeotype(Geotype.Northern);
//				tmplist.add(tmpn);
//			}
//			int equatornodeindex = tmplist.size()/2;
//			Iterator<Node> nit = tmplist.iterator();
//			int i = 0;
//			while (nit.hasNext()) {
//				Node tmpn = nit.next();
//				if (i > equatornodeindex) {
//					tmpn.setGeotype(Geotype.Southern);
//				} else if (i == equatornodeindex ) {
//					tmpn.setGeotype(Geotype.Equator);
//				} else {
//					tmpn.setGeotype(Geotype.Northern);
//				}
//				i++;
//			}
		}
	}

	
	
	// Below is Node, //above is RenderableNode
	public static Map<Long, Node> allnodes = new ConcurrentHashMap<Long, Node>();
	protected static AtomicLong counter = new AtomicLong(0L);
	protected Map<Long, Node> subnodes = new LinkedHashMap<Long, Node>();
	public Map<Long, Node> getSubnodes() {
		return subnodes;
	}

	protected long id = -1L;
	protected Node parentnode = null;
	
	
	public Node getParentnode() {
		return parentnode;
	}
	public long getFamilyId () {
		if (parentnode == null)
			return 0;
		else 
			return parentnode.getId();
	}


	public boolean addAChild (Node child ) {
		return subnodes.put(child.getId(), child) != null;
	}

	public void setParentnode(Node parentnode) {
		this.parentnode = parentnode;
	}


	protected String label;
	public String toString () {
		return label;
	}
	public Node() {
		id = counter.incrementAndGet();
		label = ""+id;
		allnodes.put(this.getId(), this);
	}
//	public Node(String label) 
//	{
//		this.label = label;
//		id = counter.incrementAndGet();
//		allnodes.put(this.getId(), this);
//	}



	public long getId() {
		return id;
	}



	// level starting from 0L to ...
	public long getLevel() {
		if (null == this.getParentnode())
			return 0L;
		else
			return this.getParentnode().getLevel() + 1L;

	}

	

	public void DFSprint(String header) {

		if (subnodes == null || subnodes.size() == 0) {
			String tmps = header + "n|" + this.getId() + ".";
			System.out.println(tmps);

		} else {

			Iterator<Long> it = subnodes.keySet().iterator();
			while (it.hasNext()) {
				Long key = it.next();
				Node tmpn = subnodes.get(key);
				tmpn.DFSprint(header + "n|" + this.getId() + "->\t");
			}
		}
	}
	static final double doubleconverttopixelratio = 20d;
	public int doubletopixel (double val) {
		return (int)(doubleconverttopixelratio*val);
	}
	
	public static int calculateImageWidth (GraphOrientation go ) {
		System.out.println( graphborder.getWidth() + "\t"+ graphborder.getHeight());
		if (go == GraphOrientation.LefttoRight || go == GraphOrientation.RighttoLeft)
			return (int )(Bag.OneDoubleequalpixels * graphborder.getWidth() + Bag.OneDoubleequalpixels * (Bag.boderspace)*2.0d );
		else 
			return (int )(Bag.OneDoubleequalpixels * graphborder.getHeight() + Bag.OneDoubleequalpixels * (Bag.boderspace)*2.0d );
	}
	public static int calculateImageHeight  (GraphOrientation go ) {
		if (go == GraphOrientation.LefttoRight || go == GraphOrientation.RighttoLeft)
			return (int )(Bag.OneDoubleequalpixels * graphborder.getHeight () +Bag.OneDoubleequalpixels *  (Bag.boderspace)*2.0d );
		else 
			return (int )( Bag.OneDoubleequalpixels * graphborder.getWidth () + Bag.OneDoubleequalpixels * (Bag.boderspace)*2.0d );
	}
	public static void drawAlignedOval (Graphics graphics, PixelPoint2D p2d, int ovalsize ) {
		graphics.drawOval(p2d.getX() - (ovalsize/2), p2d.getY()- (ovalsize/2) - (Bag.fontsize /3), ovalsize, ovalsize);
	}
	public static void drawAlignedString (Graphics graphics, PixelPoint2D p2d, String str ) {
		int len = str.length();
		int ttlwidth = len * Bag.fontwidth;
		graphics.drawString(str, p2d.getX() - (ttlwidth/2),p2d.getY());
		
	}
	public void image2dRenderWithoutUpdateBorder ( String fn, GraphOrientation go) {
		
		//updateGraphborder ();
		
		//int width=512;
		//int height=512;
		int img_w = calculateImageWidth (go);
		int img_h = calculateImageHeight (go);

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
		
		
		Iterator<Long> it = Node.nodestorender.keySet().iterator();
		while (it.hasNext()) {
			Node tn = Node.nodestorender.get(it.next());
			tn.node2drender( graphics, go);
			
//			Point3D pt = tn.getPosition();
//			
//			PixelPoint2D p2d = mapAPointTothePixelWorld(pt,go);
//			drawAlignedOval(graphics,p2d,ovalsize);
//			 drawAlignedString (graphics,p2d,tn.toString());
		}
		it = Node.edgestorender.keySet().iterator();
		while (it.hasNext()) {
			Edge te = Node.edgestorender.get(it.next());
			Point3D pt1 = te.getPoint1().getPosition();
			Point3D pt2 = te.getPoint2().getPosition();
			Point3D pt3 = new Point3D (pt1.getX() + Bag.nodesize/Bag.edgetrim , pt1.getY(), pt1.getZ());
			Point3D pt4 = new Point3D (pt2.getX() - Bag.nodesize/Bag.edgetrim, pt2.getY(), pt2.getZ());

			PixelPoint2D p2d1 = mapAPointTothePixelWorld(pt3,go);
			PixelPoint2D p2d2 = mapAPointTothePixelWorld(pt4,go);
			graphics.drawLine(p2d1.getX(), p2d1.getY(),p2d2.getX(), p2d2.getY());

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
	

	public void image2dRender ( String fn, GraphOrientation go) {
		
		updateGraphborder ();
		
		//int width=512;
		//int height=512;
		int img_w = calculateImageWidth (go);
		int img_h = calculateImageHeight (go);

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
		
		
		Iterator<Long> it = Node.nodestorender.keySet().iterator();
		while (it.hasNext()) {
			Node tn = Node.nodestorender.get(it.next());
			tn.node2drender( graphics, go);
			
//			Point3D pt = tn.getPosition();
//			
//			PixelPoint2D p2d = mapAPointTothePixelWorld(pt,go);
//			drawAlignedOval(graphics,p2d,ovalsize);
//			 drawAlignedString (graphics,p2d,tn.toString());
		}
		it = Node.edgestorender.keySet().iterator();
		while (it.hasNext()) {
			Edge te = Node.edgestorender.get(it.next());
			Point3D pt1 = te.getPoint1().getPosition();
			Point3D pt2 = te.getPoint2().getPosition();
			Point3D pt3 = new Point3D (pt1.getX() + Bag.nodesize/Bag.edgetrim , pt1.getY(), pt1.getZ());
			Point3D pt4 = new Point3D (pt2.getX() - Bag.nodesize/Bag.edgetrim, pt2.getY(), pt2.getZ());

			PixelPoint2D p2d1 = mapAPointTothePixelWorld(pt3,go);
			PixelPoint2D p2d2 = mapAPointTothePixelWorld(pt4,go);
			graphics.drawLine(p2d1.getX(), p2d1.getY(),p2d2.getX(), p2d2.getY());

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
	
	public void BFSGraphConstruction () {
		Node.nodestorender.clear();
		Node.edgestorender.clear();
		
		Map<Long, Node> stack = new LinkedHashMap<Long, Node> ();
		Node n = this;
		n.setPosition(0.0d, 0.0d, 0.0d);
		n.setGeotype(Geotype.Equator);
		
		nodestorender.put(n.getId(), n);
		long level = 0l;
		Tier tier = new Tier(level);
		tier.setBound(-1.0d * Bag.nodespace/2.0d, 1.0d * Bag.nodespace/2.0d, -1.0d * Bag.nodespace/2.0d, 
				1.0d * Bag.nodespace/2.0d, 0.0d, 0.0d );
		tierstorage.put(tier.getLevel(), tier);
		stack.put(this.getId(), this);
		Map<Long, Node> tmpstack = new LinkedHashMap<Long, Node> ();
		Map<Long, Node> stackref = null;
		int southernnodescount = 0;
		int northernnodescount = 0;
		List <Node> tmplist = new LinkedList <Node >();
		while (stack.size() > 0 ) {
			level = level + 1;
			tmpstack.clear();
			// first pass count sounthern count, northern nodes count, decide equator index.
			Iterator<Long> it = stack.keySet().iterator();
			southernnodescount = 0;
			northernnodescount = 0;
			tmplist = new LinkedList <Node >();
			@SuppressWarnings("unused")
			int i = 0 ;
			while (it.hasNext()) {
				Node tn = stack.get(it.next());
				tn.assignSubnodesGeotype();
				Map<Long, Node> subnodes = tn.getSubnodes();
				Iterator<Long> it2 = subnodes.keySet().iterator();
				while (it2.hasNext()) {
					Node tmpn = subnodes.get(it2.next());
					if (tmpn.getGeotype() == Geotype.Southern) {
						southernnodescount++;
					}
					if (tmpn.getGeotype() == Geotype.Northern) {
						northernnodescount++;
					}

					Edge e = new Edge(tn, tmpn, tmpn.getId());
					tn.addALocalEdge(e);
					
					//tmpn.setPosition(level *1.0d * Bag.tierdistance, i * Bag.nodespace,0.0d);
					Node.nodestorender.put(tmpn.getId(),tmpn);
					Node.edgestorender.put(e.getId(), e);
					tmplist.add(tmpn);
					i++;
				}
				tmpstack.putAll(subnodes);
			}
			
			//SECOND PASS INPUT: southernnodescount northernnodescount tmplist
			tier = new Tier(level);
			tier.setBound(0.0d ,level *1.0d * Bag.tierdistance , 0.0d , 0.0d , 0.0d, 0.0d );
			tierstorage.put(tier.getLevel(), tier);
			if (tmplist.size() > 0) {
				int equatorindex = BFSConstructgetEquatorIndex (southernnodescount, northernnodescount,tmplist.size());
				if (-1 != equatorindex ) {// there is equator at this level
					// set equator node pos
					Node startingnode = tmplist.get(equatorindex);
					startingnode.setPosition(level *1.0d * Bag.tierdistance, 0.0d,0.0d);
					
					
					long startfamilyid =startingnode.getFamilyId();
					long lastfamilyid = startfamilyid;
					// grow south branch
					{
						//for (int cursor = equatorindex - 1; cursor>= 0; cursor--) {
						for (int cursor = equatorindex + 1; cursor<tmplist.size(); cursor++) {
							Node currentnode = tmplist.get(cursor);
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
								proposed_y2 = proposed_y2+  Bag.familydistance;
							} else {
								proposed_y2 = proposed_y2+  Bag.nodespace;
							}
							currentnode.setPosition(level *1.0d * Bag.tierdistance, (proposed_y1>proposed_y2)?proposed_y1:proposed_y2, 0.0d);
							tier.setYmax(currentnode.getPosition().getY());
						}
					}
					lastfamilyid = startfamilyid;
					// grow north branch
					{
						//for (int cursor = equatorindex + 1; cursor<tmplist.size(); cursor++) {
						for (int cursor = equatorindex - 1; cursor>= 0; cursor--) {
							Node currentnode = tmplist.get(cursor);
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
								proposed_y2 = proposed_y2- Bag.familydistance;
							} else {
								proposed_y2 = proposed_y2- Bag.nodespace;
							}
							currentnode.setPosition(level *1.0d * Bag.tierdistance, (proposed_y1<proposed_y2)?proposed_y1:proposed_y2, 0.0d);
							tier.setYmin(currentnode.getPosition().getY());
						}
					}
				} else {// No equator at this level

//					// Case I north only
//					if (northernnodescount > 0  && southernnodescount <= 0 ){
//						int northstartindex = -2;
//						long lastfamilyid = -1;
//						for (int cursor = northstartindex; cursor>=0 ; cursor--) {
//							Node currentnode = tmplist.get(cursor);
//							
//							long currentfamilyid = currentnode.getFamilyId();
//							boolean familychanged = false;
//							if (currentfamilyid != lastfamilyid) {
//								// process 
//								familychanged = true;
//								//lastfamilyid =currentfamilyid ;
//							} 
//							double proposed_y1 =  currentnode.getParentnode().getPosition().getY();
//							double proposed_y2 =  tier.getYmin();
//							if (familychanged) {
//								if ( lastfamilyid == -1)
//									;
//								else 
//									proposed_y2 = proposed_y2 - Bag.familydistance;
//							} else {
//								proposed_y2 = proposed_y2 - Bag.nodespace;
//							}
//							lastfamilyid =currentfamilyid ;
//							currentnode.setPosition(level *1.0d * Bag.tierdistance, (proposed_y1<proposed_y2)?proposed_y1:proposed_y2, 0.0d);
//							tier.setYmin(currentnode.getPosition().getY());
//						}
//
//						
//					} // Case I north only
//					
//					// Case II south only
//					if (southernnodescount > 0  && northernnodescount <= 0) {
//						long lastfamilyid = -1;
//						int southstartindex = -1;
//						if (northernnodescount > 0) {
//							southstartindex = northernnodescount;
//						} else
//							southstartindex = 0;
//						// System.out.println("southstartindex :" +
//						// southstartindex+" " +tmplist.get(southstartindex));
//						for (int cursor = southstartindex; cursor < tmplist
//								.size(); cursor++) {
//							Node currentnode = tmplist.get(cursor);
//							long currentfamilyid = currentnode.getFamilyId();
//							boolean familychanged = false;
//							if (currentfamilyid != lastfamilyid) {
//								// process
//								familychanged = true;
//
//							}
//							double proposed_y1 = currentnode.getParentnode()
//									.getPosition().getY();
//							double proposed_y2 = tier.getYmax();
//							if (familychanged) {
//								if ( lastfamilyid == -1)
//									;
//								else 
//									proposed_y2 = proposed_y2 + Bag.familydistance;
//							} else {
//								proposed_y2 = proposed_y2 + Bag.nodespace;
//							}
//							lastfamilyid = currentfamilyid;
//							currentnode.setPosition(level * 1.0d
//									* Bag.tierdistance,
//									(proposed_y1 > proposed_y2) ? proposed_y1
//											: proposed_y2, 0.0d);
//							tier.setYmax(currentnode.getPosition().getY());
//						}
//					}// Case II south only
//					
//					// Case III both
//					if (southernnodescount > 0  && northernnodescount > 0) {
//						int northstartindex = northernnodescount -1;
//						int southstartindex = northernnodescount;
//						long northstartfamilyid  =  tmplist.get(northstartindex).getFamilyId();
//						long southstartfamilyid  =  tmplist.get(southstartindex).getFamilyId();
//						long lastfamilyid = southstartfamilyid;
//						for (int cursor = northstartindex; cursor>=0 ; cursor--) {
//							Node currentnode = tmplist.get(cursor);
//							
//							long currentfamilyid = currentnode.getFamilyId();
//							boolean familychanged = false;
//							if (currentfamilyid != lastfamilyid) {
//								// process 
//								familychanged = true;
//								//lastfamilyid =currentfamilyid ;
//							} 
//							double proposed_y1 =  currentnode.getParentnode().getPosition().getY();
//							double proposed_y2 =  tier.getYmin();
//							if (familychanged) {
//								if (lastfamilyid == southstartfamilyid)
//									proposed_y2 = proposed_y2 - Bag.familydistance/2.0d;
//								else 
//									proposed_y2 = proposed_y2 - Bag.familydistance;
//							} else {
//								proposed_y2 = proposed_y2 - Bag.nodespace;
//							}
//							lastfamilyid =currentfamilyid ;
//							currentnode.setPosition(level *1.0d * Bag.tierdistance, (proposed_y1<proposed_y2)?proposed_y1:proposed_y2, 0.0d);
//							tier.setYmin(currentnode.getPosition().getY());
//						}
//						lastfamilyid = northstartfamilyid;
//						for (int cursor = southstartindex; cursor < tmplist
//								.size(); cursor++) {
//							Node currentnode = tmplist.get(cursor);
//							long currentfamilyid = currentnode.getFamilyId();
//							boolean familychanged = false;
//							if (currentfamilyid != lastfamilyid) {
//								// process
//								familychanged = true;
//
//							}
//							double proposed_y1 = currentnode.getParentnode()
//									.getPosition().getY();
//							double proposed_y2 = tier.getYmax();
//							if (familychanged) {
//								if (lastfamilyid == northstartfamilyid)
//									proposed_y2 = proposed_y2 - Bag.familydistance/2.0d;
//								else 
//									proposed_y2 = proposed_y2 + Bag.familydistance;
//							} else {
//								proposed_y2 = proposed_y2 + Bag.nodespace;
//							}
//							lastfamilyid = currentfamilyid;
//							currentnode.setPosition(level * 1.0d
//									* Bag.tierdistance,
//									(proposed_y1 > proposed_y2) ? proposed_y1
//											: proposed_y2, 0.0d);
//							tier.setYmax(currentnode.getPosition().getY());
//						}
//
//					}
					
					
					//BEG original
					int northstartindex = -2;
					int southstartindex =  - 1;
					if (northernnodescount > 0 ) {
						northstartindex = northernnodescount -1;
						long lastfamilyid = -1;
						for (int cursor = northstartindex; cursor>=0 ; cursor--) {
							Node currentnode = tmplist.get(cursor);
							
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
									proposed_y2 = proposed_y2 - Bag.familydistance;
								else  {
									if ( southernnodescount  > 0 ) {
										southstartindex = northernnodescount;
										//northstartindex
										long southstartfamilyid = tmplist.get(southstartindex).getFamilyId();
										long northstartfamilyid = tmplist.get(northstartindex).getFamilyId();
										if (southstartfamilyid == northstartfamilyid ) {
											proposed_y2 = proposed_y2 - Bag.nodespace/2.0d;
										} else {
											proposed_y2 = proposed_y2 -  Bag.familydistance/2.0d;
										}
									} else {
										proposed_y2 = proposed_y2 - Bag.nodespace/2.0d;
									}
								}
							} else {
								proposed_y2 = proposed_y2 - Bag.nodespace;
							}
							lastfamilyid =currentfamilyid ;
							currentnode.setPosition(level *1.0d * Bag.tierdistance, (proposed_y1<proposed_y2)?proposed_y1:proposed_y2, 0.0d);
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
							Node currentnode = tmplist.get(cursor);
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
								// Bag.familydistance;
								if (cursor != southstartindex)
									proposed_y2 = proposed_y2
											+ Bag.familydistance;
								// else
								// proposed_y2 = proposed_y2 +
								// Bag.familydistance/2.0d;
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
													+ Bag.nodespace / 2.0d;
										} else {
											proposed_y2 = proposed_y2
													+ Bag.familydistance / 2.0d;
										}
									} else {
										proposed_y2 = proposed_y2
												+ Bag.nodespace / 2.0d;
									}
								}
							} else {
								proposed_y2 = proposed_y2 + Bag.nodespace;
							}
							
							lastfamilyid =currentfamilyid ;
							currentnode.setPosition(level *1.0d * Bag.tierdistance, (proposed_y1>proposed_y2)?proposed_y1:proposed_y2, 0.0d);
							tier.setYmax(currentnode.getPosition().getY());
						}
					}
					//END original
				}//if (-1 == equatorindex )
			}//if (tmplist.size() > 0)
			stackref = tmpstack;
			tmpstack = stack;
			stack = stackref;			
		}
	}
	// 0 first element ...; -1 none, ttl greater than 1
	static public int BFSConstructgetEquatorIndex (int southernnodescount, int northernnodescount, int ttl) {
		int result = -1;
		if  (( southernnodescount+ northernnodescount) != ttl)  {
			result = ttl - southernnodescount - 1;
		}
		return result;
	}
	
	public void BFSprint() {
		Map<Long, Node> stack = new LinkedHashMap<Long, Node> ();
		System.out.println(this.getId());
		//stack.putAll(this.getSubnodes());
		stack.put(this.getId(), this);
		Map<Long, Node> tmpstack = new LinkedHashMap<Long, Node> ();
		Map<Long, Node> stackref = null;
		while (stack.size() > 0 ) {
			tmpstack.clear();
			Iterator<Long> it = stack.keySet().iterator();
			while (it.hasNext()) {
				String tmp = "";
				Node tn = stack.get(it.next());
				Map<Long, Node> subnodes = tn.getSubnodes();
				Iterator<Long> it2 = subnodes.keySet().iterator();
				int c = 0;
				while (it2.hasNext()) {
					if (c == 0) {
						Long l2 = it2.next();
						Node tcn =  subnodes.get(l2);
						tmp += (tcn.getParentnode().getId() +  "-");
						tmp += l2;
						
					} else  
						tmp += it2.next();
					tmp += " ";
					c++;
				}
				tmp += "\t";
				
				System.out.print(tmp);
				tmpstack.putAll(subnodes);
			}
			System.out.println();
			stackref = tmpstack;
			tmpstack = stack;
			stack = stackref;
		}
	}

	// TODO make it thread safe
	// TODO you have not detect cycle yet
	public static int addNodeSubnodePair(long parentnodeid,
			long childnodeid) {
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
		return 1;
	}
	
	// TODO make it thread safe
	// TODO you have not detect cycle yet
	public static int removeNodeLeafSubnodePair(long parentnodeid,
			long childnodeid) {
		//System.out.println("["+parentnodeid+","+childnodeid+"]" );
		//System.out.println("Node.addNodeSubnodePair("+ parentnodeid+","+childnodeid+");");
		Node parent = Node.allnodes.get(parentnodeid);
		if (parent == null)
			return -1;
		Node child = Node.allnodes.get(childnodeid);
		if (child.getParentnode() == null)
			return -4;
		//parent.addAChild(child);
		//child.setParentnode(parent);
		if (child.getSubnodes().size()  > 0) {
			return -3; // you can not remove non-leaf node with this function
		
		}
		Node.allnodes.remove(child.getId());
		parent.getSubnodes().remove(child.getId());
		child.setParentnode(null);
		Node.allnodes.remove(child);
		
		return 1;
	}
	
}
