import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int sherlockAndAnagrams(String s) {
        int sLen=s.length();
        int limit=sLen/2;
        int pairs=0;
        List<Map<Character,Integer>> distributions=calcDistributions(s);
        int dLen=distributions.size();
        for(int i=0;i<dLen-1;i++){
            for(int j=i+1;j<dLen;j++){
                if(distributions.get(i).equals(distributions.get(j)))
                    pairs++;
            }
        }
        return pairs;
    }

    static List<Map<Character,Integer>> calcDistributions(String s){
        int sLen=s.length();
        int limit=sLen-1;
        List<Map<Character,Integer>> distributions=
            new ArrayList<Map<Character,Integer>>();
        for(int i=1;i<=limit;i++){
            for(int j=0;j<=sLen-i;j++)
                distributions.add(calcCounters(s.substring(j,j+i)));
        }
        return distributions;
    }

    static Map<Character,Integer> calcCounters(String s){
        Map<Character,Integer> counters=new TreeMap<Character,Integer>();
        int sLen=s.length();
        for(int i=0;i<sLen;i++){
            char ch=s.charAt(i);
            if(!counters.containsKey(ch))
                counters.put(ch,1);
            else{
                int chVal=counters.get(ch)+1;
                counters.replace(ch,chVal);
            }
        }
        return counters;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
