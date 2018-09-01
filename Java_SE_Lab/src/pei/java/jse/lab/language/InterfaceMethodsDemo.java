package pei.java.jse.lab.language;

public class InterfaceMethodsDemo {
    public static void main(String[] args) {
        InterfaceMethodsDemo instance = new InterfaceMethodsDemo();
        instance.new Tank().print();
        instance.new Cart().print();
        instance.new Automobile().print();
        Vehicle.blowHorn(); // interface static method can be called only this way
    }
    
    
    /**
     * Demo of default impl of interface method
     * Demo of static method of interface.
     * @author pei
     */
    interface Vehicle {
        // default impl of the interface method
        default void print() {
            System.out.println("I am a Vehicle!");
        }
        // static method of interface
        static void blowHorn() { 
            System.out.println("Blowing horn!");
        }
    }

    interface FourWheeler {
        default void print() {
            System.out.println("I am a Four Wheeler!");
        }
    }

    class Tank implements Vehicle{} 
    
    class Cart implements FourWheeler{}
    
    class Automobile implements Vehicle, FourWheeler {
        public void print() {// implement to solve ambiguity
//            Vehicle.super.print();// use a specific interface's default impl
            System.out.println("I am an Automobile, a Four Wheeler Vehicle!"); 
        }
    }

}
