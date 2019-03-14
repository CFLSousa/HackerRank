import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    static int[] gradingStudents(int[] grades) {
        int[] result = new int[grades.length];
        for(int i = 0; i < grades.length; i++){
            if(grades[i] >= MINIMUM_GRADE){
                int remainder = grades[i] % 5;
                //distance <= 2 from grade[i] to next multiple of 5
                if(remainder >= 3)
                    result[i] = (grades[i] - remainder) + 5;
                else
                    result[i] = grades[i];
            }
            else
                result[i] = grades[i];
        }
        return result;
    }

    private static final Scanner scan = new Scanner(System.in);
    public static final int MINIMUM_GRADE = 38;

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scan.nextLine().trim());

        int[] grades = new int[n];

        for (int gradesItr = 0; gradesItr < n; gradesItr++) {
            int gradesItem = Integer.parseInt(scan.nextLine().trim());
            grades[gradesItr] = gradesItem;
        }

        int[] result = gradingStudents(grades);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bw.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bw.write("\n");
            }
        }

        bw.newLine();

        bw.close();
    }
}
