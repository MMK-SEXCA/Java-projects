import java.util.ArrayList;
import java.util.Random;

/**
 * 
 */

/**
 * @author rakshith
 *
 */


public class Main {
	
	private static final int MAX = 100000;
	
	public static void main(String[] args) {
		System.out.println("Hello World");
		System.out.println(" a power b = "+recursivePower(2,11));
		ArrayList<Integer> List = new ArrayList<Integer>();
		Random rand = new Random();
		for(int i=0;i<10;i++){
			List.add(rand.nextInt(50)+1);
		}
		//int array[] = {1,2};
		//System.out.println(" sum bit difference = "+sumBitDifferences(array,64));
		int array[] = {1,4,45,6,7,10,8};
		int x = 10;
		printPairs(array,x);
		
	}
	
	/*recursive function to retrieve power.*/
	public static int recursivePower(int base, int exponent){
		if (base == 1){
			return 1;
		}else if(exponent == 1){
			return base;
		}
		int value;
		if(exponent%2 == 0){
			value = (int) Math.pow(Double.parseDouble(Integer.toString(base)), Double.parseDouble(Integer.toString(exponent/2)));
			return value*value;
		}else{
			value = (int) Math.pow(Double.parseDouble(Integer.toString(base)), Double.parseDouble(Integer.toString(exponent - 1)));
			return base*value;
		}
	}
	
	/*Minimum number of swaps for pairing array of integers*/
	public static int minimumSwaps(ArrayList<Integer> numbers){
		
		return 0;
	}
	
	/*Sum of Bit Differences. Function not implemented correctly.*/
	public static int sumBitDifferences(int array[], int n){
		int ans = 0;
		for(int i=0; i < 32; i++){
			int count = 0;
			if( !(( array[i] & 1 << i) == 0)){
				count++;
			}
			ans += (count * (n - count)*2);
		}
		return ans;
	}
	
	/*Find integers to sum x, in a given array.*/
	public static void printPairs(int array[], int sum){
		boolean[] binmap = new boolean[MAX];
		
		for(int i=0 ; i<array.length ; i++){
			int temp = sum - array[i];
			
			if(temp >= 0 && binmap[temp]){
				System.out.println(" Pair with given sum " +
						sum + " is ( "+ array[i] + ", "+temp+")");
			}
			
			binmap[array[i]] = true;
		}
	}
}
