

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.*;

import frc.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Drive extends SubsystemBase {

  private TalonSRX rDriveMotor, lDriveMotor;

  //@Override
  public Drive() {
    //Setting Talon Addresses
    rDriveMotor = new TalonSRX(1);
    lDriveMotor = new TalonSRX(2);

    rDriveMotor.configFactoryDefault();
    rDriveMotor.setNeutralMode(NeutralMode.Coast);
    
    lDriveMotor.configFactoryDefault();
    lDriveMotor.setNeutralMode(NeutralMode.Coast);

    rDriveMotor.setInverted(true);

  }

  public void setRight(double speed){ //set right drive motor to given speed
    rDriveMotor.set(ControlMode.PercentOutput, speed);
  }

  public void setLeft(double speed){ //set left drive motor to given speed
    lDriveMotor.set(ControlMode.PercentOutput, speed);
  }

  public void setBoth(double speed){ //set both motors to given speed
    rDriveMotor.set(ControlMode.PercentOutput, speed);
    lDriveMotor.set(ControlMode.PercentOutput, speed);
  }

  public void stop(){ //stop both motors
    setBoth(0);
  }

  public int getRightEncoder(){
    return (int)rDriveMotor.getSensorCollection().getQuadraturePosition();
  }

  public int getLeftEncoder(){
    return (int)lDriveMotor.getSensorCollection().getQuadraturePosition();
  }

  public void resetEncoders(){
    rDriveMotor.getSensorCollection().setQuadraturePosition(0, 0);
    lDriveMotor.getSensorCollection().setQuadraturePosition(0, 0);
  }

}

