import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    static int pageCount(int n, int p) {
        int minStart=0;
        int minEnd=0;
        minStart=countFromBookStart(n,p);
        minEnd=countFromBookEnd(n,p);
        if(minEnd<minStart)
            return minEnd;
        else
            return minStart;
    }

    static int countFromBookEnd(int n, int p){
        return ((n/2)-(p/2));
    }
    
    static int countFromBookStart(int n, int p){
        return (p/2);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int p = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int result = pageCount(n, p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
