package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BoxCmd extends Command {
	private double scale = .2;

    public BoxCmd() {
    	requires(Robot.box);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.getLT() < -.25) {
    		Robot.box.intake.set(-.75);
    	}
    	
    	else if (Robot.oi.getRT() < -.25) {
    		Robot.box.intake.set(.50);
    	}
    	
    	else {
    		Robot.box.intake.set(0);
    	}
    	
    	Robot.box.lift((Robot.oi.getRY()*scale));
    	
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
