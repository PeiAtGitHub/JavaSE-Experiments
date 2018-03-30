package pei.java.design.pattern.lab.factory;

import java.util.Random;

/**
 * 
 * @author pei
 *
 */
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        new TheApplication(getGuiFactory());
    }

    public static GUIFactory getGuiFactory() {
        if (new Random().nextBoolean()) {
            return new WinFactory();
        } else {
            return new OSXFactory();
        }
    }
}

class TheApplication {
    public TheApplication(GUIFactory factory) {
        factory.createButton().paint();
        factory.createCheckBox().paint();
    }
}

/*
 * the abstract factory.
 */
interface GUIFactory {
    public Button createButton();
    public CheckBox createCheckBox();
}

/*
 * Factories
 */
class WinFactory implements GUIFactory {
    public Button createButton() {
        return new WinButton();
    }
    public CheckBox createCheckBox() {
        return new WinCheckBox();
    }
}

class OSXFactory implements GUIFactory {
    public Button createButton() {
        return new OSXButton();
    }
    public CheckBox createCheckBox() {
        return new OSXCheckBox();
    }
}

/*
 * 
 */
interface Button {
    public void paint();
}
class WinButton implements Button {
    public void paint() {
        System.out.println("A WinButton");
    }
}
class OSXButton implements Button {
    public void paint() {
        System.out.println("An OSXButton");
    }
}

interface CheckBox {
    public void paint();
}
class WinCheckBox implements CheckBox {
    public void paint() {
        System.out.println("A WinCheckBox");
    }
}
class OSXCheckBox implements CheckBox {
    public void paint() {
        System.out.println("An OSXCheckBox");
    }
}

