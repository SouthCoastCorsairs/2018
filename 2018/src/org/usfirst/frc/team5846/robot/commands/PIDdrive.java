package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDdrive extends Command {
	Timer timer = new Timer();
	private double distance;
	private double LeftSpeed;
	private double RightSpeed;
	private double HeadingError;
	private double CurrentHeading;
	private double EncoderError;
	private double Error;
	private double P_Error;
	private double I_Error;
	private double Error_Prev;
	private double speed;
	private double rpsL;
	private double rpsR;
	private double d_speed;
	

    public PIDdrive(double distance) {

    	requires(Robot.drivetrain);
    	this.distance = distance;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
       	timer.start();
    	Robot.drivetrain.ResetEncoders();
    	Robot.drivetrain.ResetGyro();
    	
    	speed = .2;
    	
    	rpsL = rpsR = 1.173; 
    	
    	LeftSpeed = speed;
    	RightSpeed = speed;
    	
    	CurrentHeading = Robot.drivetrain.getAngle();
    	EncoderError = 0;
    	P_Error = 0;
    	Error_Prev = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	P_Error = Robot.drivetrain.KP * Error;
    	
    	I_Error = (P_Error - Error_Prev)*Robot.drivetrain.KI;
    	
    	rpsL = ((Robot.drivetrain.getLeftDistance())/360)/timer.get();
    	
    	rpsR = ((Robot.drivetrain.getRightDistance())/360)/timer.get();
    	
    	Error_Prev = P_Error;
    	
    	HeadingError = CurrentHeading - Robot.drivetrain.getAngle();
    	
    	EncoderError = (Robot.drivetrain.LeftIN())-(Robot.drivetrain.RightIN());
    	
    	Error = (Robot.drivetrain.getLeftDistance()) - (Robot.drivetrain.getRightDistance());
    	
    	if (LeftSpeed > speed || RightSpeed > speed) {
    	
    			
    			LeftSpeed = (LeftSpeed - ((LeftSpeed * P_Error)+(LeftSpeed * I_Error)));
    			RightSpeed = (RightSpeed + ((RightSpeed * P_Error)+(RightSpeed * I_Error)));
    		//}
    	
//    		else if (A_Error < 0){
//    			RightSpeed = RightSpeed + (RightSpeed * A_Error);
//    			LeftSpeed = LeftSpeed - (LeftSpeed * A_Error);
//    		}
    	
//    		else {
//    			LeftSpeed = RightSpeed = speed;
//    		}
    }
    	
    	else if (LeftSpeed > .4 || RightSpeed > .4) {
    		Robot.drivetrain.stopTank();
    	}
    	
    	Robot.drivetrain.tank(LeftSpeed, -RightSpeed);
    	
    	SmartDashboard.putNumber("Gyro Error", HeadingError);
    	SmartDashboard.putNumber("Encoder Error", EncoderError);
    	SmartDashboard.putNumber("Proportional Error", P_Error);
    	SmartDashboard.putNumber("Left Speed", LeftSpeed);
     	SmartDashboard.putNumber("Right Speed", RightSpeed);
     	SmartDashboard.putNumber("Error", Error);
     	SmartDashboard.putNumber("Integral Error", I_Error);
     	SmartDashboard.putNumber("Timer", timer.get());

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
