package org.texastorque.input;

import org.texastorque.constants.Ports;
import org.texastorque.subsystem.FlyWheel;
import org.texastorque.torquelib.util.GenericController;
import org.texastorque.torquelib.util.TorqueToggle;

public class HumanInput extends Input {

	private static HumanInput instance;
	
	private GenericController driver;
	
	private TorqueToggle flyWheelUpshift;
	private TorqueToggle flyWheelDownshift;
	
	public void init() {
		driver = new GenericController(Ports.HI_DRIVER, 0.1);
		
		flyWheelUpshift = new TorqueToggle(false);
	}
	
	public void update() {

		DB_SpeedLeft = -driver.getLeftYAxis() + driver.getRightXAxis();
		DB_SpeedRight = -driver.getLeftYAxis() - driver.getRightXAxis();

//		DEFINE: toggle to increment RPMS
		flyWheelUpshift.calc(driver.getYButton());
		if(flyWheelUpshift.get()) {
			FlyWheel.getInstance().upshift();
		}
		
		flyWheelDownshift.calc(driver.getAButton());
		if(flyWheelDownshift.get()) {
			FlyWheel.getInstance().downshift();
		}
		
//		TODO: methodology for setting flywheel speeds
		
		
	}
	
	public static HumanInput getInstance() {
		return instance == null ? instance = new HumanInput() : instance;
	}
	
}
