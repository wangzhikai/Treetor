/*
 * The author Zhikai Wang/www.heteroclinic.net reserves all rights of this file.
 * (c) 2012  Zhikai Wang/www.heteroclinic.net
 *  
 */

package net.heteroclinic.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.atomic.AtomicLong;

public class RNode extends Node {
	public static AtomicLong rnodecount = new AtomicLong (0l);
	protected long rnodeid  ;
	public long getRnodeid() {
		return rnodeid;
	}

	public void drawNodeShape(Graphics graphics,PixelPoint2D p2d) {
		int ovalsize = (int) (Bag.nodesize * ((double)Bag.OneDoubleequalpixels)) ;
		Color c = graphics.getColor();
		//drawAlignedOval(graphics,p2d,ovalsize);
		graphics.drawRect(p2d.getX() - (ovalsize/2), p2d.getY()- (ovalsize/2) - (Bag.fontsize /3), ovalsize, ovalsize);
		graphics.setColor(this.fillcolor);
		graphics.fillRect(p2d.getX() - (ovalsize/2),p2d.getY()- (ovalsize/2) - (Bag.fontsize /3), ovalsize,ovalsize);
		//graphics.drawOval(p2d.getX() - (ovalsize/2), p2d.getY()- (ovalsize/2) - (Bag.fontsize /3), ovalsize, ovalsize);
		graphics.setColor(c);
		
	}
	public void drawNodeLabel(Graphics graphics,PixelPoint2D p2d) {
		drawAlignedString (graphics,p2d,"R"+rnodeid);
	}

	public RNode() {
		// TODO Auto-generated constructor stub
		rnodeid = rnodecount.incrementAndGet();
		setFillcolor(255, 110, 110);
	}

	public RNode(long rnodeid) {
		// TODO Auto-generated constructor stub
		//rnodeid = rnodecount.incrementAndGet();
		this.rnodeid = rnodeid ;
		setFillcolor(255, 110, 110);
	}
	public RNode(long nid, long rnodeid) {
		// TODO Auto-generated constructor stub
		//rnodeid = rnodecount.incrementAndGet();
		super(nid);
		this.rnodeid = rnodeid ;
		setFillcolor(255, 110, 110);
	}
//	public RNode(String label) {
//		super(label);
//		// TODO Auto-generated constructor stub
//	}

}
