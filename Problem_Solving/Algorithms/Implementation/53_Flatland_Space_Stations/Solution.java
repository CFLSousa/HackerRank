import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int flatlandSpaceStations(int n, int[] c) {
        int cLen = c.length;
        int result = 0;
        if (n != cLen) {
            TreeSet<Integer> spaceStations = getSpaceStations(c, cLen);
            TreeSet<Integer> minDists = new TreeSet<Integer>();
            for (int city = 0; city < n; city++) {
                if (!spaceStations.contains(city))
                    minDists.add(getNearestSpaceStation(city, spaceStations, n));
                else
                    minDists.add(0);
            }
            result = minDists.last();
        }
        return result;
    }

    /**
     * Get distance from this city to the nearest space station
     */
    static int getNearestSpaceStation(int city, TreeSet<Integer> spaceStations, int n) {
        int minDist = Integer.MAX_VALUE;
        int nextDiff = -1;
        int previousDiff = -1;
        if (spaceStations.ceiling(city) != null)
            nextDiff = Math.abs(spaceStations.ceiling(city) - city);
        if (spaceStations.floor(city) != null)
            previousDiff = Math.abs(city - spaceStations.floor(city));
        if (nextDiff != -1 && previousDiff != -1)
            minDist = Math.min(nextDiff, previousDiff);
        else if (nextDiff != -1)
            minDist = nextDiff;
        else
            minDist = previousDiff;
        return minDist;
    }

    /**
     * Puts spaceStations array values inside TreeSet for performance and sorting
     * purpose
     */
    static TreeSet<Integer> getSpaceStations(int[] c, int cLen) {
        TreeSet<Integer> spaceStations = new TreeSet<Integer>();
        for (int i = 0; i < cLen; i++)
            spaceStations.add(c[i]);
        return spaceStations;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[] c = new int[m];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = flatlandSpaceStations(n, c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
