package ch03;

/**
 * 使用双端队列

创建一个双端队列deque，deque只存储当前的窗口中有用的元素，有用也就是会对未来的选择有影响的元素。插入时保证下面两个性质：

1） 首先，我们保证每次都是队尾插入元素，也就是队首的元素的肯定是距离当前元素最远的。因此每次滑动窗口，只判断队首元素是否超出窗口范围，超出的话则删除。

2） 然后保证deque中的元素是升序的，队尾是最小的元素。由于是从队尾插入，只需要把队尾的元素和当前元素比较即可，不符合条件，则删除队尾元素。

为了判断元素是否超出窗口范围，我们需要存储元素下标。

这种队列，又叫做单调队列，练手题目:POJ 2823
 */
import java.util.Deque;
import java.util.LinkedList;
//给定大小为N的数组。数组被分为大小为k的子数组,找到每个子数组的最大值。子数组即为滑动窗口
public class MaximumSubArrK {

	static void printKMax(int arr[],int k){
		Deque<Integer> deque = new LinkedList<Integer>();
		for(int i=0; i < arr.length; i++){
			while(!deque.isEmpty() && (i-deque.peekFirst()>=k) )
				deque.pollFirst();
			while(!deque.isEmpty() && arr[deque.peekLast()] <= arr[i] )
				deque.pollLast();
			deque.addLast(i);
			if(i >= k-1)
				System.out.print(arr[ deque.peekFirst() ] + ",");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int arr[] =  {8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
		printKMax(arr ,4);

	}
}