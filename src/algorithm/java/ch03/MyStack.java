package ch03;

public class MyStack {
	//底层实现是一个数组
	private long[] arr;
	private int top;
	
	/**
	 * 默认的构造方法
	 */
	public MyStack() {
		arr = new long[10];
		top = -1;
	}
	
	/**
	 * 带参数构造方法，参数为数组初始化大小
	 */
	public MyStack(int maxsize) {
		arr = new long[maxsize];
		top = -1;
	}
	
	/**
	 * 添加数据
	 */
	public void push(int value) {
		if(!isFull()) {
			arr[++top] = value;
		}else{
			throw new RuntimeException("full");
		}
	}
	
	/**
	 * 移除数据
	 */
	public long pop() {
		if(!isEmpty()) {
			return arr[top--];
		}else{
			throw new RuntimeException("emp");
		}
	}
	
	/**
	 * 查看数据
	 */
	public long peek() {
		return arr[top];
	}
	
	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return top == -1;
	}
	
	/**
	 * 判断是否满了
	 */
	public boolean isFull() {
		return top == arr.length - 1;
	}
}
