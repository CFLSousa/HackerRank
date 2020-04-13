import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String gameOfThrones(String s) {
        int sLen=s.length();
        int oddCounters=0;
        Map<Character,Integer> counters=new TreeMap<Character,Integer>();
        for(int i=0;i<sLen;i++){
            Character ch=new Character(s.charAt(i));
            if(!counters.containsKey(ch))
                counters.put(ch,1);
            else{
                Integer val=counters.get(ch)+1;
                counters.replace(ch,val);
            }
        }
        for(Map.Entry<Character,Integer> counter:counters.entrySet()){
            if(counter.getValue()%2!=0){
                oddCounters++;
                if(oddCounters==2)
                    break;
            }
        }
        return (oddCounters<2)?"YES":"NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = gameOfThrones(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
