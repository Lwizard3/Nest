package Pathfinding;

import java.awt.Image;
import java.awt.Point;

import Interface.Interface;
import Interface.Timeout;
import UI.PathfindingWindow;
import frc.robot.Egg.Pathfinding.Path;
import frc.robot.Egg.Utility.*;
import frc.robot.Egg.Utility.Error;
import frc.robot.Egg.Utility.Math.Clamp;
import frc.robot.Egg.Utility.Math.DoublePoint;

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
	
	public boolean drive(Path path) {
		
		I.Start();
		
		double left = 0, right = 0, heading = 0, headingDiff = 0, angle = 0, distance = 0;
		DoublePoint oldPoint, newPoint;
		
		oldPoint = path.get(0);
		
		for (int i = 0; i < 100; i++) {
			System.out.println(path.getD2((double)i / 100));
		}
				
		for (int i = 1; i < 100; i++) {
			
			newPoint = path.get((double)i / 100);
			
			angle = Math.tan((newPoint.getY() - oldPoint.getY()) / (newPoint.getX() - oldPoint.getX()));
			distance = Math.sqrt(Math.pow(newPoint.getX() - oldPoint.getX(), 2) + Math.pow(newPoint.getY() - oldPoint.getY(), 2));
			
			headingDiff = heading - angle;
			headingDiff = Math.toDegrees(headingDiff);
			
			System.out.println(oldPoint + " " + newPoint + " " + Math.toDegrees(headingDiff) + " " + distance);
			
			double rightMax = 1;
			double leftMax = 1;
			
			if (0 < headingDiff && headingDiff < 1) {
				headingDiff = 1;
			}
			
			if (-1 < headingDiff && headingDiff < 0) {
				headingDiff = 1;
			}
			
			if (headingDiff < 0) {
				right = rightMax;
				left = leftMax / Math.abs(path.getD2((double)i / 100));
			} else if (headingDiff > 0) {
				right = rightMax / Math.abs(path.getD2((double)i / 100));
				left = leftMax;
			} else {
				left = leftMax;
				right = rightMax;
						
			}
			
			heading = angle;
			oldPoint = newPoint;
			
			left = Clamp.clamp(0, 1, left);
			right = Clamp.clamp(0, 1, right);
			
			System.out.println(left + " " + right);
			
			
			//I.send(left / 5, right / 5, Timeout.Allow);
	
			
			try {
				Thread.sleep((int)distance / 100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//I.send(0, 0, Timeout.Allow);
		
		I.Close();
		
		return true;
	}
	
	public boolean drive(DoublePoint start, DoublePoint goal) {		
		Path path = Field.map.calculatePath(start, goal);
		return drive(path);
		
	}
	
	/*
	public Image getVisionOutput() {
		return I.getImage();
	}
	*/
	
}
