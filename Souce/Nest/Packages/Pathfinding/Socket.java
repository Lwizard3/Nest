package Pathfinding;

import java.awt.Image;
import java.awt.Point;

import Interface.Interface;
import UI.PathfindingWindow;
import Utility.*;
import Utility.Error;
import Utility.ErrorType;
import Utility.Math.DoublePoint;

public class Socket {
	public Field Field;
	public PathfindingWindow PW;
	public Interface I = new Interface();
	
	
	public Socket(PathfindingWindow pathfindingWindow) {		
		PW = pathfindingWindow;
	}
	
	public Socket(PathfindingWindow pathfindingWindow, Field f) {
		PW = pathfindingWindow;
		Field = f;	
	}
	
	public void mount(Field field) {
		Field = field;
	}
	
	public void unMount() {
		Field = null;
	}
	
	public void check() {
		if (Field == null) {
			new Error("Field not mounted", ErrorType.Fatal);
		}
	}
	
	public void update() {
		check();
		
		PW.FC.Background = Field.Image();
		
		PW.setSize(Field.Width, Field.Height + 30);
		
		PW.FC.setSize(Field.Width, Field.Height);
	}
	
	public Path FindPath(DoublePoint start, DoublePoint goal) {
		return Field.map.calculatePath(start, goal);
		
		
	}
	
	public boolean drive(DoublePoint start, DoublePoint goal) {
		
		System.out.println("driving");
		
		Path path = Field.map.calculatePath(start, goal);
		double left = 0, right = 0, heading = 0, headingDiff = 0, angle = 0, distance = 0;
		DoublePoint oldPoint, newPoint;
		
		oldPoint = path.get(0);
		
		System.out.println("Path Calculated");
		
		for (int i = 1; i < 100; i++) {
			
			newPoint = path.get((double)i / 100);
			
			angle = Math.tan((newPoint.getY() - oldPoint.getY()) / (newPoint.getX() - oldPoint.getX()));
			distance = Math.sqrt(Math.pow(newPoint.getX() - oldPoint.getX(), 2) + Math.pow(newPoint.getY() - oldPoint.getY(), 2));
			
			headingDiff = heading - angle;
			headingDiff = Math.toDegrees(headingDiff);
			
			System.out.println(oldPoint + " " + newPoint + " " + Math.toDegrees(headingDiff) + " " + distance);
			
			double rightMax = 0.1;
			double leftMax = 0.1;
			
			if (headingDiff > 0) {
				right = rightMax;
				left = leftMax / headingDiff;
			} else if (headingDiff < 0) {
				right = rightMax / headingDiff;
				left = leftMax;
			} else {
				left = leftMax;
				right = rightMax;
						
			}
			
			heading = angle;
			oldPoint = newPoint;
			
			
			I.send(left, right);
	
			
			try {
				Thread.sleep((int)distance / 100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		I.send(0, 0);
		
		return true;
	}
	
	/*
	public Image getVisionOutput() {
		return I.getImage();
	}
	*/
	
}
