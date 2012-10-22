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

}
