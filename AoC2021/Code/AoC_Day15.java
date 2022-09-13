import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC_Day15 {
    
    public static class Node {
        
        boolean isVisited;
        
        int row = 0;
        int col = 0;
        int cost;
        int totalCost;
        
        Node(int row, int col, int cost) {
            
            this.row = row;
            this.col = col;
            
            this.cost = cost;
            totalCost = Integer.MAX_VALUE;
            
            isVisited = false;
            
            
        }
        
    }
    
    public static int partOne(ArrayList<ArrayList<Node>> graph) {
        
        PriorityQueue<Node> q = new PriorityQueue<>((a,b) -> Integer.compare(a.totalCost,b.totalCost));
        
        Node tmp = graph.get(0).get(0);
        tmp.totalCost = 0;
        
        q.add(graph.get(0).get(0));
        
        int targetRow = graph.size() - 1;
        int targetCol = graph.get(0).size() - 1;
        
        while(!q.isEmpty()) {
            
            int size = q.size();
            
            for(int i = 0; i < size; i++) {
                
                Node curr = q.poll();
                curr.isVisited = true;
                
                if(targetRow == curr.row && targetCol == curr.col) {
                    
                    return curr.totalCost;
                    
                }
                
                addAdjacent(graph,q,curr);
                
            }
            
        }
        
        return -1;
    }
    
    public static void addAdjacent(ArrayList<ArrayList<Node>> graph, PriorityQueue<Node> q, Node curr) {
        
        int row = curr.row;
        int col = curr.col;
        
        if(row - 1 >= 0) {
            Node tmp = graph.get(row-1).get(col);
            if(!tmp.isVisited) {
                if(curr.totalCost + tmp.cost < tmp.totalCost) {
                    //q.remove(tmp);
                    tmp.totalCost = curr.totalCost + tmp.cost;
                    q.add(tmp);
                }
            }
        }
        if(col - 1 >= 0) {
            Node tmp = graph.get(row).get(col-1);
            if(!tmp.isVisited) {
                if(curr.totalCost + tmp.cost < tmp.totalCost) {
                    //q.remove(tmp);
                    tmp.totalCost = curr.totalCost + tmp.cost;
                    q.add(tmp);
                }
            }
        }
        if(row + 1 < graph.size()) {
            Node tmp = graph.get(row+1).get(col);
            if(!tmp.isVisited) {
                if(curr.totalCost + tmp.cost < tmp.totalCost) {
                    //q.remove(tmp);
                    tmp.totalCost = curr.totalCost + tmp.cost;
                    q.add(tmp);
                }
            }
        }
        if(col + 1 < graph.get(0).size()) {
            Node tmp = graph.get(row).get(col+1);
            if(!tmp.isVisited) {
                if(curr.totalCost + tmp.cost < tmp.totalCost) {
                    //q.remove(tmp);
                    tmp.totalCost = curr.totalCost + tmp.cost;
                    q.add(tmp);
                }
            }
        }
        
        
    }
    
    public static int partTwo(ArrayList<ArrayList<Node>> graph) {
        
        int rowLen = graph.size();
        int colLen = graph.get(0).size();
        
        for(ArrayList<Node> x : graph) {
            for(Node n : x) {
                n.isVisited = false;
                n.totalCost = Integer.MAX_VALUE;
            }
        }
        
        for(int row = 0; row < rowLen; row++) {
            for(int col = colLen; col < colLen * 5; col++) {
                
                Node prev = graph.get(row).get(col-colLen);
                
                int cost = (prev.cost + 1) % 10;
                if(cost == 0) cost = 1;
                
                Node curr = new Node(row,col,cost);
                graph.get(row).add(curr);
            }
        }
        
        colLen = graph.get(0).size();
        
        for(int row = 0; row < rowLen * 4; row++) {
            
            ArrayList<Node> buildRow = new ArrayList<>(colLen);
            
            for(int col = 0; col < colLen; col++) {
                
                Node prev = graph.get(row).get(col);
                
                int cost = (prev.cost + 1) % 10;
                if(cost == 0) cost = 1;
                
                Node curr = new Node(row+rowLen,col,cost);
                
                buildRow.add(curr);
                
            }
            
            graph.add(buildRow);
            
        }
        
        return partOne(graph);
    }
    
    public static void main(String args[]) {
        
        try {
            
            String path = "C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_15.txt";
            
            Scanner input = new Scanner(new File(path));
            
            ArrayList<ArrayList<Node>> graph = new ArrayList<>();
            
            int row = 0;
            
            while(input.hasNext()) {
                
                String data = input.nextLine();
                
                ArrayList<Node> currRow = new ArrayList<>();
                
                for(int col = 0; col < data.length(); col++) {
                    
                    int val = data.charAt(col) - '0';
                    
                    currRow.add(new Node(row,col,val));
                    
                }
                
                graph.add(currRow);
                
                row++;
            }
            
            input.close();
            
            System.out.printf("Part 1: %d\n", partOne(graph));
            System.out.printf("Part 2: %d\n", partTwo(graph));
            
            
        } catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
        
    }
    
}
