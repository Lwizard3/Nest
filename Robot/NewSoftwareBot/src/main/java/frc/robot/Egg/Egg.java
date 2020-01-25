/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Egg;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

import java.util.*;
import java.awt.*;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.commands.*;
import frc.robot.control_systems.DriveController;
import frc.robot.Egg.Pathfinding.*;
import frc.robot.Egg.Utility.*;
import frc.robot.Egg.Utility.Math.*;

public class Egg extends CommandBase {
Robot robot;
Schedule S;
PurePursuit purePursuit;

  public Egg(Robot R) {
    //addRequirements(Robot.drive);
    robot = R;

    ArrayList<Task> Tasks = new ArrayList<Task>();

    S = new Schedule(Tasks);

    ArrayList<DoublePoint> points = new ArrayList<DoublePoint>();
    points.add(new DoublePoint(0, 0)); 
    points.add(new DoublePoint(100, 0));
    points.add(new DoublePoint(0, 0));

    /*
    points.add(new DoublePoint(25, 0));
    points.add(new DoublePoint(37, 0));
    points.add(new DoublePoint(37, 10));
    points.add(new DoublePoint(37, 20));
    points.add(new DoublePoint(37, 30));
    points.add(new DoublePoint(37, 40)); 
    points.add(new DoublePoint(37, 50));
    points.add(new DoublePoint(37, 60));   
    points.add(new DoublePoint(37, 69));
    points.add(new DoublePoint(63, 69));
    points.add(new DoublePoint(63, 60));
    points.add(new DoublePoint(63, 50));
    points.add(new DoublePoint(63, 40));
    points.add(new DoublePoint(63, 30));
    points.add(new DoublePoint(63, 20));
    points.add(new DoublePoint(63, 10));
    points.add(new DoublePoint(63, 0));

    
    points.add(new DoublePoint(30, 0));
    points.add(new DoublePoint(30, 80));
    points.add(new DoublePoint(70,  80));
    points.add(new DoublePoint(70, -0));
    points.add(new DoublePoint(100, 0)); 
    //points.add(new DoublePoint(80, 100));
    //points.add(new DoublePoint(0, 50));
    //points.add(new DoublePoint(100, 100));
    //points.add(new DoublePoint(5, 5)); 
    */



    Path P = new Path(points);


    S.addTask(new Task(P, 1));

    Task T = S.getNext();
    
    purePursuit = new PurePursuit(T.path, b);


    
  }

  protected double x, y, angle, b = 20;
  protected double oldEval = 0, oldLE = 0, oldRE = 0;
  protected AHRS Navx;  

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    Robot.drive.resetEncoders();
    
    x = (double)0;
    y = (double)0;


    Navx = new AHRS(SPI.Port.kMXP);

    Navx.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    angle = Navx.getAngle();
    trackPos();

    double r = -10, Vmax = 0.3;
    double Vr = 0, Vl = 0;


    if (purePursuit.isFinished()) {
      r = 0;
    } else {
      r = purePursuit.get(x, y, angle);
    }

    //System.out.println(r);

    if (r > 0) {
      r -= b / 2;
      Vr = Vmax;
      Vl = ((r * Vr) / b) / (1 + r / b);
    } else if (r < 0) {
      r *= -1;
      r -= b / 2;
      Vl = Vmax;
      Vr = ((r * Vl) / b) / (1 + r / b);

    } else {
      Vl = 0;
      Vr = 0;
    }

    Vr = Clamp.clamp(0, Vmax, Vr);
    Vl = Clamp.clamp(0, Vmax, Vl);

    //System.out.println(Vr + " " + Vl);

    Robot.drive.setRight(Vr);
    Robot.drive.setLeft(Vl);

    System.out.println("X: " + (int)x + " Y: " + (int)y + " Angle: " + (int)angle);
    
    
  }
  
  public void trackPos() {
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

    x -= Math.cos(angle * Math.PI / 180) * distance;
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
