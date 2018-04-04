package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RawTurn extends Command {
	public double angle;

    public RawTurn(double angle) {
    	requires(Robot.drivetrain);
    	requires(Robot.navx);
    	this.angle = angle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.ResetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (angle > 0) {
    		Robot.drivetrain.tank(.25, .25); //Forward Left Backward Right 
    	}
    	
    	if (angle < 0) {
    		Robot.drivetrain.tank(-.25, -.25); //Backward Left Forward Right
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.isAtAngle(angle);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
