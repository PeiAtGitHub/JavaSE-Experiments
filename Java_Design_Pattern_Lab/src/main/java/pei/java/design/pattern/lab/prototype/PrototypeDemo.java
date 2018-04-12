package pei.java.design.pattern.lab.prototype;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 
 * @author pei
 *
 */
public class PrototypeDemo {

    public static void main(String args[]) throws CloneNotSupportedException {
        Prototype prototype = new PrototypeImpl(10);

        for (int i = 0; i < 10; i++) {
            // new object created from prototype has same value as prototype
            Prototype newObj= prototype.clone();
            
            // customize the new created object
            newObj.setX(newObj.getX() * (i+1));
            newObj.printX();
        }
    }
}

@AllArgsConstructor @Setter @Getter
class PrototypeImpl extends Prototype {
    int x;

    public void printX() {
        System.out.println("Value: " + x);
    }
}

abstract class Prototype implements Cloneable {
    @Override
    public Prototype clone() throws CloneNotSupportedException {
        // Default clone() is shallow copy, which is adequate
        // for this case.
        return (Prototype) super.clone();
    }

    public abstract void setX(int x);

    public abstract int getX();

    public abstract void printX();
}
