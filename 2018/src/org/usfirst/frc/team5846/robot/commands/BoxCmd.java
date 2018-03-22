package org.usfirst.frc.team5846.robot.commands;

import org.usfirst.frc.team5846.robot.Robot;
import org.usfirst.frc.team5846.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BoxCmd extends Command {
	//Scale of the Lift Speed
	private double scale = RobotMap.LiftSpeed;
	private double scale1 = RobotMap.IntakeSpeed;
	private double t_currentt=0;
	private double loop1=0;
	Timer timer = new Timer();
	public BoxCmd() {
    	requires(Robot.box);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	

    	//if((Robot.oi.getY2axis()<-.25))
    	{
    	//Lift Method
    		//loop1=1;
    	//	timer.start();
    		//if(loop1==1)
    			
    		//{Robot.box.lift((Robot.oi.getY2axis()*scale));
    		//}
    		//t_currentt = timer.get();
    	     // if (t_currentt>6)
    	      {
    		    //Robot.box.lift(scale);
    	 	    //t_currentt=0;
    	 	   // loop1=2;
    			}  		
    	      
    	}
    	//else if ((Robot.oi.getY2axis()>.25)) 
    	
    	Robot.box.lift((Robot.oi.getY2axis()*scale));
    	
    	Robot.box.intake((-Robot.oi.getRY2()*scale1));
    	//SmartDashboard.putNumber("lift time", t_currentt);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
