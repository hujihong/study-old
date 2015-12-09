package ch03;

public class FindHalf {
	
	public static void main(String[] args) {
		long[] arr = new long[]{1,2,3,5,6};
		long curValue = arr[0];
		int count = 1;
		for(int i = 1; i < arr.length; ++i) {
			if(arr[i] == curValue){
				count ++;
			}else{
				count --;
				if(count < 0) {
					curValue = arr[i];
					count = 1;
				}
			}
		}
		// System.out.println(curValue);
		
//		int k = 40;
//		System.out.println(k^k);
//		System.out.println(k^0);
		
		arr = new long[]{2,2,3,6,6,7,7};
		curValue = arr[0];
		for(int i = 1; i < arr.length; i++) {
			curValue = curValue^arr[i];
		}
		System.out.println(curValue);
 	}
}
