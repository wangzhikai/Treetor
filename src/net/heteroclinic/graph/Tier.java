/*
 * The author Zhikai Wang/www.heteroclinic.net reserves all rights of this file.
 * (c) 2012  Zhikai Wang/www.heteroclinic.net
 *  
 */

package net.heteroclinic.graph;

public class Tier {

	
	







	protected long level;
	public Tier (long level ) {
		this.level = level;
	}
	
	
	public long getLevel() {
		return level;
	}







	public void setLevel(long level) {
		this.level = level;
	}







	public double getXmin() {
		return xmin;
	}







	public void setXmin(double xmin) {
		this.xmin = xmin;
	}







	public double getYmin() {
		return ymin;
	}







	public void setYmin(double ymin) {
		this.ymin = ymin;
	}







	public double getZmin() {
		return zmin;
	}







	public void setZmin(double zmin) {
		this.zmin = zmin;
	}







	public double getXmax() {
		return xmax;
	}







	public void setXmax(double xmax) {
		this.xmax = xmax;
	}







	public double getZmax() {
		return zmax;
	}







	public void setZmax(double zmax) {
		this.zmax = zmax;
	}







	public double getYmax() {
		return ymax;
	}







	public void setYmax(double ymax) {
		this.ymax = ymax;
	}
	
	public void setBound (double xmin,double xmax,double  ymin,double ymax, double zmin, double zmax) {
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		this.zmin = zmin;
		this.zmax = zmax;
	}







	protected double xmin=0.0d,ymin=0.0d,zmin=0.0d,xmax=0.0d,zmax=0.0d,ymax=0.0d;
	
}
