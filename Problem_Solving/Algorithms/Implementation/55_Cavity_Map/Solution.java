import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String[] cavityMap(String[] grid) {
        int numRowsColumns=grid.length;
        String[] result=new String[numRowsColumns];
        if(numRowsColumns<3)
            return grid;
        for(int i=0;i<numRowsColumns;i++){
            if(i==0 || i ==numRowsColumns-1)
                result[i]=grid[i];
            else
                result[i]=getCavs(grid[i],grid[i-1],grid[i+1]);
        }
        return result;
    }

    static String getCavs(String thisLine,String previous,String next){
        int size=thisLine.length()-2;
        StringBuilder sb=new StringBuilder();
        sb.append(thisLine.charAt(0));
        for(int j=1;j<=size;j++){
            if(isCavity(thisLine.charAt(j),previous.charAt(j),next.charAt(j),
                thisLine.charAt(j-1),thisLine.charAt(j+1)))
                sb.append("X");
            else
                sb.append(thisLine.charAt(j));
        }
        sb.append(thisLine.charAt(size+1));
        return sb.toString();
    }

    static boolean isCavity(char thisChar,char up,char down,char left,char right){
        boolean result=false;
        if(thisChar>up && thisChar>down && thisChar>left && thisChar>right)
            result=true;
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] result = cavityMap(grid);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(result[i]);

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
