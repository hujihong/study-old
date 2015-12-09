package ch03;

import java.util.Stack;

public class MyStack04 {
	// 这个递归出要作用是把栈中的元素展开
	public static void reverseStack(Stack stack) {
		if (!stack.empty()) {
			Object t = stack.pop();
			reverseStack(stack);
			PushStack(stack, t);
		}
	}

	// 这个递归的作用就是上面提到的把元素进行颠倒
	public static void PushStack(Stack stack, Object t) {
		if (stack.empty()) {
			stack.push(t);
		} else {
			Object top = stack.pop();
			PushStack(stack, t);
			stack.push(top);
		}
	}

	public static void main(String[] args) {

		Stack stack = new Stack();

		for (int i = 0; i < 5; i++) {
			stack.push(i + 1);
		}
		printStack(stack);
		reverseStack(stack);
		printStack(stack);
		
	}
	
	public static void printStack(Stack<Integer> stack){  
        for(Integer x:stack){  
            System.out.print(x+",");  
        }  
        System.out.println();  
    }  
}