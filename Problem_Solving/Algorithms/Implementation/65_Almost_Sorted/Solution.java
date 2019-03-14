import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static void almostSorted(int[] arr) {
        LinkedHashMap<Integer,Integer> arrElems=optimizeArr(arr);
        if(isSorted(arrElems)){
            System.out.println("yes");
        }
        else{
            LinkedHashMap<Integer,Integer> sortedArrElems=sortedCopy(arr);
            LinkedHashMap<Integer,Integer> diffIndexes=findDiffs(arrElems,sortedArrElems);
            int numDiffs=diffIndexes.size();
            //there are just two different elements
            //and if swapped, array turns sorted
            //or
            //there are just two different elements
            //and if swapped, array maintains unsorted
            if(numDiffs==2){
                int firstIndex=diffIndexes.get(0);
                int secondIndex=diffIndexes.get(numDiffs-1);
                swap(arrElems,firstIndex,secondIndex);
                if(isSorted(arrElems))
                    System.out.println("yes\nswap "+(firstIndex+1)+" "+(secondIndex+1));
                //reverse operation cannot sort the array because it's the same
                //as doing the swap when there are just two elements to swap
                else
                    System.out.println("no");
            }
            //there are more than two different elements
            //and if reversed, array turns sorted
            //(means that this indexes are adjacent
            //and diff indexes are in descending order)
            //or
            //there are more than two different elements
            //and if reversed, array maintain unsorted
            //(means that this indexes are not adjacent
            //or diff indexes are not in descending order)
            else if(numDiffs>2){
                if(areIndexesAdjacent(diffIndexes)){
                    if(areDescOrder(diffIndexes,arrElems)){
                        //get first and last index to print
                        int firstIndex=diffIndexes.get(0);
                        int lastIndex=diffIndexes.get(numDiffs-1);
                        System.out.println("yes\nreverse "+(firstIndex+1)+" "+(lastIndex+1));
                    }
                    else
                        System.out.println("no");
                }
                else
                    System.out.println("no");
            }
            else
                System.out.println("no");
        }
    }
    
    /**
     * Puts array arr elements inside LinkedHashMap for optimization purpose
     * Maintains order of insertion
     */
    static LinkedHashMap<Integer,Integer> optimizeArr(int[] arr){
        LinkedHashMap<Integer,Integer> arrElems=new LinkedHashMap<Integer,Integer>();
        int arrLen=arr.length;
        for(int i=0;i<arrLen;i++)
            arrElems.put(i,arr[i]);
        return arrElems;
    }

    /**
    * Are elements in arrElems Map, indicated by indexes of elements out of place at
    * diffIndexes Map, in descending order?
    */
    static boolean areDescOrder(LinkedHashMap<Integer,Integer> diffIndexes, 
                LinkedHashMap<Integer,Integer> arrElems){
        int indexesLen=diffIndexes.size();
        for(int i=0;i<indexesLen-1;i++){
            if(arrElems.get(diffIndexes.get(i))<=arrElems.get(diffIndexes.get(i+1)))
                return false;
        }
        return true;
    }

    /**
    * Are indexes adjacent?
    */
    static boolean areIndexesAdjacent(LinkedHashMap<Integer,Integer> diffIndexes){
        int indexesLen=diffIndexes.size();
        for(int i=0;i<indexesLen-1;i++){
            if(diffIndexes.get(i)!=diffIndexes.get(i+1)-1)
                return false;
        }
        return true;
    }

    /**
    * Sorts a copy of array arr and returns the sorted elements
    * in LinkedHashMap
    */
    static LinkedHashMap<Integer,Integer> sortedCopy(int[] arr){
        int arrLen=arr.length;
        int[] sortedCopy=Arrays.copyOf(arr, arrLen);
        Arrays.sort(sortedCopy);
        LinkedHashMap<Integer,Integer> sortedCopyElems=new LinkedHashMap<Integer,Integer>();
        for(int i=0;i<arrLen;i++)
            sortedCopyElems.put(i,sortedCopy[i]);
        return sortedCopyElems;
    }

    /**
    * Compares the Map with unsorted elements with the Map with sorted elements 
    * to find the indexes of unsorted elements.
    * Returns the Map with the indexes that need to be swapped or reversed
    */
    static LinkedHashMap<Integer,Integer> findDiffs(LinkedHashMap<Integer,Integer> arrElems,
                    LinkedHashMap<Integer,Integer> sortedArrElems){
        LinkedHashMap<Integer,Integer> diffIndexes=new LinkedHashMap<Integer,Integer>();
        int arrLen=arrElems.size();
        int countDiff=0;
        for(int i=0;i<arrLen;i++){
            if(!arrElems.get(i).equals(sortedArrElems.get(i)) || 
                    (arrElems.get(i).equals(sortedArrElems.get(i)) && 
                        ((i-1>=0 && !arrElems.get(i-1).equals(sortedArrElems.get(i-1))) && 
                        (i+1<arrLen && !arrElems.get(i+1).equals(sortedArrElems.get(i+1)))))){
                diffIndexes.put(countDiff,i);
                countDiff++;
            }
        }
        return diffIndexes;
    }

    /**
    * Checks if the arr elements in Map are sorted
    */
    static boolean isSorted(LinkedHashMap<Integer,Integer> arrElems){
        int arrLength=arrElems.size();
        for(int i=0;i<arrLength-1;i++){
            if(arrElems.get(i)>=arrElems.get(i+1)){
                return false;
            }
        }
        return true;
    }

    /**
    * Swaps two elements of the arrElems Map
    */
    static void swap(LinkedHashMap<Integer,Integer> arrElems, int index1, int index2){
        int temp=arrElems.get(index1);
        arrElems.replace(index1,temp,arrElems.get(index2));
        arrElems.replace(index2,arrElems.get(index2),temp);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        almostSorted(arr);

        scanner.close();
    }
}
