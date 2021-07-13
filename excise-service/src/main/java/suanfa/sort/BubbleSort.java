package suanfa.sort;

import java.util.Arrays;

/**
 * @author maguoqiang
 * @date 2021-07-12 12:41
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] a = {4, 5, 6, 3, 2, 1};
        int n = a.length;
        //循环次数，时间复杂度：O(n^2),空间复杂度：O(n)
        for (int i = 0; i < n - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (a[j] < a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
