package org.usfirst.frc.team5846.robot.subsystems;

import org.usfirst.frc.team5846.robot.Robot;
import org.usfirst.frc.team5846.robot.RobotMap;
import org.usfirst.frc.team5846.robot.commands.BoxCmd;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BoxHolder extends Subsystem {
	
	//Lift Motor
	Victor lift1 = new Victor(RobotMap.liftMotor1);
	Victor lift2 = new Victor(RobotMap.liftMotor2);
	
	//Two Intake Motors
	Victor intakeLeft = new Victor(RobotMap.intakeLeft);
	Victor intakeRight = new Victor(RobotMap.intakeRight);
	
	//Both Intake Motors Combined
	//public SpeedControllerGroup intake = new SpeedControllerGroup(intakeLeft, intakeRight);
	public SpeedControllerGroup intake = new SpeedControllerGroup(intakeLeft);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//Timer timer = new Timer();
	
    public void initDefaultCommand() {
    	setDefaultCommand(new BoxCmd());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void lift(double speed) {
    	//timer.start();
    	lift1.set(-speed);
    	lift2.set(speed);
    	//double t_currentt = timer.get();
    }
    
    public void intake(double speed) {
//    	if (Robot.oi.getA2()) {
    		intakeLeft.set(-speed); 
    		intakeRight.set(speed);
 //   	}
    	
  //  	else if (Robot.oi.getY2()) {
  //  		intakeLeft.set(.5);
  //      	intakeRight.set(-.5);//Unsure about the negatives
  //  	}
  //  	
  //  	else {
  //  		intakeLeft.set(0);
    		//intakeRight.set(0);
  //  	}
    	
    }
}

