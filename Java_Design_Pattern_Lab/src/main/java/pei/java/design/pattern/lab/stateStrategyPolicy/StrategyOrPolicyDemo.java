package pei.java.design.pattern.lab.stateStrategyPolicy;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * the strategy pattern, also known as the policy pattern
 * is almost same as the state pattern
 * probably the only difference is they represents different 
 * real world scenario meanings in their names
 * 
 * @author pei
 *
 */
public class StrategyOrPolicyDemo {

    public static void main(String[] args) throws Exception {

        SingleCalculator sc = new SingleCalculator(new StrategyAdd());
        System.out.println(sc.execute(3, 3));
        
        sc.setStrategy(new StrategyMultiply());
        System.out.println(sc.execute(3, 3));

        sc.setStrategy(new StrategySubtract());
        System.out.println(sc.execute(3, 3));
    }
    
}

/**
 * the context class
 */
@AllArgsConstructor @Setter
class SingleCalculator {
    
    private Strategy strategy;
    
    public int execute(int a, int b) {
        return strategy.execute(a, b);
    }
}

interface Strategy {
    int execute(int a, int b);
}
class StrategyAdd implements Strategy {
    public int execute(int a, int b) {
        return a + b;
    }
}
class StrategySubtract implements Strategy {
    public int execute(int a, int b) {
        return a - b;
    }
}
class StrategyMultiply implements Strategy {
    public int execute(int a, int b) {
        return a * b;
    }
}
