import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int equalizeArray(int[] arr) {
        Arrays.sort(arr);
        int maxCounter=1;
        int auxCounter=1;
        for(int i=1;i<arr.length;i++){
            if(arr[i]==arr[i-1]){
                auxCounter++;
                if(i==arr.length-1 && auxCounter>maxCounter){
                        maxCounter=auxCounter;
                }
            }
            else {
                if(auxCounter>maxCounter){
                    maxCounter=auxCounter;
                }
                auxCounter=1;
            }
        }
        return arr.length-maxCounter;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = equalizeArray(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
