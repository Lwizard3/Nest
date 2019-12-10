/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.control_systems;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

import java.util.ArrayList;
import edu.wpi.first.networktables.*;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Egg extends Command {
  public Egg() {
    requires(Robot.drive);
  }
  
  public ArrayList<double[]> Xconstants = new ArrayList<double[]>();
  public ArrayList<double[]> Yconstants = new ArrayList<double[]>();
  
  NetworkTableInstance inst;
  NetworkTable table;

  NetworkTableEntry Right;
  NetworkTableEntry Left;
  NetworkTableEntry auth;
  NetworkTableEntry comm;

  public static double left;
  public static double right;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drive.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    left = 0;
    right = 0;

    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("Nest");
    Right = table.getEntry("Right");
    Left = table.getEntry("Left");
    auth = table.getEntry("auth");
    comm = table.getEntry("comm");
    //inst.startClientTeam(589);

    comm.setDouble(0);

    auth.addListener(event -> {
      //System.out.println(auth.getDouble(0));
       if (auth.getDouble(0) == 1.0) {
        left = Left.getDouble(0.00000);
        right = Right.getDouble(0.00000);
        auth.setValue(2);
  
        Robot.drive.setLeft(right);
        Robot.drive.setRight(left);

        //System.out.println(left + " " + right);
       }
     }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    
  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}