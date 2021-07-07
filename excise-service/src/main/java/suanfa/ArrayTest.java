package suanfa;

/**
 * @author maguoqiang
 * @date 2021-07-02 12:41
 */
public class ArrayTest {

    /**
     * 数组大小
     */
    private int size;
    /**
     * 数据
     */
    private int[] data;
    /**
     * 当前已经存的数量大小
     */
    private int index;

    public ArrayTest(int size) {
        this.size = size;
        data = new int[size];
        index = 0;
    }

    public void print() {
        System.out.println("index:" + index);
        for (int i = 0; i < index; i++) {
            System.out.println(data[i] + " ");
            System.out.println();
        }
    }

    /**
     * 插入数据
     *
     * @param loc 位置
     * @param n   数据。
     *            <p>
     *            时间复杂度：O(n)
     */
    public void insert(int loc, int n) {
        if (index++ < size) {
            for (int i = size - 1; i > loc; i--) {
                //把数据往后面移动一位
                data[i] = data[i] - 1;
            }
            data[loc] = n;
        } else {
            //扩容
            int oldSize = size;
            size = size * 2;
            int[] temp = new int[size];
            //迁移数据。
            if (oldSize >= 0) {
                System.arraycopy(data, 0, temp, 0, oldSize);
            }
            data = temp;
        }
    }

    /**
     * O(n)
     * @param loc
     */
    public void delete(int loc) {
        for (int i = loc; i < size; i++) {
            if (i != size - 1) {
                data[i] = data[i + 1];
            } else {
                data[i] = -1;
            }
        }
        index--;
    }

    /**
     * O(1)
     * @param loc
     * @param n
     */
    public void update(int loc, int n) {
        data[loc] = n;
    }
    /**
     * O(1)
     */
    public int get(int loc) {
        return data[loc];
    }


    public static void main(String[] args) {

    }
}
