import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String caesarCipher(String s, int k) {
        int sLen=s.length();
        StringBuilder cipher=new StringBuilder();
        for(int i=0;i<sLen;i++){
            int cp=s.codePointAt(i);
            int cpRotated=cp+(k%26);
            //Uppercase letter character
            if(cp>=65 && cp<=90){
                if(cpRotated>=65 && cpRotated<=90)
                    cipher.appendCodePoint(cpRotated);
                else
                    cipher.appendCodePoint(cpRotated-26);
            }
            //Lowercase letter character
            else if(cp>=97 && cp<=122){
                if(cpRotated>=97 && cpRotated<=122)
                    cipher.appendCodePoint(cpRotated);
                else
                    cipher.appendCodePoint(cpRotated-26);
            }
            //Other not letter character
            else
                cipher.appendCodePoint(cp);
        }
        return cipher.toString();    
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        int k = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String result = caesarCipher(s, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
