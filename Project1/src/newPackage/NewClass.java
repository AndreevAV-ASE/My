package newPackage;

public class NewClass {
	
	public static float Validate(float a, float b, float c) throws Exception {
		if(b == 0 & a > 0 & c > 0)
			throw new Exception("Uravnenie does't have roots");
		float D = (float)Math.pow(b, 2) - 4*a*c;
		if(D < 0)
			throw new Exception("Discriminant  lesser than 0");
		return D;
	}
	
	public static String SolveOneRoot(float a, float b, float c) {
		float x1 = -b/(2*a);
		float result1 = a * (float)Math.pow(x1, 2) + b * x1 + c;
		if(result1 == 0) {
			System.out.println("SolveOneRoot It's work");
		}else {
			System.out.println("SolveOneRoot" + result1);
		}
		return String.format("%f*%f^2+%f*%f+%f=0", a,x1,b,x1,c);
	}
	
	public static String SimpleSolve(float b, float c) throws Exception {
		if (b == 0)
			throw new Exception ("Roots does't exist");
		
		float x1 = -c/b;
		
		float result = b*x1+c;
		
		if(result == 0) {
			System.out.println("SimpleSolve It's work");
		}
		else {
			System.out.println("SimpleSolve " + result);
		}
		return String.format("%f*%f+%f=0", b,x1,c);
	}
	
	public static String Solve(float a, float b, float c) throws Exception {
		float x1, x2 = 0;
		if(a == 0) {
			return SimpleSolve(b,c);
		}else {
			float D = Validate(a,b,c);
			if(D == 0) {
				return SolveOneRoot(a,b,c);
			}
			
			else {
				x1 = (-b + (float)Math.sqrt(D))/(2*a);
				
				x2 = (-b - (float)Math.sqrt(D))/(2*a);
				
				float result1 = a * (float)Math.pow(x1, 2) + b * x1 + c;
				float result2 = a * (float)Math.pow(x2, 2) + b * x2 + c;
				
				if(result1 == 0 & result2 == 0) {
					System.out.println("It's work");
				}else {
					System.out.println(result1);
					System.out.println(result2);
				}
				return String.format("%f*%f^2+%f*%f+%f=0", a,x1,b,x1,c) + " or\n " +String.format("%f*%f^2+%f*%f+%f=0", a,x2,b,x2,c);
			}
		}
		
		
	}
	public static void main(String[] args) throws Exception {
		try {
			System.out.println(Solve(0, 0, 10));
		}
		catch (Exception e){
			throw e;
		}
	}
}
