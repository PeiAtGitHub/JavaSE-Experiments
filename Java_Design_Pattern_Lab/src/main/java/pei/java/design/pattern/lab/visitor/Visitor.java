package pei.java.design.pattern.lab.visitor;

/**
 * 
 * @author pei
 *
 */
public interface Visitor {

	public void visit(SuperMarketItem item);
	
	public void visit(ShoppingCart cart);

}
