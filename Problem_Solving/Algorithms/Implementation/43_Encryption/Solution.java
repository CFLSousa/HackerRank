import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String encryption(String s) {
        String sWithoutSpaces=s.replace(" ","");
        HashMap<Integer,String> charPositions=new HashMap<Integer,String>();
        StringBuilder sb=new StringBuilder();
        int sLength=sWithoutSpaces.length();
        int ceilColumns=(int)Math.ceil(Math.sqrt(sLength));
        int floorRows=(int)Math.floor(Math.sqrt(sLength));
        int endOfLength=0;
        if(floorRows*ceilColumns<sLength)
            floorRows=floorRows+1;
        for(int i=0;i<sLength;i=i+ceilColumns){
            String sSubstring="";
            if(i+ceilColumns<sLength)
                sSubstring=sWithoutSpaces.substring(i,i+ceilColumns);
            else{
                sSubstring=sWithoutSpaces.substring(i);
                endOfLength=sSubstring.length();
            }
            charPositions.put(i/ceilColumns,sSubstring);
        }
        for(int i=0;i<ceilColumns;i++){
            for(int j=0;j<floorRows;j++){
                if(i<endOfLength || (i>=endOfLength && j<floorRows-1)){
                    sb.append(charPositions.get(j).charAt(i));
                }
                if(j==floorRows-1)
                    sb.append(" ");
            }
        }
        return sb.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = encryption(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
