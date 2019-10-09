package Pathfinding;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

import Utility.Math.AStar;
import Utility.Math.DoublePoint;
import Utility.Math.Matrix;

public class Map implements Serializable {
	public Matrix map;
	public int resolution;
		
	public Map(int width, int height) {
		map = new Matrix(width, height);
	}
	
	public Map(int imgWidth, int imgHeight, int Resolution) {
		map = new Matrix(imgWidth / Resolution, imgHeight / Resolution);
		resolution = Resolution;
		
		for (int y = 0; y < map.height; y++) {
			for (int x = 0; x < map.width; x++) {
				map.set(x, y, 1);
			}
		}
	}
	
	public double get(int x, int y) {
		return map.get(x, y);
	}
	
	public double get(Point P) {
		return map.get(P.x, P.y);
	}
	
	public void set(int x, int y, double value) {
		map.set(x, y, value);
	}
	
	public Path calculatePath(DoublePoint start, DoublePoint goal) {
		Path temp;
		ArrayList<DoublePoint> points = new ArrayList<DoublePoint>();
		
		points = AStar.calculate(map, DoublePoint.divide(start, resolution).getPoint(), DoublePoint.divide(goal, resolution).getPoint());
		points = AStar.Smooth(points);
		
		for (int i = 0; i < points.size(); i++) {
			points.set(i, DoublePoint.multiply(points.get(i), resolution));
		}	
		
		points.add(start);
		points.add(0, goal);
		
		System.out.println(points.size());
		
		for (int i = 0; i < points.size(); i++) {
			System.out.println(points.get(i));
		}
		
		temp = new Path(points);
		temp.reverse();
		//temp.calculate();
		
		try {
			temp.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return temp;
	}
}
