import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static class Obstacle implements Comparable<Obstacle> {
        private final int row;
        private final int column;

        Obstacle(int row,int column){
            this.row=row;
            this.column=column;
        }

        int getRow(){
            return this.row;
        }

        int getColumn(){
            return this.column;
        }

        @Override public boolean equals(Object other) {
            if (!(other instanceof Obstacle)) {
                return false;
            }
            Obstacle otherObstacle = (Obstacle) other;
            return row == otherObstacle.row && column == otherObstacle.column;
        }

        @Override public int hashCode() {
            return row * 13 + column * 37;
        }

        @Override public int compareTo(Obstacle other) {
            return row != other.row 
                ? Integer.compare(row, other.row) 
                : Integer.compare(column, other.column);
        }

        public String toString(){
            return row + " : " + column;
        }
    }

    static int queensAttack(int n, int k, int r_q, int c_q, int[][] obstacles) {
        int attackCells=0;
        if(n>1){
            TreeSet<Obstacle> obstaclesSet=setObstacles(obstacles);
            attackCells = attackCells + northWestCells(obstaclesSet,n,r_q,c_q);
            attackCells = attackCells + southWestCells(obstaclesSet,n,r_q,c_q);
            attackCells = attackCells + northEastCells(obstaclesSet,n,r_q,c_q);
            attackCells = attackCells + southEastCells(obstaclesSet,n,r_q,c_q);
            attackCells = attackCells + northCells(obstaclesSet,n,r_q,c_q);
            attackCells = attackCells + southCells(obstaclesSet,n,r_q,c_q);
            attackCells = attackCells + westCells(obstaclesSet,n,r_q,c_q);
            attackCells = attackCells + eastCells(obstaclesSet,n,r_q,c_q);
        }
        return attackCells;
    }

    static int northWestCells(TreeSet<Obstacle> obstaclesSet,int n,int r_q,int c_q){
        int nWCells=0;
        for(int r=r_q+1,c=c_q-1;r<=n&&c>=1;r++,c--){
            if(obstaclesSet.contains(new Obstacle(r,c)))
                break;
            else
                nWCells++;
        }
        return nWCells;
    }

    static int southWestCells(TreeSet<Obstacle> obstaclesSet,int n,int r_q,int c_q){
        int sWCells=0;
        for(int r=r_q-1,c=c_q-1;r>=1&&c>=1;r--,c--){
            if(obstaclesSet.contains(new Obstacle(r,c)))
                break;
            else
                sWCells++;
        }
        return sWCells;
    }

    static int northEastCells(TreeSet<Obstacle> obstaclesSet,int n,int r_q,int c_q) {
        int nECells = 0;
        for (int r = r_q + 1, c = c_q + 1; r <= n&&c <= n; r++, c++) {
            if (obstaclesSet.contains(new Obstacle(r, c)))
                break;
            else
                nECells++;
        }
        return nECells;
    }

    static int southEastCells(TreeSet<Obstacle> obstaclesSet,int n,int r_q,int c_q) {
        int sECells = 0;
        for (int r = r_q - 1, c = c_q + 1; r >= 1&&c <= n; r--, c++) {
            if (obstaclesSet.contains(new Obstacle(r, c)))
                break;
            else
                sECells++;
        }
        return sECells;
    }

    static int northCells(TreeSet<Obstacle> obstaclesSet, int n,int r_q,int c_q) {
        int nCells = 0;
        for(int r=r_q+1;r<=n;r++){
            if (obstaclesSet.contains(new Obstacle(r, c_q)))
                break;
            else
                nCells++;
        }
        return nCells;
    }

    static int southCells(TreeSet<Obstacle> obstaclesSet, int n,int r_q,int c_q) {
        int sCells = 0;
        for (int r = r_q - 1; r>=1; r--) {
            if (obstaclesSet.contains(new Obstacle(r, c_q)))
                break;
            else
                sCells++;
        }
        return sCells;
    }

    static int westCells(TreeSet<Obstacle> obstaclesSet, int n,int r_q,int c_q) {
        int wCells = 0;
        for (int c = c_q - 1; c>=1; c--) {
            if (obstaclesSet.contains(new Obstacle(r_q,c)))
                break;
            else
                wCells++;
        }
        return wCells;
    }

    static int eastCells(TreeSet<Obstacle> obstaclesSet, int n,int r_q,int c_q) {
        int eCells = 0;
        for (int c = c_q + 1; c<=n; c++) {
            if (obstaclesSet.contains(new Obstacle(r_q, c)))
                break;
            else
                eCells++;
        }
        return eCells;
    }

    static TreeSet<Obstacle> setObstacles(int[][] obstacles){
        TreeSet<Obstacle> obstaclesSet=new TreeSet<Obstacle>();
        int obsLen=obstacles.length;
        for(int i=0;i<obsLen;i++)
            obstaclesSet.add(new Obstacle(obstacles[i][0],obstacles[i][1]));
        return obstaclesSet;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
