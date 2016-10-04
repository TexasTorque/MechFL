package org.texastorque.output;

import org.texastorque.constants.Ports;
import org.texastorque.torquelib.component.TorqueMotor;

import edu.wpi.first.wpilibj.VictorSP;
/**
 * This class manages all of the outputs to motors on the robot.
 * @author Glen Lauritsen
 *
 */
public class RobotOutput {

	private static RobotOutput instance;
	
//	TODO: define all DriveBase variables
	
	private TorqueMotor DB_leftBack;
	private TorqueMotor DB_leftFore;
	
	private TorqueMotor DB_rightBack;
	private TorqueMotor DB_rightFore;
	
//	TODO: define all FlyWheel variables
	
	private TorqueMotor FW_leftBack;
	private TorqueMotor FW_leftFore;
	
	private TorqueMotor FW_rightBack;
	private TorqueMotor FW_rightFore;

	/**
	 * Defines all motors that will be on the robot
	 * @author Glen Lauritsen
	 */
	public RobotOutput() {
		boolean reversed = true;
	
		DB_leftBack = new TorqueMotor(new VictorSP(Ports.DB_LEFTBACK), !reversed);
		DB_leftFore = new TorqueMotor(new VictorSP(Ports.DB_LEFTFORE), !reversed);
		DB_rightBack = new TorqueMotor(new VictorSP(Ports.DB_RIGHTBACK), reversed);
		DB_rightFore = new TorqueMotor(new VictorSP(Ports.DB_RIGHTFORE), reversed);
		
		FW_leftBack = new TorqueMotor(new VictorSP(Ports.FW_LEFTBACK), reversed);
		FW_leftFore = new TorqueMotor(new VictorSP(Ports.FW_LEFTFORE), reversed);
		FW_rightBack = new TorqueMotor(new VictorSP(Ports.FW_RIGHTBACK), reversed);
		FW_rightFore = new TorqueMotor(new VictorSP(Ports.FW_RIGHTFORE), reversed);
		
	}
	
	public void setDriveSpeeds(double left, double right) {
		DB_leftBack.set(left);
		DB_leftFore.set(left);

		DB_rightBack.set(right);
		DB_rightFore.set(right);
	}
	
	public void setFlyWheelSpeeds(double leftSpeed, double rightSpeed) {
		FW_leftBack.set(leftSpeed);
		FW_leftFore.set(leftSpeed);

		FW_rightBack.set(rightSpeed);
		FW_rightFore.set(rightSpeed);
	}
	
	public void setOutputs() {
		
	}
	
	public static RobotOutput getInstance() {
		return instance == null ? instance = new RobotOutput() : instance;
	}
	
}
