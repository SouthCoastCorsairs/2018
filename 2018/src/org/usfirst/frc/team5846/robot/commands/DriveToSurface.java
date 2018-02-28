package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToSurface extends Command implements PIDOutput {
	
	private double distance;
	private double pidOutput;

    public DriveToSurface(double inches) {
    	requires(Robot.drivetrain);
    	
    	distance = inches;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.ResetEncoders();
    	Robot.drivetrain.ResetGyro();
    	Robot.drivetrain.initPID(this);
    	Robot.drivetrain.pidSetPoint(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.tank((.3 + pidOutput), -(.3 - pidOutput));
    	
    	SmartDashboard.putNumber("PIDOutput", pidOutput);
    	SmartDashboard.putNumber("Ultrasonic Distance", Robot.drivetrain.ReadInches());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.drivetrain.ReadInches() < 5) {
    		Robot.drivetrain.stopTank();
    		return true;
    	}
    		return false;
    	
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

	@Override
	public void pidWrite(double output) {
		pidOutput = output;
		// TODO Auto-generated method stub
		
	}
	
	 public double getDistance() {
	        return distance;
	    }

	    public void setDistance(double distance) {
	        this.distance = distance;
	    }
}
