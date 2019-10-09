package Utility.Math;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Hashtable;

public class AStar {
	public static ArrayList<DoublePoint> calculate(Matrix map, Point start, Point goal) {
		
		ArrayList<DoublePoint> path = new ArrayList<DoublePoint>();
		
		ArrayList<Point> pointList = new ArrayList<Point>();
		Hashtable<Point, Point> cameFrom = new Hashtable<Point, Point>();
		Hashtable<Point, Integer> cost = new Hashtable<Point, Integer>();
		Hashtable<Point, Integer> Gcost = new Hashtable<Point, Integer>();
		Hashtable<Point, Integer> Fcost = new Hashtable<Point, Integer>();

		
		pointList.add(start);
		cameFrom.put(start, new Point(-1, -1));
		cost.put(start, getDistance(start, goal));
		Gcost.put(start, 0);
		Fcost.put(start,  getDistance(start, goal));
		
		Point current = new Point();
		
		int newCost;
		while (!pointList.isEmpty()) {
						
			//System.out.println(pointList.size());
			
			
			pointList = sort(pointList, cost);
			
			
			//System.out.println(pointList.size());

			
			current = pointList.get(0);
			pointList.remove(0);
				
			if (pointList.contains(goal) || current.toString().equals(goal.toString())) {
				break;
			}
			
			for (Point next : getNeighbors(current, map)) {
				
				Fcost.put(next, getDistance(next, goal));
				
				newCost = (int) (Gcost.get(current) + Fcost.get(next) + 1 + (1/map.get(next)));
				
				
				if (!cost.containsKey(next) || newCost < cost.get(next)) {
					if (cost.containsKey(next)) {
						cost.replace(next, newCost);
						cameFrom.replace(next, current);
						Gcost.replace(next, Gcost.get(current) + 1);
					} else {
						cost.put(next, newCost);
						cameFrom.put(next, current);
						Gcost.put(next, Gcost.get(current) + 1);
					}
					pointList.add(next);
					
					
					
					
				}
				
			}		
			
		}
		
		//System.out.println(current);

		
		while (current != start) {
			current = cameFrom.get(current);
			//System.out.println(current);
			
			path.add(new DoublePoint(current));
			
		}
		
		
	
		
		return path;
	}
	
	public static ArrayList<DoublePoint> Smooth(ArrayList<DoublePoint> input) {
		
		ArrayList<DoublePoint> temp = new ArrayList<DoublePoint>();
		temp = input;
		
		for (int i = 0; i < input.size() - 1; i++) {
			if ((input.get(i).getX() == input.get(i + 1).getX()) || (input.get(i).getY() == input.get(i + 1).getY())) {
				temp.remove(input.get(i));
			}
		}
		
		return temp;
	}
	
	static ArrayList<Point> getNeighbors(Point P, Matrix map) {
		
		ArrayList<Point> temp = new ArrayList<Point>();
				
		if (P.y > 0 && map.get(P.x, P.y - 1) != 0) {
			temp.add(new Point(P.x, P.y - 1));
		}
		
		if (P.x < map.width - 1 && map.get(P.x + 1, P.y) != 0) {
			temp.add(new Point(P.x + 1, P.y));
		}
		
		if (P.y < map.height - 1 && map.get(P.x, P.y + 1) != 0) {
			temp.add(new Point(P.x, P.y + 1));
		}
		
		if (P.x > 0 && map.get(P.x - 1, P.y) != 0) {
			temp.add(new Point(P.x - 1, P.y));
		}
		
		
		
		if (P.y > 0 && P.x < map.width - 1 && map.get(P.x + 1, P.y - 1) != 0) {
			temp.add(new Point(P.x + 1, P.y - 1));
		}
		
		if (P.x < map.width - 1 && P.y < map.height - 1 && map.get(P.x + 1, P.y + 1) != 0) {
			temp.add(new Point(P.x + 1, P.y + 1));
		}
		
		if (P.y < map.height - 1 && P.x > 0 && map.get(P.x - 1, P.y + 1) != 0) {
			temp.add(new Point(P.x - 1, P.y + 1));
		}
		
		if (P.x > 0 && P.y > 0 && map.get(P.x - 1, P.y - 1) != 0) {
			temp.add(new Point(P.x - 1, P.y - 1));
		}
		
				
		return temp;
		
	}
	
	static int getDistance(Point P, Point G) {
		
		return (int) (Math.sqrt(Math.pow(P.x - G.x, 2) + Math.pow(P.y - G.y, 2)) * 100);
	}
	
	static ArrayList<Point> sort(ArrayList<Point> P, Hashtable<Point, Integer> C) {
		
		ArrayList<Point> tempP = new ArrayList<Point>();
		
		int min = (int) Math.floor(C.get(P.get(0)));
		int i = 0;
		
		while (P.size() > 0) {
					
			i = 0;
			min = (int) Math.floor(C.get(P.get(0)));
			for (int k = 0; k < P.size(); k++) {
				if (C.get(P.get(k)) < min) {
					min = (int) Math.floor(C.get(P.get(k)));
					i = k;
				}
			}
			
			tempP.add(P.get(i));
			
			P.remove(i);			
			
		}
					
		return tempP;
		
	}
}
