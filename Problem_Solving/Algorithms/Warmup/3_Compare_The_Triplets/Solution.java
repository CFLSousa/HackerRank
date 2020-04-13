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

    static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        List<Integer> results=new ArrayList<Integer>();
        int aliceScore=0;
        int bobScore=0;
        int i=0;
        int aliceCategory=0;
        int bobCategory=0;
        int numberOfCategories=a.size();

        do {
            aliceCategory=a.get(i);
            bobCategory=b.get(i);

            if(aliceCategory>bobCategory)
                aliceScore++;
            else if (aliceCategory<bobCategory)
                bobScore++;

            i++;
        }while(i<numberOfCategories);

        results.add(aliceScore);
        results.add(bobScore);
        return results;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> b = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = compareTriplets(a, b);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining(" "))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
