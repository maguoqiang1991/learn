package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author maguoqiang
 * @date 2021-06-01 19:04
 */
public class ReentrantLockTest {

    private final ReentrantLock lock = new ReentrantLock();

    // ...
    public void m() throws InterruptedException {
        lock.lock(); // block until condition holds
        try {
            // ... method body
            System.out.println("阻塞了");
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        new ReentrantLockTest().m();
    }
}
