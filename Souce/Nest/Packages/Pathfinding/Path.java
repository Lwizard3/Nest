package Pathfinding;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Utility.Error.ErrorType;
import Utility.Error;
import Utility.Math.CubicSpline;
import Utility.Math.DoublePoint;
import Utility.Math.Matrix; 


public class Path extends Thread implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8694272386834623413L;

	
	public ArrayList<double[]> Xconstants = new ArrayList<double[]>();
	public ArrayList<double[]> Yconstants = new ArrayList<double[]>();
	public ArrayList<DoublePoint> points = new ArrayList<DoublePoint>();
	
	int length;
	
	public Path(ArrayList<DoublePoint> p) {
		points = p;
	}
	
	public void calculate() {
		this.start();
	}
	
	public void run() {
		
		length = points.size() - 1;
		
		ArrayList<DoublePoint> Xlist = new ArrayList<DoublePoint>();
		ArrayList<DoublePoint> Ylist = new ArrayList<DoublePoint>();

		
		for (int i = 0; i < points.size(); i++) {
			Xlist.add(new DoublePoint(i, points.get(i).x));
			Ylist.add(new DoublePoint(i, points.get(i).y));
		}
		
		Xconstants = CubicSpline.calculate(Xlist);
		Yconstants = CubicSpline.calculate(Ylist);	
		
	}
	
	public DoublePoint get(double T) {
		
		if (T > 1 || T < 0) {
			new Error("Cannot access parts of path outside of range", ErrorType.Fatal);
		}
		
		//System.out.println(length);
		
		T *= length;
		
		//System.out.println(T);
		
		int function = (int) Math.floor(T);
		
		if (T == length) {
			function--;
		}
		
		//System.out.println(function);
		
		return new DoublePoint((
				Xconstants.get(function)[0] * Math.pow(T, 3) + 
				Xconstants.get(function)[1] * Math.pow(T, 2) + 
				Xconstants.get(function)[2] * Math.pow(T, 1) +
				Xconstants.get(function)[3] * Math.pow(T, 0)
				),(
				Yconstants.get(function)[0] * Math.pow(T, 3) + 
				Yconstants.get(function)[1] * Math.pow(T, 2) + 
				Yconstants.get(function)[2] * Math.pow(T, 1) +
				Yconstants.get(function)[3] * Math.pow(T, 0)
				) );
	}
	
	public double getD1(double T) {
		if (T > 1 || T < 0) {
			new Error("Cannot access parts of path outside of range", ErrorType.Fatal);
		}
		
		//System.out.println(length);
		
		T *= length;
		
		//System.out.println(T);
		
		int function = (int) Math.floor(T);
		
		if (T == length) {
			function--;
		}
		
		return ((
				Xconstants.get(function)[0] * Math.pow(T, 2) * 3 + 
				Xconstants.get(function)[1] * Math.pow(T, 1) * 2 + 
				Xconstants.get(function)[2] * Math.pow(T, 0) * 1 +
				Xconstants.get(function)[3] * Math.pow(T, 0) * 0
				)/(
				Yconstants.get(function)[0] * Math.pow(T, 2) * 3 + 
				Yconstants.get(function)[1] * Math.pow(T, 1) * 2 + 
				Yconstants.get(function)[2] * Math.pow(T, 0) * 1 +
				Yconstants.get(function)[3] * Math.pow(T, 0) * 0
				) );
	
	}
	
	public double getD2(double T) {
		if (T > 1 || T < 0) {
			new Error("Cannot access parts of path outside of range", ErrorType.Fatal);
		}
		
		//System.out.println(length);
		
		T *= length;
		
		//System.out.println(T);
		
		int function = (int) Math.floor(T);
		
		if (T == length) {
			function--;
		}
		
		return (
				(
				(Xconstants.get(function)[0] * Math.pow(T, 2) * 3 + 
				 Xconstants.get(function)[1] * Math.pow(T, 1) * 2 + 
				 Xconstants.get(function)[2] * Math.pow(T, 0) * 1 +
				 Xconstants.get(function)[3] * Math.pow(T, 0) * 0)
				*
				(Yconstants.get(function)[0] * Math.pow(T, 1) * 6 + 
				 Yconstants.get(function)[1] * Math.pow(T, 0) * 2 + 
				 Yconstants.get(function)[2] * Math.pow(T, 0) * 0 +
				 Yconstants.get(function)[3] * Math.pow(T, 0) * 0)
				- 
				(Yconstants.get(function)[0] * Math.pow(T, 2) * 3 + 
				 Yconstants.get(function)[1] * Math.pow(T, 1) * 2 + 
				 Yconstants.get(function)[2] * Math.pow(T, 0) * 1 +
				 Yconstants.get(function)[3] * Math.pow(T, 0) * 0) 
				*
				(Xconstants.get(function)[0] * Math.pow(T, 1) * 6 + 
				 Xconstants.get(function)[1] * Math.pow(T, 0) * 2 + 
				 Xconstants.get(function)[2] * Math.pow(T, 0) * 0 +
				 Xconstants.get(function)[3] * Math.pow(T, 0) * 0)
				) 
				/ Math.pow(
				(Xconstants.get(function)[0] * Math.pow(T, 2) * 3 + 
				 Xconstants.get(function)[1] * Math.pow(T, 1) * 2 + 
				 Xconstants.get(function)[2] * Math.pow(T, 0) * 1 +
				 Xconstants.get(function)[3] * Math.pow(T, 0) * 0), 3)				
				);
	
	}
	
	
}
