import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static void plusMinus(int[] arr) {
        double posNums=0.0;
        double negNums=0.0;
        double zeroNums=0.0;
        double posFraction=0.0;
        double negFraction=0.0;
        double zeroFraction=0.0;
        DecimalFormat df=new DecimalFormat("0.000000");
        df.setRoundingMode(RoundingMode.HALF_UP);
        int arrLen=arr.length;

        for(int i=0;i<arrLen;i++){
            if(arr[i]==0)
                zeroNums++;
            else if(arr[i]>0)
                posNums++;
            else
                negNums++;
        }

        posFraction=posNums/arrLen;
        negFraction=negNums/arrLen;
        zeroFraction=zeroNums/arrLen;

        System.out.println(df.format(posFraction) + "\n" + 
            df.format(negFraction) + "\n" + 
            df.format(zeroFraction));
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        plusMinus(arr);

        scanner.close();
    }
}
