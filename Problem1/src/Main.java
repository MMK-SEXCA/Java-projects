/*Given a array of string followed by two words. 
You have to find the minimum distance between the two words in the given array of string. For example:
(“the”, “quick”, “brown”, “fox”, “quick”)
distance(“fox”,”the”) == 3
distance(“quick”, “fox”) == 1
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Hello World ");
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("the");
		list.add("quick");
		list.add("brown");
		list.add("fox");
		list.add("quick");
		
		printList(list);
		HashMap<String,TreeSet<Integer>> hashmap = getHashMap(list);
		printHashMap(hashmap);
		printDistance("the","brown",hashmap);
		printDistance("quick","fox",hashmap);
	}
	
	private static void printDistance(String string, String string2, HashMap<String,TreeSet<Integer>> hashmap) {
		// TODO Auto-generated method stub
		int distance1=Integer.MAX_VALUE;
		int distance2=Integer.MAX_VALUE;
		int minDistance = Integer.MAX_VALUE;
		TreeSet<Integer> list_2 = hashmap.get(string2);
		Iterator<Integer> iterator1 = hashmap.get(string).iterator();
		while(iterator1.hasNext()){
			int value = iterator1.next();
			if(list_2.floor(value) != null){
				distance1 = Math.abs(list_2.floor(value)-value);
			}
			if(list_2.ceiling(value) != null){
				distance2 = Math.abs(list_2.ceiling(value)-value);
			}
			minDistance = Math.min(minDistance, Math.min(distance2, distance1));
		}
		System.out.println("minimum distance between \""+string+"\" and \""+string2+"\" is : " + minDistance);
	}

	private static void printHashMap(Object hashmap) {
		// TODO Auto-generated method stub
		System.out.println("Current hashmap is " + hashmap);
	}

	private static HashMap<String, TreeSet<Integer>> getHashMap(ArrayList<String> list) {
		// TODO Auto-generated method stub
		HashMap<String,TreeSet<Integer>> hashmap = new HashMap<String,TreeSet<Integer>>();
		int count = 0;
		for(String temp : list){
			if(hashmap.get(temp) == null){
				TreeSet<Integer> indexList = new TreeSet<Integer>();
				indexList.add(count);
				hashmap.put(temp, indexList);
			}else{
				hashmap.get(temp).add(count);
			}
			count++;
		}
		return hashmap;
	}

	private static void printList(ArrayList<String> list){
		System.out.println("Current List is " + list);
	}
}
