package org.texastorque.feedback;

import org.texastorque.constants.Ports;
import org.texastorque.torquelib.component.TorqueEncoder;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class Feedback {

	private static Feedback instance;
	
//	DEFINE: conversions
	
	private final double CONVERSION_DRIVEBASE = 0.0;
	
//	DEFINE: drivebase variables
	
	private double DB_leftPosition;
	private double DB_leftVelocity;
	private double DB_leftAcceleration;
	
	private double DB_rightPosition;
	private double DB_rightVelocity;
	private double DB_rightAcceleration;
	
//	DEFINE: encoders
	
//	DEFINE: DriveBase encoders
	private TorqueEncoder DB_leftEncoder;
	private TorqueEncoder DB_rightEncoder;
	
	public void init() {
		DB_leftEncoder = new TorqueEncoder(Ports.DB_LEFTENCODER_A, Ports.DB_LEFTENCODER_B, false, EncodingType.k4X);
		DB_rightEncoder = new TorqueEncoder(Ports.DB_RIGHTENCODER_B, Ports.DB_LEFTENCODER_B, false, EncodingType.k4X);
	}
	
	public void update() {
		DB_leftPosition = DB_leftEncoder.get() * CONVERSION_DRIVEBASE;
		DB_leftVelocity = DB_leftEncoder.getRate() * CONVERSION_DRIVEBASE;
		DB_leftAcceleration = DB_leftEncoder.getAcceleration() * CONVERSION_DRIVEBASE;

		DB_rightPosition = DB_rightEncoder.get() * CONVERSION_DRIVEBASE;
		DB_rightVelocity = DB_rightEncoder.getRate() * CONVERSION_DRIVEBASE;
		DB_rightAcceleration = DB_rightEncoder.getAcceleration() * CONVERSION_DRIVEBASE;
	}
	
	public double getDB_leftPosition() {
		return DB_leftPosition;
	}
	
	public double getDB_leftVelocity() {
		return DB_leftVelocity;
	}
	
	public double getDB_leftAcceleration() {
		return DB_leftAcceleration;
	}
	
	public double getDB_rightPosition() {
		return DB_rightPosition;
	}
	
	public double getDB_rightVelocity() {
		return DB_rightVelocity;
	}
	
	public double getDB_rightAcceleration() {
		return DB_rightAcceleration;
	}
	
	public void resetDriveEncoders() {
		DB_leftEncoder.reset();
		DB_rightEncoder.reset();
	}
	
	public static Feedback getInstance() {
		return instance == null ? instance = new Feedback() : instance;
	}
	
}
