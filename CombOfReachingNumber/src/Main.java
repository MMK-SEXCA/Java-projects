import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		int n = 4;
		ArrayList<Integer> list = new ArrayList<Integer>();
		enlistAllCombinations(n+1,list);
		
	}

	private static void enlistAllCombinations(int n, ArrayList<Integer> list) {
		// TODO Auto-generated method stub
		if(n<=1){
			if(list.size() != 1){
				for(int i : list){
					System.out.print(i+" ");
				}
				System.out.println();
			}
		}else{
			for(int i=1;i<n;i++){
				list.add(i);
				enlistAllCombinations(n-i,list);
				list.remove(list.size()-1);
			}
		}
	}
}
