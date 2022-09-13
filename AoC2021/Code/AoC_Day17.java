import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC_Day17 {
    
    public static int sumOfN(int n) {
        return (n * (n + 1)) / 2;
    }
    
    public static int[] buildBound(String tmp) {
        
        int[] res = new int[2];
        
        String[] vals = tmp.split("\\..");
        
        res[0] = Integer.valueOf(vals[0]);
        res[1] = Integer.valueOf(vals[1]);
        
        return res;
    }
    
    public static int[][] getBounds(String s) {
        
        int[][] res = new int[2][2];
        
        String[] tmp = s.split(",");
        tmp[1] = tmp[1].substring(3);
        
        res[1] = buildBound(tmp[1]);
        res[0] = buildBound(tmp[0]);
        
        return res;
    }
    
    public static boolean validTestFlight(int[][] bounds, int x, int y) {
        
        int currX = 0;
        int currY = 0;
        
        while(currX <= bounds[0][1] && currY >= bounds[1][0]) {
            
            if(currX >= bounds[0][0] && currX <= bounds[0][1]) {
                if(currY >= bounds[1][0] && currY <= bounds[1][1]) {
                    return true;
                }
            }
            
            currX += x;
            currY += y;
            if(x != 0) x--;
            y--;
            
        }
        
        return false;
        
    }
    
    public static int partTwo(int[][] bounds) {
        
        int low = 0;
        int hi = bounds[0][1];
        
        while(low < hi) {
            
            int mid = low + (hi - low) / 2;
            
            if(sumOfN(mid) < bounds[0][0]) {
                low = mid + 1;
            }
            else {
                hi = mid;
            }
        }
        
        int y = sumOfN(bounds[1][0]);
        
        int count = 0;
        
        for(int i = low; i <= bounds[0][1]; i++) {
            for(int j = y; j >= bounds[1][0]; j--) {
                
                if(validTestFlight(bounds,i,j)) count++;
                
            }
        }
        
        //System.out.printf("X: %d loY: %d hiY %d\n",6,lowYBound(bounds,y,6),hiYBound(bounds,y,6)); //0 9
        
        return count;
    }
    
    
    
    public static void main(String args[]) {
        
        try {
            
            String path = "C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_17.txt";
            Scanner input = new Scanner(new File(path));
            
            String s = input.nextLine();
            
            int[][] bounds = getBounds(s.substring(15));
            
            System.out.printf("Part 1: %d\n", sumOfN(bounds[1][0]));
            System.out.printf("Part 2: %d\n", partTwo(bounds));
            input.close();
            
        } 
        catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
        
    }
    
}
