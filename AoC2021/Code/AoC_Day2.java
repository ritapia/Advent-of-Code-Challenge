import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC_Day2 {
    
    private static class Node {
        
        private int xVal = 0;
        private int yVal = 0;
        private Node next = null;
        
        Node() {}
        
        Node(int x, int y) {
            this.xVal = x;
            this.yVal = y;
        }
        
        public int getX() {
            return this.xVal;
        }
        
        public int getY() {
            return this.yVal;
        }
        
    }
    
    public static int partOne(Node head) {
        
        int x = 0;
        int y = 0;
        
        while(head != null) {
            x += head.getX();
            y += head.getY();
            head = head.next;
        }
        
        int res = x * y;
        
        return res;
        
    }
    
    public static int partTwo(Node head) {
        
        int x = 0;
        int y = 0;
        int aim = 0;
        
        while(head != null) {
            
            int tmpX = head.getX();
            int tmpY = head.getY();
            if(tmpY > 0) {
                aim += tmpY;
            }
            if(tmpY < 0) {
                aim += tmpY;
            }
            if(tmpX > 0) {
                y += (aim * tmpX);
            }
            
            x += tmpX;
            //y += tmpY; change of rules for depth change
            
            head = head.next;
            
        }
        
        int res = x * y;
        
        return res;
    }
    
    public static void main(String args[]) {
        
        try {
            
            Scanner input = new Scanner(new File("C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_2.txt"));
            
            Node dummy = new Node();
            Node head = dummy;
            
            while(input.hasNext()) {
                String command = input.next();
                int x = 0;
                int y = 0;
                
                switch(command) {
                    case "forward":
                        x += input.nextInt();
                        break;
                    case "down":
                        y += input.nextInt();
                        break;
                    case "up":
                        y -= input.nextInt();
                        break;
                    default:
                        break;
                }
                
                head.next = new Node(x,y);
                head = head.next;
                
            }
            
            input.close();
            
            System.out.printf("Part 1: %d\n", partOne(dummy.next));
            System.out.printf("Part 2: %d\n", partTwo(dummy.next));
            
        } catch(FileNotFoundException ex) {
            
            System.out.println(ex);
            
        }
        
    }
    
}
