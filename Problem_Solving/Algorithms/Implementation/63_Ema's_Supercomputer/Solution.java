import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static class Coordinate implements Comparable<Coordinate> {
        private final int x;
        private final int y;

        Coordinate(int x,int y){
            this.x=x;
            this.y=y;
        }

        int getRowCoord() {
            return this.x;
        }

        int getColumnCoord() {
            return this.y;
        }

        @Override
        public boolean equals(Object other){
            if (!(other instanceof Coordinate)){
                return false;
            }
            Coordinate otherCoordinate=(Coordinate)other;
            return x==otherCoordinate.x && y==otherCoordinate.y;
        }

        @Override
        public int hashCode(){
            return x*31+y*13;
        }

        @Override
        public int compareTo(Coordinate other) {
            return x!=other.x?Integer.compare(x,other.x):Integer.compare(y,other.y);
        }

        public String toString(){
            return x+" : "+y;
        }
    }

    static int twoPluses(String[] grid){
        int n=grid.length;
        int m=grid[0].length();
        LinkedHashSet<TreeSet<Coordinate>> allPluses=
            new LinkedHashSet<TreeSet<Coordinate>>();
        TreeSet<Coordinate> goodCells=getGoodCells(grid,n,m);
        for(Coordinate coord:goodCells)
            findPlus(goodCells,coord,n,m).stream()
            .forEach(plus -> allPluses.add(plus));
        return getBiggestArea(allPluses);
    }

    /**
    * Search all pluses for the biggest area possible
    * doing the multiplication of two pluses.
    */
    static int getBiggestArea(LinkedHashSet<TreeSet<Coordinate>> allPluses){
        int result=0;
        int pSize=allPluses.size();
        LinkedList<TreeSet<Coordinate>> pluses=allPluses.stream()
            .sorted(Comparator.comparing(TreeSet<Coordinate>::size).reversed())
            .collect(Collectors.toCollection(LinkedList::new));
        for(int i=0;i<pSize-1;i++){
            for(int j=i+1;j<pSize;j++){
                if((pluses.get(i).size()*pluses.get(j).size())>result && 
                    noRepetitions(pluses.get(i),pluses.get(j))){
                    result=pluses.get(i).size()*pluses.get(j).size();
                }
            }
        }
        return result;
    }

    /**
    * Returns true if there are no repetitions between the two coordinate sets,
    * false, otherwise
    */
    static boolean noRepetitions(TreeSet<Coordinate> firstCoord,
            TreeSet<Coordinate> secondCoord){
        boolean result=true;
        for(Coordinate coord:firstCoord){
            if(secondCoord.contains(coord)){
                result=false;
                break;
            }
        }
        return result;
    }

    /**
    * For this coordinate, finds the coordinates of the pluses that can be created
    */
    static LinkedList<TreeSet<Coordinate>> findPlus(TreeSet<Coordinate> goodCells, 
            Coordinate coord,int n,int m) {
        LinkedList<TreeSet<Coordinate>> plusesCoord=
            new LinkedList<TreeSet<Coordinate>>();
        TreeSet<Coordinate> onePlus=new TreeSet<Coordinate>();
        TreeSet<Coordinate> auxPlusOne=new TreeSet<Coordinate>(onePlus);
        auxPlusOne.add(coord);
        plusesCoord.add(auxPlusOne);
        onePlus.add(coord);
        int r=coord.getRowCoord();
        int c=coord.getColumnCoord();
        for(int i=1;i<=n && i<=m;i++) {  
            if(c-i>=1 && c+i<=m && r-i>=1 && r+i<=n){
                Coordinate cEast=new Coordinate(r,c+i);
                Coordinate cWest=new Coordinate(r,c-i);
                Coordinate cNorth=new Coordinate(r-i,c);
                Coordinate cSouth=new Coordinate(r+i,c);
                if(goodCells.contains(cWest) && 
                    goodCells.contains(cEast) &&
                    goodCells.contains(cNorth) && 
                    goodCells.contains(cSouth)){
                    TreeSet<Coordinate> auxPlus=new TreeSet<Coordinate>(onePlus);
                    auxPlus.add(cWest);
                    auxPlus.add(cEast);
                    auxPlus.add(cNorth);
                    auxPlus.add(cSouth);
                    plusesCoord.add(auxPlus);
                    onePlus.add(cWest);
                    onePlus.add(cEast);
                    onePlus.add(cNorth);
                    onePlus.add(cSouth);
                }
                else    
                    break;
            }
            else
                break;
        }
        return plusesCoord;
    }

    /**
    * Find and return just the good cells
    */
    static TreeSet<Coordinate> getGoodCells(String[] grid,int n,int m){
        TreeSet<Coordinate> goodCells=new TreeSet<Coordinate>();
        for(int r=1;r<=n;r++){
            String str=grid[r-1];
            for(int c=1;c<=m;c++){
                if(str.charAt(c-1)=='G')
                    goodCells.add(new Coordinate(r,c));
            }
        }
        return goodCells;
    }
    
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        int result = twoPluses(grid);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
