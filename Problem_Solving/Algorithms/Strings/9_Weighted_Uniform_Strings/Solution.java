import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static String[] weightedUniformStrings(String s,int[] queries) {
        int qLen=queries.length;
        int sLen=s.length();
        List<Integer> queriesList=prepareQueries(queries,qLen);
        
        //Map String s characters respective position in String s 
        //and value weight (1-26) 
        Map<Integer,Integer> sWeights=mapS(s,sLen);
           
        //Maximum times we saw each letter consecutively?
        //Map<char_weight_value,max_consecutive_times_seen>
        Map<Integer,Integer> lettersCounter=getMaxConsecutiveSubstrings(sWeights,sLen);
        
        //Check all weight queries against the weights of each founded maximum substring
        //to get the final responses about the presence of that particular weight query
        //in String s
        return checkFoundedWeights(queriesList,lettersCounter,qLen);
    }      
    
    /**
     * Checks if each query weight is divisible by any of the chars weight values 
     * and simultaneously if the multiplication of the size of maximum
     * consecutive occurrences of that char by the weight value of that char
     * is equal or bigger than the query weight being checked. 
     * This represents the existence of the query weight in String s.
     * Returns the final String responses.
     */
    static String[] checkFoundedWeights(List<Integer> queriesList,
    		Map<Integer,Integer> lettersCounter,int qLen){
    	List<String> responses=new ArrayList<String>();
    	boolean flag=false;
    	
    	for(Integer query:queriesList){
    		for(Map.Entry<Integer,Integer> pair:lettersCounter.entrySet()){
	    		if((query%pair.getKey()==0)&&((pair.getValue()*pair.getKey())>=query)){
	    			responses.add("Yes");
	    			flag=true;
	    			break;
	    		}
    		}
    		if(flag==false)
    			responses.add("No");
    		else
    			flag=false;
    	}
    	
        String[] result=new String[qLen];
    	result=responses.toArray(result);
    	return result;
    }
    
    /**
     * Check all String s consecutive character substrings and
     * returns the size of the maximum consecutive character substring 
     * for each character from a to z.
     */
    static Map<Integer,Integer> getMaxConsecutiveSubstrings(
            Map<Integer,Integer> sWeights,int sLen){
    	Map<Integer,Integer> lettersCounter=new TreeMap<Integer,Integer>();
    	int maxRepetitions=1;
    	int firstCharWeight=0;
    	for(int i=0;i<sLen;i++){
    		firstCharWeight=sWeights.get(i);
    		while(i+1<sLen && sWeights.get(i+1)==firstCharWeight){
    			i++;
    			maxRepetitions++;
    		}
    		//Add or update counter
            updateCounter(firstCharWeight,maxRepetitions,lettersCounter);
            maxRepetitions=1;
    	}
    	return lettersCounter;
    }

    /**
    * Adds or updates counter of a consecutive character of String s.
    */
    static void updateCounter(int chWeight,int repetitions,
    		Map<Integer,Integer> lettersCounter){
        if(lettersCounter.containsKey(chWeight)){
            if(lettersCounter.get(chWeight)<repetitions)
            	lettersCounter.replace(chWeight,repetitions);
        }    
        else
            lettersCounter.put(chWeight,repetitions);
    }
    
    /**
     * Map String s characters respective position in String s and value weight (1-26).
     */
    static Map<Integer,Integer> mapS(String s,int sLen){
    	Map<Integer,Integer> sWeights=new LinkedHashMap<Integer,Integer>();
    	for(int i=0;i<sLen;i++)
    		//[ascii: 97-122 -> 1-26]
    		sWeights.put(i,((int)s.charAt(i))-96);
    	return sWeights;
    }

    /**
    * Puts queries weights in List.
    * Order maintenance is maintained.
    */
    static List<Integer> prepareQueries(int[] queries,int qLen){
    	List<Integer> queriesList=new ArrayList<Integer>();
        for(int i=0;i<qLen;i++)
        	queriesList.add(queries[i]);
        return queriesList;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        int queriesCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] queries = new int[queriesCount];

        for (int i = 0; i < queriesCount; i++) {
            int queriesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            queries[i] = queriesItem;
        }

        String[] result = weightedUniformStrings(s, queries);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(result[i]);

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}