import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC_Day5 {

    static int points = 0;
    
    public static void fillArr(String[] arr1,int[] arr2) {
        arr2[0] = Integer.valueOf(arr1[0]);
        arr2[1] = Integer.valueOf(arr1[1]);
    }
    
    public static void fillGrid(int[][] grid, int[] p1, int[] p2) {
        
        if(p1[0] == p2[0]) {
            int min = Math.min(p1[1],p2[1]);
            int max = Math.max(p1[1],p2[1]);
            
            while(min <= max) {
                grid[min][p1[0]] += 1;
                if(grid[min][p1[0]] == 2) points++;
                min++;
            }
        }
        else if(p1[1] == p2[1]){
            int min = Math.min(p1[0],p2[0]);
            int max = Math.max(p1[0],p2[0]);
            
            while(min <= max) {
                grid[p1[1]][min]++;
                if(grid[p1[1]][min] == 2) points++;
                min++;
            }
            
        }
        else {
            
            int xInc = (p2[1] > p1[1]) ? 1 : -1;
            int yInc = (p2[0] > p1[0]) ? 1 : -1;
            
            while(p2[0] != p1[0]) {
                
                grid[p1[1]][p1[0]]++;
                
                if(grid[p1[1]][p1[0]] == 2) points++;
                
                p1[0] += yInc;
                p1[1] += xInc;
                
            }
            //need to fill in last slot
            grid[p1[1]][p1[0]]++;
            if(grid[p1[1]][p1[0]] == 2) points++;
        }
    }
    
    public static void main(String args[]) {
        
        try{
            Scanner input = new Scanner(new File("C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_5.txt"));
            
            int[][] grid = new int[1000][1000];
            
            while(input.hasNext()) {
                
                int[] p1 = new int[2];
                int[] p2 = new int[2];
                
                String[] coord = input.next().split(",");
                fillArr(coord,p1);
                
                input.next();
                
                coord = input.next().split(",");
                fillArr(coord,p2);
                
                fillGrid(grid,p1,p2);
                
            }
            
            input.close();
            
            System.out.printf("Part 1: %d\n",points);
            
            for(int i = 0; i < 10; i++) {
                for(int j = 0; j < 10; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println("");
            }
            
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
        
    }
    
}
