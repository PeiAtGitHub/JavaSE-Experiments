package pei.java.jse.lab.specialty;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.jupiter.api.Test;

import lombok.RequiredArgsConstructor;

import static com.github.peiatgithub.java.utils.Utils.*;

/**
 * @author pei
 */
public class TestTimer {
    
        @Test
        public void timerAndTimerTask() {
            Timer timer1 = new Timer();             
            timer1.schedule(new Task("T1"), 0, 5000);

            Timer timer2 = new Timer();             
            timer2.schedule(new Task("T2"), 0, 3000);
            
            threadSleep(15000);
            
            timer1.cancel();
            timer2.cancel();
        }

}

@RequiredArgsConstructor
class Task extends TimerTask {// TimerTask implemented Runnable
    
    private final String taskName;
    private int runCounter = 0;

    public void run() {
        System.out.format("Task %s runs x%s%n", taskName, ++runCounter); 
    }

}
