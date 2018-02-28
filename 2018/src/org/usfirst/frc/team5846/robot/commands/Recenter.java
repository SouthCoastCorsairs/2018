package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Recenter extends Command implements PIDOutput {
	private double PIDOutput;
	private double time;
	private PIDController pid;

    public Recenter(double seconds) {
    	requires(Robot.drivetrain);
    	
    	
    	time = seconds;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    public void initPID() {
    	pid = new PIDController(Robot.drivetrain.KP, Robot.drivetrain.KI, Robot.drivetrain.KD, Robot.drivetrain.KF, Robot.drivetrain.ahrs, this);
    	pid.setInputRange(-180.0f, 180.0f);
        pid.setOutputRange(-0.3, 0.3);
        pid.setAbsoluteTolerance(0.5f);
        pid.setContinuous(true);
        pid.setSetpoint(0);
        pid.enable();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	initPID();
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.tank(PIDOutput, -PIDOutput);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (pid.onTarget() && isTimedOut()) {
            Robot.drivetrain.stopTank();
            pid.disable();
            pid.free();
            return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	public void pidWrite(double output) {
		PIDOutput = output;
		// TODO Auto-generated method stub
		
	}
}
