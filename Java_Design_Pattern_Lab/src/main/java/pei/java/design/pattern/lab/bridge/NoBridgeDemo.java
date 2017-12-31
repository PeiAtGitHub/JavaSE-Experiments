package pei.java.design.pattern.lab.bridge;

import lombok.extern.slf4j.Slf4j;

/**
 * RC: Remote Controller
 * 
 * In this example:
 * 1. RC and Car each has their own hierarchies, but they are put in one...(missed one abstraction here) 
 * 2. So, when we have 2 different cars, 3 different RCs, in sum, we have 6 RC implementations.
 * 3. Imagine when we need to add another type of car or RC...
 * 
 * @author pei
 *
 */
@Slf4j
public class NoBridgeDemo {

	public static void main(String[] args) {

		new HondaLockRC().turnOn();
		new ToyotaAlarmRC().turnOff();
		new ToyotaStarterRC().turnOn();
	}

	//
	static abstract class RC {
		public abstract void turnOn();
		public abstract void turnOff();
	}

	static class AlarmRC extends RC {
		public void turnOn() {
			log.info("turn on alarm");
		}
		public void turnOff() {
			log.info("turn off alarm");
		}
	}
	static class StarterRC extends RC {
		public void turnOn() {
			log.info("start car");
		}
		public void turnOff() {
			log.info("stop car");
		}
	}
	static class LockRC extends RC {
		public void turnOn() {
			log.info("lock door");
		}
		public void turnOff() {
			log.info("unlock door");
		}
	}

	//
	static class ToyotaAlarmRC extends AlarmRC {
		public void turnOn() {
			log.info("turn on toyota alarm");
		}
		public void turnOff() {
			log.info("turn off toyota alarm");
		}
	}
	static class HondaAlarmRC extends AlarmRC {
		public void turnOn() {
			log.info("turn on honda alarm");
		}
		public void turnOff() {
			log.info("turn off honda alarm");
		}
	}
	static class ToyotaStarterRC extends StarterRC {
		public void turnOn() {
			log.info("start toyota");
		}
		public void turnOff() {
			log.info("stop toyota");
		}
	}
	static class HondaStarterRC extends StarterRC {
		public void turnOn() {
			log.info("start honda");
		}
		public void turnOff() {
			log.info("stop honda");
		}
	}
	static class ToyotaLockRC extends LockRC {
		public void turnOn() {
			log.info("lock toyota");
		}
		public void turnOff() {
			log.info("unlock toyota");
		}
	}
	static class HondaLockRC extends LockRC {
		public void turnOn() {
			log.info("lock honda");
		}
		public void turnOff() {
			log.info("unlock honda");
		}
	}

}