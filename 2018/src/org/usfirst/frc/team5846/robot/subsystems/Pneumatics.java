package org.usfirst.frc.team5846.robot.subsystems;

import org.usfirst.frc.team5846.robot.RobotMap;
import org.usfirst.frc.team5846.robot.commands.PneumaticsCmd;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Pneumatics extends Subsystem {
//	public Compressor c = new Compressor(0);
//	//public DoubleSolenoid ds = new DoubleSolenoid(0,1);
//	public Solenoid s1 = new Solenoid(RobotMap.Solenoid1);
//	public Solenoid s2 = new Solenoid(RobotMap.Solenoid2);
//	public boolean enabled = c.enabled();
//	public boolean pressureSwitch = c.getPressureSwitchValue();
//	public double current = c.getCompressorCurrent();

    public void initDefaultCommand() {
    	setDefaultCommand(new PneumaticsCmd());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    } 
    
    public void setClosedLoopControl() {
    	//Automatically Enables Compressor When the Robot is Enabled
    	//c.setClosedLoopControl(true);
    }
    
    
   
    
    
}

