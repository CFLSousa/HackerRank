import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    /*
     * (a%k)+(b%k)=k
     */
    static int nonDivisibleSubset(int k, int[] S) {
        if (S.length == 1 || k == 1)
            return 1;
        else {
            HashMap<Integer, Integer> sData = getSPacked(S);
            int sDataSize = sData.size();
            HashMap<Integer, Integer> sDataRemainders = getRemainders(sData, sDataSize, k);
            return calcBiggestSize(sDataRemainders, sDataRemainders.size(), k);
        }
    }

    /**
     * Calculate biggest subset checking remainders of elements.
     */
    static int calcBiggestSize(HashMap<Integer, Integer> sDataRemainders, int sDataRSize, int k) {
        int counter = 0;
        for (int j = 0; j <= k / 2; j++) {
            if (j == 0 || (k % 2 == 0 && j == k / 2)) {
                if(countFreqRemainder(sDataRemainders, sDataRSize, j)>0)
                    counter++;
            } else {
                int bottomLimit = countFreqRemainder(sDataRemainders, sDataRSize, j);
                int upperLimit = countFreqRemainder(sDataRemainders, sDataRSize, k - j);
                if (bottomLimit > upperLimit)
                    counter = counter + bottomLimit;
                else
                    counter = counter + upperLimit;
            }
        }
        return counter;
    }

    /**
     * Count number of occurrences of a specific remainder.
     */
    static int countFreqRemainder(HashMap<Integer, Integer> sDataRemainders, 
            int sDataRSize, int search) {
        int frequency = 0;
        for (int i = 0; i < sDataRSize; i++) {
            if (sDataRemainders.get(i) == search)
                frequency++;
        }
        return frequency;
    }

    /**
     * Calculate remainder of each sData element.
     */
    static HashMap<Integer, Integer> getRemainders(HashMap<Integer, Integer> sData, 
            int sDataSize, int k) {
        HashMap<Integer, Integer> sDataRemainders = new HashMap<Integer, Integer>();
        for (int i = 0; i < sDataSize; i++)
            sDataRemainders.put(i, sData.get(i) % k);
        return sDataRemainders;
    }

    /**
     * Puts S elements inside HashMap for performance purpose.
     */
    static HashMap<Integer, Integer> getSPacked(int[] S) {
        HashMap<Integer, Integer> sData = new HashMap<Integer, Integer>();
        int sLen = S.length;
        for (int i = 0; i < sLen; i++)
            sData.put(i, S[i]);
        return sData;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] S = new int[n];

        String[] SItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int SItem = Integer.parseInt(SItems[i]);
            S[i] = SItem;
        }

        int result = nonDivisibleSubset(k, S);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
