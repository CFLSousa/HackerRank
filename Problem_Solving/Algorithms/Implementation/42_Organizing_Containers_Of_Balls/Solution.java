import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String organizingContainers(int[][] container) {
        String result="";
        int cLen=container.length;
        if(cLen==1)
            result="Possible";
        else{
            TreeMap<Integer,TreeMap<Integer,Long>> containers=mapData(container,cLen);
            TreeMap<Integer,Long> containersCount=countContainers(containers,cLen);
            TreeMap<Integer,Long> ballTypesCount=countBallTypes(containers,cLen);
            if(sameCounts(containersCount,ballTypesCount))
                result="Possible";
            else
                result="Impossible";
        }
        return result;
    }

    /**
    * Checks if there's some vertical sum (quantity of a ball type) 
    * different from some horizontal sum (quantity of balls in one container)
    */
    static boolean sameCounts(TreeMap<Integer,Long> containersCount,
            TreeMap<Integer,Long> ballTypesCount){
        boolean result=true;
        for(int i=0;i<containersCount.size();i++){
            if(!ballTypesCount.containsValue(containersCount.get(i))){
                result=false;
                break;
            }
        }
        return result;
    }

    /**
    * Count and map the total of balls of each type
    */
    static TreeMap<Integer,Long> countBallTypes(
            TreeMap<Integer,TreeMap<Integer,Long>> containers,int cLen){
        TreeMap<Integer,Long> ballTypesCount=new TreeMap<Integer,Long>();
        Long sum=0L;
        for(int r=0;r<cLen;r++){
            for(int c=0;c<cLen;c++)
                sum+=new Long(containers.get(c).get(r));
            ballTypesCount.put(r,sum);
            sum=0L;
        }
        return ballTypesCount;
    }

    /**
    * Count and map the total of balls in each container
    */
    static TreeMap<Integer,Long> countContainers(
            TreeMap<Integer,TreeMap<Integer,Long>> containers,int cLen){
        TreeMap<Integer,Long> containersCount=new TreeMap<Integer,Long>();
        Long sum=0L;
        for(int r=0;r<cLen;r++){
            for(int c=0;c<cLen;c++)
                sum+=new Long(containers.get(r).get(c));
            containersCount.put(r,sum);
            sum=0L;
        }
        return containersCount;
    }

    /**
    * <Container<BallType,Quantity>>
    */
    static TreeMap<Integer,TreeMap<Integer,Long>> mapData(int[][] container,int cLen){
        TreeMap<Integer,TreeMap<Integer,Long>> containers=
            new TreeMap<Integer,TreeMap<Integer,Long>>();
        for(int i=0;i<cLen;i++){
            TreeMap<Integer,Long> ballTypesQuantities=new TreeMap<Integer, Long>();
            for(int j=0;j<cLen;j++)
                ballTypesQuantities.put(j,new Long(container[i][j]));
            containers.put(i,ballTypesQuantities);
        }
        return containers;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[][] container = new int[n][n];

            for (int i = 0; i < n; i++) {
                String[] containerRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < n; j++) {
                    int containerItem = Integer.parseInt(containerRowItems[j]);
                    container[i][j] = containerItem;
                }
            }

            String result = organizingContainers(container);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
