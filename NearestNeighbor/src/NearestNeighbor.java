/*Name: Sofia Syed
 *CPSC-50100 Programming Fundamentals
 *Summer 2020
 *PROGRAMMING ASSIGNMENT 3 - NearestNeighbor
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.lang.NullPointerException;

public class NearestNeighbor {
		//EMPTY ARRAYS CREATED
		static double [][] trainingValues = new double [75][4]; //2D integer array of doubles with 75 rows and 4 columns for storing training attribute values
		static double [][] testingValues = new double [75][4]; //2D integer array of doubles with 75 rows and 4 columns for storing testing attribute values
		static double [] distances = new double[75];
		//(75 TESTING AND TRAINING EXAMPLES)
		static String [] trainingClassLabels = new String [75]; //1D string array with 75 rows for storing training class labels
		static String [] testingClassLabels = new String [75]; //1D string array with 75 rows for storing testing class labels
		static String [] closestFlower = new String [75]; //1D string array with 75 rows for storing closest flower
			
		//VARIABLES INITIALIZED AS DOUBLE
		static double shortestDistance = 0;
		static double accuracy = 0;
		
		//PROMPTING USER TO SUBMIT TRAINING FILE NAME
		static double[][] trainingDataScanner() throws FileNotFoundException {
			String trainingFile;
			Scanner scan = new Scanner(System.in);
			System.out.print("Enter the name of the training file: ");
			trainingFile = scan.nextLine();
			File training = new File(trainingFile);
			Scanner trainingDataScan = new Scanner(training);
		int row = 0;
			while(trainingDataScan.hasNext()){
				String line = trainingDataScan.nextLine();
				String[] lineParts = line.split(",");
				//i = column
				for(int i=0; i<4; i++) {
					trainingValues[row][i] = Double.parseDouble(lineParts[i]);
				}
				trainingClassLabels[row] = lineParts[4]; 
				row++; }
			trainingDataScan.close();
			return trainingValues;
		}	
		//PROMPTING USER TO SUBMIT TESTING FILE NAME
		static double[][] testingDataScanner() throws FileNotFoundException {
			String testingFile;
			Scanner scan = new Scanner(System.in);
			System.out.print("Enter the name of the testing file: ");
			testingFile = scan.nextLine();
			File testing = new File(testingFile);
			Scanner testingDataScan = new Scanner(testing);
		int row = 0;
			while (testingDataScan.hasNext()) {
				String line = testingDataScan.nextLine();
				String[] lineParts = line.split(",");
				for (int i = 0; i < 4; i++) {
					testingValues[row][i] = Double.parseDouble(lineParts[i]);
				}
				testingClassLabels[row] = lineParts[4]; 
				row++;
			}
			testingDataScan.close();
			return testingValues;
		}
		//CALCULATING THE SHORTEST DISTANCE
		static String[] closeFlower() {
			int rowShortest = 0;
			for(int rowSequence = 0; rowSequence<75; rowSequence++ ) {
				for(int rowTesting = 0; rowTesting < 75; rowTesting++) {
					
					double sepalLengthDifference = testingValues[rowTesting][0] - trainingValues[rowSequence][0];
					double sepalWidthDifference = testingValues[rowTesting][1] - trainingValues[rowSequence][1];
					double petalLengthDifference = testingValues[rowTesting][2] - trainingValues[rowSequence][2];
					double petalWidthDifference = testingValues[rowTesting][3] - trainingValues[rowSequence][3];
					
					double distance = Math.pow(sepalLengthDifference,  2) + Math.pow(sepalWidthDifference, 2) +
							Math.pow(petalLengthDifference, 2) + Math.pow(petalWidthDifference, 2);
					
					distances[rowSequence] = Math.sqrt(distance);
	                if (rowTesting == 0 || distances[rowSequence] < shortestDistance) {
	                    rowShortest = rowTesting;
	                    shortestDistance = distances[rowSequence];
	                }
	            }
	            closestFlower[rowSequence] = testingClassLabels[rowShortest];
	        }

					return closestFlower;
			}
		//CASE FOR DIFFERENT FLOWER TYPES UP UNTIL CORRECT GUESS
	    static double accuracyPrediction() {
	        int correctGuess = 0;

	        for(int row = 0; row < 75; row++){
	            switch(closestFlower[row]){

	                case "Iris-setosa":
	                    if (closestFlower[row].equals(trainingClassLabels[row])){
	                        correctGuess++;
	                    }
	                    break;
	                case "Iris-versicolor":
	                    if (closestFlower[row].equals(trainingClassLabels[row])) {
	                        correctGuess++;
	                    }
	                    break;
	                case "Iris-virginica":
	                    if (closestFlower[row].equals(trainingClassLabels[row])) {
	                        correctGuess++;
	                    }
	                    break;
	            }
	        }
	        accuracy = (double)correctGuess / 75;
	        return accuracy;
	        }
	    static void printResults() {
	    	System.out.println();
	        System.out.println("EX#: TRUE LABEL, PREDICTED LABEL");

	        for(int row = 0; row < 75; row++){
	            System.out.print(row + 1 + ": ");

	            System.out.print(trainingClassLabels[row] + " ");
	            System.out.print(closestFlower[row]);
	            System.out.println(); //line break
	        }
	        accuracyPrediction();
	        System.out.println("ACCURACY: " + accuracy);
	        System.out.println("");  
	    }
	public static void main(String[] args) throws FileNotFoundException {
	//ASSIGNMENT HEADER INFORMATION IS DISPLAYED
	System.out.println("Programming Fundamentals");
	System.out.println("NAME: Sofia Syed");
	System.out.println("PROGRAMMING ASSIGNMENT 3");
	System.out.println(); //line break
	
	
	trainingDataScanner();
	testingDataScanner();
	closeFlower();
	printResults();	
	}
}
	
		
		
