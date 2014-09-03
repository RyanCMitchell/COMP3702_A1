package solution;

import java.awt.geom.Point2D;
import org.jgrapht.*;


public class Node extends Point2D{
	double x;
	double y;
	double weight;
	double startToNode;
	double endToNode;
	boolean startNode;
	boolean endNode;
	
	public Node(double x, double y) {
		this.x = x;
		this.y = y;
		weight = 0;
		
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public void setLocation(double x, double y) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public Point2D.Double toPoint2D() {
		return new Point2D.Double(this.x,this.y);
	}

	public double getDistanceTo(Node n) {
		// TODO Auto-generated method stub
		double dx = (this.getX() - n.getX());
		double dy = (this.getY() - n.getY());
		double r = Math.sqrt(dx*dx + dy*dy);
		
		return r;
	}


}
