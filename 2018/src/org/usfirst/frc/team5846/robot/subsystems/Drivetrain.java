package org.usfirst.frc.team5846.robot.subsystems;

import org.usfirst.frc.team5846.robot.Robot;
import org.usfirst.frc.team5846.robot.RobotMap;
import org.usfirst.frc.team5846.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.*;

/**
 *
 */
public class Drivetrain extends Subsystem {
	public double RightDistance1;
	public double LeftDistance1;
	
	private WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.frontLeft);
	private WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.frontRight);
	private WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.backLeft);
	private WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.backRight);
	public Encoder LeftEncoder = new Encoder(RobotMap.DriveEncoderLeftA, RobotMap.DriveEncoderLeftB);
	public Encoder RightEncoder = new Encoder(RobotMap.DriveEncoderRightA, RobotMap.DriveEncoderRightB);
	
	public final AHRS ahrs = new AHRS(SPI.Port.kMXP); //Gyro
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    SpeedControllerGroup Left = new SpeedControllerGroup(frontLeft, backLeft);
    
    SpeedControllerGroup Right = new SpeedControllerGroup(frontRight, backRight);
    
    public DifferentialDrive Ddrive = new DifferentialDrive(Left, Right);
    
    //Drive Method
    public void drive(double turn, double forward) {
    	Left.set(-forward + turn);
    	Right.set(forward + turn);
    }
    
    
    
    
    //Get Angle Method
    public double getAngle() {
    	return ahrs.getAngle();
    }
    
    
    //Get Distance from Right Encoder in Pulses
    public double getRightDistance() {
    	return RightEncoder.getDistance();
    }
    
    
    //Get Distance from Left Encoder in Pulses
    public double getLeftDistance() {
    	return LeftEncoder.getDistance();
    }
    
    
//    public void initEncoder() {
//		RightEncoder.setDistancePerPulse((getRightDistance()) / 360); 
//		LeftEncoder.setDistancePerPulse((getLeftDistance()) / 360);    
//    }
    
   // public double RightCM = (getRightDistance()*31.4)/360;
  //  public double LeftCM = (getLeftDistance()*31.4)/360;
    
    
    //Get Distance from Right Encoder in cm
    public double RightCM() {
    	return RightDistance1 = (getRightDistance()*31.4)/360; //Conversion from pulses to cm 
    }
    
    
    //Get Distance from Left Encoder in cm
    public double LeftCM() {
    	return LeftDistance1 = (getLeftDistance()*31.4)/360; //Conversion from pulses to cm
    }
   
    public void ResetEncoders() {
    		LeftEncoder.reset();
    		RightEncoder.reset();
    }
    
    
    //For Auto
    //Checks When the Robot is at a Given Distance
    public boolean isAtDistance(double distance) {
    	if(RightCM() > distance && LeftCM() > distance) {
    		stopTank();
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    
    //For Auto
    //Checks When the Robot is at a Given Angle
    public boolean isAtAngle(double angle) {
    	if(getAngle() > Math.abs(angle)) {
    		stopTank();
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    
    //Reset Gyro Method
    public void ResetGyro() {
    	ahrs.reset();
    }
   
    
    //For Auto
    //Tank Drive System
    public void tank(double left, double right) {
    	Left.set(left);
    	Right.set(right);
    }
    
    
    //For Auto
    //Stop Tank Method
    public void stopTank() {
    	this.tank(0, 0);
    }
}

