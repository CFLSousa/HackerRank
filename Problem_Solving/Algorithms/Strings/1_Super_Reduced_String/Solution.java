import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    static String superReducedString(String s) {
        String empty="Empty String";
        int sLen=s.length();
        if(sLen==1)
            return s;
        else{
            Deque<Character> stackChars=new ArrayDeque<Character>();
            for(int i=0;i<sLen;i++){
                Character ch=s.charAt(i);
                if(stackChars.peekFirst()==null || 
                        Character.compare(ch,stackChars.peekFirst())!=0)
                    stackChars.push(ch);
                else
                    stackChars.pop();
            }
            if(stackChars.peekFirst()==null)
                return empty;
            else
                return getReducedStr(stackChars);
        }
    }

    /**
    * Obtains the reduced String in Stack.
    */
    static String getReducedStr(Deque<Character> stackChars){
        StringBuilder reducedStr=new StringBuilder();
        int stackSize=stackChars.size();
        for(int i=0;i<stackSize;i++)
            reducedStr.append(""+stackChars.pop());
        return reducedStr.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = superReducedString(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
