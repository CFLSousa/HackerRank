import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int chocolateFeast(int n, int c, int m) {
        int totalChocolates=0;
        int buyChoco=n/c;
        int wrappersMod=0;
        int wrappers=0;
        totalChocolates=totalChocolates+buyChoco;
        while(m<=buyChoco+wrappersMod){
            wrappers=wrappersMod;
            wrappersMod=(buyChoco+wrappersMod)%m;
            buyChoco=(buyChoco+wrappers)/m;
            totalChocolates=totalChocolates+buyChoco;
        }
        return totalChocolates;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] ncm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(ncm[0]);

            int c = Integer.parseInt(ncm[1]);

            int m = Integer.parseInt(ncm[2]);

            int result = chocolateFeast(n, c, m);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
