package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockInterruptedTest {

    public static void main(String[] args) throws InterruptedException {
        new ReentrantLockInterruptedTest().testInterrupt();
    }
    private final ReentrantLock lock = new ReentrantLock(true);
    int o=0;
    private void testInterrupt() throws InterruptedException {

        List<Thread> threads = new ArrayList<>();


        for (int i = 0; i < 200; i++) {
            int finalI = i;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    if (Thread.interrupted()){
                        System.out.println("中断了");
                    }
                    System.out.println("这是第" + finalI + "个");
                    o++;
                    lock.unlock();
                }
            }, i + "线程");
            threads.add(t);
            t.start();

        }
        //Thread.sleep(2000);
        threads.get(5).interrupt();
        threads.forEach(Thread::start);
        System.out.println(o);

    }
}
