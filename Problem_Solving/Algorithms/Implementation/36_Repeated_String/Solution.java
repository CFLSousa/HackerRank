import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static long repeatedString(String s, long n) {
        long result=0;
        int counterA_s=0;
        StringBuilder sb=new StringBuilder(s);
        counterA_s=countA_sEnd(sb);
        if(n%sb.length()==0)
            result=(n/sb.length())*counterA_s;
        else {
            result=((n/sb.length())*counterA_s)+countA_sMiddle(sb,n%sb.length());
        }
        return result;
    }

    static int countA_sEnd(StringBuilder sb){
        int counterA_s=0;
        for(int i=0;i<sb.length();i++){
            if(sb.charAt(i)=='a' || sb.charAt(i)=='A'){
                counterA_s++;
            }
        }
        return counterA_s;
    }

    static int countA_sMiddle(StringBuilder sb, long limit){
        int counterA_s=0;
        for(int i=0;i<limit;i++){
            if(sb.charAt(i)=='a' || sb.charAt(i)=='A'){
                counterA_s++;
            }
        }
        return counterA_s;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
