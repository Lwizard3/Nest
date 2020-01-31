package Robot;

import java.awt.List;
import java.io.File;
import java.io.Serializable;

import frc.robot.Egg.Pathfinding.Task;
import frc.robot.Egg.Utility.Error;
import frc.robot.Egg.Utility.ErrorType;

public class Robot implements Serializable {
	
	//This class will be the class to describe a robot.  It will hold variables about
	//the robot as well as a list of tasks and a reference to the robot code so that eggs
	//can be installed automatically
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6385961573363212863L;
	
	
	//constants(acceleration, width, etc)

	public File RobotCode;
	
	public Task[] tasks;
	

	
	
}
