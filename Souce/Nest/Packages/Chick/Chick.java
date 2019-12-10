package Chick;

import java.util.ArrayList;
import Pathfinding.*;
import Utility.Math.DoublePoint;

public class Chick {
	public ArrayList<ControlPoint> ControlPoints = new ArrayList<ControlPoint>();
	public Field Field;
	public ArrayList<DoublePoint> points = new ArrayList<DoublePoint>();
	
	public void tune(float error, Socket S) {
		Path path = S.FindPath(new DoublePoint(20, 500), new DoublePoint(600, 50));
		ArrayList<Double> LeftSetPoints = new ArrayList<Double>();
		ArrayList<Double> RightSetPoints = new ArrayList<Double>();
		
		double x, y, x2, y2, rightSetPoint = 0, leftSetPoint = 0, distance = 20;
		DoublePoint T1, P1, P2, T2, P3, P4;
		
		T1 = path.get(0);
		x = T1.x + Math.sqrt(Math.pow(distance, 2) / (1 + Math.pow((path.getD1(0)), 2)));
		y = (-path.getD1(0)) * (x - T1.x) + T1.y;
		
		P1 = new DoublePoint(x, y);
		
		T2 = path.get(0);
		x2 = T2.x - Math.sqrt(Math.pow(distance, 2) / (1 + Math.pow((path.getD1(0)), 2)));
		y2 = (-path.getD1(0)) * (x2 - T2.x) + T2.y;
		
		P3 = new DoublePoint(x2, y2);
		
		for (int i = 0; i < 100; i++) {
			T1 = path.get((double)i / 100f);
			x = T1.x + Math.sqrt(Math.pow(distance, 2) / (1 + Math.pow((path.getD1((double)i / 100f)), 2)));
			y = (-path.getD1((double)i / 100f)) * (x - T1.x) + T1.y;
			
			P2 = new DoublePoint(x, y);
			leftSetPoint += DoublePoint.getDistance(P1, P2);
			
			LeftSetPoints.add(leftSetPoint);
			
			T2 = path.get((double)i / 100f);
			x2 = T2.x - Math.sqrt(Math.pow(distance, 2) / (1 + Math.pow((path.getD1((double)i / 100f)), 2)));
			y2 = (-path.getD1((double)i / 100f)) * (x2 - T2.x) + T2.y;
			
			P4 = new DoublePoint(x2, y2);
			rightSetPoint += DoublePoint.getDistance(P3, P4);
			
			RightSetPoints.add(rightSetPoint);
			
			points.add(P2);
			points.add(P4);
			
			P1 = P2;
			P3 = P4;
			
			System.out.println(P1.getPoint());
		}
		
		for (ControlPoint P : ControlPoints) {
			
		}
	}
	
	
}
