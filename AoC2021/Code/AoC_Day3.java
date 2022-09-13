import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AoC_Day3 {
    
    public static class Trie {
        
        public TrieNode root;
        public int[] numCount;
        
        Trie(int size) {
            root = new TrieNode();
            numCount = new int[size];
        }
        
        private class TrieNode {
            
            private int zeroCount;
            private int oneCount;
            
            private final int R = 2;
            private TrieNode[] links;
            
            private boolean isEnd;
            private int value;
            
            TrieNode() {
                links = new TrieNode[R];
                zeroCount = 0;
                oneCount = 0;
            }
            
            public void incZero() {
                zeroCount++;
            }
            
            public void incOne() {
                oneCount++;
            }
            
            public int getZeroCount() {
                if(links == null) return 0;
                return zeroCount;
            }
            
            public int getOneCount() {
                if(links == null) return 0;
                return oneCount;
            }
            
            public boolean containsKey(char c) {
                return links[c - '0'] != null;
            }
            
            public TrieNode get(char c) {
                return links[c - '0'];
            }
            
            public void put(char c, TrieNode node) {
                links[c - '0'] = node;
            }
            
            public void setEnd() {
                isEnd = true;
            }
            
            public void setValue(int value) {
                this.value = value;
            }
            
            public int getValue() {
                if(isEnd) return value;
                return -1;
            }
            
            public boolean isEnd() {
                return isEnd;
            }
        }
        
        public void insert(String word) {
            
            TrieNode node = root;
            
            int val = 0;
            int len = word.length();
            
            for(int i = 0; i < len; i++) {
                
                char c = word.charAt(i);
                
                if(c == '0') {
                    numCount[i]--;
                    node.incZero();
                }
                else {
                    numCount[i]++;
                    node.incOne();
                }
                
                if(!node.containsKey(c)) {
                    node.put(c, new TrieNode());
                }
                
                val += ((int)(c - '0') << (len - i - 1));
                
                node = node.get(c);
            }
            
            node.setEnd();
            node.setValue(val);
            
        }
        
        public int findCommon() {
            //System.out.println("In Common...");
            TrieNode node = root;
            
            int size = numCount.length;
            
            int res = 0;
            
            for(int i = 0; i < size; i++) {
                
                int zeroCount = node.getZeroCount();
                int oneCount = node.getOneCount();
                
                //System.out.println(zeroCount);
                //System.out.println(oneCount);
                
                if(oneCount >= zeroCount) {
                    node = node.get('1');
                }
                else {
                    node = node.get('0');
                }
                
            }
            
            if(node.isEnd()) {
                res = node.getValue();
            }
            
            return res;
        }
        
        public int findUncommon() {
            //System.out.println("In Uncommon...");
            TrieNode node = root;
            
            int size = numCount.length;
            
            int res = 0;
            
            for(int i = 0; i < size; i++) {
                
                int zeroCount = node.getZeroCount();
                int oneCount = node.getOneCount();
                
                //System.out.println(zeroCount);
                //System.out.println(oneCount);
                
                if(oneCount >= zeroCount) {
                    node = (node.get('0') != null) ? node.get('0') : node.get('1');
                }
                else {
                    node = (node.get('1') != null) ? node.get('1') : node.get('0');
                }
                
            }
            
            if(node.isEnd()) {
                res = node.getValue();
            }
            
            return res;
            
        }
        
    }
    
    public static int partOne(Trie t) {
        
        int len = t.numCount.length;
        
        int gamma = 0;
        int epsilon = 0;
        
        for(int i = 0; i < len; i++) {
            if(t.numCount[i] > 0) {
                gamma += (1 << (len-1) - i);
            }
            else {
                epsilon += (1 << (len-1) - i);
            }
        }
        
        return gamma * epsilon;
    }
    public static int partTwo(Trie t) {
        
        int oxyRating = t.findCommon();
        int carbonRating = t.findUncommon();
        //System.out.printf("Oxy: %d\nCarbon: %d\n",oxyRating,carbonRating);
        return oxyRating * carbonRating;
        
    }
    
    
    public static void main(String args[]) {
        
        try {
            
            Scanner input = new Scanner(new File("C:\\Users\\Ricar\\Desktop\\AoC2021\\Input\\input_3.txt"));

            int setSize = 12;
            
            Trie root = new Trie(setSize);
            
            while(input.hasNext()) {
                String curr = input.next();
                root.insert(curr);
            }
            
            input.close();
            
            System.out.printf("Part 1: %d\n", partOne(root));
            System.out.printf("Part 2: %d\n", partTwo(root));
            
        }
        
        catch(FileNotFoundException ex) {
            
            System.out.println(ex);
            
        }
        
    }
    
}
