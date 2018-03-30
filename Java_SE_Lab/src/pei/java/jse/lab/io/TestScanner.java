package pei.java.jse.lab.io;

import java.util.Scanner;

/**
 * 
 * @author pei
 *
 */
public class TestScanner {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input;
        
        while(true){
            input = sc.next();
            if(input.equalsIgnoreCase("q")){
                System.out.println("program quitting");    
                break;
            }else{
                System.out.println("program continues");
            }
        }
    }

}
