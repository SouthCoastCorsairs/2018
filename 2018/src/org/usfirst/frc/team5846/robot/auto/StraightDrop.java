package org.usfirst.frc.team5846.robot.auto;

import org.usfirst.frc.team5846.robot.commands.DriveAuto;
import org.usfirst.frc.team5846.robot.commands.IntakeAuto;
import org.usfirst.frc.team5846.robot.commands.LiftAuto;
import org.usfirst.frc.team5846.robot.commands.PIDdrive;
import org.usfirst.frc.team5846.robot.commands.StraightDrive;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StraightDrop extends CommandGroup {

    public StraightDrop() {
    	//addSequential(new DriveToSurface(150));
    	//addSequential(new PIDdrive(140));
    	//addSequential(new LiftAuto(.5));
    	addSequential(new StraightDrive(130));
    	addSequential(new LiftAuto(.5));
    	addSequential(new IntakeAuto(1));
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
