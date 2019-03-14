import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String biggerIsGreater(String w) {
        int minIndex=-1;
        char minChar=123;
        String noAnswer="no answer";
        char maxChar=123;
        int maxIndex=-1;
        char[] swapString=new char[w.length()];
        String finalResult="";
        if(w.length()==1)
            return noAnswer;
        for(int x=w.length()-2;x>=0;x--){
            if(w.charAt(x)<w.charAt(x+1)){
                minIndex=x;
                minChar=w.charAt(x);
                break;
            }
            if(x==0 && minIndex==-1)
                return noAnswer;
        }
        for(int y=minIndex+1;y<=w.length()-1;y++){
            if(w.charAt(y)>w.charAt(minIndex) && w.charAt(y)<=maxChar && y>maxIndex){
                maxChar=w.charAt(y);
                maxIndex=y;
            }
        }
        swapString=w.toCharArray();
        swapString[minIndex]=maxChar;
        swapString[maxIndex]=minChar;
        String stringSwaped=new String(swapString);
        String subsMaintain=stringSwaped.substring(0,minIndex+1);
        String subsReverse=new StringBuilder(stringSwaped.substring(minIndex+1)).reverse().toString();
        finalResult=subsMaintain.concat(subsReverse);
        return finalResult;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int TItr = 0; TItr < T; TItr++) {
            String w = scanner.nextLine();
            String result = biggerIsGreater(w);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
