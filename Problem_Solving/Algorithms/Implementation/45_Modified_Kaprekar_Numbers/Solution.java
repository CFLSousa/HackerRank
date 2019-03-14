import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static void kaprekarNumbers(int p, int q) {
        StringBuilder sb=new StringBuilder();
        for(int i=p;i<=q;i++){
            if(isKaprepkarNumber(i))
                sb.append(i+" ");
        }
        if(sb.length()>0)
            sb.deleteCharAt(sb.length()-1);
        else
            sb.append("INVALID RANGE");
        System.out.print(sb.toString());
    }

    static boolean isKaprepkarNumber(int num){
        String s=String.valueOf((long)num*num);
        long right=0;
        long left=0;
        int middle=s.length()%2==0?s.length()/2:(s.length()-1)/2;
        if(s.length()>1)
            left=Long.parseLong(s.substring(0,middle));
        right=Long.parseLong(
            s.substring(middle,s.length()).replaceFirst("^0+(?!$)",""));
        if(right>0 && left+right==num)
            return true;
        return false;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int p = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        kaprekarNumbers(p, q);

        scanner.close();
    }
}
