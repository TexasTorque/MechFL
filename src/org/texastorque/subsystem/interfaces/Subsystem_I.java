package org.texastorque.subsystem.interfaces;

public interface Subsystem_I {
	
	public abstract void initSystem();
	
	public abstract void runSystem();
	
	public abstract void setOutput();
	
	public abstract void smartDashboard();

	default public void teleopPeriodic() {
		throw new UnsupportedOperationException(getClass().getSimpleName() + " teleopPeriodic Not in use...Override Me!");
	}
	
	default public void teleopContinuous() {
		throw new UnsupportedOperationException(getClass().getSimpleName() + " teleopContinuous Not in use...Override Me!");
	}
	
	default public void autonomousPeriodic() {
		throw new UnsupportedOperationException(getClass().getSimpleName() + " autonomousPeriodic not in use...Override Me!");
	}
	
	default public void autonomousContinuous() {
		throw new UnsupportedOperationException(getClass().getSimpleName() + " autonomousContinuous Not in use...Override Me!");
	}
	
}
