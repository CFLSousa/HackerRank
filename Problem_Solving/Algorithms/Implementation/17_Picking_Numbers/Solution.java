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

class Result {

    public static int pickingNumbers(List<Integer> a) {
        int tempCounterElems=0;
        int maxCounterElems=0;
        int targetElem=0;
        Collections.sort(a);
        for(int i=0;i<a.size();i++){
            if(i==0){
                 targetElem=a.get(i);
                 tempCounterElems++;
            }
            if(i<a.size()-1 && Math.abs(targetElem-a.get(i+1))<=1){
                tempCounterElems++;
            }
            else {
                if(maxCounterElems<tempCounterElems){
                    maxCounterElems=tempCounterElems;
                }
                tempCounterElems=0;
                if(i<a.size()-1){
                    targetElem=a.get(i+1);
                    tempCounterElems++;
                }
            }
        }
        return maxCounterElems;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.pickingNumbers(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
