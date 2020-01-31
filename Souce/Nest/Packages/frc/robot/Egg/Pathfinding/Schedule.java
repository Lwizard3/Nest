package frc.robot.Egg.Pathfinding;

import java.io.Serializable;
import java.util.ArrayList;

import Pathfinding.Field;

public class Schedule implements Serializable {
	public ArrayList<Task> Tasks = new ArrayList<Task>();
	int spot = -1;
	
	public Schedule(ArrayList<Task> tasks) {	
		Tasks = tasks;
	}
	
	public void addTask(Task T) {		
		Tasks.add(T);
	}
	
	public Task getNext() {
		spot++;
		if (spot < Tasks.size()) {
			return Tasks.get(0);
		} else {
			return new Task("Null", 0);
		}
	}
}
