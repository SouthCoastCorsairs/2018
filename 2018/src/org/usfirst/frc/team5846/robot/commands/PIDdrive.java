package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDdrive extends Command {
	private double distance;
	private double LeftSpeed;
	private double RightSpeed;
	private double HeadingError;
	private double CurrentHeading;
	

    public PIDdrive(double distance) {

    	requires(Robot.drivetrain);
    	this.distance = distance;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.ResetEncoders();
    	Robot.drivetrain.ResetGyro();
    	
    	CurrentHeading = Robot.drivetrain.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	HeadingError = CurrentHeading - Robot.drivetrain.getAngle();
    	
    	Robot.drivetrain.tank(LeftSpeed, RightSpeed);
    	
    	
    	
    	SmartDashboard.putNumber("Error", HeadingError);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.isAtDistance(distance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stopTank();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
