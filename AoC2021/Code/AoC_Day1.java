import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

class AoC_Day1 {
    
    /* Part One parses through the array and counts the amount of times a measurement increases in the array. */
    private static int partOne(ArrayList<Integer> arr) {
        
        int res = 0; //res
        
        //loop through the integer and count increase
        for(int i = 1; i < arr.size(); i++) {
            
            //check for increase in measurement
            if(arr.get(i-1) < arr.get(i)) {
                
                res++; //inrement result
                
            }
            
        }
        
        return res;
        
    }
    
    /* 
     * Part Two parses through the array with 2 sliding windows. 
     * The sliding windows each look at three values in the array and sum them.
     * Sliding window sums are compared and the amount of increments are counted.
     */
    private static int partTwo(ArrayList<Integer> arr) {
        
        int res = 0;
        
        int prevSum = arr.get(0) + arr.get(1) + arr.get(2); //initialize first window
        int currSum = prevSum; //second window
        
        //loop through the array and compare windows
        for(int i = 3; i < arr.size(); i++) {
            
            //remove end of prev array and add newest value to get current window for comparison with prev
            currSum = (currSum - arr.get(i - 3)) + arr.get(i);
            
            if(currSum > prevSum) {
                
                res++;
                
            }
            
            prevSum = currSum;
            
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        
        try {
            
            String filePath = "C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_1.txt";
            Scanner file = new Scanner(new File(filePath));
            
            ArrayList<Integer> arr = new ArrayList<>();
            
            //loop through file and add integers to array
            while(file.hasNext()) {
                arr.add(file.nextInt());
            }
            
            file.close();
            
            System.out.printf("Part 1: %d\n", partOne(arr));
            System.out.printf("Part 2: %d\n", partTwo(arr));
            
        } catch (FileNotFoundException ex) {
            
            System.out.println(ex);
            
        }
        
    }
    
}