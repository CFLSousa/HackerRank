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

    public static final int MINIMUM_GRADE=38;

    public static List<Integer> gradingStudents(List<Integer> grades) {
        int gradesSize=grades.size();
        List<Integer> roundedGrades=new ArrayList<Integer>(gradesSize);

        for(int i=0;i<gradesSize;i++){
            //round if distance<=2 from ith grade to next multiple of 5
            if(grades.get(i)>=MINIMUM_GRADE && grades.get(i)%5>=3)
                roundedGrades.add(grades.get(i)-(grades.get(i)%5)+5);
            else
                roundedGrades.add(grades.get(i));
        }

        return roundedGrades;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int gradesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> grades = IntStream.range(0, gradesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.gradingStudents(grades);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
