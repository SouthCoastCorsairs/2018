package org.usfirst.frc.team5846.robot.subsystems;

import org.usfirst.frc.team5846.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climb extends Subsystem {
	
	Victor climb1 = new Victor(RobotMap.climb1);
	Victor climb2 = new Victor(RobotMap.climb2);
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void climb(double speed) {
    	climb1.set(speed);
    	climb2.set(speed);
    }
}

