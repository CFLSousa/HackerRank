import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int anagram(String s) {
        int result=-1;
        int sLen=s.length();
        if(sLen%2==0){
            List<String> separatedS=separateS(s,sLen);
            String s1=separatedS.get(0);
            String s2=separatedS.get(1);
            if(s1.compareTo(s2)!=0){
                Map<Character,Integer> s1Elems=countOccurrences(s1,sLen);
                Map<Character,Integer> s2Elems=countOccurrences(s2,sLen);
                result=getNecessaryChanges(s1Elems,s2Elems);
            }
        }
        return result;
    }

    static int getNecessaryChanges(Map<Character,Integer> s1Elems,
            Map<Character,Integer> s2Elems){
        int diff=0;
        for(Map.Entry<Character,Integer> pair:s1Elems.entrySet()){
            int s1Occurr=pair.getValue();
            Character s1Ch=pair.getKey();
            if(s2Elems.containsKey(s1Ch)){
                int s2Occurr=s2Elems.get(s1Ch);
                if(s1Occurr>s2Occurr)
                    diff=diff+(s1Occurr-s2Occurr);
            }
            else
                diff=diff+s1Occurr;
        }
        return diff;
    }

    static Map<Character,Integer> countOccurrences(String s,int sLen){
        Map<Character,Integer> sElems=new TreeMap<Character,Integer>();
        for(int i=0;i<sLen/2;i++){
            char ch=s.charAt(i);
            if(!sElems.containsKey(ch))
                sElems.put(ch,1);
            else{
                int incVal=sElems.get(ch)+1;
                sElems.replace(ch,incVal);
            }
        }
        return sElems;
    }

    static List<String> separateS(String s,int sLen){
        List<String> separatedS=new ArrayList<String>();
        separatedS.add((new StringBuilder(s.substring(0,sLen/2))).toString());
        separatedS.add((new StringBuilder(s.substring(sLen/2,sLen))).toString());
        return separatedS;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = anagram(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
