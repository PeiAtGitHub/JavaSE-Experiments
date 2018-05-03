package pei.java.design.pattern.lab.builder;

import lombok.extern.slf4j.Slf4j;

/**
 * Not using builder pattern 
 * The problem is : 
 * you have to remember the order of the arguments of the constructor
 * you have to remember the recipe of the specific type of pizza 
 * you have to remember the process/steps of building a pizza and their order 
 * all above you have to hard-code, and they appear in your source code wherever you
 * create a pizza, maintainability not good.
 * even thread-safety issues.
 * 
 * @author pei
 *
 */
@Slf4j
public class NoBuilder {
    public static void main(String[] args) {
        // way1
        Pizza pizza1 = new Pizza("Hawaiian Pizza", 35, "cross", "mild", "ham + pineapple");
        // way2
        Pizza pizza2 = new Pizza();
        pizza2.setName("Spicy Pizza");
        pizza2.setSize(25);
        pizza2.setDough("pan baked");
        pizza2.setSauce("hot");
        pizza2.setTopping("pepperoni + salami");

        log.info("{}: Size: {}; Dough: {}; Sause: {}; Top: {};", 
                pizza1.getName(), pizza1.getSize(), pizza1.getDough(),pizza1.getSauce(), pizza1.getTopping());
        log.info("{}: Size: {}; Dough: {}; Sause: {}; Top: {};", 
                pizza2.getName(), pizza2.getSize(), pizza2.getDough(),pizza2.getSauce(), pizza2.getTopping());    
    }

}


