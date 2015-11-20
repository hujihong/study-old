package ch02;

public class TestSort {
	public static void main(String[] args) {
		long[] arr = new long[5];
		arr[0] = 34;
		arr[1] = 23;
		arr[2] = 2;
		arr[3] = 1;
		arr[4] = 20;
		
		display(arr);
		// System.out.println();
		System.out.println("====");
		// InsertSort.sort(arr);
		// InsertSort.insertion_sort(arr);
		SelectionSort.sort(arr);
		// BubbleSort.sort(arr);
		// InsertSort.sort2(arr);
		System.out.println("====");
		display(arr);
		// System.out.println();
		
	}

	public static void display(long[] arr) {
		System.out.print("[");
		for(long num : arr) {
			System.out.print(num + " ");
		}
		System.out.print("]");
		System.out.println();
	}
}
