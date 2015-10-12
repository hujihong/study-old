package ch02;

// 选择排序  比冒泡效率高（循环次数少些）
public class SelectionSort {
	
	public static void sort(long[] arr) {
		int k = 0;
		long tmp = 0;
		// 从头开始
		for(int i = 0; i < arr.length - 1; i++) {
			k = i;  // k 指向 i
			for(int j = i; j < arr.length; j++) {
				if(arr[j] < arr[k]) {
					k = j; // 交换
				}
			}
			if(i != k) {
				tmp = arr[i];
				arr[i] = arr[k];
				arr[k] = tmp;
			}
		}
	}
}
