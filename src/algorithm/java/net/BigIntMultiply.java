package net;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 大整数乘法，就是乘法的两个乘数比较大，最后结果超过了整型甚至长整型的最大范围，此时如果需要得到精确结果，就不能常规的使用乘号直接计算了。没错，就需要采用分治的思想，将乘数“分割”，将大整数计算转换为小整数计算。

在这之前，让我们回忆一下小学学习乘法的场景吧。个位数乘法，是背诵乘法口诀表度过的，不提也罢；两位数乘法是怎么做的呢？现在就来一起回忆下12*34吧：
   3  4  （是不是很多小朋友喜欢将大的整数作为被乘数的，呵呵）
 *1  2 
---------
   6  8
3 4
---------
4 0  8

接下来就是找规律了。其实大家多做几个两位数的乘法，都会发现这个规律：AB * CD = AC (AD+BC) BD 。没错，这里如果BD相乘超过一位数，需要进位（这里二四得八，没有进位）；(AD+BC+低位进位)如果超过一位数，需要进位（就像刚才的3*1，最后+1得4的操作）。

到这里可以看出，我们任意位数的整数相乘，最终都是可以转化为两位数相乘。当然，不同位的两位数乘的结果，最后应该如何拼接呢？这需要我们来找下更深层次的规律了。这下来个四位数的乘法，找找感觉：
             1  2  3  4
          *  5  6  7  8
------------------------
              9  8  7  2
          8  6  3  8
      7  4  0  4
 6  1  7  0
------------------------
 7  0  0  6  6  5  2

这个结果看起来也没什么特别的，如果按照我们分治的思想，转换为两位数相乘，之间能否有些关系呢？
1234分为 12（高位）和34（低位）；5678分为56（高位）和78（低位）
高位*高位结果：12*56=672
高位*低位+低位*高位：12*78+34*56=936+1094=2840
低位*低位结果：34*78=2652

这里要拼接了。需要说明的是，刚才我们提到两位数分解成一位数相乘的规则：超过一位数，需要进位。同理（这里就不证明了），两位数乘以两位数，结果超过两位数，也要进位。
从低位开始：低两位：2652，26进位，低位保留52；中间两位，2840+26（低位进位）=2866,28进位，中位保留66；高位，672+28（进位）=700,7进位，高位保留00。再往上就没有了，现在可以拼接起来：最高位进位7，高两位00，中位66，低位52，最后结果：7006652。

规律终于找到了！任意位数（例如N位整数相乘），都可以用这种思想实现：低位保留N位数字串，多余高位进位；高位要加上低位进位，如果超过N位，依然只保留N位，高位进位。（如果是M位整数乘以N位整数怎么办？高位补0，凑成一样位数的即可，不赘述。）

分治的规律找到了，接下来就是具体实现的思想了。
没啥新花样，依然是递归思想（这里为了简化，就不递归到两位数相乘了，4位数相乘，计算机还是能够得到精确值的）：
1. 如果两个整数M和N的长度都小于等于4位数，则直接返回M*N的结果的字符串形式。
2. 如果如果M、N长度不一致，补齐M高位0（不妨设N>M），使都为N位整数。
3. N/2取整，得到整数的分割位数。将M、N拆分成m1、m2，n1，n2。
4. 将m1、n1；m2、n1；m1、n2；m2、n2递归调用第1步，分别得到结果AC(高位)、BC(中位)、AD(中位)、BD(低位)。
5. 判断BD位是否有进位bd，并截取bd得到保留位BD'；判断BC+AD+bd是否有进位abcd，并截取进位得到保留位ABCD'；判断AC+abcd是否有进位ac，并截取进位得到保留位AC'。
6. 返回最终大整数相乘的结果：ac AC' ABCD' BD'。
 */

/**
 * 大整数乘法
 * @author Maxy
 *
 */
public class BigIntMultiply
{

    //规模只要在这个范围内就可以直接计算了
    private final static int SIZE = 4;

    // 此方法要保证入参len为X、Y的长度最大值
    private static String bigIntMultiply(String X, String Y, int len)
    {
        // 最终返回结果
        String str = "";
        // 补齐X、Y，使之长度相同
        X = formatNumber(X, len);
        Y = formatNumber(Y, len);

        // 少于4位数，可直接计算
        if (len <= SIZE)
        {
            return "" + (Integer.parseInt(X) * Integer.parseInt(Y));
        }

        // 将X、Y分别对半分成两部分
        int len1 = len / 2;
        int len2 = len - len1;
        String A = X.substring(0, len1);
        String B = X.substring(len1);
        String C = Y.substring(0, len1);
        String D = Y.substring(len1);

        // 乘法法则，分块处理
        int lenM = Math.max(len1, len2);
        String AC = bigIntMultiply(A, C, len1);
        String AD = bigIntMultiply(A, D, lenM);
        String BC = bigIntMultiply(B, C, lenM);
        String BD = bigIntMultiply(B, D, len2);

        // 处理BD，得到原位及进位
        String[] sBD = dealString(BD, len2);

        // 处理AD+BC的和
        String ADBC = addition(AD, BC);
        // 加上BD的进位
        if (!"0".equals(sBD[1]))
        {
            ADBC = addition(ADBC, sBD[1]);
        }

        // 得到ADBC的进位
        String[] sADBC = dealString(ADBC, lenM);

        // AC加上ADBC的进位
        AC = addition(AC, sADBC[1]);

        // 最终结果
        str = AC + sADBC[0] + sBD[0];

        return str;
    }

    // 两个数字串按位加
    private static String addition(String ad, String bc)
    {
        // 返回的结果
        String str = "";

        // 两字符串长度要相同
        int lenM = Math.max(ad.length(), bc.length());
        ad = formatNumber(ad, lenM);
        bc = formatNumber(bc, lenM);

        // 按位加，进位存储在temp中
        int flag = 0;

        // 从后往前按位求和
        for (int i = lenM - 1; i >= 0; i--)
        {
            int t =
                flag + Integer.parseInt(ad.substring(i, i + 1))
                    + Integer.parseInt(bc.substring(i, i + 1));

            // 如果结果超过9，则进位当前位只保留个位数
            if (t > 9)
            {
                flag = 1;
                t = t - 10;
            }
            else
            {
                flag = 0;
            }

            // 拼接结果字符串
            str = "" + t + str;
        }
        if (flag != 0)
        {
            str = "" + flag + str;
        }
        return str;
    }

    // 处理数字串，分离出进位；
    // String数组第一个为原位数字，第二个为进位
    private static String[] dealString(String ac, int len1)
    {
        String[] str = {ac, "0"};
        if (len1 < ac.length())
        {
            int t = ac.length() - len1;
            str[0] = ac.substring(t);
            str[1] = ac.substring(0, t);
        }
        else
        {
            // 要保证结果的length与入参的len一致，少于则高位补0
            String result = str[0];
            for (int i = result.length(); i < len1; i++)
            {
                result = "0" + result;
            }
            str[0] = result;
        }
        return str;
    }

    // 乘数、被乘数位数对齐
    private static String formatNumber(String x, int len)
    {
        while (len > x.length())
        {
            x = "0" + x;
        }
        return x;
    }

    //测试桩
    public static void main(String[] args)
    {
        // 正则表达式：不以0开头的数字串
        String pat = "^[1-9]\\d*$";
        Pattern p = Pattern.compile(pat);

        // 获得乘数A
        System.out.println("请输入乘数A（不以0开头的正整数）：");
        Scanner sc = new Scanner(System.in);
        String A = sc.nextLine();
        Matcher m = p.matcher(A);
        if (!m.matches())
        {
            System.out.println("数字不合法！");
            return;
        }

        // 获得乘数B
        System.out.println("请输入乘数B（不以0开头的正整数）：");
        sc = new Scanner(System.in);
        String B = sc.nextLine();
        m = p.matcher(B);
        if (!m.matches())
        {
            System.out.println("数字不合法！");
            return;
        }
        System.out.println(A + " * " + B + " = "
            + bigIntMultiply(A, B, Math.max(A.length(), B.length())));
    }
}