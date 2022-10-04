import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedReader;

public class Polynomial{
	double [] coeff;
	int [] expo;

	public Polynomial() {
		coeff = new double [] {0};
		expo = new int [] {0};

	}
	
	public Polynomial(double [] coeff_arr, int [] expo_arr)     {
		coeff = new double [coeff_arr.length];
		expo = new int [expo_arr.length];
		for(int i=0;i<coeff_arr.length; i++) {
			coeff[i] = coeff_arr[i];
			expo[i] = expo_arr[i];
		}
	}

	public int in_array(int [] arr, int num) {

		for(int i = 0; i<arr.length; i++) {
			if(arr[i] == num)
				return i;

		}

		return -1;

	}

	

	public Polynomial add(Polynomial func) {

		
		Polynomial new_poly = new Polynomial();

		new_poly.coeff = new double [coeff.length+func.coeff.length];
		new_poly.expo = new int [expo.length + func.expo.length];

		
		for (int h = 0; h<coeff.length; h++){

			new_poly.coeff[h] = coeff[h];
			new_poly.expo[h] = expo[h];
		}

		for(int m = coeff.length; m<coeff.length+func.coeff.length; m++) {
		
			new_poly.coeff[m] = func.coeff[m-coeff.length];
			new_poly.expo[m] = func.expo[m-coeff.length];

		}
		


		for(int i=0; i<new_poly.expo.length; i++) {

			for (int j = 0; j<new_poly.expo.length; j++){
	

				if((new_poly.expo[i] == new_poly.expo[j]) && (i != j)) {
					new_poly.coeff[i] += new_poly.coeff[j];
					new_poly.coeff[j] = 0;
					new_poly.expo[j] = 0;
				}
					

			}

		}


		return new_poly;
	}

	public double evaluate(double x) {
		double solution = 0.0;

		for(int i=0;i<coeff.length; i++) {
			solution = solution +(coeff[i] * (Math.pow(x, expo[i])));
		}
		
		return solution;
	}

	public boolean hasRoot(double x) {
		double solution = 0;

		for(int i=0;i<coeff.length; i++) {
			solution = solution +(coeff[i] * (Math.pow(x, expo[i])));
		}
		
		if (solution == 0) {
			return true;
		}
		else {
			return false;
		}

	}




	public Polynomial multiply(Polynomial other){

		Polynomial result = new Polynomial();
		result.coeff = new double [coeff.length*other.coeff.length];
		result.expo = new int [expo.length*other.expo.length];

		int counter = 0;
		for(int i=0;i<coeff.length; i++) {
			for(int j=0;j<other.coeff.length; j++) {
				
				result.coeff[counter] = coeff[i] *other.coeff[j];
				result.expo[counter] = expo[i] +other.expo[j];
				counter +=1;
				
				
			
			}
		}

		for(int k=0; k<result.expo.length; k++) {

			for (int j = 0; j<result.expo.length; j++){
	

				if((result.expo[k] == result.expo[j]) && (k != j)) {
					result.coeff[k] += result.coeff[j];
					result.coeff[Math.max(k,j)] = 0;
					result.expo[Math.max(k,j)] = 0;
				}
					

			}

		}


		return result;
	
	 


	}

	public int count_occurrence(String ln, char occurrence) {

		char [] ch = ln.toCharArray();
		int count = 0;
		for (char c: ch) {
			if(c == '-')
				count += 1;

		}
	
		return count;


	}	

	public String add_plus(String ln) {
	
		int index = ln.indexOf("-");
		int [] minus_array = new int [count_occurrence(ln, '-')]; 
		int i = 0;
		while (index != -1) {
			minus_array[i] = index;
			index = ln.indexOf("-", index + 1);
			i += 1;		

		}
		int len = minus_array.length;
		String new_str = "";
		
		for(int j = 0; j< len; j++) {
	
			if(j==0 && j==len-1) {
				new_str += ln.substring(0, minus_array[0]) + "+" +ln.substring(minus_array[0]); 
			}
			else if (j == 0)
				new_str = new_str + ln.substring(0, minus_array[0]) + "+";
			
			else if (j==len-1)
				new_str = new_str + ln.substring(minus_array[j-1], minus_array[j]) + "+" +ln.substring(minus_array[j]);
			else 
				new_str = new_str + ln.substring(minus_array[j-1], minus_array[j]) + "+";
			
		}
		return new_str;
		

	}

	public Polynomial(File poly_file) throws IOException {


		BufferedReader br = new BufferedReader(new FileReader(poly_file));
		String line = br.readLine();
		br.close();
		String plus_line = add_plus(line);

		String [] plus_split = plus_line.split("\\+",-2);
		
		//System.out.println(plus_line);
		
		String [][] splitted = new String [plus_split.length][2]; 

		for(int i = 0; i<plus_split.length; i++) {
			splitted[i] = plus_split[i].split("x", -2);
			
		}

		coeff = new double [splitted.length];
		expo = new int [splitted.length];
		int count = 0;
		for(int i = 0; i<splitted.length;i++) {

			if(!splitted[i][0].isEmpty() && splitted[i].length == 1) {
				coeff[count] = Double.parseDouble(splitted[i][0]);
				expo[count] = 0;
				count +=1;
			}

			if(!splitted[i][0].isEmpty() && splitted[i].length > 1) {
				coeff[count] = Double.parseDouble(splitted[i][0]);
				expo[count] = Integer.parseInt(splitted[i][1]);
				count +=1;

			}



		}
		
		
	

	}
	
	public void saveToFile(String file_name) throws FileNotFoundException {
		String poly = "";
		for(int i = 0; i<coeff.length; i++) {
			if(expo[i] != 0)
				if(coeff[i] < 0)
					poly = poly+String.valueOf(coeff[i])+"x"+String.valueOf(expo[i]);
			
				else
					poly = poly+ '+' + String.valueOf(coeff[i])+"x"+String.valueOf(expo[i]);
			else
				if(coeff[i] < 0)
					poly = poly + String.valueOf(coeff[i]);
				else
					poly = poly + '+'+ String.valueOf(coeff[i]);
		}
		PrintStream ps = new PrintStream(file_name);
		ps.println(poly);
		ps.close();
		
		
	}



}

