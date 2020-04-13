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

    static int birthday(List<Integer> s, int d, int m) {

        int solutions = 0;
        int sLen = s.size();

        if(m <= sLen){
            //Sum progression in each list position
            for(int i=1;i<sLen;i++)
                s.set(i,s.get(i-1)+s.get(i));
                
            //If month m is equal to list s size
            //and the sum of the m elements in s is equal to day d,
            //there is only one solution.
            //
            //If the sum of the m elements in list s is different from day d,
            //there is no solution
            if(m==sLen && s.get(m-1)==d)
                solutions++;
            else if(sLen>m){
                for(int i=m;i<=sLen;i++){
                    //Each possible combination of m squares is verified,
                    //Each combination equalizing day d is a solution
                    if((i>m && s.get(i-1)-s.get(i-1-m)==d) || 
                            (i==m && s.get(i-1)==d))
                        solutions++;
                }
            }
        }
        return solutions;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        String[] dm = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int d = Integer.parseInt(dm[0]);

        int m = Integer.parseInt(dm[1]);

        int result = birthday(s, d, m);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
