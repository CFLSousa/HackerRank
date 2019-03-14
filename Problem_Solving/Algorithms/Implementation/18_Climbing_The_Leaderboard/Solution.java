import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        int[] positionsAlice=new int[alice.length];
        LinkedHashSet<Integer> playersScores=new LinkedHashSet<Integer>();
        LinkedHashMap<Integer,Integer> aliceScores=new LinkedHashMap<Integer,Integer>();
        for(int i=0;i<scores.length;i++)
            playersScores.add(scores[i]);
        for(int i=0;i<alice.length;i++)
            aliceScores.put(i,alice[i]);
        Iterator<Integer> it=playersScores.iterator();
        Integer compValue=Integer.MAX_VALUE;
        int position=0;
        for(int i=aliceScores.size()-1;i>=0;i--){
            if(aliceScores.get(i)>=compValue)
                positionsAlice[i]=position;
            while(aliceScores.get(i)<compValue){
                if(it.hasNext()){
                    compValue=it.next();
                    position++;
                }
                if(aliceScores.get(i)>=compValue){
                    positionsAlice[i]=position;
                    break;
                }
                else if(aliceScores.get(i)<compValue && position==playersScores.size()){
                    positionsAlice[i]=position+1;
                    break;
                }
            }
        }
        return positionsAlice;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int scoresCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < scoresCount; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int aliceCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] alice = new int[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < aliceCount; i++) {
            int aliceItem = Integer.parseInt(aliceItems[i]);
            alice[i] = aliceItem;
        }

        int[] result = climbingLeaderboard(scores, alice);

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
