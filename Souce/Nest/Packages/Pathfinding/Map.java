package Pathfinding;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import UI.PathfindingWindow;
import frc.robot.Egg.Pathfinding.Path;
import frc.robot.Egg.Utility.*;
import frc.robot.Egg.Utility.Error;
import frc.robot.Egg.Utility.Math.AStar;
import frc.robot.Egg.Utility.Math.DoublePoint;
import frc.robot.Egg.Utility.Math.Matrix;

public class Map implements Serializable {
	public Matrix map;
	public int resolution;
		
	public Map(int width, int height) {
		map = new Matrix(width, height);
	}
	
	public Map(int imgWidth, int imgHeight, int Resolution, Image img, ArrayList<Color> badColors) {
		BufferedImage bimg = (BufferedImage) img;
		map = new Matrix(imgWidth / Resolution, imgHeight / Resolution);
		resolution = Resolution;
		
		Color color;
		
		for (int y = 0; y < map.height; y++) {
			for (int x = 0; x < map.width; x++) {
				if (x <= 0 || x >= map.width - 1 || y <= 0 || y >= map.height - 1) {
					map.set(x, y, 0);
					continue;
				}
				color = new Color(bimg.getRGB(x * resolution, y * resolution));
				
				map.set(x, y, 1);
				
				for (int y2 = y * resolution; y2 < (y + 1) * resolution; y2++) {
					for (int x2 = x * resolution; x2 < (x + 1) * resolution; x2++) {
						color = new Color(bimg.getRGB(x2, y2));
						
						if (badColors.contains(color)) {
							map.set(x, y, 0);
						}
					}
				}
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
	
	public void Print() {
		JFrame Frame = new JFrame();
		
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		
		JLabel label;
		
		Frame.setLayout(new GridLayout(map.width, map.height));
		Frame.setSize(map.width * 10, map.height * 10);	
		
		for (int y = 0; y < map.height; y++) {
			for (int x = 0; x < map.width; x++) {
				if (map.get(x, y) == 0) {
					//System.out.print("           ");
				} else {
					BigDecimal bd = new BigDecimal(Double.toString(map.get(x, y)));
				    bd = bd.setScale(8, RoundingMode.HALF_DOWN);
					//System.out.print(map.get(x, y) + " ");
				}
				
				//System.out.println((map.get(x, y)));
				
				String temp = ".";
				
				if (map.get(x, y) == 0) {
					temp = "0";
				}
				
				label = new JLabel(temp);
				label.setOpaque(true);
				label.setSize(50, 50);
				label.setBackground(new Color((int)(map.get(x, y) * 255), (int)(map.get(x, y) * 255), (int)(map.get(x, y) * 255)));
				labels.add(label);
				Frame.add(labels.get(labels.size() - 1));
			}		
			//System.out.println();
			
			
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Frame.setVisible(true);
	}
	
	public Path calculatePath(DoublePoint start, DoublePoint goal) {
				
		Path temp;
		ArrayList<DoublePoint> points = new ArrayList<DoublePoint>();
		
		if (map.get(DoublePoint.divide(goal, resolution).getPoint()) == 0) {
			new Error("Goal is inside of obsticle", ErrorType.Fatal);
		}
		points = AStar.calculate(map, DoublePoint.divide(start, resolution).getPoint(), DoublePoint.divide(goal, resolution).getPoint());
		if (points.size() < 1) {
			new Error("Path could not be found", ErrorType.Fatal);
		}
		
		
		//points = AStar.CloseCull(points);
		//points = AStar.LinearCull(points);
		//points = AStar.CloseCull(points);
		//points = AStar.LinearCull(points);
		//points = AStar.AngleCull(points);
		//points = AStar.CloseCull(points);
		points = AStar.CountCull(points, 15);



		
		for (int i = 0; i < points.size(); i++) {
			points.set(i, DoublePoint.multiply(points.get(i), resolution));
			//points.set(i, new DoublePoint(points.get(i).x + (resolution), points.get(i).y - (resolution)));
		}	
		
		
		if (!points.contains(start)) {
			points.add(start);
		}
		points.add(0, goal);
		
		
		temp = new Path(points);
		temp.reverse();
		//temp.calculate();
		

		
		
		return temp;
	}
	
	public void SmoothMap() {
		Matrix temp = new Matrix(map.width, map.height);
		double average = 0;
		int c = 0;
		for (int y = 0; y < map.height; y++) {
			for (int x = 0; x < map.width; x++) {
				c = 0;
				average = 0;
				for (int y2 = -2; y2 <= 2; y2++) {
					for (int x2 = -2; x2 <= 2; x2++) {
						try {
							average += map.get(x2 + x, y2 + y);
							c++;
						} catch (Exception E) {
							
						}						
					}
				}
				
				//System.out.println(average + " " + c);
				
				average /= c;
				
				if (map.get(x, y) != 0) {
					temp.set(x, y, average);
				}
			}
		}
		
		map = temp;
	}
}
