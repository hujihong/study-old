package net;

/**
 * 用递归颠倒一个栈
 */
import java.util.Stack;

public class MyStack04 {
	
	public static void reverseStack(Stack<Object> stack) {
		if (!stack.empty()) {
			Object t = stack.pop();
			reverseStack(stack);
			PushStack(stack, t);
		}
	}

	// 这个递归的作用就是上面提到的把元素进行颠倒
	public static void PushStack(Stack<Object> stack, Object t) {
		if (stack.empty()) {
			stack.push(t);
		} else {
			Object top = stack.pop();
			PushStack(stack, t);
			stack.push(top);
		}
	}

	public static void main(String[] args) {

		Stack<Object> stack = new Stack<Object>();

		for (int i = 0; i < 5; i++) {
			stack.push(i + 1);
		}

		reverseStack(stack);

		for (int i = 0; i < 5; i++) {
			System.out.println(stack.pop());
		}
	}
}