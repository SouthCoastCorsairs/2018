package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnAngle extends Command {
	private float angle;
	private double time;
	private double pidOutput;
	
	

    public TurnAngle(float angle) {
    	requires(Robot.drivetrain);
    	requires(Robot.navx);
    	this.angle = angle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.ResetGyro();
    	Robot.navx.turnController.setInputRange(-180.0f, 180.0f);
    	Robot.navx.turnController.setContinuous(true);
    	Robot.navx.turnController.setOutputRange(-.5, .5);
    	Robot.navx.turnController.setSetpoint(angle);
    	Robot.navx.turnController.enable();
    	
//    	Robot.navx.initPID(this);
//    	Robot.navx.pidSetPoint(angle);
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double speed = Robot.navx.turnSpeed.getSpeed();
    	Robot.drivetrain.tank(speed, speed);
    
    	
    	//Robot.drivetrain.tank(pidOutput, pidOutput);
    	
    	//Right is Positive, Left is Negative
    	//Turn to Corresponding Angle
//    	if (angle > 0) {
//    		Robot.drivetrain.tank(.25, .25); //Forward Left Backward Right 
//    	}
//    	
//    	if (angle < 0) {
//    		Robot.drivetrain.tank(-.25, -.25); //Backward Left Forward Right
//    	}
    	
    	//SmartDashboard.putNumber("PIDOutput (angle)", pidOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
    	 return !(Robot.navx.turnController.isEnabled());
//        if (Robot.navx.onTarget() || isTimedOut()) {
//            Robot.drivetrain.tank(0, 0);
//            Robot.navx.freePID();
//            return true;
//        } else {
//            return false;
//        }
//    	if (Robot.drivetrain.isAtAngle(angle) || isTimedOut()) {
//    		Robot.drivetrain.stopTank();
//    		return true;
//    		
//    	}
//    	else {
//    		return false;
    	}

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }

}
