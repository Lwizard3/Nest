package frc.robot.Egg.Pathfinding;

import frc.robot.Egg.Pathfinding.Path;

public class Task {
	
	//This is a class to hold the abstract idea of a task.  A task is a command programmed into
	//the robot, such a putting a hatch panel on the cargo ship in the 2019 game

	public String Name;
	public float Duration;
	
	public boolean Drive;
	public Path path;

	public Task(Path P, float time) {
		path = P;
		Duration = time;
	}
	
	public Task(String name, float time) {
		Name = name;
		Duration = time;
	}
	
}
