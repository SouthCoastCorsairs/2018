
package org.usfirst.frc.team5846.robot;

import org.usfirst.frc.team5846.robot.commands.Auto;
import org.usfirst.frc.team5846.robot.commands.DriveStraight;
import org.usfirst.frc.team5846.robot.subsystems.BoxHolder;
import org.usfirst.frc.team5846.robot.subsystems.Drivetrain;
import org.usfirst.frc.team5846.robot.subsystems.Pneumatics;
import org.usfirst.frc.team5846.robot.commands.DriveStraight;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//Linking Classes
	public static OI oi;
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Pneumatics pt = new Pneumatics();
	public static final BoxHolder box = new BoxHolder();

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		//chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		
		chooser.addDefault("Auto", new Auto());
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();
		
		Robot.drivetrain.ResetEncoders();
		
		String gameData;
		int gameStation;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		gameStation = DriverStation.getInstance().getLocation();
		
		
		//Auto chooser based on randomized lights and station number
		//if (first letter) and (station number) = true, then run corresponding auto
		/*if (gameData.charAt(0) == 'L' && gameStation == 1 ) {
			//STATION 1 LEFT AUTO
		}
		else if (gameData.charAt(0) == 'R' && gameStation == 1) {
			//STATION 1 RIGHT AUTO
		}
		else if (gameData.charAt(0) == 'L' && gameStation == 2) {
			//STATION 2 LEFT AUTO
		}
		else if (gameData.charAt(0) == 'R' && gameStation == 2) {
			//STATION 2 RIGHT AUTO
		}
		else if (gameData.charAt(0) == 'L' && gameStation == 3) {
			//STATION 3 LEFT AUTO
		}
		else if (gameData.charAt(0) == 'R' && gameStation == 3) {
			//STATION 3 RIGHT AUTO
		}*/
		

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
		SmartDashboard.putNumber("Angle", Robot.drivetrain.getAngle());
		SmartDashboard.putNumber("Left Distance", Robot.drivetrain.getLeftDistance());
		SmartDashboard.putNumber("Right Distance", Robot.drivetrain.getRightDistance());
		SmartDashboard.putBoolean("Compressor Status", Robot.pt.enabled);
		SmartDashboard.putBoolean("Pressure Switch Status", Robot.pt.pressureSwitch);
		SmartDashboard.putNumber("Compressor Current", Robot.pt.current);
		//SmartDashboard.putNumber("Right Encoder (rotation)", Robot.drivetrain.getRightDistance()/360);
		//SmartDashboard.putNumber("Right Encoder (cm)", (Robot.drivetrain.getRightDistance()*31.4)/360);
		SmartDashboard.putNumber("Right Encoder (cm)", Robot.drivetrain.RightIN());
		SmartDashboard.putNumber("Left Encoder (cm)", Robot.drivetrain.LeftIN());
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Robot.drivetrain.ResetEncoders();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//Printing stuff to SmartDashboard during teleop
		SmartDashboard.putNumber("Angle", Robot.drivetrain.getAngle());
		SmartDashboard.putNumber("Left Distance", Robot.drivetrain.getLeftDistance());
		SmartDashboard.putNumber("Right Distance", Robot.drivetrain.getRightDistance());
		SmartDashboard.putBoolean("Compressor Status", Robot.pt.enabled);
		SmartDashboard.putBoolean("Pressure Switch Status", Robot.pt.pressureSwitch);
		SmartDashboard.putNumber("Compressor Current", Robot.pt.current);
		//SmartDashboard.putNumber("Right Encoder (rotation)", Robot.drivetrain.getRightDistance()/360);
		//SmartDashboard.putNumber("Right Encoder (cm)", (Robot.drivetrain.getRightDistance()*31.4)/360);
		SmartDashboard.putNumber("Right Encoder (cm)", Robot.drivetrain.RightIN());
		SmartDashboard.putNumber("Left Encoder (cm)", Robot.drivetrain.LeftIN());
		
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
