import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int fairRations(int[] B) {
        LinkedHashMap<Integer,Integer> loavesBread=prepareData(B);
        int loavesSize=loavesBread.size();
        int givenLoaves=0;
        if(allEven(loavesBread))
            return givenLoaves;
        else{
            for(int i=0;i<loavesSize;i++){
                if(loavesBread.get(i)%2!=0 && i+1<loavesSize){
                    loavesBread.replace(i,loavesBread.get(i),loavesBread.get(i)+1);
                    loavesBread.replace(i+1,loavesBread.get(i+1),loavesBread.get(i+1)+1);
                    givenLoaves=givenLoaves+2;
                }
            }
        }
        if(!allEven(loavesBread))
            return -1;
        else
            return givenLoaves;
    }

    static LinkedHashMap<Integer,Integer> prepareData(int[] B){
        LinkedHashMap<Integer,Integer> loavesBread=new LinkedHashMap<Integer,Integer>();
        int bLen=B.length;
        for(int i=0;i<bLen;i++)
            loavesBread.put(i,B[i]);
        return loavesBread;
    }

    static boolean allEven(LinkedHashMap<Integer,Integer> loavesBread){
        int loavesSize=loavesBread.size();
        for(int i=0;i<loavesSize;i++)
            if(loavesBread.get(i)%2!=0)
                return false;
        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int N = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] B = new int[N];

        String[] BItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < N; i++) {
            int BItem = Integer.parseInt(BItems[i]);
            B[i] = BItem;
        }

        int result = fairRations(B);

        if(result!=-1)
            bufferedWriter.write(String.valueOf(result));
        else
            bufferedWriter.write("NO");

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
