package org.usfirst.frc.team5846.robot.subsystems;

import org.usfirst.frc.team5846.robot.commands.PneumaticsCmd;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Pneumatics extends Subsystem {
	public Compressor c = new Compressor(0);
	public DoubleSolenoid ds = new DoubleSolenoid(0,1);
	public boolean enabled = c.enabled();
	public boolean pressureSwitch = c.getPressureSwitchValue();//change ports
	public double current = c.getCompressorCurrent();
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    		setDefaultCommand(new PneumaticsCmd());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    } 
    
    public void setClosedLoopControl() {
    		c.setClosedLoopControl(true); //automatically turns on compressor to about 120PSI if pressure switch is closed
    }
    
    
   
    
    
}

