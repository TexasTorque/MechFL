package org.texastorque.subsystem;

import org.texastorque.output.RobotOutput;
import org.texastorque.torquelib.util.TorqueMathUtil;

public class DriveBase extends Subsystem {

	/**
	 * KNOWN DRIVEBASE VARIABLES:
	 * - 4 Motors (2 to each side)
	 */
	
	private static DriveBase instance;
	
	private double leftSpeed;
	private double rightSpeed;
	
	@Override
	public void initSystem() {
		// TODO: implement initialization code
	}

	@Override
	public void runSystem() {
		leftSpeed = input.getDB_SpeedLeft();
		rightSpeed = input.getDB_SpeedRight();
	}

	@Override
	public void setOutput() {
		leftSpeed = TorqueMathUtil.constrain(leftSpeed , 1.0);
		rightSpeed = TorqueMathUtil.constrain(rightSpeed , 1.0);
		
		RobotOutput.getInstance().setDriveSpeeds(leftSpeed, rightSpeed);
	}
	
	public void smartDashboard() {
	}

	public static DriveBase getInstance() {
		return instance == null ? instance = new DriveBase() : instance;
	}

}
