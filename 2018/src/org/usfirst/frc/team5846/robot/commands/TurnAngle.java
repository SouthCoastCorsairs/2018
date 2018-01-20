package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnAngle extends Command {
	private double angle;
	

    public TurnAngle(double angle) {
    	requires(Robot.drivetrain);
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
    		Robot.drivetrain.tank(.15, .15); //foward left backward right
    	}
    	if (angle < 0) {
    		Robot.drivetrain.tank(-.15, -.15);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return Robot.drivetrain.isAtAngle(angle);
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
