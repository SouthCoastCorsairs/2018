package org.usfirst.frc.team5846.robot.auto;

import org.usfirst.frc.team5846.robot.commands.IntakeAuto;
import org.usfirst.frc.team5846.robot.commands.LiftAuto;
import org.usfirst.frc.team5846.robot.commands.StraightDrive;
import org.usfirst.frc.team5846.robot.commands.RawTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleToLeft extends CommandGroup {

    public MiddleToLeft() {
    	addSequential(new StraightDrive(40));
    	addSequential(new RawTurn(90), 4);
    	addSequential(new StraightDrive(95));
    	addSequential(new RawTurn(-90), 4);
    	addSequential(new StraightDrive(80), 2);
    	addSequential(new LiftAuto(.25));
    	addSequential(new IntakeAuto(.5));
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
