package org.texastorque.subsystem;

import org.texastorque.output.RobotOutput;

public class FlyWheel extends Subsystem{

	private double flywheelSpeed;
	
	@Override
	public void initSystem() {
	}

	@Override
	public void runSystem() {
	}

	@Override
	public void setOutput() {
		RobotOutput.getInstance().setFlyWheelSpeeds(flywheelSpeed);
	}
	
	@Override
	public void smartDashboard() {
	}

}
