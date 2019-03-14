import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int[] acmTeam(String[] topic) {
        int maxTopics=0;
        int numTeams=0;
        int[] results=new int[2];
        for(int i=0;i<topic.length-1;i++){
            for(int k=i+1;k<topic.length;k++){
                int aux=compareTeamTopics(topic[i], topic[k]);
                if(aux>maxTopics){
                    maxTopics=aux;
                    numTeams=1;
                }
                else if(aux>0 && aux==maxTopics)
                    numTeams++;
            }
        }
        results[0]=maxTopics;
        results[1]=numTeams;
        return results;
    }

    static int compareTeamTopics(String s1, String s2){
        int topics=0;
        for(int k=0;k<s1.length();k++){
            int s1Int=Character.getNumericValue(s1.charAt(k));
            int s2Int=Character.getNumericValue(s2.charAt(k));
            if((s1Int|s2Int)==1)
                topics++;
        }
        return topics;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        String[] topic = new String[n];

        for (int i = 0; i < n; i++) {
            String topicItem = scanner.nextLine();
            topic[i] = topicItem;
        }

        int[] result = acmTeam(topic);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
