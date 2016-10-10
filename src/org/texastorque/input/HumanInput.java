package org.texastorque.input;

import org.texastorque.constants.Ports;
import org.texastorque.output.RobotOutput;
import org.texastorque.subsystem.FlyWheel;
import org.texastorque.torquelib.util.GenericController;
import org.texastorque.torquelib.util.TorqueToggle;

public class HumanInput extends Input {

	private static HumanInput instance;
	
	private GenericController driver;
	
	private TorqueToggle flyWheelUpshift;
	private TorqueToggle flyWheelDownshift;
	
	public HumanInput() {
		init();
	}
	
	public void init() {
		System.out.println("Initializing HumanInput...");
		driver = new GenericController(Ports.HI_DRIVER, 0.1);
		
		flyWheelDownshift = new TorqueToggle(false);
		flyWheelUpshift = new TorqueToggle(false);
	}
	
	public void update() {
		DB_SpeedLeft = -driver.getLeftYAxis() + driver.getRightXAxis();
		DB_SpeedRight = -driver.getLeftYAxis() - driver.getRightXAxis();

//		DEFINE: toggle to increment RPMS
		flyWheelUpshift.calc(driver.getYButton());
		if(flyWheelUpshift.get()) {
			System.out.println("SHIFTUP");
			FlyWheel.getInstance().upshift();
			flyWheelUpshift.set(false);
		}
		
		RobotOutput.getInstance().setKicker(driver.getBButton());
		
		flyWheelDownshift.calc(driver.getAButton());
		if(flyWheelDownshift.get()) {
			System.out.println("SHIFTDOWN");
			FlyWheel.getInstance().downshift();
			flyWheelDownshift.set(false);
		}
		
	}
	
	public static HumanInput getInstance() {
		return instance == null ? instance = new HumanInput() : instance;
	}
	
}
