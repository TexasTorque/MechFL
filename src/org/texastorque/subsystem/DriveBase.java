package org.texastorque.subsystem;

import org.texastorque.constants.Constants;
import org.texastorque.feedback.Feedback;
import org.texastorque.output.RobotOutput;
import org.texastorque.torquelib.controlLoop.TorquePV;
import org.texastorque.torquelib.controlLoop.TorqueTMP;
import org.texastorque.torquelib.util.TorqueMathUtil;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveBase extends Subsystem {

	/**
	 * KNOWN DRIVEBASE VARIABLES:
	 * - 4 Motors (2 to each side)
	 */
	
	private static DriveBase instance;
	
	private double leftSpeed;
	private double rightSpeed;
	
//	DEFINE: TMP variables
	
	private double leftPosition;
	private double rightPosition;
	
	private double leftVelocity;
	private double rightVelocity;
	
	private double leftAcceleration;
	private double rightAcceleration;
	
	private double targetPosition;
	private double targetVelocity;
	private double targetAcceleration;
	
	private double setpoint;
	private double previousSetpoint;
	
	private double previousTime;
	
	private TorqueTMP driveProfile;
	private TorquePV rightPV;
	private TorquePV leftPV;
	
	@Override
	public void initSystem() {
		driveProfile = new TorqueTMP(Constants.DB_TMPVELOCITY.getDouble(), Constants.DB_TMPACCELERATION.getDouble());
	}

	@Override
	public void runSystem() {
		
//		update the values of both sides of drive train on the robot.
//		by doing so, we are prepared in case of needing to update the TMP profile
		leftPosition = Feedback.getInstance().getDB_leftPosition();
		rightPosition = Feedback.getInstance().getDB_rightPosition();

		leftVelocity = Feedback.getInstance().getDB_leftVelocity();
		rightVelocity = Feedback.getInstance().getDB_rightVelocity();

		leftAcceleration = Feedback.getInstance().getDB_leftAcceleration();
		rightAcceleration = Feedback.getInstance().getDB_rightAcceleration();

		if(Constants.DB_DOTMP.getBoolean()) {
			setpoint = input.getDB_Setpoint();

//			if the setpoint has changed, we need to start a new TMP to calculate the motion path.
			if(setpoint != previousSetpoint) {
				previousSetpoint = setpoint;
				Feedback.getInstance().resetDriveEncoders();
				driveProfile.generateTrapezoid(setpoint, 0, 0);
			}
//			calculate the new deltaTime (change in time) since the previous TMP calculation
			double dt = Timer.getFPGATimestamp() - previousTime;
			
			targetPosition = driveProfile.getCurrentPosition();
			targetVelocity = driveProfile.getCurrentVelocity();
			targetAcceleration = driveProfile.getCurrentAcceleration();
			
//			
			driveProfile.calculateNextSituation(dt);

			leftSpeed = leftPV.calculate(driveProfile, leftPosition, leftVelocity);
			rightSpeed = rightPV.calculate(driveProfile, rightPosition, rightVelocity);
		} else {
			leftSpeed = input.getDB_SpeedLeft();
			rightSpeed = input.getDB_SpeedRight();
		}
	}

	@Override
	public void setOutput() {
		leftSpeed = TorqueMathUtil.constrain(leftSpeed , 1.0);
		rightSpeed = TorqueMathUtil.constrain(rightSpeed , 1.0);
		
		RobotOutput.getInstance().setDriveSpeeds(leftSpeed, rightSpeed);
	}
	
	public void smartDashboard() {
		SmartDashboard.putNumber("DB_MOTOR_LEFTSPEED", leftSpeed);
		SmartDashboard.putNumber("DB_MOTOR_RIGHTSPEED", rightSpeed);

		SmartDashboard.putNumber("DB_TMP_LEFTPOSITION", leftPosition);
		SmartDashboard.putNumber("DB_TMP_RIGHTPOSITION", rightPosition);

		SmartDashboard.putNumber("DB_TMP_LEFTVELOCITY", leftVelocity);
		SmartDashboard.putNumber("DB_TMP_RIGHTVELOCITY", rightVelocity);
		
		SmartDashboard.putNumber("DB_TMP_LEFTACCELERATION", leftAcceleration);
		SmartDashboard.putNumber("DB_TMP_RIGHTACCELERATION", rightAcceleration);
		
		SmartDashboard.putNumber("DB_TMP_SETPOINT", setpoint);
		SmartDashboard.putNumber("DB_TMP_PREVIOUSSETPOINT", previousSetpoint);
	}

	public static DriveBase getInstance() {
		return instance == null ? instance = new DriveBase() : instance;
	}
	
	public void teleopPeriodic() {
		runSystem();
	}

}
