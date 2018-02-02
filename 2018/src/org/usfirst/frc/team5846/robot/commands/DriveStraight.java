package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveStraight extends Command {
	private double distance;
	private double LeftSpeed;
	private double RightSpeed;
	private double CurrentHeading;
	public double HeadingError;
	private double iDistance;
	private double EncoderError;

    public DriveStraight(double distance) {
    	requires(Robot.drivetrain);
        this.distance = distance;
    }
   

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.ResetEncoders();
    	Robot.drivetrain.ResetGyro();
    	
    	//Initial Speed of Robot in Auto
    	LeftSpeed = .18;
    	RightSpeed = .18;
    	
    	//iDistance is the First 80% of the Total Distance
    	iDistance = .8 * distance;
    	
    	//CurrentHeading is the Initial Angle
    	CurrentHeading = Robot.drivetrain.getAngle();
    	EncoderError = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Calculates the Error of the Robot
    	HeadingError = CurrentHeading - Robot.drivetrain.getAngle();
    	
    	EncoderError = (Robot.drivetrain.LeftCM())-(Robot.drivetrain.RightCM());
    	
    	Robot.drivetrain.tank(LeftSpeed, -RightSpeed);
    	
    	
    	//For the First 80% of the Distance
    	if ((Robot.drivetrain.LeftCM() < iDistance) && (Robot.drivetrain.RightCM() < iDistance)) {
    	
    		//Error Correction 
    		if (HeadingError > .4) {
    			LeftSpeed = 0.3; 
    		}
    		
    		else if (HeadingError < -.4) {
    			RightSpeed = 0.3; 
    		}
    		
    		//Normal Speed of the Robot
    		else {
    			LeftSpeed = RightSpeed = 0.18; 
    		}
    	}
    	
    	
    	//For the Last 20% of the DistanceS
    	else if ((Robot.drivetrain.LeftCM() > iDistance) && (Robot.drivetrain.RightCM() > iDistance)) {
    		
    		//Error Correction
    		if (HeadingError > .4) {
        		LeftSpeed = 0.3; 
        	}
        	
    		else if (HeadingError < -.4) {
        		RightSpeed = 0.3; 
        	}
        	
    		//Normal Speed for the Last 20%
    		else {
        		LeftSpeed = RightSpeed = .09;
        	}
    	}
    	
    	SmartDashboard.putNumber("Error", HeadingError); //Error is opposite sign of Angle
    	SmartDashboard.putNumber("Encoder Error", EncoderError);
    	
   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //When it Reaches the Given Distance
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
