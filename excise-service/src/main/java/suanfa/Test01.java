package suanfa;

import org.junit.Test;

/**
 * @author maguoqiang
 * @date 2021-06-30 12:39
 */
public class Test01 {
    //判断一个数是否是2的N次方。
    //1.递归计算
    @Test
    public void test1() {

        int i = 128;
        if (is2N(i)){
            System.out.println(i+"是2的n次方");
        }else {
            System.out.println(i+"不是2的n次方");
        }
    }
    private boolean is2N(int n){
        while (n>1){
            if(n%2==0){
                n=n/2;
            }else {
                return false;
            }

        }
        return true;
    }
    //2.按位与
    @Test
    public void test2() {
        int i = 128;
        if ((i & (i - 1)) == 0) {
            System.out.println(i+"是2的n次方");
        }else {
            System.out.println(i+"不是2的n次方");
        }
    }
}
