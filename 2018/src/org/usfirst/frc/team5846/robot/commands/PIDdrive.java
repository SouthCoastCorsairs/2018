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
	private double EncoderError;
	private double Error;
	private double A_Error;
	

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
    	
    	LeftSpeed = .25;
    	RightSpeed = .25;
    	
    	CurrentHeading = Robot.drivetrain.getAngle();
    	EncoderError = 0;
    	Error = 0;
    	A_Error = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	A_Error = Robot.drivetrain.KP * Error;
    	
    	HeadingError = CurrentHeading - Robot.drivetrain.getAngle();
    	
    	EncoderError = (Robot.drivetrain.LeftCM())-(Robot.drivetrain.RightCM());
    	
    	Error = (Robot.drivetrain.getLeftDistance()) - (Robot.drivetrain.getRightDistance());
    	
    	if (LeftSpeed > .25 || RightSpeed > .25) {
    	
    		if (A_Error > 0) {
    			LeftSpeed = LeftSpeed - (LeftSpeed * A_Error);
    		}
    	
    		else if (A_Error < 0){
    			RightSpeed = RightSpeed + (RightSpeed * A_Error);
    		}
    	
    		else {
    			LeftSpeed = RightSpeed = .25;
    		}
    }
    	
    	Robot.drivetrain.tank(LeftSpeed, -RightSpeed);
    	
    	
    	
    	SmartDashboard.putNumber("Gyro Error", HeadingError);
    	SmartDashboard.putNumber("Encoder Error", EncoderError);
    	SmartDashboard.putNumber("Actual Error", A_Error);
    	SmartDashboard.putNumber("Left Speed", LeftSpeed);
     	SmartDashboard.putNumber("Right Speed", RightSpeed);
     	SmartDashboard.putNumber("Error", Error);

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
