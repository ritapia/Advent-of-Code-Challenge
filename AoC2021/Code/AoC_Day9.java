import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class AoC_Day9 {
    
    public static int findLowPointsSum(ArrayList<ArrayList<Integer>> graph) {
        
        int res = 0;
        int m = graph.size();
        int n = graph.get(0).size();
        
        for(int i = 0; i < m; i++) {
            
            for(int j = 0; j < n; j++) {
                
                if(isLowPoint(graph, i, j)) {
                    res += graph.get(i).get(j) + 1;
                }
                
            }
            
        }
        
        return res;
    }
    
    private static boolean isLowPoint(ArrayList<ArrayList<Integer>> graph, int row, int col) {
        
        int up = Integer.MAX_VALUE;
        int down = Integer.MAX_VALUE;
        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        
        ArrayList<Integer> currRow = graph.get(row);
        int val = currRow.get(col);
        
        if(row - 1 >= 0) {
            up = graph.get(row-1).get(col);
        }
        if(row + 1 < graph.size()) {
            down = graph.get(row+1).get(col);
        }
        if(col - 1 >= 0) {
            left = currRow.get(col - 1);
        }
        if(col + 1 < currRow.size()) {
            right = currRow.get(col + 1);
        }
        
        
        return (val < up && val < down && val < left && val < right);
    }
    
    public static int prodOfBasins(ArrayList<ArrayList<Integer>> graph) {
        
        int max1 = 0;
        int max2 = 0;
        int max3 = 0;
        
        for(int i = 0; i < graph.size(); i++) {
            
            for(int j = 0; j < graph.get(i).size(); j++) {
                
                if(isLowPoint(graph,i,j)) {
                    
                    int val = bfs(graph,i,j);
                    
                    if(val > max1) {
                        max3 = max2;
                        max2 = max1;
                        max1 = val;
                    }
                    else if(val > max2) {
                        max3 = max2;
                        max2 = val;
                    }
                    else if(val > max3) {
                        max3 = val;
                    }
                    
                }
                
            }
            
        }
        
        //System.out.println(max1);
        //System.out.println(max2);
        //System.out.println(max3);
        
        return max1 * max2 * max3;
    }
    
    private static int bfs(ArrayList<ArrayList<Integer>> graph, int row, int col) {
        
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{row,col});
        
        int m = graph.size();
        int n = graph.get(0).size();
        
        boolean[][] visited = new boolean[m][n];
        
        int res = 0;
        
        while(!q.isEmpty()) {
            int size = q.size();
            
            for(int i = 0; i < size; i++) {
                
                int[] coord = q.poll();
                int val = graph.get(coord[0]).get(coord[1]);
                
                if(val == 9) continue;
                res++;
                
                if(coord[0] - 1 >= 0 && !visited[coord[0] - 1][coord[1]]) {
                    if(graph.get(coord[0]-1).get(coord[1]) > val) {
                        q.push(new int[]{coord[0]-1,coord[1]});
                        visited[coord[0] - 1][coord[1]] = true;
                    }
                }
                if(coord[1] - 1 >= 0 && !visited[coord[0]][coord[1]-1]) {
                    if(graph.get(coord[0]).get(coord[1]-1) > val) {
                       q.push(new int[]{coord[0],coord[1]-1});
                       visited[coord[0]][coord[1]-1] = true;
                    }
                }
                if(coord[0] + 1 < m && !visited[coord[0]+1][coord[1]]) {
                    if(graph.get(coord[0]+1).get(coord[1]) > val) {
                        q.push(new int[]{coord[0]+1,coord[1]});
                        visited[coord[0]+1][coord[1]]= true;
                    }
                }
                if(coord[1] + 1 < n && !visited[coord[0]][coord[1]+1]) {
                    if(graph.get(coord[0]).get(coord[1]+1) > val) {
                        q.push(new int[]{coord[0],coord[1]+1});
                        visited[coord[0]][coord[1]+1] = true;
                    }
                }
                
            }
            
        }
        
        return res;
    }
    
    public static void main(String args[]) {
        
        try {
            Scanner input = new Scanner(new File("C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_9.txt"));
            
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            
            while(input.hasNext()) {
                
                
                String line = input.nextLine();
                ArrayList<Integer> row = new ArrayList<>(line.length());
                
                for(int i = 0; i < line.length(); i++) {
                    
                    char c = line.charAt(i);
                    int val = c - '0';
                    
                    row.add(val);
                    
                }
                
                graph.add(row);
                
            }
            
            System.out.printf("Part 1: %d\n", findLowPointsSum(graph));
            System.out.printf("Part 2: %d \n", prodOfBasins(graph)); //786780
            
            for(ArrayList<Integer> r : graph) {
                //System.out.println(r.toString());
            }
            
            input.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
        
    }
}
