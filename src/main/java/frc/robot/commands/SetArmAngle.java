// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ArmFeedforward;

public class SetArmAngle extends CommandBase {
  /** Creates a new SetArmAngle. */

  private double angleArm;
  private PIDController PID = new PIDController(0.0, 0.0, 0.0);
  private ArmFeedforward feedForward = new ArmFeedforward(0.0, 0.0, 0.0);

  public SetArmAngle(double angle) {
    addRequirements(RobotContainer.getArm());
    angleArm = angle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    PID.setSetpoint(angleArm);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.getArm().setArmPower(PID.calculate(RobotContainer.getArm().getArmAngle()) + feedForward.calculate(RobotContainer.getArm().getArmAngle(), 0));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
