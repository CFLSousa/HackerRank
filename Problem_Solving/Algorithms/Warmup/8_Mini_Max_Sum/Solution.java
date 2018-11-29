import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static void miniMaxSum(int[] arr) {
        long minSumVal = 0L;
        long maxSumVal = 0L;
        long minVal = Long.MAX_VALUE;
        long maxVal = Long.MIN_VALUE;
        int minIndex = 0;
        int maxIndex = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] > maxVal){
                maxVal = arr[i];
                maxIndex = i;
            }
            if(arr[i] < minVal){
                minVal = arr[i];
                minIndex = i;
            }
        }
        for(int j = 0; j < arr.length; j++){
            if(j != maxIndex){
                minSumVal = minSumVal + arr[j];
            }
            if(j != minIndex){
                maxSumVal = maxSumVal + arr[j];
            }
        }
        System.out.println(minSumVal + " " + maxSumVal);
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
