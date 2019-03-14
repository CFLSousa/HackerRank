import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int[] absolutePermutation(int n, int k) {
        int[] resultNo=new int[]{-1};
        //Natural order of key
        TreeSet<Integer> candidates=new TreeSet<Integer>();
        //Insertion order of key
        LinkedHashSet<Integer> permutation=new LinkedHashSet<Integer>();
        for(int i=1;i<=n;i++)
            candidates.add(i);
        if(k==0)
            return candidates.stream().mapToInt(cand -> cand.intValue()).toArray();
        for(int i=1;i<=n;i++){
            int lowTarget=i-k;
            int highTarget=i+k;
            if(candidates.contains(lowTarget)){
                permutation.add(lowTarget);
                candidates.remove(lowTarget);
            }
            else if(candidates.contains(highTarget)){
                permutation.add(highTarget);
                candidates.remove(highTarget);
            }
            else
                break;
        }
        if(permutation.size()==n)
            return permutation.stream().mapToInt(i -> i.intValue()).toArray();
        else
            return resultNo;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);

            int[] result = absolutePermutation(n, k);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
