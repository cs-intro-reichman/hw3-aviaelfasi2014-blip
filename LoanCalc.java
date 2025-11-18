public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	public static void main(String[] args) {		
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}
	private static double endBalance(double loan, double rate, int n, double payment) {	
			double balance = loan ;
			double r = rate / 100.0;
			for (int i=0; i<n; i++){
				balance = (balance - payment) * (1 + r);
			}
			return balance;
		}		
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		double g = loan/n ;
		iterationCounter = 0;
		double balance = endBalance(loan, rate, n, rate);
		while (balance > 0){
			g = g + epsilon;
			balance = endBalance(loan, rate, n, rate);
			iterationCounter ++;
		}
		return g;
    }
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) { 
		iterationCounter = 0;
		double lo = loan/n ;
		double hi = loan;
		double fLo = endBalance(loan, rate, n, lo);
    	double fHi = endBalance(loan, rate, n, hi);
		while (fHi > 0) {
        hi *= 2;
        fHi = endBalance(loan, rate, n, hi);
    	}
		double mid = (lo + hi) / 2.0;
    	double fMid = endBalance(loan, rate, n, mid);
		while (hi - lo > epsilon) {
       		if (fMid * fLo > 0) {
            lo = mid;
            fLo = fMid;
        } else {
            hi = mid;
        }
        mid = (lo + hi) / 2.0;
        fMid = endBalance(loan, rate, n, mid);
        iterationCounter++;
    }

    return mid;
	}
}