package Robot;

import Pathfinding.Path;

public class Task {
	
	//This is a class to hold the abstract idea of a task.  A task is a command programmed into
	//the robot, such a putting a hatch panel on the cargo ship in the 2019 game

	public String Name;
	public float Duration;
	
	public boolean Drive;
	public Path path;
	
}
