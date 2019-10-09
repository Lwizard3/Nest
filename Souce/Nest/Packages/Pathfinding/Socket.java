package Pathfinding;

import UI.PathfindingWindow;
import Utility.*;
import Utility.Error;
import Utility.Error.ErrorType;
import Utility.Math.DoublePoint;

public class Socket {
	public Field Field;
	public PathfindingWindow PW;
	
	
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
	
	
	
}
