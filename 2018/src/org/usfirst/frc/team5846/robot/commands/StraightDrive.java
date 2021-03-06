
package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StraightDrive extends Command {
	
	private double Distance;
	private double CurrentHeading;
	private double LeftSpeed;
	private double RightSpeed;
	private double HeadingError;
	private int direction;

    public StraightDrive(double Distance, boolean isReversed) {
    	requires(Robot.drivetrain);
    	this.Distance = Distance;
    	direction = isReversed ? -1 : 1;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.ResetEncoders();
    	Robot.drivetrain.ahrs.reset();
    	LeftSpeed = 0.6 * direction; //Speed of the left side
    	RightSpeed = 0.6 * direction; //Speed of the right side
    	CurrentHeading = Robot.drivetrain.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	HeadingError = CurrentHeading - Robot.drivetrain.getAngle();
    	
    	if (HeadingError > .5) {
    		RightSpeed = 0.8 * direction; //the speed for error correction (drifting) RAISE THIS IF IT DRIFTS
    	}
    	
    	else if (HeadingError < -.5) {
    		LeftSpeed = 0.8 * direction; //Error correction for right side  RAISE THIS IF IT DRIFTS
    	}
    	
    	else {
    		LeftSpeed = RightSpeed = 0.6 * direction; 
    	}
    	
    	//if (Robot.drivetrain.driveEncoder.getDistance() > (Distance + 3)) {
    	//	Robot.drivetrain.tankDrive(-RightSpeed, LeftSpeed);
    	//}
    	//else {
    		Robot.drivetrain.tank(LeftSpeed, -RightSpeed);
    	//}
    	//Robot.drivetrain.tankDrive(LeftSpeed, -RightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return Robot.drivetrain.isAtDistance(Distance);
    	return Robot.drivetrain.isAtDistance(Math.abs(Distance)); 
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.drivetrain.slowDown();
    	Robot.drivetrain.stopTank();
    	//Robot.drivetrain.turnAngleAdditional(-(Robot.drivetrain.getAngle()));
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}