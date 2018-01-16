package org.usfirst.frc.team5846.robot.subsystems;

import org.usfirst.frc.team5846.robot.RobotMap;
import org.usfirst.frc.team5846.robot.commands.Drive;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
	double Distance = 31.4;
	public double pulseperrotation=0;
	private Victor frontLeft = new Victor(RobotMap.frontLeft);
	private Victor frontRight = new Victor(RobotMap.frontRight);
	private Victor backLeft = new Victor(RobotMap.backLeft);
	private Victor backRight = new Victor(RobotMap.backRight);
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
    
    public void initEncoder() {
    		RightEncoder.setDistancePerPulse(6*Math.PI / 360); 
    		LeftEncoder.setDistancePerPulse(6*Math.PI / 360);
    }
    
    
    public double getRightDistance() {
    		return RightEncoder.getDistance();
    }
    
    public double getLeftDistance() {
    		return LeftEncoder.getDistance();
    }
    public double printEncoder() {
    	
    if (getLeftDistance() == Distance) {
    		pulseperrotation = LeftEncoder.getDistancePerPulse();
    }
    return pulseperrotation;
    
    }
    
    public void ResetEncoders() {
    		LeftEncoder.reset();
    		RightEncoder.reset();
    }
   
    
    public void tank(double left, double right) {
    		frontLeft.set(left);
    		backLeft.set(left);
    		frontRight.set(right);
    		backRight.set(right);
    }
}

