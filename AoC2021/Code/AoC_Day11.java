import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC_Day11 {
    
    public static int nextStep(int[][] grid) {
        
        boolean[][] flashed = new boolean[10][10];
        
        for(int i = 0; i < 10; i++ ) {
            
            for(int j = 0; j < 10; j++) {
                
                grid[i][j]++;
                
                if(grid[i][j] > 9 && !flashed[i][j])  {
                    flashed[i][j] = true;
                    incrementSurrounding(grid,flashed,i,j);
                }
                
            }
            
        }
        
        int flashes = 0;
        
        //find flash points and set to zero
        for(int i = 0; i < 10; i++ ) {
            for(int j = 0; j < 10; j++) {
                if(grid[i][j] > 9) {
                    grid[i][j] = 0;
                    flashes++;
                }
            }
        }
        
        return flashes;
    }
    
    private static void incrementSurrounding(int[][] grid, boolean[][] flashed, int row, int col) {
        
        if(row - 1 >= 0) {
            
            grid[row-1][col]++;
            
            if(col - 1 >= 0)
                grid[row-1][col-1]++;
            
            if(col + 1 < 10)
                grid[row-1][col+1]++;
            
        }
        
        if(col - 1 >= 0) {
            
            grid[row][col-1]++;
            
        }
        
        if(col + 1 < 10) {
            
            grid[row][col+1]++;
            
        }
        
        if(row + 1 < 10) {
            
            grid[row+1][col]++;
            
            if(col - 1 >= 0)
                grid[row+1][col-1]++;
            
            if(col + 1 < 10)
                grid[row+1][col+1]++;
            
        }
        
        if(row - 1 >= 0) {
            
            if(grid[row-1][col] > 9 && !flashed[row-1][col]) {
                flashed[row-1][col] = true;
                incrementSurrounding(grid,flashed,row-1,col);
            }
            
            if(col - 1 >= 0) {
                if(grid[row-1][col-1] > 9 && !flashed[row-1][col-1]) {
                    flashed[row-1][col-1] = true;
                    incrementSurrounding(grid,flashed,row-1,col-1);
                }
            }
            
            if(col + 1 < 10) {
                if(grid[row-1][col+1] > 9 && !flashed[row-1][col+1]) {
                    flashed[row-1][col+1] = true;
                    incrementSurrounding(grid,flashed,row-1,col+1);
                }
            }
            
        }
        
        if(col - 1 >= 0) {
            if(grid[row][col-1] > 9 && !flashed[row][col-1]) {
                flashed[row][col-1] = true;
                incrementSurrounding(grid,flashed,row,col-1);
            }
        }
        
        if(col + 1 < 10) {
            if(grid[row][col+1] > 9 && !flashed[row][col+1]) {
                flashed[row][col+1] = true;
                incrementSurrounding(grid,flashed,row,col+1);
            }
        }
        
        if(row + 1 < 10) {
            
            if(grid[row+1][col] > 9 && !flashed[row+1][col]) {
                flashed[row+1][col] = true;
                incrementSurrounding(grid,flashed,row+1,col);
            }
            
            if(col - 1 >= 0) {
                if(grid[row+1][col-1] > 9 && !flashed[row+1][col-1]) {
                    flashed[row+1][col-1] = true;
                    incrementSurrounding(grid,flashed,row+1,col-1);
                }
            }
            
            if(col + 1 < 10) {
                if(grid[row+1][col+1] > 9 && !flashed[row+1][col+1]) {
                    flashed[row+1][col+1] = true;
                    incrementSurrounding(grid,flashed,row+1,col+1);
                }
            }
            
        }
        
    }
    
    private static void print(int[][] grid) {
        for(int[] x : grid) {
            for(int y : x) {
                System.out.print(y + " ");
            }
            System.out.println("");
        }
    }
    
    public static boolean partTwo(int[][] grid) {
        
        boolean[][] flashed = new boolean[10][10];
        
        for(int i = 0; i < 10; i++ ) {
            
            for(int j = 0; j < 10; j++) {
                
                grid[i][j]++;
                
                if(grid[i][j] > 9 && !flashed[i][j])  {
                    flashed[i][j] = true;
                    incrementSurrounding(grid,flashed,i,j);
                }
                
            }
            
        }
        
        for(int i = 0; i < 10; i++ ) {
            for(int j = 0; j < 10; j++) {
                if(grid[i][j] > 9) {
                    grid[i][j] = 0;
                }
            }
        }
        
        for(int[] x : grid) {
            for(int y : x) {
                if(y != 0) return false;
            }
        }
        
        return true;
        
    }
    
    public static void main(String args[]) {
        
        try {
            
            Scanner input = new Scanner(new File("C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_11.txt"));
            
            int[][] grid = new int[10][10];
            
            for(int i = 0; i < 10; i++) {
                String curr = input.next();
                for(int j = 0; j < 10; j++) {
                    int val = curr.charAt(j) - '0';
                    grid[i][j] = val;
                }
            }
            
            input.close();
            
            int steps = 100;
            int res1 = 0;
            
            for(int i = 0; i < steps; i++) {
                res1 += nextStep(grid);
            }
            
            System.out.printf("Part 1: %d\n", res1);
            //System.out.printf("\nStep: %d\n",steps);
            //print(grid);
            
            int res2 = 101;
            
            while(!partTwo(grid)) {
                if(res2 > 500) break;
                res2++;
            }
            
            System.out.printf("Part 2: %d\n", res2);
            
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
        
    }
}
