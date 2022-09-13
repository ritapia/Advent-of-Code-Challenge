
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AoC_Day8 {
    
    static int uniqueCount = 0;
    
    public static int buildOutput(String data, String[] helper) {
        
        int res = 0;
        
        int len = data.length();
        
        if(len == 5) {
            
            int similar = 0;
            for(int i = 0; i < data.length(); i++) {
                if(data.charAt(i) == helper[0].charAt(0) || data.charAt(i) == helper[0].charAt(1)) similar++;
            }
            if(similar == 2) {
                res += 3;
            }
            else {
                similar = 0;
                for(int i = 0; i < data.length(); i++) {
                    if(data.charAt(i) == helper[2].charAt(0) || data.charAt(i) == helper[2].charAt(1)) similar++;
                }
                if(similar == 2) {
                    res += 5;
                }
                else {
                    res += 2;
                }
            }
            
        }
        else if(len == 6) {
        
            int similar = 0;
            for(int i = 0; i < data.length(); i++) {
                if(data.charAt(i) == helper[1].charAt(0) || data.charAt(i) == helper[1].charAt(1) || data.charAt(i) == helper[1].charAt(2) || data.charAt(i) == helper[1].charAt(3)) similar++;
            }
            if(similar == 4) {
                res += 9;
            }
            else {
                similar = 0;
                for(int i = 0; i < data.length(); i++) {
                    if(data.charAt(i) == helper[0].charAt(0) || data.charAt(i) == helper[0].charAt(1)) similar++;
                }
                
                if(similar != 2) {
                    res += 6;
                }
            }
        }
        else {
            switch(len) {
                case 2 -> res++;
                case 3 -> res += 7;
                case 4 -> res += 4;
                case 7 -> res += 8;
            }
        }
        
        return res;
    
    }
    
    private static String[] findHelper(String[] data) {
        String[] res = new String[3];
        
        int len = data.length;

        for(int i = 0; i < len; i++) {
            if(i == len - 5) continue;
            int currLen = data[i].length();
            
            if(currLen == 2) {
                res[0] = data[i];
                if(i >= len - 5) uniqueCount++;
            }
            else if(currLen == 4) {
                res[1] = data[i];
                if(i >= len - 5) uniqueCount++;
            }
            else if(currLen == 3 || currLen == 7) if(i >= len - 5)uniqueCount++; 
        }
        
        String tmp = "";
        
        for(int i = 0; i < 4; i++) {
            char c = res[1].charAt(i);
            if(res[0].charAt(0) == c || res[0].charAt(1) == c) {
                continue;
            }
            else {
                tmp += c;
            }
        }
        
        res[2] = tmp;
        
        return res;
    }
    
    public static void main(String args[]) {
        
        try {
            
            Scanner input = new Scanner(new File("C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_8.txt"));
            
            int sumOfOutputs = 0;
            
            while(input.hasNext()) {
                
                String[] data = input.nextLine().split(" ");
                
                String[] helper = findHelper(data);
                
                int tmp = 0;
                
                for(int i = data.length - 4; i < data.length; i++) {
                    
                    tmp = (tmp * 10) + buildOutput(data[i], helper);
                    
                }
                sumOfOutputs += tmp;
            }
            
            input.close();
            
            System.out.printf("Part 1: %d\n",uniqueCount);
            System.out.printf("Part 2: %d\n",sumOfOutputs);
            
        }
        catch(FileNotFoundException ex) {
            
            System.out.println(ex);
        }
        
    }
    
}
