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

  double r = -10, Vmax = 1;

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double X = Robot.j0.getX();
    double Y = -Robot.j0.getY();

    if (Math.abs(X) < 0.3)
      X = 0;
    if (Math.abs(Y) < 0.3)
      Y = 0;

    double Vr = 0, Vl = 0, b = 20;

    r = -Robot.j0.getZ() * 10;

    if (Robot.j0.getRawButton(4)) {
      Vr = Vmax;
      Vl = ((r * Vr) / b) / (1 + r / b);
    }

    if (Robot.j0.getRawButton(5)) {
      Vl = Vmax;
      Vr = ((r * Vl) / b) / (1 + r / b);
    }

    if (Robot.j0.getRawButton(6) && Vmax < 1) {
      Vmax += 0.01;
      System.out.println(Vmax);
    }

    if (Robot.j0.getRawButton(7) && Vmax > 0) {
      Vmax -= 0.01;
      System.out.println(Vmax);
    }
    
    if (!Robot.j0.getRawButton(3)) {
      if (X != 0 || Y != 0) {
        Robot.drive.setRight(Y - X);
        Robot.drive.setLeft(Y + X);
      } else if (!(Robot.left != 0 || Robot.right != 0)) {
        Robot.drive.setRight(0);
        Robot.drive.setLeft(0);
      }
    } else {
      //r += Math.abs(r / 100);
      Robot.drive.setRight(Vr);
      Robot.drive.setLeft(Vl);
    }

    //System.out.println("Left: " + Robot.drive.getLeftEncoder());
    //System.out.println("Right: " + Robot.drive.getRightEncoder());
    
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
