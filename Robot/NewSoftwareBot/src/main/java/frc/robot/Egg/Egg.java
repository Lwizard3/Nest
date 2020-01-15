/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Egg;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

public class Egg extends CommandBase {
  public Egg() {
    //addRequirements(Robot.drive);
  }

  protected double x, y;
  protected double oldEval = 0, oldLE = 0, oldRE = 0;
  protected AHRS Navx;
  

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    Robot.drive.resetEncoders();
    
    Navx = new AHRS(SPI.Port.kMXP);
    x = (double)0;
    y = (double)0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    trackPos();
    //System.out.println(Navx.getAngle());
    System.out.println("X: " + (int)x + " Y: " + (int)y);
    //System.out.println(Robot.drive.getLeftEncoder() + " " + Robot.drive.getRightEncoder());
    
  }
  
  public void trackPos() {
    double angle = Navx.getAngle();
    boolean negative = (angle < 0) ? true : false;
    angle = Math.abs(angle);
    while (angle  > 360) {
      angle -= 360;
    }
    angle = (negative) ? 360 - angle : angle;

    double EncoderR = Robot.drive.getRightEncoder();
    double EncoderL = Robot.drive.getLeftEncoder();

    double distance = ((oldLE - EncoderL) + (oldRE - EncoderR)) / 2;// - oldEval;
    
    distance /= 53;

    x += Math.cos(angle * Math.PI / 180) * distance;
    y += Math.sin(angle * Math.PI / 180) * distance;

    oldLE = EncoderL;
    oldRE = EncoderR;

    oldEval = distance;
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
