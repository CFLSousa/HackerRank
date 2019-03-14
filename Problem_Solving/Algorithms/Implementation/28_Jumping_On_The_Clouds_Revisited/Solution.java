import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int jumpingOnClouds(int[] c, int k) {
        int energy=100;
        //for a wrong testcase that don't follow the description rules (10%3!=0)
        //n%k=0 always
        if(c.length%k!=0){
            //first jump - i in for cycle starts in k
            energy=energy-1;
            if(c[0]==1)
                energy=energy-2;
            for(int i=k;i%c.length!=0 && i<c.length;i=i+k){
                if(c[i]==1)
                    energy=energy-2;
                energy=energy-1;
            }
        }
        else {
            //first jump - i in for cycle starts in k
            energy=energy-1;
            if(c[0]==1)
                energy=energy-2;
            for(int i=k;i%c.length!=0 && i<c.length;i=i+k){
                if(c[i]==1)
                    energy=energy-2;
                energy=energy-1;
            }
        }
        return energy;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
