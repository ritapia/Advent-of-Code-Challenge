import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC_Day12 {
    
    public static class Node {
        
        static boolean twice = false;
        
        private final String name;
        
        private final boolean isStart;
        private final boolean isEnd;
        
        private final boolean isSmall;
        
        protected int count = 0;
        
        private boolean visited;
        
        private ArrayList<Node> adjacent;
        
        Node(String name) {
            
            this.name = name;
            
            isSmall = name.compareTo(name.toLowerCase()) == 0;
            
            isStart = name.compareTo("start") == 0;
            isEnd = name.compareTo("end") == 0;
            
            visited = false;
            
            adjacent = new ArrayList<>();
        }
        
        public void addAdjacent(Node n) {
            
            adjacent.add(n);
            
        }
        
        public String getName() {
            return name;
        }
        
        public ArrayList<Node> getAdjacent() {
            return adjacent;
        }
        
        public boolean isEnd() {
            return isEnd;
        }
        
        public boolean isSmall() {
            return isSmall;
        }
        
        public boolean isVisited() {
            return visited;
        }
        
        public void setVisited(boolean val) {
            visited = val;
        }
        
    }
    
    public static void buildNodes(String name1, String name2, HashMap<String,Node> map) {
        
        Node n1 = (map.containsKey(name1)) ? map.get(name1) : new Node(name1);
        Node n2 = (map.containsKey(name2)) ? map.get(name2) : new Node(name2);
        
        n1.addAdjacent(n2);
        n2.addAdjacent(n1);
        
        map.put(name1, n1);
        map.put(name2, n2);
        
    }
    
    public static int partOne(Node node, HashMap<String,Node> map) {
        
        if(node.isEnd())  return 1;
        if(node.isVisited()) return 0;
        
        if(node.isSmall()) node.setVisited(true);
        
        ArrayList<Node> adjacent = node.getAdjacent();
        
        int sum = 0;
        
        for(Node n : adjacent) {
            sum += partOne(n,map);
        }
        
        if(node.isSmall()) node.setVisited(false);
        
        return sum;
    }

    public static int partTwo(Node node, HashMap<String,Node> map) {
        
        if(node.isEnd()) return 1;
        if((node.isVisited() && node.twice) || (node.isStart && node.count > 0)) return 0;
        
        if(node.isSmall()) {
            node.count++;
            if(node.count > 1) node.twice = true;
            node.setVisited(true);
        }
        
        
        ArrayList<Node> adjacent = node.getAdjacent();
        
        int sum = 0;
        
        for(Node n : adjacent) {
            sum += partTwo(n,map);
        }
        
        if(node.isSmall()) {
            
            node.count--;
            
            if(node.count > 0) {
                node.twice = false;
            }
            else node.setVisited(false);
        }
        
        return sum;
        
    }
    
    public static void main(String args[]) {
        
        try {
            
            Scanner input = new Scanner(new File("C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_12.txt"));
            HashMap<String,Node> map = new HashMap<>();
            
            while(input.hasNext()) {
                
                String[] edge = input.nextLine().split("-");
                
                buildNodes(edge[0],edge[1],map);
                
            }
            
            input.close();
            
            Node start = map.get("start");
            
            System.out.printf("Part 1: %d\n", partOne(start,map));
            System.out.printf("Part 2: %d\n", partTwo(start,map));
            
        } catch(FileNotFoundException ex) {
            
            System.out.println(ex);
        }
        
    }
}
