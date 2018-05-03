package pei.java.design.pattern.lab.observer;

import java.util.Observable;
import java.util.Observer;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
public class ObserverDemo {

    public static void main(String[] args) {
 
        final TheSubject eventSource = new TheSubject();

        eventSource.addObserver(new TheObserver("Observer1"));
        eventSource.addObserver(new TheObserver("Observer2"));
        
        eventSource.whatever();
        eventSource.whatever();
        eventSource.whatever();
        
    }

}

//

@ToString
class TheSubject extends Observable {
    
    private int counter = 0;
    
    public void whatever() {
        counter++;
        setChanged();
        notifyObservers(new Event("Counter changed to: " + counter));
    }
    
}

@ToString @AllArgsConstructor @Slf4j
class TheObserver implements Observer {
    
    String name;
    
    public void update(Observable obj, Object arg) {
        log.info("{} got sth({}) from {}", this.toString(), arg.toString(), obj.toString());
    }
    
}

@AllArgsConstructor @ToString
class Event {
    String description;
}

