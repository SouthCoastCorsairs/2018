package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveAuto extends Command {
	private double time;
	private int direction;

    public DriveAuto(double seconds, boolean isReversed) {
    	requires(Robot.drivetrain);
    	direction = isReversed ? -1 : 1;
    	
    	time = seconds;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.tank(direction * .3, -direction * .3);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (isTimedOut()) {
        	Robot.drivetrain.stopTank();
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
}
