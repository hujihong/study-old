package study;
import java.util.BitSet;  
  
public class mianTestSix {  
  
    /**  
     * @param args  
     */  
    public static void main(String[] args) {  
        BitSet bs=new BitSet(100);  
        initBitSet(bs);  
        findSushuBitSet(bs);  
        printSushuBitSet(bs);  
    }  
      
    //第0,1位置成false，其余全部是true.  
    public static void initBitSet(BitSet bs){  
        for(int i=0;i<bs.size();i++){  
            if(i==0||i==1){  
                bs.set(i, false);  
            }else{  
                bs.set(i, true);  
            }  
        }  
    }  
    //处理数据，找到素数  
    public static void findSushuBitSet(BitSet bs){  
        for(int i=0;i<bs.size();i++){  
            if(bs.get(i)){  
                //内循环遍历  
                for(int j=2*i;j<bs.size();j+=i){  
                    bs.set(j, false);  
                }  
            }  
              
        }  
    }  
    //位是1的是素数，打印  
    public static void printSushuBitSet(BitSet bs){  
        StringBuffer buf=new StringBuffer();  
        int num=0;  
        for(int i=0;i<100;i++){  
            if(bs.get(i)){  
                buf.append(i+",");  
                num++;  
            }  
              
            if((num+1)%20==0&&num!=0){  
                buf.append("\n");  
            }  
        }  
        System.out.println(buf.toString());  
    }  
      
  
}  