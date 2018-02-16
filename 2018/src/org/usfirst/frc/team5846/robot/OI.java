package org.usfirst.frc.team5846.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//Joysticks, buttons, triggers, axis, etc.
	
	
	//Defining Joystick in Port 0
	Joystick driver = new Joystick(0);
	Joystick manipulator = new Joystick(1);
	
	
	//Get the Axis of the Joystick
	//X Axis is 0
	//Y Axis is 1
	public double getXaxis() {
		return driver.getRawAxis(0);
	}
	
	public double getX2axis() {
		return manipulator.getRawAxis(0);
	}
	
	public double getYaxis() {
		return driver.getRawAxis(1);
	}	
	
	public double getY2axis() {
		return manipulator.getRawAxis(1);
	}
	
	public double getRY() {
		return driver.getRawAxis(5);
	}
	
	public double getRY2() {
		return manipulator.getRawAxis(5);
	}
	
	//Get When the Buttons are Pressed
	//Numbers for Buttons are Given in DS
	public boolean getA() {
		return driver.getRawButton(1);
	}
	
	public boolean getA2() {
		return manipulator.getRawButton(1);
	}
	
	public boolean getB() {
		return driver.getRawButton(2);
	}
	
	public boolean getX() {
		return driver.getRawButton(3);
	}
	
	public boolean getY() {
		return driver.getRawButton(4);
	}
	
	public boolean getY2() {
		return manipulator.getRawButton(4);
	}
	
	public boolean getLB() {
		return driver.getRawButton(5);
	}
	
	public boolean getLB2() {
		return manipulator.getRawButton(5);
	}
	
	public boolean getRB() {
		return driver.getRawButton(6);
	}
	
	public boolean getRB2() {
		return manipulator.getRawButton(6);
	}
	
	public boolean getLstick() {
		return driver.getRawButton(9);
	}
	
	public boolean getRstick() {
		return driver.getRawButton(10);
	}
	
	public boolean getStart() {
		return driver.getRawButton(8);
	}
	
	//Triggers, Which Return as an Axis
	public double getLT() {
		return manipulator.getRawAxis(2);
	}
	
	public double getLT2() {
		return manipulator.getRawAxis(2);
	}
	
	public double getRT() {
		return manipulator.getRawAxis(3);
	}
	
	public double getRT2() {
		return manipulator.getRawAxis(3);
	}
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
