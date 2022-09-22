public class Polynomial {
	double [] coeff = new double [100];

	public Polynomial() {
		for(int i=0;i<coeff.length; i++) {
			coeff[i] = 0;
		}

	}
	
	public Polynomial(double [] array)     {

		for(int i=0;i<array.length; i++) {
			coeff[i] = array[i];
		}
	}

	public Polynomial add(Polynomial func) {
		Polynomial new_poly = new Polynomial();

		for(int i=0;i<new_poly.coeff.length; i++) {
			new_poly.coeff[i] = coeff[i] + func.coeff[i];
		}
		
		return new_poly;
	}

	public double evaluate(double x) {
		double solution = 0.0;

		for(int i=0;i<coeff.length; i++) {
			solution = solution +(coeff[i] * (Math.pow(x, i)));
		}
		
		return solution;
	}

	public boolean hasRoot(double x) {
		double solution = 0;

		for(int i=0;i<coeff.length; i++) {
			solution = solution +(coeff[i] * (Math.pow(x, i)));
		}
		
		if (solution == 0) {
			return true;
		}
		else {
			return false;
		}

	}





}
