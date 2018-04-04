package org.usfirst.frc.team5846.util;

import org.usfirst.frc.team5846.robot.Robot;
import org.usfirst.frc.team5846.robot.commands.TurnAngle;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Navx extends Subsystem {
	static final double kP = 0.00125; //0.0012
	static final double kI = .00001; //0.0001
	static final double kD = 0.015; //0.015
	static final double kF = 0;
	
	double rotateToAngleRate;
	boolean rotateToAngle;
	
	private boolean isPIDInitialized;
	
	public final AHRS ahrs = new AHRS(SPI.Port.kMXP);
	
	public SpeedOutput turnSpeed = new SpeedOutput();
	double currentRotationRate;
	//Ultrasonic us = new Ultrasonic(null, null);
	
	static final double kToleranceDegrees = 3.0f;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
//    public void initPID(PIDOutput output) {
//    	isPIDInitialized = true;
//    	turnController = new PIDController(kP, kI, kD, kF, ahrs, output);
//        turnController.setInputRange(-180.0f,  180.0f);
//        turnController.setOutputRange(-0.4, 0.4);
//        turnController.setPercentTolerance(kToleranceDegrees);
//        turnController.setContinuous(true);
//        turnController.enable();
//    }
//    
    
    public PIDController turnController = new PIDController(kP, kI, kD, ahrs, turnSpeed);
    public boolean onTarget() {
        return turnController.onTarget();
    }
    
    public void pidSetPoint(float setpoint) {
        if (isPIDInitialized) {
            turnController.setSetpoint(setpoint);
        }
    }
    
    public void freePID() {
        turnController.disable();
        turnController.free();
    }
    
    public void pidSetEnabled(boolean enabled) {
        if (enabled) {
            turnController.enable();
        } else {
            turnController.disable();
        }
    }
    
//	@Override
//	public void pidWrite(double output) {
//		 rotateToAngleRate = output;
//		
//	}
    
}

