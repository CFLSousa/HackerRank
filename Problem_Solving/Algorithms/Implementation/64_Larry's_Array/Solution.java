import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String larrysArray(int[] A) {
        Map<Integer,Integer> hm=new HashMap<Integer,Integer>();
        int aLength=A.length;
        for(int i=0;i<aLength;i++)
            hm.put(i,A[i]);
        int totalInversions=0;
        int lessCounter=0;
        for(int i=0;i<aLength-1;i++){
            for(int j=i+1;j<aLength;j++){
                if(hm.get(j)<hm.get(i)){
                    lessCounter++;
                }
            }
            totalInversions=totalInversions+lessCounter;
            lessCounter=0;
        }
        if(totalInversions%2==0)
            return "YES";
        else
            return "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] A = new int[n];

            String[] AItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int AItem = Integer.parseInt(AItems[i]);
                A[i] = AItem;
            }

            String result = larrysArray(A);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
