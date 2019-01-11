package org.usfirst.frc.team5846.robot.auto;

import org.usfirst.frc.team5846.robot.commands.DriveAuto;
import org.usfirst.frc.team5846.robot.commands.IntakeAuto;
import org.usfirst.frc.team5846.robot.commands.InvertIntake;
import org.usfirst.frc.team5846.robot.commands.LiftAuto;
import org.usfirst.frc.team5846.robot.commands.PIDdrive;
import org.usfirst.frc.team5846.robot.commands.RaiseLift;
import org.usfirst.frc.team5846.robot.commands.StraightDrive;
import org.usfirst.frc.team5846.robot.commands.TurnAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StraightDrop extends CommandGroup {

    public StraightDrop() {
    	//addSequential(new DriveToSurface(150));
    	//addSequential(new PIDdrive(140));
    	//addSequential(new LiftAuto(.5));
    	addSequential(new StraightDrive(125, false), 1.6);
    	addSequential(new LiftAuto(.35));
    	addSequential(new IntakeAuto(.5));
    	addSequential(new RaiseLift(.5));
    	addSequential(new DriveAuto(1.6, true));
//    	addSequential(new StraightDrive(20, true));
    	addSequential(new TurnAngle(-60), 1.5);
    	addSequential(new LiftAuto(.4)); //SECONDS
    	addSequential(new StraightDrive(42, false), 4); //INCHES
    	addParallel(new InvertIntake(4));
    	addSequential(new DriveAuto(1.6, true)); //DRIVE BACKWARD IN SECONDS
//    	addSequential(new StraightDrive(20, true));
    	addParallel(new InvertIntake(.5));
    	addSequential(new TurnAngle(60), 3);
    	addSequential(new RaiseLift(.5));
    	addSequential(new StraightDrive(60, false), 1.7);
    	addSequential(new LiftAuto(.25)); //SECONDS
    	addSequential(new IntakeAuto(.5));//SECONDS
    	//addSequential(new LiftAuto(1));
    	//addSequential(new StraightDrive(50), 4);
    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
