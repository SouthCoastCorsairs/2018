package org.usfirst.frc.team5846.robot.subsystems;

import org.usfirst.frc.team5846.robot.RobotMap;
import org.usfirst.frc.team5846.robot.commands.Drive;
import org.usfirst.frc.team5846.util.SpeedOutput;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import jaci.pathfinder.Pathfinder;
//import jaci.pathfinder.Trajectory;
//import jaci.pathfinder.Waypoint;
//import jaci.pathfinder.followers.EncoderFollower;
//import jaci.pathfinder.modifiers.TankModifier;

/**
 *
 */
public class Drivetrain extends Subsystem {
	public double RightDistance1;
	public double LeftDistance1;
	
	private boolean isPIDInitialized;
	static final double kToleranceDegrees = 0.1f;
	
	//Talon SRX CAN Motor Controllers
	private WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.frontLeft);
	private WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.frontRight);
	private WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.backLeft);
	private WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.backRight);
	
	//public Ultrasonic us = new Ultrasonic(1,1);
	
	PIDController drivePID;
	
	//Encoders Set Here
	public Encoder LeftEncoder = new Encoder(RobotMap.DriveEncoderLeftA, RobotMap.DriveEncoderLeftB);
	public Encoder RightEncoder = new Encoder(RobotMap.DriveEncoderRightA, RobotMap.DriveEncoderRightB);
	
	//Gyro
	public final AHRS ahrs = new AHRS(SPI.Port.kMXP);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static final double KP = .000;//.0025;
	public static final double KI = 0;//.0015;
	public static final double KD = 0.00;
	public static final double KF = 0;
	
	public static final double mv = 0;
	public static final double ma = 0;
	public static final double mj = 0;
	public static final double dt = 0;
	
	public static final double wheel_base = 0;

    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
//    public Drivetrain() {
//    	frontLeft.configPeakOutputForward(1, 0);
//    	frontLeft.configPeakOutputReverse(-1, 0);
//    	frontLeft.configNominalOutputForward(0, 0);
//    	frontLeft.configNominalOutputReverse(0, 0);
//    	
//    	backLeft.configPeakOutputForward(1, 0);
//    	backLeft.configPeakOutputReverse(-1, 0);
//    	backLeft.configNominalOutputForward(0, 0);
//    	backLeft.configNominalOutputReverse(0, 0);
//    	
//    	frontRight.configPeakOutputForward(1, 0);
//    	frontRight.configPeakOutputReverse(-1, 0);
//    	frontRight.configNominalOutputForward(0, 0);
//    	frontRight.configNominalOutputReverse(0, 0);
//    	
//    	backRight.configPeakOutputForward(1, 0);
//    	backRight.configPeakOutputReverse(-1, 0);
//    	backRight.configNominalOutputForward(0, 0);
//    	backRight.configNominalOutputReverse(0, 0);
    	
    	//frontLeft.configSelectedFeedbackSensor(FeedbackDevice., arg1, arg2)
   // }
    
    //All Left Side Drivetrain Motors 
    SpeedControllerGroup Left = new SpeedControllerGroup(frontLeft, backLeft);
    
    //All Right Side Drivetrain Motors
    SpeedControllerGroup Right = new SpeedControllerGroup(frontRight, backRight);
    
    //Alternate Drive Method
    //public DifferentialDrive Ddrive = new DifferentialDrive(Left, Right);
    
    public SpeedOutput turnTestOutput = new SpeedOutput();
    public PIDController turnTest = new PIDController(0.015, 0, 0.05, ahrs, turnTestOutput); //.009
    
    public void turnTestInit(double target) {
    	turnTest.setInputRange(-180,  180);
    	turnTest.setOutputRange(-1,  1);
    	turnTest.setAbsoluteTolerance(2);
    	turnTest.setContinuous(true);
    	turnTest.setSetpoint(target);
    	turnTest.enable();
    }
    
    public void turnTestExecute() {
    	double speed = turnTestOutput.getSpeed() * -1;
    	tank(speed, speed);
    	SmartDashboard.putNumber("turn speed", speed);
    }
    
    
    
    //Primary Drive Method
    public void drive(double turn, double forward) {
    	turn *= .90;
    	//Set with Parameters of Turn and Forward, Which are Set in Drive Command
    	Left.set((-forward) + turn);
    	Right.set(forward + turn);
    }
    
//    public EncoderFollower[] pathSetup(Waypoint[] path) {
//    	EncoderFollower left = new EncoderFollower();
//    	EncoderFollower right = new EncoderFollower();
//    	
//    	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_QUINTIC, 
//    			Trajectory.Config.SAMPLES_HIGH, 
//    			dt, mv, ma, mj);
//    	
//    	String pathHash = String.valueOf(path.hashCode());
//    	
//    	SmartDashboard.putString("Path Hash", pathHash);
//    	
//    	Trajectory trajectory = Pathfinder.generate(path, config);
//    
//    	TankModifier modifier = new TankModifier(trajectory).modify(wheel_base);
//    	
//    	left = new EncoderFollower(modifier.getLeftTrajectory());
//    	right = new EncoderFollower(modifier.getRightTrajectory());
//    	
//    	//left.configureEncoder(initial_position, ticks_per_revolution, wheel_diameter);
//    	
//    	return new EncoderFollower[] {
//    			left,
//    			right,
//    	};
   // }
    public void initPID(PIDOutput output) {
    	isPIDInitialized = true;
    	drivePID = new PIDController(KP, KI, KD, KF, ahrs, output);
        drivePID.setInputRange(-180.0f,  180.0f);
        drivePID.setOutputRange(-.4, .4);
        drivePID.setAbsoluteTolerance(kToleranceDegrees);
        drivePID.setContinuous(true);
        drivePID.enable();
    }
    
    public boolean onTarget() {
        return drivePID.onTarget();
    }
    
    public void pidSetPoint(float setpoint) {
        if (isPIDInitialized) {
            drivePID.setSetpoint(setpoint);
        }
    }
    
    public void freePID() {
        drivePID.disable();
        drivePID.free();
    }
    
//    public double ReadInches() {
//    	us.ping();
//    	return us.getRangeInches();
//    	
//    }
    
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
    	if(RightIN() >= distance && LeftIN() >= distance) {
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
    	if(Math.abs(getAngle()) >= Math.abs(angle)) {
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

