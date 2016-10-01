package org.texastorque.output;

import org.texastorque.constants.Ports;
import org.texastorque.torquelib.component.TorqueMotor;

import edu.wpi.first.wpilibj.VictorSP;

public class RobotOutput {

	private TorqueMotor DB_leftBack;
	private TorqueMotor DB_leftFore;
	
	private TorqueMotor DB_rightBack;
	private TorqueMotor DB_rightFore;
	
	public RobotOutput() {
		boolean reversed = true;
		
		DB_leftBack = new TorqueMotor(new VictorSP(Ports.DB_LEFTBACK), !reversed);
		DB_leftFore = new TorqueMotor(new VictorSP(Ports.DB_LEFTFORE), !reversed);

		DB_rightBack = new TorqueMotor(new VictorSP(Ports.DB_RIGHTBACK), reversed);
		DB_rightFore = new TorqueMotor(new VictorSP(Ports.DB_RIGHTFORE), reversed);
	}
	
	public void setDriveSpeeds(double left, double right) {
		DB_leftBack.set(left);
		DB_leftFore.set(left);

		DB_rightBack.set(right);
		DB_rightFore.set(right);
	}
	
	public void setOutputs() {
		
	}
	
}
