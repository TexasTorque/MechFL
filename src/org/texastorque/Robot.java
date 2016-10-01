package org.texastorque;

import java.util.ArrayList;

import org.texastorque.subsystem.DriveBase;
import org.texastorque.subsystem.Subsystem;
import org.texastorque.torquelib.base.TorqueIterative;

public class Robot extends TorqueIterative{
	
	private ArrayList<Subsystem> systems = new ArrayList<>();
	{
		systems.add(DriveBase.getInstance());
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
	}
}
