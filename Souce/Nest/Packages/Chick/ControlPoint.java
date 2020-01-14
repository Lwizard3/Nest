package Chick;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import Pathfinding.Path;
import Utility.Math.*;

public class ControlPoint {
	public PID PID = new PID(0, 0, 0, 0);
	
	public ControlPoint() {
		
	}
	
	public void tune(double error, ArrayList<Double> setpoints) {
		
		double avgError = 0;
		
		do  {			
			avgError = 0;
			double encoder = 0;
			ArrayList<Double> errors = new ArrayList<Double>();
			double sum = 0;
			
			Iterator I = setpoints.iterator();
			
			
			while (I.hasNext()) {
				PID.setpoint = (double) I.next();
				encoder += PID.loop(encoder);
				errors.add(PID.error);
			}
			
			for (Double D : errors) {
				sum += D;
			}
			
			avgError /= errors.size();
			
		} while (avgError > error); 
	}
}
