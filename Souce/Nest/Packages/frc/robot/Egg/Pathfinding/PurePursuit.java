package frc.robot.Egg.Pathfinding;

import java.util.ArrayList;

import org.opencv.core.RotatedRect;

import frc.robot.Egg.Utility.Math.*;

public class PurePursuit {

	public Path Path;
	
	public double t = 0;
	double lookaheadDistance = 20;
	double fluff = 0.2;
	DoublePoint lookaheadPoint = null;
	double WheelWidth = 0;
	
	public PurePursuit(Path P, double wheelWidth) {
		
		/*------------------------------------------------------------*/
		/*                                                            */
		/*fgg hi lukas hi lukas hi lukas                              */
		/*                                                            */
		/*                                                            */
		/*hi hi hi hi hi hi hi hi hi hi hi hi hi hi hi hi hi hi hi hi */
		/*     -ellen                                                 */
		/*------------------------------------------------------------*/
		
		Path = P;

		for (DoublePoint Point : Path.travelPoints) {
			//System.out.println(Point);
		}

		WheelWidth = wheelWidth;
	
	}

	public boolean isFinished() {
		//System.out.println(t);
		if (t >= Path.resolution - 1) {
			return true;
		}

		return false;
	}
	
	public double get(double x, double y, double angle) {
		angle *= -1;
		getLookAheadPoint(x, y);
		//lookaheadPoint = new DoublePoint(50, 0);
		//System.out.print(lookaheadPoint + " ");
		//System.out.println(angle);

		angle = Math.toRadians(angle);
		
		double a = -Math.tan(angle);
		double b = 1;
		double c = Math.tan(angle) * x - y;
		
		double X =  Math.abs(a * lookaheadPoint.getX() + b * lookaheadPoint.getY() + c) / Math.sqrt((a * a) + (b * b));

		double L = DoublePoint.getDistance(lookaheadPoint, new DoublePoint(x, y));
		
		double r = (L * L) / (2 * X);
		
		//System.out.println(x + " " + y);

		DoublePoint rotatedPoint = new DoublePoint(lookaheadPoint.getX() - x, lookaheadPoint.getY() - y);
	
		rotatedPoint = new DoublePoint(
			rotatedPoint.getX() * Math.cos(-angle) - rotatedPoint.getY() * Math.sin(-angle), 
			rotatedPoint.getX() * Math.sin(-angle) + rotatedPoint.getY() * Math.cos(-angle));
		
		r = Math.abs(r);

		//System.out.println(r);
		
		if (0 > rotatedPoint.getY()) {
			r *= -1;
			System.out.println("Right");
		} else {
			System.out.println("Left");
		}

		//System.out.println("R: " + r + " X:" + X);
		

		return r;
	}
	
	public DoublePoint getLookAheadPoint(double x, double y) {
		double r;
		int i = 0;
		boolean found = false;
		
		for (i = (int)t; i < Path.resolution; i++) {
			r = Math.sqrt(Math.pow(Path.travelPoints.get(i).getX() - x, 2) + Math.pow(Path.travelPoints.get(i).getY() - y, 2));

			if (r > lookaheadDistance - fluff && r < lookaheadDistance + fluff) {
				lookaheadPoint = Path.travelPoints.get(i);	
				
				t = i;

				found = true;

				//System.out.println(lookaheadPoint);
				//System.out.println(t);
			} 			
		}

		if (!found) {
			if (t > Path.resolution * 0.9) {
				lookaheadDistance -= 1;
				if (lookaheadDistance <= 0) {
					t = Path.resolution;
				}
			} else {
				lookaheadDistance += 1;
				if (lookaheadDistance >= 40) {
					t = Path.resolution;
				}
			}
		}
		
		System.out.println(t);
			
		return lookaheadPoint;
	}
		
	
}
