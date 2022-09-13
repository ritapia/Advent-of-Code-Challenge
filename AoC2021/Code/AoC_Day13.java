import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC_Day13 {
    
    public static ArrayList<int[]> buildData(Scanner input) {
        
        String curr = input.nextLine();
        ArrayList<int[]> res = new ArrayList<>();
        
        while(curr.compareTo("") != 0) {
            
            String[] tmp = curr.split(",");
            
            int[] vals = new int[]{Integer.valueOf(tmp[0]),Integer.valueOf(tmp[1])};
            
            res.add(vals);
            
            curr = input.nextLine();
        }
        
        return res;
    }
    
    public static void fold(int loc, boolean foldY, ArrayList<int[]> arr) {
        
        int subVal = loc * 2;
        
        for(int[] x : arr) {
            
            if(foldY) {
                if(x[1] > loc) {
                    x[1] = Math.abs(subVal - x[1]);
                }
            }
            else {
                if(x[0] > loc) {
                    x[0] = Math.abs(subVal - x[0]);
                }
            }
        }
        
    }
    
    public static void main(String args[]) {
        
        try {
            
            Scanner input = new Scanner(new File("C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_13.txt"));
            
            ArrayList<int[]> data = buildData(input);
            boolean isFirst = true;
            HashMap<Integer,HashSet<Integer>> map = new HashMap<>();
            
            while(input.hasNextLine()) {
                
                String[] arr = input.nextLine().split("=");
                int amt = Integer.valueOf(arr[1]);
                
                if(arr[0].charAt(arr[0].length()-1) == 'x') {
                    fold(amt,false,data);
                }
                else {
                    fold(amt,true,data);
                }
                
                if(isFirst) {
                    for(int[] x : data) {
                        HashSet<Integer> set = map.getOrDefault(x[0],new HashSet<>());
                        set.add(x[1]);
                        map.put(x[0], set);
                    }
            
                    int res = 0;
            
                    for(int key : map.keySet()) {
                        res += map.get(key).size();
                    }
                    System.out.printf("Part 1: %d\n", res);
                    isFirst = !isFirst;
                }
                
            }
            
            /*data.sort((a,b) -> a[0] - b[0]);
            
            for(int[] x : data) {
                System.out.printf("[%d,%d]\n",x[0],x[1]);
            }*/
            
            map = new HashMap<>();
            
            for(int[] x : data) {
                HashSet<Integer> set = map.getOrDefault(x[0],new HashSet<>());
                set.add(x[1]);
                map.put(x[0], set);
            }
            
            char[][] matrix = new char[6][39];
            
            for(int[] x : data) {
                HashSet<Integer> set = map.get(x[0]);
                Integer[] arr = set.toArray(new Integer[set.size()]);
                for(int i = 0; i < arr.length; i++) {
                    matrix[arr[i]][x[0]] = '#';
                }
            }
            System.out.println("Part 2:");
            for(int i = 0; i < 6; i++) {
                for(int j = 0; j < 39; j++) {
                    
                    if(matrix[i][j] != '#') System.out.print(".");
                    else System.out.print(matrix[i][j]);
                }
                System.out.println("");
            }
            
        } catch(FileNotFoundException ex) {
            
            System.out.println(ex);
            
        }
    }
    
}
