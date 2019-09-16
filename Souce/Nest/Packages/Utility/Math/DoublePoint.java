package Utility.Math;

import java.awt.Point;

//One problem I have is the Point class.  While it is nice it doesn't work with non integer
//numbers, so I wrote this class to express points as a pair of doubles

public class DoublePoint {

	public double x;
	public double y;
	
	public DoublePoint() {}
	
	public DoublePoint(double X, double Y) {
		
		x = X;
		y = Y;
	}
	
	public DoublePoint(Point P) {
		x = P.getX();
		y = P.getY();
	}
	
	public Double getX() {
		return x;		
	}
	
	public Double getY() {
		return y;		
	}
	
	@Override
	public String toString() {
		return "X=" + x + " Y=" + y;		
	}
	
	public Point getPoint() {		
		return (new Point((int)x, (int)y));
	}
	
	public boolean isNear(DoublePoint P, int range) {
		if (this.x - P.x < range && this.y - P.y < range) {
			return true;
		}
		return false;
	}
	
}
