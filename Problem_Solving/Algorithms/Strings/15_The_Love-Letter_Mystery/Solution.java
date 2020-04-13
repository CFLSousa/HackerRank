import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int theLoveLetterMystery(String s) {
        return calcDistance(s,s.length());
    }

    static int calcDistance(String s,int sLen){
        int leftCh=0;
        int rightCh=0;
        int dist=0;

        int limit=0;
        if(sLen%2==0)
            limit=sLen/2;
        else
            limit=sLen/2+1;

        for(int i=0;i<limit;i++){
            leftCh=(int)s.charAt(i);
            rightCh=(int)s.charAt(sLen-1-i);
            if(leftCh>rightCh)
                dist+=(leftCh-rightCh);
            else if(rightCh>leftCh)
                dist+=(rightCh-leftCh);
        }
        return dist;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = theLoveLetterMystery(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}