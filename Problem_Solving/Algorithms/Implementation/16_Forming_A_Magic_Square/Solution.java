import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    /*n=3 (3x3 matrix)
    magic sum - M 
    M=n((n^2)+1)/2
    M=3((3^2)+1)/2
    M=30/2
    M=15
    
    sum of (1,2,...,n^2)=n^2((n^2)+1)/2
    1+2+3+4+5+6+7+8+9=3^2((3^2)+1)/2
    45=90/2
    45=45
    
    45/n=M
    45/3=M
    M=15
    
    For n=3, M=15
    
    0<a<b<c-a and b!=2a
    a=1 b=3 c=5*/
    static List<List<Integer>> correctMatrices=new ArrayList<List<Integer>>();
    static List<Integer> m1=Arrays.asList(8,1,6,3,5,7,4,9,2);
    static List<Integer> m2=Arrays.asList(6,1,8,7,5,3,2,9,4);
    static List<Integer> m3=Arrays.asList(2,7,6,9,5,1,4,3,8);
    static List<Integer> m4=Arrays.asList(4,3,8,9,5,1,2,7,6);
    static List<Integer> m5=Arrays.asList(2,9,4,7,5,3,6,1,8);
    static List<Integer> m6=Arrays.asList(4,9,2,3,5,7,8,1,6);
    static List<Integer> m7=Arrays.asList(6,7,2,1,5,9,8,3,4);
    static List<Integer> m8=Arrays.asList(8,3,4,1,5,9,6,7,2);
    static int numberOfCorrectMatrices=8;
    static int minimalCost=Integer.MAX_VALUE;

    static void putCorrectMatricesInList(){
        correctMatrices.add(m1);
        correctMatrices.add(m2);
        correctMatrices.add(m3);
        correctMatrices.add(m4);
        correctMatrices.add(m5);
        correctMatrices.add(m6);
        correctMatrices.add(m7);
        correctMatrices.add(m8);
    }

    static List<Integer> putTargetMatrixInList(int[][] matrix){
        List<Integer> list=new ArrayList<Integer>();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                list.add(matrix[i][j]);
            }
        }
        return list;
    }

    static int calculateMatrixCost(List<Integer> targetMatrixInList, 
            List<Integer> correctMatrixInList){
        int sumCost=0;
        for(int k=0;k<targetMatrixInList.size();k++){
            sumCost=sumCost+Math.abs(targetMatrixInList.get(k)-correctMatrixInList.get(k));
        }
        return sumCost;
    }

    static int formingMagicSquare(int[][] s) {
        putCorrectMatricesInList();
        List<Integer> listPrepared=putTargetMatrixInList(s);
        int intermediateMinimalCost=0;
        int i=0;
        while(i<numberOfCorrectMatrices){
            intermediateMinimalCost=calculateMatrixCost(listPrepared,correctMatrices.get(i));
            if(minimalCost>intermediateMinimalCost){
                minimalCost=intermediateMinimalCost;
            }
            i++;
        }
        return minimalCost;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

        int result = formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
