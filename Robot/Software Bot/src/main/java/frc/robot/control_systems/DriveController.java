/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.control_systems;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveController extends Command {
  public DriveController() {
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drive.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double X = Robot.j0.getX();
    double Y = -Robot.j0.getY();

    if (Math.abs(X) < 0.1)
      X = 0;
    if (Math.abs(Y) < 0.1)
      Y = 0;
    
    //Robot.drive.setRight(Y - X);
    //Robot.drive.setLeft(Y + X);
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
