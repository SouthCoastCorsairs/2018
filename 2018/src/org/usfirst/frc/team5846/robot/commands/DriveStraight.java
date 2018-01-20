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
	private double iDistance;
	

    public DriveStraight(double distance) {
    	requires(Robot.drivetrain);
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.ResetEncoders();
    	Robot.drivetrain.ResetGyro();
    	
    	LeftSpeed = .18;
    	RightSpeed = .18;
    	
    	iDistance = .8 * distance;
    	CurrentHeading = Robot.drivetrain.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	HeadingError = CurrentHeading - Robot.drivetrain.getAngle();
    	
    	Robot.drivetrain.tank(LeftSpeed, -RightSpeed);
    	
    	if ((Robot.drivetrain.LeftCM() < iDistance) && (Robot.drivetrain.RightCM() < iDistance)) {
    	
    	
    		if (HeadingError > .4) {
    			LeftSpeed = 0.3; 
    		}
    		else if (HeadingError < -.4) {
    			RightSpeed = 0.3; 
    		}
    		else {
    			LeftSpeed = RightSpeed = 0.18; 
    		}
    	}
    	else if ((Robot.drivetrain.LeftCM() > iDistance) && (Robot.drivetrain.RightCM() > iDistance)) {
    		
    		
    		if (HeadingError > .4) {
        		LeftSpeed = 0.3; 
        	}
        	else if (HeadingError < -.4) {
        		RightSpeed = 0.3; 
        	}
        	else {
        		LeftSpeed = RightSpeed = .09;
        	}
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
