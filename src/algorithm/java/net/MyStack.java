package net;
import java.util.Stack;

/**
 * 要求：
1. 定义栈的数据结构，要求添加一个 min函数，能够得到栈的最小元素。
2. 要求函数 min、push 以及 pop 的时间复杂度都是 O(1)。

这是考验“栈”数据结构设计。众所周知，栈是一种“后进先出”的线性数据结构，其push和pop的操作都是在栈顶实现的，时间复杂度为O(1)，不难设计。关键是min函数，要求时间复杂度为O(1)，略有难度。

难点在于，我们一方面入栈的时候，要比较得到当前栈最小值，另一方面出栈的时候，要考虑出栈元素是否为当前栈最小值。这样，就不能简单的使用一个存储空间存放当前的最小值了。

一开始我的想法是在栈内创建两个数组，一个是当做栈操作的存储数组，一个是放从大到小排序的值数组。这个“从大到小排序的值数组”不仅消耗空间，而且由于要排序，导致push操作的时间复杂度变成O(n)。

后来，偷偷搜了下，发现July同学的解法很好：自定义栈内，另建一个栈，用于存储最小值的数组下标。即，入栈时，通过最小值索引栈，得到当前最小值下标，取数组对应值，如果比入栈的当前值大，则将当前值入栈下标，压入到索引栈中；出栈时，若当前出栈值的索引为索引栈的栈顶元素，则索引栈也进行栈顶元素出栈操作。

其它push、pop操作都比较传统，也很简单，不赘述了。

java实现，自然而然想到泛型的使用了。这里的泛型也是有要求的：必须实现Compareable接口

 */
/**
 * 自定义栈
 * @author oh_Maxy
 */
public class MyStack<E>
{

    private int size = 16;//默认规模

    private int nextSize = 16;//超过范围，扩展操作

    private Object[] valArr;//存储数据结构

    //private Object[] minArr;//存储顺序排序//笨办法

    private int index = -1;//栈顶下标

    private Stack<Integer> minStack;//存储最小值下标的栈//参考July的方法

    /**
     * 构造器
     */
    public MyStack()
    {
        valArr = new Object[size];
        //minArr = new Object[size];//笨办法
        minStack = new Stack<Integer>();
    }

    /**
     * 入栈
     * @param value
     */
    public void push(E value)
    {
        //不能为空
        if (null == value)
        {
            throw new NullPointerException("Pushed value can not be null!");
        }

        //入参必须为可比较的
        if (!(value instanceof Comparable))
        {
            throw new UnsupportedOperationException("The value must be Comparable!");
        }

        //栈满了，重构栈
        if (index >= size)
        {
            resetStack();
        }

        //入栈
        valArr[++index] = value;

        //最小值插入排序
        //putMinObj(value);//笨办法
        //最小值的索引入栈
        checkMinObj(value);
    }

    /**
     * 笨办法
     * 将push的值插入minArr中
     * @param value
     */
    /*private void putMinObj(E value)
    {
        Comparable valueComp = (Comparable) value;
        int i = index;
        for (; i > 0; i--)
        {
            if (valueComp.compareTo(minArr[i - 1]) < 0)
            {
                minArr[i] = valueComp;
                break;
            }
            minArr[i] = minArr[i - 1];
        }

        //push的值为最大值，放在首位
        if (i == 0)
        {
            minArr[0] = valueComp;
        }
    }*/

    /**
     * 参考July的最小索引入栈思想
     * @param value
     */
    @SuppressWarnings("unchecked")
    private void checkMinObj(E value)
    {
        //第一个参数，直接为最小参数
        if (index == 0)
        {
            minStack.push(index);
            return;
        }

        //入参的值
        Comparable valueComp = (Comparable) value;

        //当前最小值
        Comparable valueMinComp = (Comparable) valArr[minStack.peek()];

        //新入栈的值小于当前最小值，则当前索引入栈
        if (valueComp.compareTo(valueMinComp) < 0)
        {
            minStack.push(index);
        }
    }

    /**
     * 出栈
     */
    @SuppressWarnings("unchecked")
    public E pop()
    {
        //栈为空，报错
        if (index < 0)
        {
            throw new NullPointerException("The statck is empty!");
        }

        //若是最小值出栈(比较下标判断)，需要将其索引出栈
        if (index == minStack.peek())
        {
            minStack.pop();
        }

        //出栈
        return (E) valArr[index--];

    }

    /**
     * 最小值
     */
    @SuppressWarnings("unchecked")
    public E getMin()
    {
        //return (E) minArr[index];//笨办法
        //若栈空，则报错空指针
        if (index < 0)
        {
            throw new NullPointerException("The statck is empty,no minValue returned!");
        }

        return (E) valArr[minStack.peek()];
    }

    /**
     * 重构栈
     */
    private void resetStack()
    {
        size += nextSize;
        Object[] newArr = new Object[size];

        System.arraycopy(valArr, 0, newArr, 0, size - nextSize);

        valArr = newArr;
    }

    //测试桩
    public static void main(String[] args)
    {
        //栈初始化//也可以直接使用Integer等实现了Comparable接口的封装类
        MyStack<MyValue> stack = new MyStack<MyValue>();

        //初始化几个值
        MyValue v1 = new MyValue(1);
        MyValue v2 = new MyValue(2);
        MyValue v3 = new MyValue(3);
        MyValue v4 = new MyValue(4);
        MyValue v5 = new MyValue(5);

        //入栈
        stack.push(v5);
        stack.push(v4);
        stack.push(v3);
        stack.push(v2);
        stack.push(v1);
        stack.push(v4);
        stack.push(v1);

        //最小值
        System.out.println("min: " + stack.getMin());
        //出栈
        System.out.println("pop: " + stack.pop());
        System.out.println("min: " + stack.getMin());
        System.out.println("pop: " + stack.pop());
        System.out.println("min: " + stack.getMin());
        System.out.println("pop: " + stack.pop());
        System.out.println("min: " + stack.getMin());
        System.out.println("pop: " + stack.pop());
        System.out.println("min: " + stack.getMin());
        System.out.println("pop: " + stack.pop());
        //最小值
        System.out.println("min: " + stack.getMin());
        System.out.println("min: " + stack.getMin());
        System.out.println("pop: " + stack.pop());
        System.out.println("min: " + stack.getMin());
        System.out.println("pop: " + stack.pop());
    }
}


/**
 * 自定义数值类
 */
class MyValue
    implements Comparable<MyValue>
{

    private int value;

    public MyValue(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    public int compareTo(MyValue obj)
    {
        //不能与null比较
        if (null == obj)
        {
            throw new NullPointerException("Can not compare to null!");
        }

        return this.value - obj.getValue();
    }

    @Override
    public String toString()
    {
        return "" + value;
    }
}