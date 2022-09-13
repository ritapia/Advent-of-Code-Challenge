import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC_Day16 {
    
    static HashMap<Character,String> hexMap = new HashMap<>() {
        {
        this.put('0', "0000");
        this.put('1', "0001");
        this.put('2', "0010");
        this.put('3', "0011");
        this.put('4', "0100");
        this.put('5', "0101");
        this.put('6', "0110");
        this.put('7', "0111");
        this.put('8', "1000");
        this.put('9', "1001");
        this.put('A', "1010");
        this.put('B', "1011");
        this.put('C', "1100");
        this.put('D', "1101");
        this.put('E', "1110");
        this.put('F', "1111");
        }
    };
    
    public static String convert(String s) {
        
        StringBuilder sb = new StringBuilder();
        
        for(char c : s.toCharArray()) {
            sb.append(hexMap.get(c));
        }
        
        return sb.toString();
    }
    
    public static long[] decode(String s, long idx) {
        
        
        long ver = binToLong(s,(int)idx,(int)idx+2);
        long id = binToLong(s,(int)idx+3,(int)idx+5);
        
        long[] res = new long[]{ver,id,0,0,idx};
        
        idx += 6;
        
        if(id == 4) {
            
            StringBuilder sb = new StringBuilder();
            
            while(true) {
                int num = 4;
                if(s.charAt((int)idx++) == '1') {
                    while(num > 0) {
                        sb.append(s.charAt((int)idx++));
                        num--;
                    }
                } 
                else {
                    while(num > 0) {
                        sb.append(s.charAt((int)idx++));
                        num--;
                    }
                    break;
                }
            }
            
            res[0] = ver; res[1] = id; res[3] = binToLong(sb.toString(),0,sb.length()-1); res[4] = idx;
            
        }
        else {
            
            char c = s.charAt((int)idx++);
            Stack<Long> stk = new Stack<>();
            
            if(c == '0') {
                
                res[2] = 0;
                
                long len = binToLong(s,(int)idx,(int)idx+14);
                
                idx += 15;
                len = idx + len;
                
                while(idx < len) {
                    long[] tmp = decode(s,idx);
                    stk.push(tmp[3]);
                    idx = (int)tmp[4];
                    res[0] += tmp[0];
                }
                res[3] = operate(stk,id);
                res[4] = idx;
            }
            else {
                
                res[2] = 1;
                
                long amt = binToLong(s,(int)idx,(int)idx+10);
                idx += 11;
                
                while(amt > 0) {
                    long[] tmp = decode(s,idx);
                    idx = tmp[4];
                    stk.push(tmp[3]);
                    res[0] += tmp[0];
                    amt--;
                }
                res[3] = operate(stk,id);
                res[4] = idx;
            }
            
        }
        //System.out.printf("Version: %d ID: %d TypeID: %d Res: %d Idx: %d\n", res[0],res[1],res[2],res[3],res[4]);
        return res;
        
    }
    
    private static long operate(Stack<Long> stk, long id) {
        long res = (!stk.isEmpty()) ? stk.pop() : 0;
        
        int flag = (int)id;
        
        switch(flag) {
            
            case 0 -> {
                while(!stk.isEmpty()) {
                    res += stk.pop();
                }
            }
            
            case 1 -> {
                while(!stk.isEmpty()) {
                    res *= stk.pop();
                }
            }
            
            case 2 -> {
                while(!stk.isEmpty()) {
                    res = Math.min(res, stk.pop());
                }
            }
            
            case 3 -> {
                while(!stk.isEmpty()) {
                    res = Math.max(res, stk.pop());
                }
            }
            
            case 5 -> {
                //reverse because of stack (we pop last instead of front)
                long tmp = stk.pop();
                res = (res < tmp) ? 1 : 0;
            }
            
            case 6 -> {
                //reverse because of stack (we pop last instead of front)
                long tmp = stk.pop();
                res = (res > tmp) ? 1 : 0;
            }
            
            case 7 -> {
                long tmp = stk.pop();
                res = (res == tmp) ? 1 : 0;
            }
                
        }
        
        return res;
        
    }
    
    private static long binToLong(String s, int start, int end) {
        
        long res = 0;
        
        while(start <= end) {
            
            int tmp = s.charAt(start++) - '0';
            res <<= 1;
            res |= tmp;
            
        }
        
        return res;
        
    }
    
    public static void main(String args[]) {
        
        try {
            String path = "C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_16.txt";
            Scanner input = new Scanner(new File(path));
            
            int res = 0;
            
            while(input.hasNext()) {
                
                String str = convert(input.nextLine());
                //System.out.println(str);
                long[] tmp = decode(str,0);
                System.out.printf("Version: %d ID: %d TypeID: %d Res: %d\n", tmp[0],tmp[1],tmp[2],tmp[3]);
                res += tmp[0];
            }
            
            input.close();
            
            System.out.printf("Part 1: %d\n", res);
        }
        catch(FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    
}
