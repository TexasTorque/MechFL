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

	private double targetRPMS = 0;
	
	private double previousCap = .2;
	
	private final double FW_SHIFTBY = 100;

	@Override
	public void initSystem() {
		flywheelControlLeft = new BangBang();
		flywheelControlRight = new BangBang();
	}

	@Override
	public void runSystem() {
		if(targetRPMS == 0) {
			flywheelControlLeft.setSetpoint(targetRPMS);
			flywheelControlRight.setSetpoint(targetRPMS);
		} else {
			flywheelControlLeft.setSetpoint(targetRPMS+100);
			flywheelControlRight.setSetpoint(targetRPMS-100);
		}
		rpmsLeft = Feedback.getInstance().getFW_leftRPM();
		rpmsRight = Feedback.getInstance().getFW_rightRPM();

		flywheelSpeedLeft = flywheelControlLeft.calculate(rpmsLeft);
		flywheelSpeedRight = flywheelControlRight.calculate(rpmsRight);
		
		setOutput();
	}

	@Override
	public void setOutput() {
		double maxRPMS = 1500;
//		double currentCap = previousCap;
		
		double motorCapL = 1 - .8 * ((maxRPMS - rpmsLeft) / maxRPMS);
		double motorCapR = 1 - .8 * ((maxRPMS - rpmsRight) / maxRPMS);
		
//		if(Math.min(flywheelSpeedLeft, flywheelSpeedRight) >= currentCap ) {
//			currentCap += .2;
//		}
//		
//		previousCap = currentCap;
//		currentCap = motorCap;
		
		if(motorCapL > 1.0)
			motorCapL = 1.0;
		
		if(motorCapR > 1.0)
			motorCapR = 1.0;
		
		flywheelSpeedLeft = TorqueMathUtil.constrain(flywheelSpeedLeft, motorCapL);
		flywheelSpeedRight = TorqueMathUtil.constrain(flywheelSpeedRight, motorCapR);
		
		RobotOutput.getInstance().setFlyWheelSpeeds(flywheelSpeedLeft, flywheelSpeedRight);
	}

	@Override
	public void smartDashboard() {
		SmartDashboard.putNumber("FW_MOTOR_SPEEDLEFT", flywheelSpeedLeft);
		SmartDashboard.putNumber("FW_MOTOR_SPEEDRIGHT", flywheelSpeedRight);
		
		SmartDashboard.putNumber("FW_RPMSLEFT", rpmsLeft);
		SmartDashboard.putNumber("FW_RPMSRIGHT", rpmsRight);
		
		SmartDashboard.putNumber("FW_TARGETRPMS", targetRPMS);
	}
	
	public void upshift() {
		targetRPMS += FW_SHIFTBY;
	}
	
	public void downshift() {
		if(targetRPMS >= 0 && targetRPMS - 100 >= 0)
			targetRPMS -= FW_SHIFTBY;
	}
	
	public static FlyWheel getInstance() {
		return instance == null ? instance = new FlyWheel() : instance;
	}
	
	public void teleopPeriodic() {
		runSystem();
	}

}
