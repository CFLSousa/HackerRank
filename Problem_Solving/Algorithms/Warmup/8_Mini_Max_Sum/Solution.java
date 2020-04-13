import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static void miniMaxSum(int[] arr) {
        int arrLen=arr.length;
        long sum=0L;
        long minVal=Long.MAX_VALUE;
        long maxVal=Long.MIN_VALUE;

        for(int i=0;i<arrLen;i++){
            sum+=arr[i];
            if(arr[i]>maxVal)
                maxVal=arr[i];
            if(arr[i]<minVal)
                minVal=arr[i];
        }
        
        System.out.println((sum-maxVal) + " " + (sum-minVal));
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] arr = new int[5];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < 5; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        miniMaxSum(arr);

        scanner.close();
    }
}
