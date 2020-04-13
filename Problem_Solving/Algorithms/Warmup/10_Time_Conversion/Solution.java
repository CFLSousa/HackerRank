import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    static String timeConversion(String s) {
        String regExpr12=("^(([0][1-9])|([1][0-2])):[0-5][0-9]:[0-5][0-9][AP][M]$");
        Pattern pattern=Pattern.compile(regExpr12);
        Matcher matcher=pattern.matcher(s);
        StringBuilder sb24=new StringBuilder();

        if(matcher.matches()){
            //character in index 8 (is A or P) and identifies
            //12 or 24 hour mode
            char mode=s.charAt(8);
            String hour=s.substring(0,2);

            if(mode=='P' && (hour.compareTo("12")<0))
                sb24.append((Integer.parseInt(hour)+12)+"");
            else if(mode=='A' && hour.equals("12"))
                sb24.append("00");
            else
                sb24.append(hour);

            sb24.append(s.substring(2,8));
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
