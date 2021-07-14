package suanfa.sort.exise;

import org.junit.Test;

/**
 * @author maguoqiang
 * @date 2021-07-13 13:23
 */
public class Fibonacci {


    private int fab(int n) {
        if (n <= 2) {
            return 1;
        }
        return fab(n - 2) + fab(n - 1);
    }

    @Test
    public void testFab() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(fab(i));
        }
    }

    private int tailFab(int n, int pre, int res) {

        if (n <= 2) {
            return res;
        }
        return tailFab(n - 1, res, res + pre);
    }

    @Test
    public void testTailFab() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(tailFab(i, 1, 1));
        }
    }
}
