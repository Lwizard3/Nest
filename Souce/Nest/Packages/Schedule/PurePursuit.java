package Schedule;

import java.util.ArrayList;

import Utility.Math.DoublePoint;

public class PurePursuit {

	Path Path;
	
	double t = 0;
	double lookaheadDistance = 100;
	double fluff = 0.1;
	DoublePoint lookaheadPoint = null;
	
	public PurePursuit(Path P) {
		
		/*------------------------------------------------------------*/
		/*                                                            */
		/*fgg hi lukas hi lukas hi lukas                              */
		/*                                                            */
		/*                                                            */
		/*hi hi hi hi hi hi hi hi hi hi hi hi hi hi hi hi hi hi hi hi */
		/*     -ellen                                                 */
		/*------------------------------------------------------------*/
		
		
		P = Path;
		
	}
	
	public double get(double x, double y, double angle) {
		getLookAheadPoint(x, y);
		
		double a = -Math.tan(angle);
		double b = 1;
		double c = Math.tan(angle) * x - y;
		
		double X =  Math.abs(a * lookaheadPoint.getX() + b * lookaheadPoint.getY() + c) * Math.sqrt(a * a + b * b);
		
		double r = (lookaheadDistance * lookaheadDistance) / 2 * X;
		
		DoublePoint rotatedPoint = new DoublePoint(
				lookaheadPoint.getX() * Math.cos(-angle) - lookaheadPoint.getY() * Math.sin(-angle), 
				lookaheadPoint.getX() * Math.sin(-angle) + lookaheadPoint.getY() * Math.cos(-angle));
		
		r = Math.abs(r);
		
		if (y < rotatedPoint.getY()) {
			r *= -1;
		}
		
		return r;
	}
	
	public DoublePoint getLookAheadPoint(double x, double y) {
		double r, i;
		
		for (i = t; i < Path.resolution; i++) {
			r = Math.pow(Path.travelPoints.get(t).getX() - x, 2) + Math.pow(Path.travelPoints.get(t).getY() - y, 2);
			r *= r;
			if (lookaheadDistance - fluff <= r && lookaheadDistance + fluff >= r) {
				lookaheadPoint = Path.travelPoints.get(t);
			}
			
		}
		
		t = i;
			
		return lookaheadPoint;
	}
		
	
}
