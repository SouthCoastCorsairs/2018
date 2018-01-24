package org.usfirst.frc.team5846.robot.subsystems;

import org.usfirst.frc.team5846.robot.RobotMap;
import org.usfirst.frc.team5846.robot.commands.BoxCmd;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BoxHolder extends Subsystem {
	Victor lift = new Victor(RobotMap.liftMotor);
	Victor intakeLeft = new Victor(RobotMap.intakeLeft);
	Victor intakeRight = new Victor(RobotMap.intakeRight);
	
	
	public SpeedControllerGroup intake = new SpeedControllerGroup(intakeLeft, intakeRight);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new BoxCmd());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void lift(double speed) {
    	lift.set(speed);
    }
}

