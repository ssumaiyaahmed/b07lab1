import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Driver { 
 public static void main(String [] args) throws IOException, FileNotFoundException{ 
  Polynomial p = new Polynomial(); 
  System.out.println(p.evaluate(3)); 
  double [] c1 = {6,0,0,5}; 
  int [] e1 = {3, 2, 1, 0};
  Polynomial p1 = new Polynomial(c1,e1); 
  double [] c2 = {0,-2,0,0,-9}; 
  int [] e2 = {6, 5, 4, 2, 0};
  Polynomial p2 = new Polynomial(c2,e2); 
  Polynomial s = p1.add(p2);
  System.out.println("s(0.1) = " + s.evaluate(0.1));
  Polynomial s1 = p1.multiply(p2);
  System.out.println("s1(1.25) = " + s1.evaluate(1.25));

  if(s.hasRoot(1)) 
   System.out.println("1 is a root of s"); 
  else 
   System.out.println("1 is not a root of s"); 

  File input_file = new File("C:\\Users\\Syeda Sumaiya Ahmed\\Desktop\\lab2_input.txt");
  Polynomial p3 = new Polynomial(input_file);
  System.out.println("p3(0.1) = " + p3.evaluate(0.1));
  
  double [] c4 = {-3, 9, -8, 7, 0, -1}; 
  int [] e4 = {5, 4, 3, 2, 1, 0};
  Polynomial p4 = new Polynomial(c4, e4);	
		
  p4.saveToFile("C:\\Users\\Syeda Sumaiya Ahmed\\Desktop\\lab2_output.txt");
  p3.saveToFile("C:\\Users\\Syeda Sumaiya Ahmed\\Desktop\\lab2_output2.txt");
 
 } 
} 

