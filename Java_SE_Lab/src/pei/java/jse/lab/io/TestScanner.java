package pei.java.jse.lab.io;

import java.util.Scanner;

/**
 * 
 * @author pei
 *
 */
public class TestScanner {
    
    public static void main(String[] args) {

        try(Scanner sc = new Scanner(System.in)){
        	while(true){
        		if(sc.next().equalsIgnoreCase("q")){
        			System.out.println("program quitting");    
        			break;
        		}else{
        			System.out.println("program continues");
        		}
        	}
        }
        
    }

}
