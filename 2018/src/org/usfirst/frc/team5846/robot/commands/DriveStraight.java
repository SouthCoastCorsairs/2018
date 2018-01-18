package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {
	private double distance;
	private double LeftSpeed;
	private double RightSpeed;
	private double CurrentHeading;
	private double HeadingError;

    public DriveStraight(double distance) {
    	requires(Robot.drivetrain);
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.ResetEncoders();
    	Robot.drivetrain.ResetGyro();
    	
    	LeftSpeed = .2;
    	RightSpeed = .2;
    	
    	CurrentHeading = Robot.drivetrain.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	HeadingError = CurrentHeading - Robot.drivetrain.getAngle();
    	
    	Robot.drivetrain.tank(LeftSpeed, -RightSpeed);
    	
    	if (HeadingError > 1) {
    		LeftSpeed = 0.3; 
    	}
    	else if (HeadingError < -1) {
    		RightSpeed = 0.3; 
    	}
    	else {
    		LeftSpeed = RightSpeed = 0.2; 
    	}
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
