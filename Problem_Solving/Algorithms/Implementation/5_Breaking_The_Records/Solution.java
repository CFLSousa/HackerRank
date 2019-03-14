import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int[] breakingRecords(int[] scores) {
        int maxPoints = scores[0];
        int minPoints = scores[0];
        int maxCounter = 0;
        int minCounter = 0;
        int[] recordTimes = new int[2];
        for(int i = 1; i < scores.length; i++){
            if(scores[i] > maxPoints){
                maxPoints = scores[i];
                maxCounter++;
            }
            else if(scores[i] < minPoints){
                minPoints = scores[i];
                minCounter++;
            }
        }
        recordTimes[0] = maxCounter;
        recordTimes[1] = minCounter;
        return recordTimes;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[n];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int[] result = breakingRecords(scores);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
