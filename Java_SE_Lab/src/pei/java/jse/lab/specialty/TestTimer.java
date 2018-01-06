package pei.java.jse.lab.specialty;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class TestTimer {
	
		@Test
		public void timerAndTimerTask() {

			Timer timer1 = new Timer();             
			timer1.schedule(new Task("T1"), 0, 5000);

			Timer timer2 = new Timer();             
			timer2.schedule(new Task("T2"), 0, 3000);
			
			try {
				Thread.sleep(15000); // 15s
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			timer1.cancel();
			timer2.cancel();
			
		}

}

class Task extends TimerTask {// TimerTask implemented Runnable
	
	private String taskName;
	private int runCounter = 0;

	Task(String tasktName) {
		this.taskName = tasktName;
		runCounter = 0;
	}

	public void run() {
		runCounter++;
		System.out.format("Task %s runs x%s%n", taskName, runCounter); 
	}

}
