package s1;

/* 先定义一个Node类用来存储节点的值域和指针域
 * 即当前节点中的值和后面节点的方法
 * 在C中就是相当与定义一个结构体类型一个数据域和指针域的方法
 */
class LNode {// 这个写法已经非常固定了 设置两个属性分别用set函数和get函数来得到这两个属性
	private int data;
	private LNode next;// 这个和String应该比较相似的用法，类名用来表示数据类型，表示next的数据类型也是节点

	public void setData(int data) {
		this.data = data;
	}

	public int getData() {
		return this.data;
	}

	public void setNext(LNode next) {
		this.next = next;
	}

	public LNode getNext() {
		return this.next;
	}

	@Override
	public String toString() {
		return "LNode [data=" + data + ", next=" + next + "]";
	}

}

/*
 * 定义一个链表主类，并且定义各种对链表操作的方法
 */
public class Linklist {

	public LNode head;// 定义一个头结点

	/*
	 * 定义一个创建链表的方法 该方法称之为 ：尾插法：新产生的节点从尾部插入链表
	 */
	public void createlink(int[] a) {
		LNode pnew = null;// 定义pnew表示新产生的结点
		LNode ptail = new LNode();// 为尾节点分配堆内存
		head = ptail;// 初始时是头结点与尾节点相等
		for (int i = 0; i < a.length; i++) {
			pnew = new LNode();// 为新产生的节点分配堆内存
			pnew.setData(a[i]);// 传递data值
			if(i == 0){
				head = pnew;
			}else{
				ptail.setNext(pnew);// 把新产生的节点设置为ptail的后继节点
			}
			pnew.setNext(null);// 把新产生的节点的后继节点设为空
			ptail = pnew;// 移动 ptail节点的位置使之一直指向尾部
		}
		System.out.println("");
	}

	/*
	 * 定义判断链表中元素是否存在的方法
	 */
	public void seachlink(int value) {
		LNode ptr;
		// ptr = head.getNext();
		ptr = head;
		while (ptr != null) {// 在节点非空的情况下寻找匹配的的值
			if (value == ptr.getData()) {// 匹配成功是
				System.out.println("找到数据:" + ptr.getData());
				break;// 退出循环
			} else {// 当当前值不是要查找的值时，查找下一个
				ptr = ptr.getNext();
			}
		}
		if (ptr == null) // 链表遍历完毕，没有找到时
			System.out.println("链表中没有要查找数据");
	}

	/*
	 * 定义一个删除节点的方法
	 */
	public void deletelink(int value) {
		LNode ptr;
		LNode p;
		p = head;
		// ptr = head.getNext();
		ptr = head;
		while (ptr != null) {
			if (value == ptr.getData()) {// 判断链表中的当前值是否是要删除的节点
				p.setNext(ptr.getNext());// 把ptr的后继节点设置为p的后继节点，即在形式上在链表中删除了ptr节点
				// System.gc();
				System.out.println("删除数据" + value + "成功！");
				break;
			} else {
				p = ptr;// p指向ptr位置
				ptr = ptr.getNext();// ptr指向其直接后继位置
			}
		}
		if (ptr == null)
			System.out.println("链表中没有要删除的数据！");
	}

	/*
	 * 定义插入节点的方法
	 */
	public void insertlink(int pos, int value) {// 两个参数，一个表示插入的位置，另一个表示插入的值
		LNode ptr;
		LNode pnew;// 实例化新节点
		// ptr = head.getNext();
		ptr = head;
		while (ptr != null) {
			if (pos == ptr.getData()) {
				pnew = new LNode();
				pnew.setData(value);
				pnew.setNext(ptr.getNext());
				ptr.setNext(pnew);//
				System.out.println("插入数据" + value + "成功！");
				break;
			} else {
				ptr = ptr.getNext();
			}
		}
		if (ptr == null)
			System.out.println("插入数据失败！");
	}

	public void insertlinkPost(int pos, int value) {// 两个参数，一个表示插入的位置，另一个表示插入的值
		// 查找位置
		int len = 1;
		LNode ptr;
		LNode pnew;// 实例化新节点
		// ptr = head.getNext();
		ptr = head;
		while (ptr != null) {
			if (len == pos) {
				pnew = new LNode();
				pnew.setData(value);
				LNode tmp = ptr.getNext();
				ptr.setNext(pnew);
				pnew.setNext(tmp);
				System.out.println("插入成功");
				break;
			} else {
				len++;
				ptr = ptr.getNext();
			}
		}
		if (ptr == null)
			System.out.println("插入数据失败！");
	}

	public int getLength() {
		int len = 0;
		LNode ptr;
		// ptr = head.getNext();
		ptr = head;
		while (ptr != null) {
			len++;
			ptr = ptr.getNext();
		}
		return len;
	}

	/*
	 * 定义一个输出链表内容方法
	 */
	public void printlink() {
		LNode ptr;// 实例化一个节点
		// ptr = head.getNext();// 该节点取得头结点的后继节点
		ptr = head;
		while (ptr != null) {
			System.out.print(ptr.getData() + "->");
			ptr = ptr.getNext();
		}
		System.out.println(" NULL");
	}

	// 将单链表反转,循环
	public void reverse2() {
		if (null == head) {
			return;
		}
		LNode pre = head;
		// LNode cur = head.getNext();
		LNode cur = head;
		LNode next;
		int i = 0;
		while (null != cur && cur.getData() != 0) {
			// System.out.println(++i);
			next = cur.getNext();
			cur.setNext(pre);
			pre = cur;
			cur = next;
		}
		// 将原链表的头节点的下一个节点置为null，再将反转后的头节点赋给head
		head.setNext(null);
		head = pre;
		// head = new LNode();
		// head.setNext(pre);
	}

	/**
	 * 因为在对链表进行反转的时候，需要更新每一个node的“next”值，但是，在更新 next 的值前，我们需要保存 next 的值，否则我们无法继续。
	 * 所以，我们需要两个指针分别指向前一个节点和后一个节点，每次做完当前节点“next”值更新后，把两个节点往下移，直到到达最后节点。
	 * 
	 * public Node reverse(Node current) {
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
	 * 
	 */
	public void reverse() {
		LNode end = null, p, q; // p代表当前节点，end代表翻转后p后面的，q代表翻转前p后面的
		p = head; 
		while (p != null) { // 如果当前节点不为空
			q = p.getNext(); // q赋值为p后面的节点
			p.setNext(end); // p指向p后面那个
			end = p; // end后移一位
			p = q; // p后移一位
		}
		head = end;
	}

	// 递
	public void reverse1() {
		LNode end = null, currentNode, bnextNode; // p代表当前节点，end代表翻转后p后面的，q代表翻转前p后面的
		currentNode = head; 
		while (currentNode != null) { // 如果当前节点不为空
			bnextNode = currentNode.getNext(); // q赋值为p后面的节点
			currentNode.setNext(end); // p指向p后面那个
			end = currentNode; // end后移一位
			currentNode = bnextNode; // p后移一位
		}
		head = end;
	}
	
	
	/*
	 * 下面给出一个测试用例，用数组创建一个整型的链表，并且把它输出
	 */
	public static void main(String args[]) {
		int a[] = new int[10];
		for (int i = 0; i < a.length; i++) {
			a[i] = 100 + i;
		}
		Linklist list = new Linklist();
		list.createlink(a);
		list.printlink();
		// list.reverse2();
		// list.reverse();
		// list.printlink();

		// System.out.println(" 链表输出如下：");
		// list.printlink();
		// System.out.println(list.getLength());
		// list.seachlink(102);
		// list.deletelink(102);
		// list.insertlink(2, 20);
		// list.insertlinkPost(2, 10);
		// System.out.println(" 操作元素后链表的输出如下：");
		list.printlink();
		// System.out.println(list.getLength());

	}
}
