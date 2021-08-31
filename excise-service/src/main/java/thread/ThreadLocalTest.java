package thread;

/**
 * @author maguoqiang
 * @date 2021-08-25 12:28
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        ThreadLocal t = new ThreadLocal();
        t.set(1);
        t.remove();
    }
}
