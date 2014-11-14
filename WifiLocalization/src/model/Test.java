package model;

public class Test {

	 public static void main(String[] args) {

	  double[] numbers = new double[10];

	  // Take the 10 numbers in array for which we 
	  // want to calculate the standard deviation
	  numbers[0] = 23;
	  numbers[1] = 92;
	  numbers[2] = 46;
	  numbers[3] = 55;
	  numbers[4] = 63;
	  numbers[5] = 94;
	  numbers[6] = 77;
	  numbers[7] = 38;
	  numbers[8] = 84;
	  numbers[9] = 26;

	  System.out.println("1. Get the mean of numbers. The mean is : ");

	  double sum = 0;

	  // Taking the average to numbers
	  for(int i =0; i< numbers.length; i++) {
	   sum = sum + numbers[i];
	  }

	  double mean = sum/numbers.length;

	  System.out.println(mean);

	  System.out.println("\n2. Get deviation of mean from each number : ");

	  double[] deviations = new double[10];

	  // Taking the deviation of mean from each numbers
	  for(int i = 0; i < deviations.length; i++) {
	   deviations[i] = numbers[i] - mean ; 
	   System.out.printf("%2.2f",deviations[i]);
	   System.out.print(" ");   
	  }

	  System.out.println();
	  System.out.println("\n3. Get squares of deviations : ");
	  double[] squares = new double[10];

	  // getting the squares of deviations
	  for(int i =0; i< squares.length; i++) {
	   squares[i] = deviations[i] * deviations[i];
	   System.out.printf("%4.2f",squares[i]);
	   System.out.print(" ");
	  }

	  System.out.println();
	  System.out.println("\n4. Get addition of squares : ");

	  sum = 0;

	  // adding all the squares
	  for(int i =0; i< squares.length; i++) {
	   sum = sum + squares[i];
	  }

	  System.out.println(sum);

	  // dividing the numbers by one less than total numbers
	  System.out.println("\n5. Divide addition of squares by total (numbers) - 1 : ");
	  double result = sum / (numbers.length - 1);

	  System.out.printf("%4.2f",result);

	  double standardDeviation = Math.sqrt(result);
	  
	  // Taking square root of result gives the
	  // standard deviation
	  System.out.println("\n\n6. Take the square root of result which gives");
	  System.out.println("the Standard Deviation of the ten numbers : ");
	  System.out.printf("%4.2f",standardDeviation);

	 }

	}


