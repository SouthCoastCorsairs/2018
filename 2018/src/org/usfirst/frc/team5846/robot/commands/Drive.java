package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.OI;
import org.usfirst.frc.team5846.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {
	private double speed = 1;
	private double turn = .80;

    public Drive() {
    	requires(Robot.drivetrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if (Robot.oi.getA()) {
    		speed = 1;
    		turn = .80;
    	}
    	
    	if (Robot.oi.getB()) {
    		speed = .75;
    		turn = .55;
    	}
    	
    	if (Robot.oi.getY()) {
    		speed = .5;
    		turn = .30;
    	}
    	
    	if (Robot.oi.getX()) {
    		speed = .25;
    		turn = .10;
    	}
    	
    	Robot.drivetrain.drive((Robot.oi.getXaxis())*turn, (Robot.oi.getYaxis())*speed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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
