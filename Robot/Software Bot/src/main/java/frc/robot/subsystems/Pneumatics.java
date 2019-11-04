/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Compressor;

public class Pneumatics extends Subsystem {
  Compressor compressor = new Compressor(RobotMap.CompressorPort);

  @Override
  public void initDefaultCommand() {
    if(!compressor.getPressureSwitchValue())
      compressor.start();
    else
      compressor.stop();
  }
}
