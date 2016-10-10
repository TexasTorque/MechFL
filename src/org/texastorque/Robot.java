package org.texastorque;

import java.util.ArrayList;

import org.texastorque.feedback.Feedback;
import org.texastorque.input.HumanInput;
import org.texastorque.output.RobotOutput;
import org.texastorque.subsystem.DriveBase;
import org.texastorque.subsystem.FlyWheel;
import org.texastorque.subsystem.Subsystem;
import org.texastorque.torquelib.base.TorqueIterative;

public class Robot extends TorqueIterative{
	
	private ArrayList<Subsystem> systems;
	
	
	@Override
	public void robotInit() {
		System.out.println("RobotInit...");
		
		RobotOutput.getInstance();
		Feedback.getInstance();
		HumanInput.getInstance();

		systems = new ArrayList<Subsystem>();
		systems.add(DriveBase.getInstance());
		systems.add(FlyWheel.getInstance());
		
		for(Subsystem system : systems) {
			system.initSystem();
		}
	}
	
	@Override
	public void teleopInit() {
		systems.forEach((subsystem) -> subsystem.setInput(HumanInput.getInstance()));
	}

	@Override
	public void teleopContinuous() {
		HumanInput.getInstance().update();
		Feedback.getInstance().update();
		for(Subsystem system : systems) {
			try {
				system.teleopContinuous();
			} catch(UnsupportedOperationException uoe) {
				System.out.println(uoe.getMessage());
			}
		}
	}
	
	@Override
	public void teleopPeriodic() {
		System.out.println("Teleop...");
		HumanInput.getInstance().update();
		Feedback.getInstance().update();
		for(Subsystem system : systems) {
			try {
				system.teleopPeriodic();
			} catch(UnsupportedOperationException uoe) {
				System.out.println(uoe.getMessage());
			}
		}
		for(Subsystem system : systems) {
			system.smartDashboard();
		}
		Feedback.getInstance().smartDashboard();
	}
	
	@Override
	public void alwaysContinuous() {
		
	}
}
