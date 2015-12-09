package ch04;
/*
 * 链表，相当于火车
 */
public class LinkList {
	//头结点
	private Node first;
	
	public LinkList() {
		first = null;
	}
	
	/**
	 * 插入一个结点，在头结点后进行插入
	 */
	public void insertFirst(long value) {
		Node node = new Node(value);
		node.next = first;
		first = node;
	}
	
	/**
	 * 删除一个结点，在头结点后进行删除
	 */
	public Node deleteFirst() {
		Node tmp = first;
		first = tmp.next;
		return tmp;
	}
	
	/**
	 * 显示方法
	 */
	public void display() {
		Node current = first;
		while(current != null) {
			current.display();
			current = current.next;
		}
		System.out.println();
	}
	
	/**
	 * 查找方法
	 */
	public Node find(long value) {
		Node current = first;
		while(current.data != value) {
			if(current.next == null) {
				return null;
			}
			current = current.next;
		}
		return current;
	}
	
	/**
	 * 删除方法，根据数据域来进行删除
	 */
	public Node delete(long value) {
		Node current = first;
		Node previous = first;
		while(current.data != value) {
			if(current.next == null) {
				return null;
			}
			previous = current;
			current = current.next;
		}
		
		if(current == first) {
			first = first.next;
		} else {
			previous.next = current.next;
		}
		return current;
		
	}
	
	// 反转  递归
	public Node reverse(Node current)  {  
	     if (current == null || current.next == null) return current;  
	     Node nextNode = current.next;  
	     current.next = null;  
	     Node reverseRest = reverse(nextNode);  
	     nextNode.next = current;  
	     return reverseRest;  
	 }  
	
	// 反转  非递归
	public Node reverse1(Node current) {  
	    //initialization  
	    Node previousNode = null;  
	    Node nextNode = null;  
	      
	    while (current != null) {  
	        //save the next node  
	        nextNode = current.next;  
	        //update the value of "next"  
	        current.next = previousNode;  
	        //shift the pointers  
	        previousNode = current;  
	        current = nextNode;           
	    }  
	    return previousNode;  
	}  
	
}
