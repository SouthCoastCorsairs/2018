package org.usfirst.frc.team5846.robot.subsystems;

import org.usfirst.frc.team5846.robot.Robot;
import org.usfirst.frc.team5846.robot.RobotMap;
import org.usfirst.frc.team5846.robot.commands.Drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
	public double RightDistance1;
	public double LeftDistance1;
	
	
	private Talon frontLeft = new Talon(RobotMap.frontLeft);
	private Talon frontRight = new Talon(RobotMap.frontRight);
	private Talon backLeft = new Talon(RobotMap.backLeft);
	private Talon backRight = new Talon(RobotMap.backRight);
	public Encoder LeftEncoder = new Encoder(RobotMap.DriveEncoderLeftA, RobotMap.DriveEncoderLeftB);
	public Encoder RightEncoder = new Encoder(RobotMap.DriveEncoderRightA, RobotMap.DriveEncoderRightB);
	
	public final AHRS ahrs = new AHRS(SPI.Port.kMXP);
	//Gyro
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void drive(double turn, double foward) {
    		frontLeft.set(-foward + turn);
    		frontRight.set(foward + turn);
    		backLeft.set(-foward + turn);
   		backRight.set(foward + turn);
    }
    
    public double getAngle() {
    		return ahrs.getAngle();
    }
    
    
    public double getRightDistance() {
    		return RightEncoder.getDistance();
    }
    
    public double getLeftDistance() {
    		return LeftEncoder.getDistance();
    }
    
    public void initEncoder() {
		RightEncoder.setDistancePerPulse((getRightDistance()) / 360); 
		LeftEncoder.setDistancePerPulse((getLeftDistance()) / 360);    
    }
    
   // public double RightCM = (getRightDistance()*31.4)/360;
  //  public double LeftCM = (getLeftDistance()*31.4)/360;
    
    public double RightCM() {
    	return RightDistance1 = (getRightDistance()*31.4)/360;
    }
    
    public double LeftCM() {
    	return LeftDistance1 = (getLeftDistance()*31.4)/360;
    }
   
    public void ResetEncoders() {
    		LeftEncoder.reset();
    		RightEncoder.reset();
    }
    
    public boolean isAtDistance(double distance) {
    	if(RightCM() > distance && LeftCM() > distance) {
    		stopTank();
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public boolean isAtAngle(double angle) {
    	if(getAngle() > Math.abs(angle)) {
    		stopTank();
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public void ResetGyro() {
    	ahrs.reset();
    }
   
    
    public void tank(double left, double right) {
    		frontLeft.set(left);
    		backLeft.set(left);
    		frontRight.set(right);
    		backRight.set(right);
    }
    
    public void stopTank() {
    	this.tank(0, 0);
    }
}

