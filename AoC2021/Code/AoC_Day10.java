import java.util.Scanner;
import java.util.Stack;
import java.util.HashSet;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC_Day10 {
    
    static HashMap<Character,Integer> map = new HashMap<>(){
        {
        put(')',3);
        put(']',57);
        put('}',1197);
        put('>',25137);
        }
    };
    
    public static int partOne(String data) {
        
        Stack<Character> stack = new Stack<>();
        
        HashSet<Character> open = new HashSet<>(){{
            add('(');
            add('[');
            add('{');
            add('<');
        }};
        
        for(int i = 0; i < data.length(); i++) {
            
            char c = data.charAt(i);
            
            if(open.contains(c)) {
                
                stack.push(c);
                
            }
            else if(stack.isEmpty()) {
                
                return map.get(c);
                
            }
            else {
                
                char top = stack.peek();
                
                switch(top) {
                    
                    case '(' -> {
                        if(c != ')') return map.get(c);
                    }
                    case '[' -> {
                        if(c != ']') return map.get(c);
                    }
                    case '{' -> {
                        if(c != '}') return map.get(c);
                    }
                    case '<' -> {
                        if(c != '>') return map.get(c);
                    }
                    
                }
                
                stack.pop();
                
            }
            
        }
        
        
        return 0;
        
    }
    
    public static Long partTwo(String data) {
        
        Stack<Character> stack = new Stack<>();
        
        HashSet<Character> open = new HashSet<>(){{
            add('(');
            add('[');
            add('{');
            add('<');
        }};
        
        for(int i = 0; i < data.length(); i++) {
            
            char c = data.charAt(i);
            
            if(open.contains(c)) {
                
                stack.push(c);
                
            }
            else {
                
                stack.pop();
                
            }
            
        }
        
        long res = 0;
        
        while(!stack.isEmpty()) {
            
            char c = stack.pop();
            
            res *= 5;
            
            switch(c) {
                case '(' -> {
                    res += 1;
                }
                case '[' -> {
                    res += 2;
                }
                case '{' -> {
                    res += 3;
                }
                case '<' -> {
                    res += 4;
                }
            }
            
        }
        
        return res;
    }
    
    public static void main(String args[]) {
        
        try {
            Scanner input = new Scanner(new File("C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_10.txt"));
            
            int res1 = 0;
            
            PriorityQueue<Long> q = new PriorityQueue<>();
            
            while(input.hasNext()) {
                String tmp = input.nextLine();
                
                int tmpVal = partOne(tmp);
                
                res1 += tmpVal;
                if(tmpVal == 0) q.add(partTwo(tmp));
                
            }
            
            input.close();
            
            int size = q.size();
            long res2 = 0;
            
            for(int i = 0; i < size; i++) {
                long buff = q.poll();
                if(i == size / 2) {
                    res2 = buff;
                    break;
                }
            }
            
            System.out.printf("Part 1: %d\n",res1);
            System.out.printf("Part 2: %d\n", res2);
            
        } 
        catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
        
    }
    
}
