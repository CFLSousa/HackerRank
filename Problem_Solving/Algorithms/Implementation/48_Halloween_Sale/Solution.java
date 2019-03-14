import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int howManyGames(int p, int d, int m, int s) {
        int numGames=0;
        int money=s;
        if(p>money)
            return numGames;
        for(int i=0;money>=m && money-(p-(d*i))>=0 && money-m>=0;i++){
            if(money>=money-(p-(d*i)) && (p-(d*i))>=m){
                money=money-(p-(d*i));
                numGames++;
            }   
            else if(money>=money-m && (p-(d*i))<m) {
                money=money-m;               
                numGames++;
            }
        }
        return numGames;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] pdms = scanner.nextLine().split(" ");

        int p = Integer.parseInt(pdms[0]);

        int d = Integer.parseInt(pdms[1]);

        int m = Integer.parseInt(pdms[2]);

        int s = Integer.parseInt(pdms[3]);

        int answer = howManyGames(p, d, m, s);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
