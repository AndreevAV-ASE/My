package id;


public class Calculator {
	
	public String Calc(String example){
		String[] operation = null;
		try {
			operation = example.split(" ");
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return ex.getMessage();
		}
		
		return Solve (Integer.parseInt(operation[0]), Integer.parseInt(operation[2]),operation[1]);
	}
	public String Solve(int a, int b, String op){
		switch(op) {
		case "+" : return "Result "+ (a + b) + "\n";
		case "-" : return "Result "+ (a - b) + "\n";
		case "*" : return "Result "+ (a * b) + "\n";
		case "/" : return "Result "+ (a / b) + "\n"; 
		default : return "Unexist operator";
		}
	}
}
