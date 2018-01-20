package org.usfirst.frc.team5846.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Motor controllers, solenoids, ultrasonics, etc.
	
	//Motor Controllers
	public static int frontLeft = 0;
	public static int frontRight = 2;
	public static int backLeft = 1;
	public static int backRight = 3;
	
	//Encoders in PWM Ports
	public static int DriveEncoderLeftA = 3;
	public static int DriveEncoderLeftB = 4;
	public static int DriveEncoderRightA = 2;
	public static int DriveEncoderRightB = 1;
	
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
