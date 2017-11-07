package pei.java.jse.experiments.language;

public class TestNumbers {
	
	public static void main(String[] args) {
//		MathClass();
		Calculations();
//		RandomClass();
		
	}
	
	private static void MathClass(){
		System.out.println(Math.round(0.4));
		System.out.println(Math.round(0.6));

		//
		for(int i =0; i<=9;i++){
			System.out.println(Math.random());
		}

	}
	
	private static void Calculations(){
		System.out.println(6/10);
		System.out.println(6d/10);
		System.out.println(6.0/10);
		
		
		//
		int i1=3, i2=6;
		double d1=3, d2=6;
		
		System.out.println(i1*i2);
		System.out.println(d1*d2);
		System.out.println(i1*d2);
		System.out.println(i1/i2);
		System.out.println(i1/d2);
		System.out.println(i1+d2);		
		
	}
	
	private static void RandomClass() {

	}

}
