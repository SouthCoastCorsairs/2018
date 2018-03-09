package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PneumaticsCmd extends Command {

    public PneumaticsCmd() {
    	requires(Robot.pt);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Retract When LB is Pressed
//    	if (Robot.oi.getLB2()) {
//    		Robot.pt.s1.set(false);
//    		Robot.pt.s2.set(false);
//    	}
//    	
//    	
//    	//Extend When RB is Pressed
//    	if (Robot.oi.getRB2()) {
//    		Robot.pt.s1.set(true);
//    		Robot.pt.s2.set(true);
//    	}
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
