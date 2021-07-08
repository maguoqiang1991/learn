package suanfa.sort;

import org.junit.Test;

/**
 * @author maguoqiang
 * @date 2021-07-08 13:27
 * 插入排序
 */
public class InsertionSort {


    public static void main(String[] args) {

    }

    /**
     * 升序
     */
    @Test
    public void testAsc() {
        int[] a = {9, 8, 7, 0, 1, 3, 2};
        int n = a.length;
        //为什么要从1开始？第一个不用排序
        for (int i = 1; i < n; i++) {
            int data = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                //ATTENTION
                if (a[j] > data) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }

            }
            a[j + 1] = data;
            System.out.print("第" + i + "次排序的结果为:");
            for (j = 0; j < n; j++) {
                System.out.print(a[j] + " ");
            }
            System.out.println();

        }
    }

    /**
     * 降序
     */
    @Test
    public void testDesc() {
        int[] a = {9, 8, 7, 0, 1, 3, 2};
        int n = a.length;
        //为什么要从1开始？第一个不用排序
        for (int i = 1; i < n; i++) {
            int data = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                //ATTENTION
                if (a[j] < data) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }

            }
            a[j + 1] = data;
            System.out.print("第" + i + "次排序的结果为:");
            for (j = 0; j < n; j++) {
                System.out.print(a[j] + " ");
            }
            System.out.println();

        }
    }
}
