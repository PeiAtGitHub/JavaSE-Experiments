package pei.java.jse.lab.language;

/**
 * @author pei
 */
public class AnonymousInnerClassDemo {
    
    public static void main(String[] args) {
        System.out.println("======  Anonymous Inner Class from interface ======");
        new Vehicle() {
            int speed = 10;
            public void go() {
                System.out.format("%s going at speed %s Kph.%n", vehicleName, speed);       
            }
        }.go();
        
        System.out.println("======  Explicit impl of interface  ======");
        new Car().go();
        
        System.out.println("======  Anonymous Inner Class from class  ======");
        new Car(){
            String myName;
            {
                this.myName = "VW";
                this.speed = 30;
            }
            
            public void go() {
                System.out.format("%s going at speed %s Kph.%n", vehicleName, speed);       
                System.out.format("%s going at speed %s Kph.%n", carName, speed);       
                System.out.format("%s going at speed %s Kph.%n", myName, speed);        
            }
        }.go();
    }

}

interface Vehicle {
	String vehicleName = "VEHICLE";
	void go();
}

class Car implements Vehicle{
	String carName = "CAR";
	int speed = 20;  // In Kph
	
	public void go() {
		System.out.format("%s going at speed %s Kph.%n", vehicleName, speed);		
		System.out.format("%s going at speed %s Kph.%n", carName, speed);		
	}
}

