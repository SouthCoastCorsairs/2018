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
public class TurnAngle extends Command implements PIDOutput {
	private float angle;
	private double time;
	private double pidOutput;
	
	

    public TurnAngle(float angle, double seconds) {
    	requires(Robot.drivetrain);
    	requires(Robot.navx);
    	this.angle = angle;
    	time = seconds;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.ResetGyro();
    	
    	Robot.navx.initPID(this);
    	Robot.navx.pidSetPoint(angle);
    	
    	setTimeout(time);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
    	
    	Robot.drivetrain.tank(pidOutput, pidOutput);
    	
    	//Right is Positive, Left is Negative
    	//Turn to Corresponding Angle
//    	if (angle > 0) {
//    		Robot.drivetrain.tank(.2, .2); //Forward Left Backward Right 
//    	}
//    	
//    	if (angle < 0) {
//    		Robot.drivetrain.tank(-.2, -.2); //Backward Left Forward Right
//    	}
    	
    	//SmartDashboard.putNumber("PIDOutput (angle)", pidOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        if (Robot.navx.onTarget() && isTimedOut()) {
            Robot.drivetrain.tank(0, 0);
            Robot.navx.freePID();
            return true;
        } else {
            return false;
        }
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
}
