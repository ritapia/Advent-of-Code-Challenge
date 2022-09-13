import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC_Day7 {
    
    public static int sumNewPos(int[] arr, int pos) {
        int sum = 0;
        
        for(int i = 0; i < arr.length; i++) {
            int absSum = Math.abs(arr[i] - pos);
            sum += absSum;
        }
        
        return sum;
    }
    
    public static int sumNewPos2(int[] arr, int pos) {
        int sum = 0;
        
        for(int i = 0; i < arr.length; i++) {
            int absSum = Math.abs(arr[i] - pos);
            sum += increasingSum(absSum);
        }
        
        return sum;
    }
    
    public static int increasingSum(int dis) {
        return (int)(dis *((dis + 1) / 2.0));
    }
    
    public static void main(String args[]) {
        try {
            
            Scanner input = new Scanner(new File("C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_7.txt"));
        
            String[] tmp = input.next().split(",");
            
            input.close();
            
            int[] arr = new int[tmp.length];
            
            for(int i = 0; i < tmp.length; i++) {
                arr[i] = Integer.valueOf(tmp[i]);
            }
            
            Arrays.sort(arr);
            
            int res = Integer.MAX_VALUE;
            
            for(int i = arr[0]; i <= arr[arr.length-1]; i++) {
                int sum = sumNewPos(arr,i);
                if(sum < res) {
                    res = sum;
                }
            }
            
            System.out.printf("Part 1: %d\n",res);
            
            res = Integer.MAX_VALUE;
            
            for(int i = arr[0]; i <= arr[arr.length-1]; i++) {
                int sum = sumNewPos2(arr,i);
                if(sum < res) {
                    res = sum;
                }
            }
            
            System.out.printf("Part 2: %d\n",res);
            
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
        
    }
    
}
