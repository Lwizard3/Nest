/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Pneumatics;
import frc.robot.control_systems.DriveController;
import frc.robot.control_systems.Egg;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.Timer;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
//import edu.wpi.first.wpilibj.util.Color;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static OI oi;
  public static DriveController drivecontroller;
  public static Drive drive = new Drive();
  //public static Egg egg;

  public static Joystick j0;

  public static Pneumatics pneumatics = new Pneumatics();

  NetworkTableInstance inst;
  NetworkTable table;

  NetworkTableEntry Right;
  NetworkTableEntry Left;
  NetworkTableEntry auth;
  NetworkTableEntry comm;

  public static double left;
  public static double right;

  AHRS Navx;

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);

  Timer T = new Timer();

  int connectionCount = 0;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    j0 = new Joystick(0);

    oi = new OI();

    Navx = new AHRS(SPI.Port.kMXP);
     

    left = 0;
    right = 0;

    T.start();

    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("Nest");
    Right = table.getEntry("Right");
    Left = table.getEntry("Left");
    auth = table.getEntry("auth");
    comm = table.getEntry("comm");
    //inst.startClientTeam(589);

    comm.setDouble(0);

    /*
    auth.addListener(event -> {
      //System.out.println(auth.getDouble(0));
       if (auth.getDouble(0) == 1.0) {
        left = Left.getDouble(0.00000);
        right = Right.getDouble(0.00000);
        auth.setValue(2);
  
        drive.setLeft(right);
        drive.setRight(left);

        //System.out.println(left + " " + right);
       }
     }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
       
     */

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    //System.out.println(auth.getDouble(0));

    //System.out.println("Navx Angle: " + Navx.getAngle());
    
    


    if (inst.getConnections().length != connectionCount) {
      connectionCount = inst.getConnections().length;
      System.out.println(connectionCount + " Connections :");

      for (ConnectionInfo C : inst.getConnections()) {
        System.out.println(C);        
      }

      if (connectionCount == 0) {
        //inst.flush();
        left = 0;
        right = 0;

        inst.stopServer();
        inst.startServer();
        
      } else {
        //inst.close();

        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("Nest");
        Right = table.getEntry("Right");
        Left = table.getEntry("Left");
        auth = table.getEntry("auth");
        comm = table.getEntry("comm");
        //inst.startClientTeam(589);

        /*
        auth.addListener(event -> {
          //System.out.println(auth.getDouble(0));
           if (auth.getDouble(0) == 1.0) {
            left = Left.getDouble(0.00000);
            right = Right.getDouble(0.00000);
            auth.setValue(2);
      
            drive.setLeft(right);
            drive.setRight(left);
    
            //System.out.println(left + " " + right);
           }
         }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
         */
      }

      //System.out.println("-" + auth.getDouble(0.0));
      
    }
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {

    left = 0;
    right = 0;
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    left = 0;
    right = 0;

    T.start();

    drivecontroller = new DriveController();
    drivecontroller.start();

    //egg = new Egg();
    //egg.start();

    

    //drive.setBoth(0.1);
  
  }

  double temp = 0;

  /*
  int c = 0;
  double timer = 0.1;
  double time = 0;
  */

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    if (auth.getDouble(0.0000) != temp) {
      System.out.println(auth.getDouble(0.0000));
      temp = auth.getDouble(0.0000);
    }

    if (auth.getDouble(0) == 1.0) {
      left = Left.getDouble(0.00000);
      right = Right.getDouble(0.00000);
      auth.setValue(2);

      drive.setLeft(right);
      drive.setRight(left);

      System.out.println(left + " " + right);
     }

     /*
     if (c == 0) {
        if (timer > 0) {
          drive.setBoth(0.2);
          timer -= T.get() - time;
          time = T.get();
        } else {
          c = 1;
          timer = 1;
        }
     } else if (c == 1) {
      if (timer > 0) {
        drive.setLeft(0.3);
        drive.setRight(-0.3);
        timer -= T.get() - time;
        time = T.get();
      } else {
        c = 0;
        timer = 4;
      }
   }
   */

   //System.out.println(timer);

    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {}
}
