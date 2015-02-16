/*
 * The author Zhikai Wang/www.heteroclinic.net reserves all rights of this file.
 * (c) 2012  Zhikai Wang/www.heteroclinic.net
 *  
 */

package net.heteroclinic.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.atomic.AtomicLong;

public class TNode extends Node {
	public static AtomicLong tnodecount = new AtomicLong (0l);
	protected long tnodeid  ;
	public long getTnodeid() {
		return tnodeid;
	}
	public void drawNodeShape(Graphics graphics,PixelPoint2D p2d) {
		if (this.getLevel()  > 0) {
			setFillcolor(255,255,0 );
		} else  
			setFillcolor(0,255,0 );
		if (this.isDeadlockednode()) {
			setFillcolor(255,0,0 );
		}
		int ovalsize = (int) (Bag.nodesize * ((double)Bag.OneDoubleequalpixels)) ;
		Color c = graphics.getColor();
		drawAlignedOval(graphics,p2d,ovalsize);
		graphics.setColor(this.fillcolor);
		graphics.fillOval(p2d.getX() - (ovalsize/2),p2d.getY()- (ovalsize/2) - (Bag.fontsize /3), ovalsize,ovalsize);
		//graphics.drawOval(p2d.getX() - (ovalsize/2), p2d.getY()- (ovalsize/2) - (Bag.fontsize /3), ovalsize, ovalsize);
		graphics.setColor(c);
	}
	public void drawNodeLabel(Graphics graphics,PixelPoint2D p2d) {
		//drawAlignedString (graphics,p2d,"T"+this.toString());
		drawAlignedString (graphics,p2d,"T"+tnodeid);
	}

	public TNode(long tnodeid) {
		// TODO Auto-generated constructor stub
		//tnodeid = tnodecount.incrementAndGet();
		this.tnodeid = tnodeid;
		setFillcolor(0,255,0 );
	}
	public TNode(long nid,long tnodeid) {
		// TODO Auto-generated constructor stub
		//tnodeid = tnodecount.incrementAndGet();
		super(nid);
		this.tnodeid = tnodeid;
		setFillcolor(0,255,0 );
	}
	public TNode() {
		// TODO Auto-generated constructor stub
		tnodeid = tnodecount.incrementAndGet();
		setFillcolor(0,255,0 );
	}

//	public TNode(String label) {
//		super(label);
//		// TODO Auto-generated constructor stub
//		tnodeid = tnodecount.incrementAndGet();
//	}

}
