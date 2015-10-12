package ch02;

// 冒泡排序 (从小到大)
public class BubbleSort {
	
	public static void sort(long[] arr) {
		long tmp = 0;
		// 从头开始
		for(int i = 0; i < arr.length - 1; i++) {
			// 从尾开始
			for(int j = arr.length - 1; j > i; j--) {
				if(arr[j] < arr[j - 1]) {
					// 交换
					tmp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = tmp;
				}
			}
		}
	}
}
