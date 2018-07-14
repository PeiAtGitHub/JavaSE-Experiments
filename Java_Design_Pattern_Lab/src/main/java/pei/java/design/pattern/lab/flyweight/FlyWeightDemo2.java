package pei.java.design.pattern.lab.flyweight;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import static com.github.peiatgithub.java.utils.Utils.*;
import static org.assertj.core.api.Assertions.*;

/**
 * @author pei
 */
public class FlyWeightDemo2 {
    
    public static final String CAPPUCCINO = "Cappuccino";
    public static final String FRAPPE = "Frappe";
    public static final String XPRESSO = "Xpresso";
    
    public static void main(String[] args) {
        
        CoffeeOrderFactory coffeOrderFactory = new CoffeeOrderFactory();
        
        /*
         *  appears 10 order objects are needed,
         *  actually we have only 3 order objects created, each for one flavor, and each is
         *  reused for different table
         */
        coffeOrderFactory.getCoffeeOrder(FRAPPE).setTableNumber(1).serveCoffee();
        coffeOrderFactory.getCoffeeOrder(CAPPUCCINO).setTableNumber(1).serveCoffee();
        coffeOrderFactory.getCoffeeOrder(XPRESSO).setTableNumber(1).serveCoffee();
        coffeOrderFactory.getCoffeeOrder(CAPPUCCINO).setTableNumber(2).serveCoffee();
        coffeOrderFactory.getCoffeeOrder(CAPPUCCINO).setTableNumber(2).serveCoffee();
        coffeOrderFactory.getCoffeeOrder(FRAPPE).setTableNumber(2).serveCoffee();
        coffeOrderFactory.getCoffeeOrder(FRAPPE).setTableNumber(3).serveCoffee();
        coffeOrderFactory.getCoffeeOrder(XPRESSO).setTableNumber(3).serveCoffee();
        coffeOrderFactory.getCoffeeOrder(CAPPUCCINO).setTableNumber(95).serveCoffee();
        coffeOrderFactory.getCoffeeOrder(XPRESSO).setTableNumber(96).serveCoffee();
        
        assertThat(coffeOrderFactory.getOrders()).hasSize(3);
    }
}

@Getter @AllArgsConstructor @Builder
class CoffeeOrder {

    private String flavor; //common data
    private int tableNumber;
    
    public CoffeeOrder setTableNumber(int num) {
        this.tableNumber = num;
        return this;
    }
    public void serveCoffee() {
        System.out.format("Serving Coffee (flavor %s) to table %s%n", flavor, tableNumber);
    }
}


@Getter
class CoffeeOrderFactory {
    
    // a CoffeOrders pool 
    private Map<String, CoffeeOrder> orders = new HashMap<String, CoffeeOrder>();

    public CoffeeOrder getCoffeeOrder(String flavor) {
    	ifThen(!orders.containsKey(flavor), ()->orders.put(flavor, CoffeeOrder.builder().flavor(flavor).build()));
        return orders.get(flavor);
    }
    
}

