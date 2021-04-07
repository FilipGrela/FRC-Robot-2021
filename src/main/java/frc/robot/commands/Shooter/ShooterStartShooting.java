// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;


public class ShooterStartShooting extends CommandBase {
  /** Creates a new ShooterStartShooting. */
  PIDController pidRight;
  PIDController pidLeft;
  private final Shooter m_shooter;
  public ShooterStartShooting(Shooter subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = subsystem;
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Creates a PIDController with gains kP, kI, and kD
    pidRight = new PIDController(0.1, 0, 0);
    pidLeft = new PIDController(0.1, 0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.setRightShooterVoltage(pidRight.calculate(m_shooter.getRightRPS(), 2));
    m_shooter.setLeftShooterVoltage(pidLeft.calculate(m_shooter.getLeftRPS(), 2));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.shooterStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
