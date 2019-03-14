import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    /**
    * Class to represent each matrix position.
    */
    static class MatrixPosition implements Comparable<MatrixPosition> {
        private final int row;
        private final int column;

        MatrixPosition(int row,int column){
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
            if (!(other instanceof MatrixPosition)) {
                return false;
            }
            MatrixPosition otherMatrixPosition = (MatrixPosition) other;
            return row == otherMatrixPosition.row && column == otherMatrixPosition.column;
        }

        @Override public int hashCode() {
            return row * 13 + column * 37;
        }

        @Override public int compareTo(MatrixPosition other) {
            return row != other.row 
                ? Integer.compare(row, other.row) 
                : Integer.compare(column, other.column);
        }

        public String toString(){
            return row + ":" + column;
        }
    }

    /**
    * Puts matrix list elements inside map.
    */
    static TreeMap<MatrixPosition,Integer> mapMatrix(List<List<Integer>> matrix,
            int rows,int columns){
        TreeMap<MatrixPosition,Integer> matrixMap=new TreeMap<MatrixPosition,Integer>();
        for(int h=0;h<rows;h++)
            for(int v=0;v<columns;v++)
                matrixMap.put(new MatrixPosition(h,v),matrix.get(h).get(v));
        return matrixMap;
    }

    static void matrixRotation(List<List<Integer>> matrix, int r) {
        //m - number of lines
        int m=matrix.size();
        //n - number of columns
        int n=matrix.get(0).size();
        //Put matrix elements on map for performance purpose
        TreeMap<MatrixPosition,Integer> matrixMap=mapMatrix(matrix,m,n);
        //Check if is necessary to rotate the matrix
        if(!sameElements(matrixMap))
            rotateMatrix(matrixMap,m,n,r);
        printMatrix(matrixMap,m,n);
    }

    /**
    * Does k anti-clockwise matrix rotations(with limit=r%((2*m-2)+(2*n-2))).
    */
    static void rotateMatrix(TreeMap<MatrixPosition,Integer> matrixMap,int m,int n,int r){
        //from left and right sides to middle - columns (horizontal progression)
        int horizontal=0;
        //from top and bottom edges to middle - rows (vertical progression)
        int vertical=0;
        int rotationPositions=(2*m-2)+(2*n-2);
        int k=r%rotationPositions;
        while(vertical<m/2 && m-vertical+1>m/2 && 
                horizontal<n/2 && n-horizontal+1>n/2){
            LinkedHashMap<MatrixPosition,Integer> layer=
                getLayer(matrixMap,vertical,horizontal,m,n);
            //Rotates the layer elements and updates the positions of the 
            //rotated layer elements in the matrixMap
            if(k>0){
                LinkedHashMap<MatrixPosition,Integer> lRot=getLayerRotated(layer,layer.size(),
                    vertical,horizontal,m,n,k);
                updateMatrixMap(matrixMap,lRot);
            }
            else
                updateMatrixMap(matrixMap,layer);
            if((vertical+1==m/2 && m-vertical+1-1==m/2) || 
                    (horizontal+1==n/2 && n-horizontal+1-1==n/2))
                break;
            //Updates the position and rotation counters
            else{
                horizontal++;
                vertical++;
                if(rotationPositions-8<=0)
                    rotationPositions=0;
                else
                    rotationPositions=rotationPositions-8;
                if(rotationPositions>0)
                    k=r%rotationPositions;
                else
                    k=0;
            }
        }
    }

    /**
    * Rotates the layer elements.
    * Uses a copy of LinkedHashMap to update the value of each MatrixPosition in layer.
    * rotations always <= keys.size()
    */
    static LinkedHashMap<MatrixPosition,Integer> getLayerRotated(
            LinkedHashMap<MatrixPosition,Integer> layer,int layerSize,
            int vertical,int horizontal,int m,int n,int rotations){
        LinkedHashMap<MatrixPosition,Integer> layerRotated=
            new LinkedHashMap<MatrixPosition,Integer>();
        LinkedHashSet<MatrixPosition> keys=new LinkedHashSet<MatrixPosition>(layer.keySet());
        List<Integer> values=new ArrayList<Integer>(layer.values());
        LinkedHashMap<Integer,List<Integer>> halfs=
            getHalfValues(values,rotations);
        List<Integer> firstHalfValues=halfs.get(0);
        List<Integer> secondHalfValues=halfs.get(1);
        Iterator<Integer> firstHalfIter=firstHalfValues.iterator();
        Iterator<Integer> secondHalfIter=secondHalfValues.iterator();
        for(MatrixPosition mp:keys){
            if(secondHalfIter.hasNext())
                layerRotated.put(mp,secondHalfIter.next());
            else if(firstHalfIter.hasNext())
                layerRotated.put(mp,firstHalfIter.next());
        }
        return layerRotated;
    }

    /**
    * Gets values of MatrixPosition's divided in half.
    * 0<->rotations-1 and rotations<->values.size()-1
    * Uses List for the case of equal values in at least two different
    * matrix positions.
    */
    static LinkedHashMap<Integer,List<Integer>> 
            getHalfValues(List<Integer> values,int rotations){
        List<Integer> firstHalfValues=new ArrayList<Integer>();
        List<Integer> secondHalfValues=new ArrayList<Integer>();
        LinkedHashMap<Integer,List<Integer>> halfs=
            new LinkedHashMap<Integer,List<Integer>>();
        int valSize=values.size();
        Iterator<Integer> it=values.iterator();
        for(int i=0;i<valSize;i++){
            if(i<=rotations-1)
                firstHalfValues.add(it.next());
            else
                secondHalfValues.add(it.next());
        }
        halfs.put(0,firstHalfValues);
        halfs.put(1,secondHalfValues);
        return halfs;
    }

    /**
    * Updates the positions of the rotated layer elements in the matrixMap.
    */
    static void updateMatrixMap(TreeMap<MatrixPosition,Integer> matrixMap,
            LinkedHashMap<MatrixPosition,Integer> layerRotated){
        LinkedHashSet<MatrixPosition> layerRotatedKeys=
                new LinkedHashSet<MatrixPosition>(layerRotated.keySet());
        for(MatrixPosition mp:layerRotatedKeys)
            matrixMap.replace(mp,layerRotated.get(mp));
    }

    /**
    * Checks if all elements are equal or not.
    */
    static boolean sameElements(TreeMap<MatrixPosition,Integer> matrixMap){
        int firstVal=matrixMap.get(matrixMap.firstKey());
        boolean result=true;
        Set<MatrixPosition> keys=matrixMap.keySet();
        for(MatrixPosition mp:keys){
            if(matrixMap.get(mp)!=firstVal){
                result=false;
                break;
            }
        }
        return result;
    }

    /**
    * Gets rowNumber, n-(columnNumber+1), m-(rowNumber+1) and columnNumber 
    * (by this order - clockwise) matrix positions.
    */
    static LinkedHashMap<MatrixPosition,Integer> getLayer(
            TreeMap<MatrixPosition,Integer> matrixMap,int rowNumber,int columnNumber,
            int m,int n){
        LinkedHashMap<MatrixPosition,Integer> layer=new LinkedHashMap<MatrixPosition,Integer>();
        //top row
        for(int i=columnNumber;i<n-columnNumber;i++){
            MatrixPosition mp=new MatrixPosition(rowNumber,i);
            layer.put(mp,matrixMap.get(mp));
        }
        //rightmost column
        for(int i=rowNumber+1;i<m-rowNumber;i++){
            MatrixPosition mp=new MatrixPosition(i,n-(columnNumber+1));
            layer.put(mp,matrixMap.get(mp));
        }
        //bottom row
        for(int i=(n-(columnNumber+1))-1;i>=columnNumber;i--){
            MatrixPosition mp=new MatrixPosition(m-(rowNumber+1),i);
            layer.put(mp,matrixMap.get(mp));
        }
        //leftmost column
        for(int i=(m-(rowNumber+1))-1;i>=rowNumber+1;i--){
            MatrixPosition mp=new MatrixPosition(i,columnNumber);
            layer.put(mp,matrixMap.get(mp));
        }
        return layer;
    }

    /**
    * Prints matrix.
    */
    static void printMatrix(TreeMap<MatrixPosition,Integer> matrixMap,int rows,int columns){
        StringBuilder sb=new StringBuilder();
        Set<MatrixPosition> keys=matrixMap.keySet();
        for(MatrixPosition mp:keys){
            sb.append(matrixMap.get(mp)+"");
            if(mp.getColumn()<columns-1)
                sb.append(" ");
            if(mp.getColumn()==columns-1 && mp.getRow()<rows-1)
                sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] mnr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(mnr[0]);

        int n = Integer.parseInt(mnr[1]);

        int r = Integer.parseInt(mnr[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        IntStream.range(0, m).forEach(i -> {
            try {
                matrix.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
