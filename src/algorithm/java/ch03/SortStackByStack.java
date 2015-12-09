package ch03;

import java.util.Stack;

/**
 * 一个栈元素的类型为整数，现在要想将该栈从顶到底按从大到小的顺序排列，只允许申请一个栈，除此之外， 可以申请一个变量，可以申请额外的变量，但是不能申请额外的数据结构，如何完成排序
 * 
 * @author Think 思路：我们需要排序的栈为stack,然后我们申请一个栈记为help,在stack上面执行pop()操作，弹出的元素记为cur 如果cur小于或者等于栈顶元素，则将cur压入help,
 *         如何cur大于help的栈顶元素，则将help的元素涿步弹出，涿一压入stack，直到cur小于或等于help的栈顶元素、再讲 cur压入help
 */
public class SortStackByStack {
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(3);
		s.push(2);
		s.push(5);
		s.push(4);
		s.push(1);
		s = sortStackbyStack(s);
		while (!s.isEmpty()) {
			System.out.println(s.pop());
		}
	}

	public static Stack<Integer> sortStackbyStack(Stack<Integer> stack) {
		Stack<Integer> help = new Stack<Integer>();
		while (!stack.isEmpty()) {
			int cur = stack.pop();
			while (!help.isEmpty() && cur > help.peek()) {
				stack.push(help.pop());
			}
			help.push(cur);
		}
		while (!help.isEmpty()) {
			stack.push(help.pop());
		}
		return stack;
	}
	
}
