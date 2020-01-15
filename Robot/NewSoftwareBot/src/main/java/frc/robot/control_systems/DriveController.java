/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.control_systems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DriveController extends CommandBase {
  public DriveController() {
    addRequirements(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    //Robot.drive.resetEncoders();
  }

  double r = -10, Vmax = 1;

  // Called repeatedly when this Command is scheduled to run
  //Yes
  @Override
  public void execute() {  
    double JX = Robot.j0.getX();
    double JY = Robot.j0.getY();  
    double X = Math.pow(JX, 2);
    if (JX < 0) {
      X = -X;
    }
    double Y = Math.pow(JY, 2);
    if (JY > 0) {
      Y = -Y;
    }

    
    if (Math.abs(Robot.j0.getX()) < 0.1)
      X = 0;
    if (Math.abs(Robot.j0.getY()) < 0.1)
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
        Robot.drive.setRight(Y - X);
        Robot.drive.setLeft(Y + X); 
    } else {
      // r += Math.abs(r / 100);
      Robot.drive.setRight(Vr);
      Robot.drive.setLeft(Vl);
    }

     //System.out.println("Left: " + Robot.drive.getLeftEncoder());
     //System.out.println("Right: " + Robot.drive.getRightEncoder());

  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  //@Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  //@Override
  protected void interrupted() {
  }
}
