import java.util.Scanner;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC_Day14 {
    
    public static void buildMap(Scanner input, HashMap<String,Character> map) {
        
        String key = input.next();
        input.next();
        char c = input.next().charAt(0);
        
        map.put(key,c);
    }
    
    public static HashMap<String,Long> takeStep(HashMap<String,Character> pairMap, HashMap<String,Long> templatePair, long[] alph) {
        
        HashMap<String,Long> newMap = new HashMap<>();
        
        for(String k : templatePair.keySet()) {
            
            char c = pairMap.get(k);
            
            long val = templatePair.get(k);
            
            alph[c-'A'] += val;
            
            StringBuilder pK1 = new StringBuilder(2);
            StringBuilder pK2 = new StringBuilder(2);
            
            pK1.append(k.charAt(0));
            pK1.append(c);
            
            pK2.append(c);
            pK2.append(k.charAt(1));
            
            String tmpOne = pK1.toString();
            String tmpTwo = pK2.toString();
                
                
            if(pairMap.containsKey(tmpOne)) {
                newMap.put(tmpOne, newMap.getOrDefault(tmpOne,0L) + val);
            }
            if(pairMap.containsKey(tmpTwo)) {
                newMap.put(tmpTwo, newMap.getOrDefault(tmpTwo,0L) + val);
            }
        
        }
        
        return newMap;
        
    }
    
    public static void printPair(HashMap<String,Long> map) {
        
        for(String k : map.keySet()) {
            
            System.out.printf("%s: %d\n", k, map.get(k));
            
        }
        System.out.println();
    }
    
    public static long diffResult(long[] alph) {
        
        long min = Long.MAX_VALUE;
        long max = 0;
            
        for(long x : alph) {
                
            if(x != 0) min = Math.min(min,x);
                
            if(x != 0) max = Math.max(max,x);
                
        }
            
        long res = max - min;
            
        return res;
            
    }
    
    public static void main(String args[]) {
        
        try {
            
            Scanner input = new Scanner(new File("C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_14.txt"));
            
            HashMap<String,Character> pairMap = new HashMap<>();
            
            String curr = input.next(); //string to parse through
            
            while(input.hasNext()) {
                
                buildMap(input,pairMap);
                
            }
            
            input.close();
            
            long[] alph = new long[26];
            
            HashMap<String,Long> templatePair = new HashMap<>();
            
            for(int i = 0; i < curr.length()-1; i++) {
                
                String key = curr.substring(i,i+2);
                
                if(pairMap.containsKey(key)) {
                    templatePair.put(key, templatePair.getOrDefault(key, 0L) + 1L);
                }
                
                alph[curr.charAt(i) - 'A']++;
                
            }
            
            alph[curr.charAt(curr.length()-1) - 'A']++;
            
            int steps = 10;
            
            //printPair(templatePair);
            
            for(int i = 0; i < steps; i++) {
                
                templatePair = takeStep(pairMap, templatePair, alph);
                
            }
            
            System.out.printf("Part 1: %d\n", diffResult(alph));
            
            for(int i = 0; i < 30; i++) {
                templatePair = takeStep(pairMap, templatePair, alph);
            }
            
            System.out.printf("Part 2: %d\n", diffResult(alph));
            
        } catch(FileNotFoundException ex) {
            
            System.out.println(ex);
            
        }
        
    }
    
}