// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimeLight;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TurnToAngle extends PIDCommand {
LimeLight limeLight;

  /** Creates a new TurnToAngle. */
  public TurnToAngle(LimeLight _limelight, double setpoint, DriveTrain driveTrain) {
    super(
        // The controller that the command will use
        new PIDController(Constants.DriveTrain.kPDriveTrainTurnToAngle, Constants.DriveTrain.kIDriveTrainTurnToAngle, Constants.DriveTrain.kDDriveTrainTurnToAngle),
        // This should return the measurement
        _limelight::getXOffset,
        // This should return the setpoint (can also be a constant)
        setpoint,
        // This uses the output
        output -> {
          driveTrain.setSpeedDriveTrainPercentOutput(0, 0, output);
        });
        limeLight = _limelight;
        _limelight.setMode(3);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.

    // getController().enableContinuousInput(-180, 180);
    // getController()
    //   .setTolerance(Constants.DriveTrain.kTurnToleranceDeg, Constants.DriveTrain.kTurnRateToleranceDegPerS);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    limeLight.setMode(1);
    return getController().atSetpoint();
  }
}
