package pei.java.design.pattern.lab.bridge;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * RC: Remote Controller 
 * 
 * In this Example: (compare to the example without using this pattern)
 * 1. RC and Car each has their own hierarchies, and they are separated...
 * 2. Decoupled an abstraction from its implementation so that the two can vary independently. 
 *    More flexible.
 * 3. Cons: Complexity increased. 
 * 
 * @author pei
 *
 */

@Slf4j
public class BridgeDemo {

    public static void main(String[] args) {
        
        new LockRC(new Honda()).turnOn();
        new AlarmRC(new Toyota()).turnOff();
        new StarterRC(new Toyota()).turnOn();
        
    }


    @AllArgsConstructor
    static abstract class RC {
        protected Car car;

        public abstract void turnOn();
        public abstract void turnOff();
    }

    static class AlarmRC extends RC {

        public AlarmRC(Car car) {
            super(car);
        }

        public void turnOn() {
            car.turnAlarmOn();
        }
        public void turnOff() {
            car.turnAlarmOff();
        }
    }

    static class StarterRC extends RC {

        public StarterRC(Car car) {
            super(car);
        }

        public void turnOn() {
            car.start();
        }
        public void turnOff() {
            car.stop();
        }
    }

    static class LockRC extends RC {
        public LockRC(Car car) {
            super(car);
        }

        public void turnOn() {
            car.lock();
        }
        public void turnOff() {
            car.unlock();
        }
    }

    //
    static abstract class Car {
        abstract public void lock();
        abstract public void unlock();

        abstract public void turnAlarmOn();
        abstract public void turnAlarmOff();

        abstract public void start();
        abstract public void stop();
    }

    static class Toyota extends Car {

        public void turnAlarmOn() {
            log.info("turn on Toyota alarm");
        }
        public void turnAlarmOff() {
            log.info("turn off Toyota alarm");
        }

        public void start() {
            log.info("start Toyota");
        }
        public void stop() {
            log.info("stop Toyota");
        }

        public void lock() {
            log.info("lock Toyota");
        }
        public void unlock() {
            log.info("unlock Toyota");
        }
    }

    static class Honda extends Car {
        
        public void turnAlarmOn() {
            log.info("turn on Honda alarm");
        }
        public void turnAlarmOff() {
            log.info("turn off Honda alarm");
        }

        public void start() {
            log.info("start Honda");
        }
        public void stop() {
            log.info("stop Honda");
        }

        public void lock() {
            log.info("lock Honda");
        }
        public void unlock() {
            log.info("unlock Honda");
        }
    }

}
