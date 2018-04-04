package org.usfirst.frc.team5846.util;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class SpeedOutput implements PIDOutput{

	double speed;
	
	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		speed = output;
	}

	public double getSpeed() {
		return speed;
	}
	
}