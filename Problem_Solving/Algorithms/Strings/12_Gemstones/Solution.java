import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int gemstones(String[] arr) {
        return checkRocks(mapRocks(arr));
    }

    static int checkRocks(Map<Integer,String> rocks){
        int rSize=rocks.size();
        Map<Integer,Boolean> chars=createChars();
        checkMinerals(chars,rocks,rSize);
        return countMinerals(chars);
    }

    static int countMinerals(Map<Integer,Boolean> chars){
        int counter=0;
        for(int i=97;i<=122;i++){
            if(chars.get(i)==true)
                counter++;
        }
        return counter;
    }

    static void checkMinerals(Map<Integer,Boolean> chars,
            Map<Integer,String> rocks,int rSize){
        for(int i=97;i<=122;i++){
            if(chars.get(i)==true){
                for(int j=0;j<rSize;j++){
                    if(rocks.get(j).contains(""+((char)i))==false){
                        chars.replace(i,false);
                        break;
                    }
                }
            }
        }
    }

    static Map<Integer,Boolean> createChars(){
        Map<Integer,Boolean> chars=new TreeMap<Integer,Boolean>();
        for(int i=97;i<=122;i++)
            chars.put(i,true);
        return chars;
    }

    static Map<Integer,String> mapRocks(String[] arr){
        int arrLen=arr.length;
        Map<Integer,String> rocks=new LinkedHashMap<Integer,String>();
        for(int i=0;i<arrLen;i++)
            rocks.put(i,arr[i]);
        return rocks;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            String arrItem = scanner.nextLine();
            arr[i] = arrItem;
        }

        int result = gemstones(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
