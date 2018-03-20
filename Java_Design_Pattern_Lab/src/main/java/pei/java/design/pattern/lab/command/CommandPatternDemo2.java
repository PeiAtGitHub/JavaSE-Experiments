package pei.java.design.pattern.lab.command;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 
 * @author pei
 *
 */
public class CommandPatternDemo2 {

	public static void main(String[] args) throws InterruptedException {

		Light light = new Light();

		Switch lightSwitch = new Switch();

		lightSwitch.storeAndExecute(new FlipUpCommand(light));
		Thread.sleep(1000);
		lightSwitch.storeAndExecute(new FlipDownCommand(light));
		
	}

}

@AllArgsConstructor
class FlipUpCommand implements Command {
	private Light theLight;

	public void execute() {
		theLight.turnOn();
	}
}

@AllArgsConstructor
class FlipDownCommand implements Command {
	private Light theLight;

	public void execute() {
		theLight.turnOff();
	}
}

/* 
 * the Invoker 
 */
@NoArgsConstructor
class Switch {
	private List<Command> history = new ArrayList<Command>();

	public void storeAndExecute(Command cmd) {
		history.add(cmd);
		cmd.execute();
	}
}

@NoArgsConstructor
class Light {
	public void turnOn() {
		System.out.println("The light is on");
	}
	public void turnOff() {
		System.out.println("The light is off");
	}
}
