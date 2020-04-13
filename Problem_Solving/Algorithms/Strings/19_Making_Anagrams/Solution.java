import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int makingAnagrams(String s1, String s2) {
        int[] s1Counters=countOccurrences(s1);
        int[] s2Counters=countOccurrences(s2);
        return countCommonChars(s1Counters,s2Counters);
    }

    static int countCommonChars(int[] s1Counters,int[] s2Counters){
        int counter=0;
        for(int i=0;i<26;i++)
            counter=counter+(Math.abs(s1Counters[i]-s2Counters[i]));
        return counter;
    }

    static int[] countOccurrences(String s){
        int[] counters=new int[26];
        int sLen=s.length();
        for(int i=0;i<sLen;i++)
            counters[s.charAt(i)-97]++;
        return counters;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = makingAnagrams(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
