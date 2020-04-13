import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String hackerrankInString(String s) {
        String wordToSearch="hackerrank";
        int wLen=wordToSearch.length();
        return Pattern.matches(buildPattern(wordToSearch,wLen),s)?"YES":"NO";
    }

    /**
    * Builds pattern with the word to search.
    */
    static String buildPattern(String wordToSearch,int wLen){
        StringBuilder pattern=new StringBuilder();
        pattern.append("[a-z]*");
        for(int i=0;i<wLen;i++)
            pattern.append(wordToSearch.charAt(i)+"[a-z]*");
        return pattern.toString();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            String result = hackerrankInString(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
