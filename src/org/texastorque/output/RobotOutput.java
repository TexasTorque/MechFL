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

	private TorqueMotor KR_sole;
	
	public RobotOutput() {
		init();
	}
	
	/**
	 * Defines all motors that will be on the robot
	 * @author Glen Lauritsen
	 */
	public void init() {
		System.out.println("Initializing RobotOutput...");
		boolean typical = false;
	
		DB_leftBack = new TorqueMotor(new VictorSP(Ports.DB_LEFTBACK), !typical);
		DB_leftFore = new TorqueMotor(new VictorSP(Ports.DB_LEFTFORE), !typical);
		DB_rightBack = new TorqueMotor(new VictorSP(Ports.DB_RIGHTBACK), typical);
		DB_rightFore = new TorqueMotor(new VictorSP(Ports.DB_RIGHTFORE), typical);
		
		FW_leftBack = new TorqueMotor(new VictorSP(Ports.FW_LEFTBACK), typical);
		FW_leftFore = new TorqueMotor(new VictorSP(Ports.FW_LEFTFORE), typical);
		FW_rightBack = new TorqueMotor(new VictorSP(Ports.FW_RIGHTBACK), !typical);
		FW_rightFore = new TorqueMotor(new VictorSP(Ports.FW_RIGHTFORE), !typical);
		
		KR_sole = new TorqueMotor(new VictorSP(Ports.KR_SOLE), typical);
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
	
	public void setKicker(boolean run) {
		if(run)
			KR_sole.set(-1.0);
		else
			KR_sole.set(0.0);
	}
	
	public void setOutputs() {
		
	}
	
	public static RobotOutput getInstance() {
		return instance == null ? instance = new RobotOutput() : instance;
	}
	
}
