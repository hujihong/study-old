package ch02;

// 插入排序
public class InsertSort {

	public static void sort(long[] arr) {
		long tmp = 0;

		for (int i = 1; i < arr.length; i++) {
			tmp = arr[i];
			int j = i;
			while (j > 0 && arr[j] >= tmp) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = tmp;
		}
	}

	
	
	public static void insertion_sort(long[] unsorted) {
        for (int i = 1; i < unsorted.length; i++) {
            if (unsorted[i - 1] > unsorted[i]) {
                long temp = unsorted[i];
                int j = i;
                while (j > 0 && unsorted[j - 1] > temp) {
                    unsorted[j] = unsorted[j - 1];
                    j--;
                }
                unsorted[j] = temp;
            }
        }
    }
	
	/**
	 * 插入排序
	 * 
	 * @paramarr
	 * @return
	 */
	public static long[] sort2(long[] arr) {
		if (arr == null || arr.length < 2) {
			return arr;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = i; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					// TODO:
					long temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				} else {
					// 接下来是无用功
					break;
				}
			}
		}
		return arr;
	}
}
