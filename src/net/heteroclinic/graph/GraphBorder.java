/*
 * The author Zhikai Wang/www.heteroclinic.net reserves all rights of this file.
 * (c) 2012  Zhikai Wang/www.heteroclinic.net
 *  
 */

package net.heteroclinic.graph;

public class GraphBorder extends Tier {
	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	protected double width = 0.0d;
	protected double height = 0.0d;

	public GraphBorder(long level) {
		super(level);

	}

	public GraphBorder(GraphBorder ngb) {
		super(ngb.level);
		this.xmin = ngb.xmin;
		this.xmax = ngb.xmax;
		this.ymin = ngb.ymin;
		this.ymax = ngb.ymax;
		this.zmin = ngb.zmin;
		this.zmax = ngb.zmax;

		this.width = ngb.width;
		this.height = ngb.height;
	}

	public void copyValueFrom(GraphBorder ngb) {
		this.level = ngb.level;
		this.xmin = ngb.xmin;
		this.xmax = ngb.xmax;
		this.ymin = ngb.ymin;
		this.ymax = ngb.ymax;
		this.zmin = ngb.zmin;
		this.zmax = ngb.zmax;

		this.width = ngb.width;
		this.height = ngb.height;
	}

	public GraphBorder(long level, double xmin, double ymin, double zmin,
			double xmax, double zmax, double ymax, double width, double height) {
		super(level);
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		this.zmin = zmin;
		this.zmax = zmax;

		this.width = width;
		this.height = height;
	}

	public void setValues(long level, double xmin, double ymin, double zmin,
			double xmax, double zmax, double ymax, double width, double height) {
		this.level = level;
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		this.zmin = zmin;
		this.zmax = zmax;

		this.width = width;
		this.height = height;
	}

	public void print() {
		System.out.println("h/w:" + getHeight() + ", " + getWidth());
		System.out.println("x/y/z:" + xmin + "," + xmax + "," + ymin + ","
				+ ymax + "," + zmin + "," + zmax);
		System.out.println("Constructor:");
		System.out.println("new GraphBorder(" + level + "," + xmin + "," + ymin
				+ "," + zmin + "," + xmax + "," + ymax + "," + zmax + ","
				+ width + "," + height + ");");

	}

}
