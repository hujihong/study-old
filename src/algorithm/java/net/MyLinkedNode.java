package net;


/**
 * 
 * 把二元查找树转变成排序的双向链表（树）（来自July大神的《算法面试100题》，果断抱大腿呀）
 题目：
输入一棵二元查找树，将该二元查找树转换成一个排序的双向链表。
要求不能创建任何新的结点，只调整指针的指向。
       10
      /    |
   6     14
 /   |     /  |
4  8 12 16
 转换成双向链表
4=6=8=10=12=14=16。

二叉查找树的特点：任意节点的左子树都要小于当前节点；右子树都要大于当前节点。
特点：查询某个值，需要的时间复杂度为O(lgN)。

现在要求将其由树状结构改造成线性结构的双向链表，感觉重点在于，获得当前节点左子树范围内最右节点（也是左子树最大值节点），以及右子树范围内最左节点（也是右子树最小值节点），然后，调整这两个节点与当前节点左右顺序。以本题为例，就是要调整8、10之间的关系，和12、10之间的关系（8的右节点为10,10的左节点为8；12的左节点为10,10的右节点为12）。
当然，这种三层关系的树看起来很简单，如果层次多了，就需要分析，如何将其中的共性抽象出来。

也是最近两天才看到July大神的这个帖子，目前还没看到答案那块。不过他的提示，结构体，猜测他用的是C++实现的。C++自己也忘的差不多了，这边就用Java实现下。

数据结构，依然沿用July的value,left,right方式。

算法思路：
1. 树根节点，分左右子树。先将“当前节点左子树范围内最右节点”leftR找出来，再将“右子树范围内最左节点”rightL找出来。（这两步放在一开始，是因为此时左右子树内的关系还没改变，先取出来，时间消耗O(lgN)。只是查找到节点，空间上只用到一个索引，不会产生新的内存分配）
2. 若左子树为叶子节点，则直接设置其右向索引指向其父节点，左向递归结束；否则，将此节点作为根节点，递归调用第1步。
3. 若右子树为叶子节点，则直接设置其左向索引指向其父节点，右向递归结束；否则，将此节点作为根节点，递归调用第1步。
4. 设置根节点的左向节点为leftR，leftR的右向节点为根节点（其左向节点，在2、3两步的递归过程中，已经赋值）；设置根节点的右向节点为rightL，rightL的左向节点为根节点（其右向节点，在2、3两步的递归过程中，已经赋值）。

算法思路看起来还是比较简单的（时间复杂度O(lgN)），这里要说明的是，与简单递归思路，“只递归调用本方法”不同，采用“主方法的两个字方法递归调用主方法”实现的。这样看起来，模块清晰，易于设计
 * 
 * 
 */
/**
 * 自定义双向链表
 * 二元查找树转变成排序的双向链表
 * @author Maxy
 */
public class MyLinkedNode
{

    private int value;//节点值

    private MyLinkedNode left;//左值节点

    private MyLinkedNode right;//右值节点

    /**
     * 节点值只能初始化时写入
     * @param value
     */
    public MyLinkedNode(int value)
    {
        this.value = value;
    }

    public MyLinkedNode getLeft()
    {
        return left;
    }

    public void setLeft(MyLinkedNode left)
    {
        this.left = left;
    }

    public MyLinkedNode getRight()
    {
        return right;
    }

    public void setRight(MyLinkedNode right)
    {
        this.right = right;
    }

    /**
     * 只提供查询节点值
     * @return
     */
    public int getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return "" + value;
    }

    /**
     * 右向打印
     */
    public void printToRight()
    {
        System.out.print(value + " ");
        if (null != right)
        {
            right.printToRight();
        }
    }

    /**
     * 左向打印
     */
    public void printToLeft()
    {
        System.out.print(value + " ");
        if (null != left)
        {
            left.printToLeft();
        }
    }

    //测试桩
    public static void main(String[] args)
    {
        //初始化2叉查找树：左子树节点值>本节点值>右子树节点值
        MyLinkedNode v10 = new MyLinkedNode(10);
        MyLinkedNode v6 = new MyLinkedNode(6);
        MyLinkedNode v14 = new MyLinkedNode(14);
        MyLinkedNode v4 = new MyLinkedNode(4);
        MyLinkedNode v8 = new MyLinkedNode(8);
        MyLinkedNode v12 = new MyLinkedNode(12);
        MyLinkedNode v16 = new MyLinkedNode(16);

        //原题基础上扩展一层叶子节点
        MyLinkedNode v3 = new MyLinkedNode(3);
        MyLinkedNode v5 = new MyLinkedNode(5);
        MyLinkedNode v7 = new MyLinkedNode(7);
        MyLinkedNode v9 = new MyLinkedNode(9);
        MyLinkedNode v11 = new MyLinkedNode(11);
        MyLinkedNode v13 = new MyLinkedNode(13);
        MyLinkedNode v15 = new MyLinkedNode(15);
        MyLinkedNode v17 = new MyLinkedNode(17);

        //构建二叉树关系
        v10.setLeft(v6);
        v10.setRight(v14);

        v6.setLeft(v4);
        v6.setRight(v8);

        v14.setLeft(v12);
        v14.setRight(v16);

        //扩展层
        v4.setLeft(v3);
        v4.setRight(v5);
        v8.setLeft(v7);
        v8.setRight(v9);
        v12.setLeft(v11);
        v12.setRight(v13);
        v16.setLeft(v15);
        v16.setRight(v17);

        //达到题目要求，因为已经有序，只需要对叶子节点以及其直接父节点的调整
        processTreeToLinked(v10);

        //最后，如果处理成功，则右向打印v3时，会升序打印出来
        System.out.print("升序打印：");
        v3.printToRight();

        System.out.println();

        //左向打印v17，会降序打印
        System.out.print("降序打印：");
        v17.printToLeft();

    }

    /**
     * 递归主方法
     * 不借助新的节点，对叶子节点以及其直接父节点的调整
     * @param node
     */
    private static void processTreeToLinked(MyLinkedNode node)
    {
        //为空不操作
        if (null == node)
        {
            return;
        }

        //获得左子树的最右节点（最大值）
        MyLinkedNode leftR = getMostRightNode(node.getLeft());
        //获得右子树的最左节点（最小值）
        MyLinkedNode rightL = getMostLeftNode(node.getRight());

        //左子树非空，递归处理左子树
        if (null != node.getLeft())
        {
            processLeftNode(node.getLeft(), node);
        }

        //右子树非空，递归处理右子树
        if (null != node.getRight())
        {
            processRightNode(node, node.getRight());
        }

        //若左子树最右节点非空，调整与根节点相邻
        if (null != leftR)
        {
            leftR.setRight(node);
            node.setLeft(leftR);
        }

        //若右子树的最左节点非空，调整与根节点相邻
        if (null != rightL)
        {
            rightL.setLeft(node);
            node.setRight(rightL);
        }
    }

    /**
     * 递归左子方法
     * 处理左子树
     * @param left
     * @param node
     */
    private static void processLeftNode(MyLinkedNode left, MyLinkedNode node)
    {
        //若左子树为叶子节点，直接将其右向索引指向父节点，并返回
        if (isLeafNode(left))
        {
            left.setRight(node);
            return;
        }

        //本节点当做根节点，递归调用
        processTreeToLinked(left);
    }

    /**
     * 递归右子方法</span>
     * 处理右子树
     * @param node
     * @param right
     */
    private static void processRightNode(MyLinkedNode node, MyLinkedNode right)
    {
        //若右子树为叶子节点，直接将其左向索引指向父节点，并返回
        if (isLeafNode(right))
        {
            right.setLeft(node);
            return;
        }

        //本节点当做根节点，递归调用
        processTreeToLinked(right);
    }

    /**
     * 判断节点是否为叶子节点
     * @param node
     * @return
     */
    private static boolean isLeafNode(MyLinkedNode node)
    {
        return (null == node.getLeft()) && (null == node.getRight());
    }

    /**
     * 获取最右节点
     * @param left
     * @return
     */
    private static MyLinkedNode getMostRightNode(MyLinkedNode left)
    {
        //若节点为空，直接返回空
        if (null == left)
        {
            return null;
        }

        //若此节点右节点为空，直接返回此节点
        if (null == left.getRight())
        {
            return left;
        }

        return getMostRightNode(left.getRight());
    }

    /**
     * 获取最左节点
     * @param left
     * @return
     */
    private static MyLinkedNode getMostLeftNode(MyLinkedNode right)
    {
        //若节点为空，直接返回空
        if (null == right)
        {
            return null;
        }

        //若此节点的左节点为空，直接返回此节点
        if (null == right.getLeft())
        {
            return right;
        }

        return getMostLeftNode(right.getLeft());
    }
}