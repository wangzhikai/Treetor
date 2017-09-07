/*
 * The author Zhikai Wang/www.heteroclinic.net reserves all rights of this file.
 * (c) 2012  Zhikai Wang/www.heteroclinic.net
 *  
 */

package net.heteroclinic.graph;

public class Point3D {
	protected double x,y,z;
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public Point3D( double x,double y,double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public String toString() {
		return "" +this.x  +"\t"+ this.y +"\t"+ this.z ;
	}
}
