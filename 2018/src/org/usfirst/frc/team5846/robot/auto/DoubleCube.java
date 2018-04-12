package org.usfirst.frc.team5846.robot.auto;

import org.usfirst.frc.team5846.robot.commands.DriveAuto;
import org.usfirst.frc.team5846.robot.commands.RaiseLift;
import org.usfirst.frc.team5846.robot.commands.IntakeAuto;
import org.usfirst.frc.team5846.robot.commands.InvertIntake;
import org.usfirst.frc.team5846.robot.commands.LiftAuto;
import org.usfirst.frc.team5846.robot.commands.StraightDrive;
import org.usfirst.frc.team5846.robot.commands.TurnAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoubleCube extends CommandGroup {

    public DoubleCube() {
    	addSequential(new StraightDrive(125), 4); 
    	addSequential(new LiftAuto(.25)); //SECONDS
    	addSequential(new IntakeAuto(.5));
    	addSequential(new RaiseLift(.5));
    	addSequential(new TurnAngle(-90), 2);
    	addSequential(new LiftAuto(.5)); //SECONDS
    	addSequential(new StraightDrive(25), 3); //INCHES
    	addSequential(new InvertIntake(1));
    	addSequential(new DriveAuto(1.5, true)); //DRIVE BACKWARD IN SECONDS
    	addSequential(new TurnAngle(90), 2);
    	addSequential(new LiftAuto(.25)); //SECONDS
    	addSequential(new IntakeAuto(.5));//SECONDS
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
