package org.texastorque;

import java.util.ArrayList;

import org.texastorque.input.HumanInput;
import org.texastorque.subsystem.DriveBase;
import org.texastorque.subsystem.FlyWheel;
import org.texastorque.subsystem.Subsystem;
import org.texastorque.torquelib.base.TorqueIterative;

public class Robot extends TorqueIterative{
	
	private ArrayList<Subsystem> systems = new ArrayList<>();
	{
		systems.add(DriveBase.getInstance());
		systems.add(FlyWheel.getInstance());
	}
	
	@Override
	public void robotInit() {
		for(Subsystem system : systems) {
			system.initSystem();
		}
	}
	
	@Override
	public void teleopInit() {
		
	}

	@Override
	public void teleopContinuous() {
		HumanInput.getInstance().update();
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
		for(Subsystem system : systems) {
			try {
				system.teleopPeriodic();
			} catch(UnsupportedOperationException uoe) {
				System.out.println(uoe.getMessage());
			}
		}
	}
	
	@Override
	public void alwaysContinuous() {
		for(Subsystem system : systems) {
			system.smartDashboard();
		}
	}
}
