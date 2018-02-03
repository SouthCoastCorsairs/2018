package org.usfirst.frc.team5846.robot.subsystems;

import org.usfirst.frc.team5846.robot.Robot;
import org.usfirst.frc.team5846.robot.RobotMap;
import org.usfirst.frc.team5846.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
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
	
	private boolean isPIDInitialized;
	
	//Talon SRX CAN Motor Controllers
	private WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.frontLeft);
	private WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.frontRight);
	private WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.backLeft);
	private WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.backRight);
	
	PIDController pid;
	
	//Encoders Set Here
	public Encoder LeftEncoder = new Encoder(RobotMap.DriveEncoderLeftA, RobotMap.DriveEncoderLeftB);
	public Encoder RightEncoder = new Encoder(RobotMap.DriveEncoderRightA, RobotMap.DriveEncoderRightB);
	
	//Gyro
	public final AHRS ahrs = new AHRS(SPI.Port.kMXP);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static final double KP = .1;
	public static final double KI = 5;
	public static final double KD = 0;
	public static final double KF = 0;

    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    //All Left Side Drivetrain Motors 
    SpeedControllerGroup Left = new SpeedControllerGroup(frontLeft, backLeft);
    
    //All Right Side Drivetrain Motors
    SpeedControllerGroup Right = new SpeedControllerGroup(frontRight, backRight);
    
    //Alternate Drive Method
    //public DifferentialDrive Ddrive = new DifferentialDrive(Left, Right);
    
    //Primary Drive Method
    public void drive(double turn, double forward) {
    	//Set with Parameters of Turn and Forward, Which are Set in Drive Command
    	Left.set((-forward) + turn);
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
    
    public void initPIDController(PIDOutput output) {
    	isPIDInitialized = true;
    	pid = new PIDController(KP, KI, KD, KF, ahrs, output);
    	pid.setInputRange(-180.0f, 180.0f);
    	pid.setOutputRange(-.04, .04);
    	pid.setAbsoluteTolerance(5.0f);
    	pid.setContinuous(true);
    	pid.enable();
    }
    
    public void onTarget() {
    	pid.onTarget();
    }
    
    public void pidSetEnabled(boolean enabled) {
        if (enabled) {
            pid.enable();
        } else {
            pid.disable();
        }
    }
    
    public void pidSetPoint(float setpoint) {
        if (isPIDInitialized) {
            pid.setSetpoint(setpoint);
        }
    }
    
    public void freePID() {
        pid.disable();
        pid.free();
    }
    
    public boolean isMoving() {
    	return ahrs.isMoving();
    }
    
    public float getDisplacementX() {
    	return ahrs.getDisplacementX();
    }
    
    public float getDisplacementY() {
    	return ahrs.getDisplacementY();
    }
    
    public void resetDisplacement() {
    	ahrs.resetDisplacement();
    }
    
//    public void initEncoder() {
//		RightEncoder.setDistancePerPulse((getRightDistance()) / 360); 
//		LeftEncoder.setDistancePerPulse((getLeftDistance()) / 360);    
//    }
    
   // public double RightIN = (getRightDistance()*31.4)/360;
  //  public double LeftCM = (getLeftDistance()*31.4)/360;
    
    
    //Get Distance from Right Encoder in cm
    public double RightIN() {
    	return RightDistance1 = (getRightDistance()*18.84)/360; //Conversion from pulses to cm 
    }
    
    
    //Get Distance from Left Encoder in cm
    public double LeftIN() {
    	return LeftDistance1 = (getLeftDistance()*18.84)/360; //Conversion from pulses to cm
    }
   
    public void ResetEncoders() {
    		LeftEncoder.reset();
    		RightEncoder.reset();
    }
    
    
    //For Auto
    //Checks When the Robot is at a Given Distance
    public boolean isAtDistance(double distance) {
    	if(RightIN() > distance && LeftIN() > distance) {
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

