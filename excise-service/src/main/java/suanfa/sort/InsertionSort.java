package suanfa.sort;

import org.junit.Test;

/**
 * @author maguoqiang
 * @date 2021-07-08 13:27
 * 插入排序
 *
 *
 * 类似于打扑克。
 * 手里的牌都是排好序的，然后新拿的牌，按照顺序插入到手里原有的牌中。
 * 具体到代码：
 * {19,2,32,4,54,34}
 * 先假如手里牌的就只有19，然后依次往后拿数据和19比较，
 * 正序排：如果比19小，则放到19的位置，然后19往后移一位，如果比19大，则不变，进行下次拿值。
 * 倒序排：如果比19大，则放到19的位置，然后19往后移一位，如果比19小，则不变，进行下次拿值。
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
