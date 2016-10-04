package org.texastorque.subsystem;

import org.texastorque.feedback.Feedback;
import org.texastorque.output.RobotOutput;
import org.texastorque.torquelib.controlLoop.BangBang;
import org.texastorque.torquelib.util.TorqueMathUtil;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		flywheelSpeedLeft = TorqueMathUtil.constrain(flywheelSpeedLeft, 1.0);
		flywheelSpeedRight = TorqueMathUtil.constrain(flywheelSpeedRight, 1.0);
		RobotOutput.getInstance().setFlyWheelSpeeds(flywheelSpeedLeft, flywheelSpeedRight);
	}

	@Override
	public void smartDashboard() {
		SmartDashboard.putNumber("FW_MOTOR_SPEEDLEFT", flywheelSpeedLeft);
		SmartDashboard.putNumber("FW_MOTOR_SPEEDRIGHT", flywheelSpeedRight);
		
		SmartDashboard.putNumber("FW_RPMSLEFT", rpmsLeft);
		SmartDashboard.putNumber("FW_RPMSRIGHT", rpmsRight);
	}
	
	public static FlyWheel getInstance() {
		return instance == null ? instance = new FlyWheel() : instance;
	}

}
