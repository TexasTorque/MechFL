package org.texastorque.input;

import org.texastorque.constants.Ports;
import org.texastorque.torquelib.util.GenericController;

public class HumanInput extends Input {

	private static HumanInput instance;
	
	private GenericController driver;
	
	public void init() {
		driver = new GenericController(Ports.HI_DRIVER, 0.1);
	}
	
	public void update() {

		DB_SpeedLeft = -driver.getLeftYAxis() + driver.getRightXAxis();
		DB_SpeedRight = -driver.getLeftYAxis() - driver.getRightXAxis();
		
//		TODO: methodology for setting flywheel speeds
		
	}
	
	public static HumanInput getInstance() {
		return instance == null ? instance = new HumanInput() : instance;
	}
	
}
