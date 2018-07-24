package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.OI;
import org.usfirst.frc.team5846.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {
	private double speed = 1;
	private double direction;
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
    	
    	//Speed Multipliers
    	//Changes When Corresponding Button is Pressed
    	if (Robot.oi.getA()) {
    		speed = 1;
    		turn = .80;
    		direction = 1;
    	}
    	
    	if (Robot.oi.getB()) {
    		speed = .75;
    		turn = .70;
    		direction = 1;
    	}
    	
    	if (Robot.oi.getY()) {
    		speed = .5;
    		turn = .45;
    		direction = 1;
    	}
    	
    	if (Robot.oi.getX()) {
    		speed = .25;
    		turn = .20;
    	}
    	
    	if (Robot.oi.getStart()) {
    		speed = 1;
    		direction = -1;
    	}
    	
    	//Calls Drive Method from Drivetrain
    	//Turnspeed, Fowardspeed
    	
    	
    	//Alternate Drive Method
    	//Robot.drivetrain.Ddrive.arcadeDrive((Robot.oi.getYaxis()*speed), (Robot.oi.getYaxis()*turn));
    	
    	if (Robot.oi.tank) {
    		Robot.drivetrain.tank(Robot.oi.getYaxis() * speed, Robot.oi.getRY() * speed);
    	}
    	
    	if (!Robot.oi.tank) {
    	//Primary Drive Method
    	Robot.drivetrain.drive((-Robot.oi.getXaxis()*speed), (Robot.oi.getYaxis()*speed * direction));
    	}
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
