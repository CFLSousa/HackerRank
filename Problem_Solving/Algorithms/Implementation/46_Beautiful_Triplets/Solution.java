import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int beautifulTriplets(int d, int[] arr) {
        int beautTriplets=0;
        HashMap<Integer,Integer> hash=new HashMap<Integer,Integer>();
        for(int m=0;m<arr.length;m++){
            hash.put(m,arr[m]);
        }
        if(hash.size()>=3){
            for(int j=0;j<hash.size();j++){
                if(hash.containsValue(hash.get(j)+2*d) && hash.containsValue(hash.get(j)+d))
                    beautTriplets++;
            }
        }
        return beautTriplets;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = beautifulTriplets(d, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
