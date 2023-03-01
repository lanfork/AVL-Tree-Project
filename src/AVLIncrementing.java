/* 
 * Writes incrementing numbers into files and into array. Inserts into AVL tree.
 * Shuffles array and deletes 1000 numbers from the tree.
 * Times for the insertion and deletion are printed to files and console.
 * 
 * Kimberly Lanford CSCI321-01M-SUMMER-2022
 */
import java.io.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AVLIncrementing {
	
	static AVL<Integer> tree = new AVL<>( );
	static int arr[];
	static int input = 0;
	static String name;
	static long startTimeInsertion, startTimeDeletion, endTimeInsertion, endTimeDeletion;
	
    public static void main(String[]args) throws IOException  {
    	
    //loops through entire program for 5 different files and inputs
    for(int i = 1; i <= 5; i++) {	
    	
    	name = "A"+i+".txt";
           
    	if (name.contains("A1")) 
    		input = 2000;
           
    	if (name.contains("A2")) 
    		input = 4000;
           
    	if (name.contains("A3")) 
    		input = 6000;
           
    	if (name.contains("A4")) 
    		input = 8000;
            
    	if (name.contains("A5"))
    		input = 10000;
    	
    	//writes the current input of random numbers into current the file name
    	try (PrintWriter file = new PrintWriter(
    			new BufferedWriter(
    					new FileWriter(name)));			
    			) {
    		
    		arr = new int[input];
    		
    		for (int j =0, k = 1; k <= input; k++) {
    			arr[j] = k;
    			while(arr[j] != k)
    				file.println(arr[j]);
    			j++;
    		}
                   
    	} catch (IOException e) {
    		System.out.println("Error reading/writing file!");
    		e.printStackTrace();
    	}
    	
    	//prints when the current file has completed inserting the incrementing numbers
    	System.out.println();
    	System.out.println("File " + name + " has been created!");
         
    	// inserts into AVL tree
    	for(int i1 = 0; i1 < arr.length; i1++) {
    		startTimeInsertion = System.nanoTime();
    		tree.insert(i1);	
        	endTimeInsertion = System.nanoTime();
        	}
    	
    	 //shuffles arrray and deletes the first 1000 elements.
        shuffleArray(arr);
        for(int i1=0;i1<1000; i1++){	
 	        startTimeDeletion = System.nanoTime();
 	       tree.remove(i1);
 	        endTimeDeletion = System.nanoTime();
        }
        
        
 	 	// sleep for 5 seconds
 	 	    try {
 	 			TimeUnit.SECONDS.sleep(5);
 	 		} catch (InterruptedException e) {
 	 			// TODO Auto-generated catch block
 	 			e.printStackTrace();
 	 		}


 	 	// get the difference between the two nano time valuess.
 	 	    // writes the differences to file and prints to console.
 	 	    long timeElapsed = endTimeInsertion - startTimeInsertion;
 	 	    long timeElapsed2 = endTimeDeletion - startTimeDeletion;
 	 	    

 	 	    System.out.println("Execution time for insertion in nanoseconds: " + timeElapsed);
 	 	    System.out.println("Execution time for deletion in nanoseconds: " + timeElapsed2);
 	 	  
 	 	    

 	 	 	try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("results"+name)))) {
 	 	 		
 		 	 	    writer.write(timeElapsed + " " + timeElapsed2);
 	 	 		
 		 	  } catch (Exception e_ ) {
 		 		  System.out.println("File writer exception!");
 		 	  }
    }
  }
    
  //method called to shuffle the array
    public static int[] shuffleArray(int[] arr){
    	
        Random rand = new Random();
        
        for(int i = 0; i < arr.length; i++){
            int r = rand.nextInt(arr.length);//generate a random number from 0 to X
            int k = arr[i];
            arr[i] = arr[r];
            arr[r] = k;
        }
     return arr;
    }
    
}

