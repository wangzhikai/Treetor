package net.heteroclinic.graph;

public class PixelPoint2D {
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	protected int x;
	protected int y;
	
	public PixelPoint2D (int x, int y) {
		this.x = x;
		this.y = y;
	}
	

}
