import java.util.Scanner;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC_Day4 {
    
    public static void printBoard(int[][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    public static int[][] readBoard(Scanner input) {
        
        int[][] res = new int[5][5];
        
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                res[i][j] = input.nextInt();
            }
        }
        
        return res;
    }
    
    public static void markBoard(int[][] board, int numDraw) {
     
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++) {
                
                if(board[i][j] == numDraw) {
                    board[i][j] = -1;
                    return;
                }
                
            }
        }
        
    }
    
    public static boolean isBingo(int[][] board) {
        
        for(int i = 0; i < 5; i++) {
            
            boolean bingo = true;
            
            for(int j = 0; j < 5; j++) {
                if(board[i][j] >= 0) {
                    bingo = false;
                    break;
                }
            }
            
            if(bingo) return true;
            
        }
        
        for(int i = 0; i < 5; i++) {
            
            boolean bingo = true;
            
            for(int j = 0; j < 5; j++) {
                if(board[j][i] >= 0) {
                    bingo = false;
                    break;
                }
            }
            
            if(bingo) return true;
            
        }
        
        return false;
    }
    
    public static int sumBoard(int[][] board) {
        int sum = 0;
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length;j++) {
                if(board[i][j] >= 0) sum += board[i][j];
            }
        }
        
        return sum;
    }
    
    public static int partOne(int[] nums, ArrayList<int[][]> boards) {
        
        int res = 0;
        
        for(int i = 0; i < nums.length; i++) {
            
            for(int j = 0; j < boards.size(); j++) {
                
                int[][] currBoard = boards.get(j);
                
                markBoard(currBoard,nums[i]);
                
                if(isBingo(currBoard)) {
                    
                    int sum = sumBoard(currBoard);
                    
                    return sum * nums[i];
                    
                }
            }
            
        }
        
        return res;
        
    }
    
    public static int partTwo(int[] nums, ArrayList<int[][]> boards) {
        int res = 0;
        
        HashSet<Integer> set = new HashSet<>();
        
        for(int i = 0; i < nums.length; i++) {
            
            for(int j = 0; j < boards.size(); j++) {
                
                if(set.contains(j)) {
                    continue;
                }
                
                int[][] currBoard = boards.get(j);
                
                markBoard(currBoard,nums[i]);
                
                if(isBingo(currBoard)) {
                    
                    if(set.size() == boards.size() - 1) {
                        
                        int sum = sumBoard(currBoard);
                        
                        return sum * nums[i];
                        
                    }
                    
                    set.add(j);
                    
                }
            }
            
        }
        
        return res;
    }
    
    public static void main(String args[]) {
        
        try {
            Scanner input = new Scanner(new File("C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_4.txt"));
            
            ArrayList<int[][]> boards = new ArrayList<>();
            String[] tmp = input.next().split(",");
            int[] nums = new int[tmp.length];
            
            for(int i = 0; i < tmp.length; i++) {
                nums[i] = Integer.valueOf(tmp[i]);
            }
            
            while(input.hasNext()) {
                boards.add(readBoard(input));
            }
            
            input.close();
            
            System.out.printf("Part 1: %d\n", partOne(nums,boards));
            System.out.printf("Part 2: %d\n", partTwo(nums,boards));
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
        
    }
}
