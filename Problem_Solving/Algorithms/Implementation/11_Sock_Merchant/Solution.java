import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int sockMerchant(int n, int[] ar) {
        int counterSocksPairs=0;
        Map<Integer,Integer> mapVals=new HashMap<Integer,Integer>();
        for(int i=0;i<ar.length;i++){
            if(mapVals.get(new Integer(ar[i]))!=null){
                mapVals.put(new Integer(ar[i]),new Integer(((mapVals.get(ar[i]))+1)));
            }
            else {
                mapVals.put(new Integer(ar[i]),new Integer(1));
            }
        }
        for(Integer entryVal:mapVals.values()){
            int val=entryVal.intValue();
            if(val>0 && val%2==0){
                counterSocksPairs=counterSocksPairs+(val/2);
            }
            else if(val>0 && val%2!=0){
                counterSocksPairs=counterSocksPairs+((val-1)/2);
            }
        }
        return counterSocksPairs;    
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
