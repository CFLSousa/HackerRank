import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static void staircase(int n) {
        StringBuilder sb=new StringBuilder();
        String format="%"+n+"s%n";
        for(int i=1;i<=n;i++){
            sb.append("#");
            if(i<n)
                System.out.printf(format,sb.toString());
            else
                System.out.printf("%"+n+"s",sb.toString());
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        staircase(n);

        scanner.close();
    }
}
