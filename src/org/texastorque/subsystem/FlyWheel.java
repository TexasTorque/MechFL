package org.texastorque.subsystem;

import org.texastorque.feedback.Feedback;
import org.texastorque.output.RobotOutput;
import org.texastorque.torquelib.controlLoop.BangBang;

public class FlyWheel extends Subsystem {

	private static FlyWheel instance;
	
//	DEFINE: control loops
	private BangBang flywheelControlRight;
	private BangBang flywheelControlLeft;
	
//	DEFINE: control variables
	private double flywheelSpeedRight;
	private double flywheelSpeedLeft;

	private double rpmsRight;
	private double rpmsLeft;

	private double targetRPMS;

	@Override
	public void initSystem() {
		flywheelControlLeft = new BangBang();
		flywheelControlRight = new BangBang();
	}

	@Override
	public void runSystem() {
		rpmsLeft = Feedback.getInstance().getFW_leftRPM();
		rpmsRight = Feedback.getInstance().getFW_rightRPM();

		flywheelSpeedLeft = flywheelControlLeft.calculate(rpmsLeft / targetRPMS);
		flywheelSpeedRight = flywheelControlRight.calculate(rpmsRight / targetRPMS);
		
	}

	@Override
	public void setOutput() {
		RobotOutput.getInstance().setFlyWheelSpeeds(flywheelSpeedLeft, flywheelSpeedRight);
	}

	@Override
	public void smartDashboard() {
	}
	
	public static FlyWheel getInstance() {
		return instance == null ? instance = new FlyWheel() : instance;
	}

}
