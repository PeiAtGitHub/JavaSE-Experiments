package pei.java.design.pattern.lab.visitor;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author pei
 *
 */
public class VisitorDemo {

    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(new SuperMarketItem("Mint", 1.0));    
        cart.addItem(new SuperMarketItem("Banana", 1.5));
        cart.addItem(new SuperMarketItem("Banana", 1.5));
        cart.addItem(new SuperMarketItem("Wine", 5.0));
        cart.addItem(new SuperMarketItem("Wine", 5.0));
        cart.addItem(new SuperMarketItem("Wine", 5.0));    

        Cashier cashier = new Cashier();
        Security security = new Security();
        //
        cart.accept(cashier);
        System.out.println("Total cost of the purchase is: " + cart.getTotalCost());
        cart.accept(security);
        System.out.println("Security check passed?: " + cart.isSecurityPassed());
        //
        cart.addItem(new SuperMarketItem("Sth stolen after cashier", 300.0));
        cart.accept(security);
        System.out.println("Security check passed?: " + cart.isSecurityPassed());
        //
        cart.accept(cashier);
        System.out.println("Total cost of the purchase is: " + cart.getTotalCost());
        cart.accept(security);
        System.out.println("Security check passed?: " + cart.isSecurityPassed());

    }

}

@NoArgsConstructor @Getter @Setter
class SuperMarketItem implements Visitee{
    
    String name = "";
    double price = 0;
    
    boolean paid = false;
    
    public SuperMarketItem(String name, double price){
        this.name = name;
        this.price = price;
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }    
}

@NoArgsConstructor @Getter @Setter
class ShoppingCart implements Visitee{

    private List<SuperMarketItem> items = new ArrayList<SuperMarketItem>();

    private boolean securityPassed = false;

    private double totalCost;

    public void addItem(SuperMarketItem item) {
        items.add(item);
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

/*
 * Business logics are implemented in visitor, not in visitee
 */

class Cashier implements Visitor {

    private double totalCost = 0;

    public void visit(ShoppingCart cart) {
        totalCost = 0;
        for (SuperMarketItem item : cart.getItems()) {
            item.accept(this);
        }
        cart.setTotalCost(totalCost);
        totalCost = 0;
    }
    
    public void visit(SuperMarketItem item) {
        item.setPaid(true);
        totalCost = totalCost + item.getPrice();
    }

}

class Security implements Visitor {
    
    private boolean passed = false;
    
    public void visit(ShoppingCart cart) {
        passed = true;
        for (SuperMarketItem item : cart.getItems()) {
            item.accept(this);
        }
        cart.setSecurityPassed(passed);
        passed = true;
    }

    public void visit(SuperMarketItem item) {
        passed = passed && item.isPaid();
    }
    
}

