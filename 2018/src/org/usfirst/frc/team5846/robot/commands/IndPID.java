package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IndPID extends Command {
	private double distance;
	private double LeftSpeed;
	private double RightSpeed;
	private double LeftError;
	private double RightError;
	private double d_speed;
	private double rps_l;
	private double rps_r;
	private double P_ErrorL;
	private double P_ErrorR;
	private double I_ErrorL;
	private double I_ErrorR;
	private double D_ErrorL;
	private double D_ErrorR;
	private double error_prevR;
	private double error_prevL;
	private double t_prev;
	private double t_current;
	private double t_diff;
	
	Timer timer = new Timer();

    public IndPID(double distance) {
    	requires(Robot.drivetrain);
    	this.distance = distance;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	
    	LeftError = RightError = 0;
    	
    	d_speed = 1.5;
    	
    	LeftSpeed = RightSpeed = .2;
    	
    	rps_l = rps_r = 1.5;
    	
    	P_ErrorL = P_ErrorR = I_ErrorL = I_ErrorR = D_ErrorL = D_ErrorR = 0;
    	
    	error_prevL = error_prevR = 0;
    	
    	t_prev = t_current = t_diff = 0;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.tank(LeftSpeed, -RightSpeed);
    	
    	t_current = timer.get();
    	
    	t_diff = t_current - t_prev;
    	
    	t_prev = t_current;
    	
    	rps_l = ((Robot.drivetrain.getLeftDistance())/360)/t_current;
    	
    	rps_r = ((Robot.drivetrain.getRightDistance())/360)/t_current;
    	
       	
    		
    		LeftError = rps_l - d_speed;
        	RightError = rps_r - d_speed;
    	
    	P_ErrorL = (Robot.drivetrain.KP) * LeftError;
    	P_ErrorR = (Robot.drivetrain.KP) * RightError;
    	
    	
    	I_ErrorL = (Robot.drivetrain.KI) * (P_ErrorL + error_prevL) * t_current;
    	I_ErrorR = (Robot.drivetrain.KI) * (P_ErrorR + error_prevR) * t_current;
    	
    	D_ErrorL = (Robot.drivetrain.KD) * ((P_ErrorL - error_prevL)/t_current);
    	D_ErrorR = (Robot.drivetrain.KD) * ((P_ErrorR - error_prevR)/t_current);
    	
    	error_prevL = P_ErrorL;
    	error_prevR = P_ErrorR;
    	
    	LeftSpeed = LeftSpeed - (P_ErrorL + I_ErrorL + D_ErrorL);
    	RightSpeed = RightSpeed - (P_ErrorR + I_ErrorR + D_ErrorR);
    	//LeftSpeed = LeftSpeed - (LeftSpeed * P_ErrorL + LeftSpeed * I_ErrorL);
    	//RightSpeed = RightSpeed - (RightSpeed * P_ErrorR + RightSpeed * I_ErrorR);
    	
//    	if (Robot.drivetrain.getAngle() > 30 || Robot.drivetrain.getAngle() < 30) {
//    		Robot.drivetrain.stopTank();
//    	}
    	
    	
    	
//    	SmartDashboard.putNumber("Left Speed", LeftSpeed);
//    	SmartDashboard.putNumber("Right Speed", RightSpeed);
//    	SmartDashboard.putNumber("RPS Left", 100 * rps_l);
//    	SmartDashboard.putNumber("RPS Right", 100 * rps_r);
//    	SmartDashboard.putNumber("Left Error", LeftError * 100);
//    	SmartDashboard.putNumber("Right Error", RightError * 100);
//    	SmartDashboard.putNumber("Integral Error", I_ErrorL);
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
