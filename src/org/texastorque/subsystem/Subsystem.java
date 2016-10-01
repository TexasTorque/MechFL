package org.texastorque.subsystem;

import org.texastorque.input.Input;
import org.texastorque.subsystem.interfaces.Subsystem_I;

public abstract class Subsystem implements Subsystem_I {

	public Input input;
	
	public final void setInput(Input _input) {
		input = _input;
	}
	
	@Override
	public abstract void initSystem();

	@Override
	public abstract void runSystem();

	@Override
	public abstract void setOutput();
	
}
