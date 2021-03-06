// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Ultrasonic.Unit;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.PortMap;

public class Transporter extends SubsystemBase {
  public WPI_VictorSPX transporterMotor;
  public DigitalInput limitSwitch;
  private Ultrasonic ultrasonic;
  
  /** Creates a new Transporter. */
  public Transporter() {
    transporterMotor = new WPI_VictorSPX(PortMap.Transporter.kTransportMotor);
    limitSwitch = new DigitalInput(PortMap.Transporter.kLimitSwitch);
    ultrasonic = new Ultrasonic(3, 4, Unit.kMillimeters);

    transporterMotor.configFactoryDefault();
    Ultrasonic.setAutomaticMode(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    logs();
  }
  
  public double getUltrasonicDistance(){
    return ultrasonic.getRangeMM()/10;
  }
  
  public boolean isTransporterFull(){
    return !limitSwitch.get();
  }
  
  public boolean isBallIntake(){
    if (getUltrasonicDistance() < Constants.Intake.minUltrasonicDistanceToDelectBall ||
          getUltrasonicDistance() > Constants.Intake.maxUltrasonicDistanceToDelectBall){
      return true;
    }
    return false;
  }
  
  private void logs(){
    SmartDashboard.putBoolean("is ball intake", isBallIntake());
    SmartDashboard.putBoolean("isTransporterFull", isTransporterFull());    
  }
}
