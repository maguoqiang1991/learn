package suanfa;

/**
 * 斐波那契数列
 * 使用递归
 */
public class Fibonacci {


    public static int fab(int n) {
        if (2 >= n) {
            return 1;
        }
        return fab(n - 1) + fab(n - 2);
    }

    /**
     * 尾递归
     * ：只递归到最后一次进行计算即可，所以就需要把前面每次计算的结果以参数的形式传递下去。
     */
    public static int tailFab(int n, int preResult, int result) {
        if (2 >= n) {
            return result;
        }
        return tailFab(n - 1, result, preResult + result);
    }

    public static void main(String[] args) {
        long start =System.currentTimeMillis();
        for (int i = 1; i < 11; i++) {
            int fab = fab(i);
            System.out.println(i+":"+fab+",耗时："+(System.currentTimeMillis() - start));
        }
    }
}
