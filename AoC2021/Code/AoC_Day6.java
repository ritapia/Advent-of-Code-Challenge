import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC_Day6 {
    
    public static void initializeMap(String[] arr, HashMap<Integer,Long> map) {
            //initialize map
            for(int i = 0; i < 9; i++) {
                map.put(i, (long)0);
            }
            
            for(String x : arr) {
                int val = Integer.valueOf(x);
                map.put(val, map.get(val) + 1);
            }
    }
    
    public static void nextDay(HashMap<Integer,Long> map) {
        long prev = map.get(8);
        
        for(int i = 7; i >= 0; i--) {
            long tmp = map.get(i);
            map.put(i, prev);
            prev = tmp;
        }
        
        map.put(6,map.get(6) + prev);
        map.put(8, prev);
    }
    
    public static long sumFish(HashMap<Integer,Long> map) {
        long sum = 0;
        
        for(long x : map.values()) {
            sum += x;
        }
        
        return sum;
    }
    
    public static void main(String args[]) {
        
        try {
            Scanner input = new Scanner(new File("C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_6.txt"));
            
            String[] tmp = input.next().split(",");
            
            input.close();
            
            HashMap<Integer,Long> fish = new HashMap<>();
            
            initializeMap(tmp,fish);
            
            int daySim = 80;
            
            for(int i = 0; i < daySim; i++) {
                
                nextDay(fish);
                
            }
            
            System.out.printf("Part 1: %d\n",sumFish(fish));
            
            int daySim2 = 256;
            
            for(int i = 80; i < daySim2; i++) {
                
                nextDay(fish);
                
            }
            
            System.out.printf("Part 2: %d\n",sumFish(fish));
            
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
        
    }
    
}
