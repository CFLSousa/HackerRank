import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String funnyString(String s) {
        return (equalAbsDiff(s))?"Funny":"Not Funny";
    }
    
    static boolean equalAbsDiff(String s){
        int sLen=s.length();
        boolean result=false;
        int c=0;
        while(c<sLen-1){
            if(Math.abs(((int)s.charAt(c))-((int)s.charAt(c+1))) !=
                Math.abs(((int)s.charAt(sLen-c-1))-((int)s.charAt(sLen-c-2)))){
                break;
            }
            else if(c+1==sLen-1){
                result=true;
                break;
            }
            else
                c++;
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            String result = funnyString(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
