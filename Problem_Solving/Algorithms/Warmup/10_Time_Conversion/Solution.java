import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    static String timeConversion(String s) {
        Pattern pattern = Pattern.compile("^(([0][1-9])|([1][0-2])):[0-5][0-9]:[0-5][0-9][AP][M]$");
        Matcher matcher = pattern.matcher(s);
        StringBuilder sb12 = new StringBuilder(s);
        StringBuilder sb24 = new StringBuilder();
        if(matcher.matches()){
            //character in index 8 (is A or P) and identifies
            //12 or 24 hour mode
            Character mode = new Character(sb12.charAt(8));
            String hour = sb12.substring(0, 2);
            if(mode.equals('P')) {
                if(hour.equals("12"))
                    sb24.append(hour);
                else
                    sb24.append((Integer.parseInt(hour) + 12) + "");
            }
            else {
                if(hour.equals("12"))
                    sb24.append("00");
                else
                    sb24.append(hour);
            }
            sb24.append(sb12.substring(2, 8));
            return sb24.toString();
        }
        else
            return "";
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scan.nextLine();

        String result = timeConversion(s);

        bw.write(result);
        bw.newLine();

        bw.close();
    }
}
