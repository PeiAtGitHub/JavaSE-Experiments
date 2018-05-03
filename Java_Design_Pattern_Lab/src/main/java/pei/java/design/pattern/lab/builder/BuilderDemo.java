package pei.java.design.pattern.lab.builder;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class BuilderDemo {

    public static void main(String[] args) {
        
        Pizza pizza1 = new HawaiianPizzaBuilder().LargeBuilder().build();
        Pizza pizza2 = new SpicyPizzaBuilder().build();
        
        log.info("{}: Size: {}; Dough: {}; Sause: {}; Top: {};", 
                pizza1.getName(), pizza1.getSize(), pizza1.getDough(),pizza1.getSauce(), pizza1.getTopping());
        log.info("{}: Size: {}; Dough: {}; Sause: {}; Top: {};", 
                pizza2.getName(), pizza2.getSize(), pizza2.getDough(),pizza2.getSauce(), pizza2.getTopping());
    }

}

@Getter
abstract class PizzaBuilder {
    protected Pizza pizza;
    protected int size = 25; 
    
    public PizzaBuilder LargeBuilder() {
        this.size = 35;
        return this;
    }
    
    public abstract Pizza build();
}


class HawaiianPizzaBuilder extends PizzaBuilder {
    public Pizza build() {
        pizza = new Pizza();
        // well, the following steps' order matters :)
        pizza.setName("Hawaiian Pizza");
        pizza.setSize(size);
        pizza.setDough("cross");
        pizza.setSauce("mild");
        pizza.setTopping("ham + pineapple");
        return pizza;
    }
}

class SpicyPizzaBuilder extends PizzaBuilder {
    public Pizza build() {
        pizza = new Pizza();
        pizza.setSize(size);
        pizza.setName("Spicy Pizza");
        pizza.setDough("pan baked");
        pizza.setSauce("hot");
        pizza.setTopping("pepperoni + salami");
        return pizza;
    }
}



