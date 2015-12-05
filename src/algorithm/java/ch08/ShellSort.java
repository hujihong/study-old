package ch08;

/*
 * 希尔排序
 */
public class ShellSort {
	/**
	 * 排序方法
	 */
	public static void sort(long[] arr) {
		// 初始化一个间隔
		int h = 1;
		// 计算最大间隔
		while (h < arr.length / 3) {
			h = h * 3 + 1;
		}

		while (h > 0) {
			// 进行插入排序
			long tmp = 0;
			for (int i = h; i < arr.length; i++) {
				tmp = arr[i];
				int j = i;
				while (j > h - 1 && arr[j - h] >= tmp) {
					arr[j] = arr[j - h];
					j -= h;
				}
				arr[j] = tmp;
			}
			// 减小间隔
			h = (h - 1) / 3;
		}
	}

	public static void sort2(long[] a) {
		// int[]a={49,38,65,97,76,13,27,49,78,34,12,64,1};
		System.out.println("排序之前：");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		// 希尔排序
		int d = a.length;
		while (true) {
			d = d / 2;
			for (int x = 0; x < d; x++) {
				for (int i = x + d; i < a.length; i = i + d) {
					long temp = a[i];
					int j;
					for (j = i - d; j >= 0 && a[j] > temp; j = j - d) {
						a[j + d] = a[j];
					}
					a[j + d] = temp;
				}
			}
			if (d == 1) {
				break;
			}
		}
		System.out.println();
		System.out.println("排序之后：");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
}
