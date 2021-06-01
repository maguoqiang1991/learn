package thread;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @author maguoqiang
 * @date 2021-06-01 19:33
 * <p>
 * 使用LockSupport实现简单的独占锁。类似ReentrantLock
 * <p>
 * LockSupport类，是JUC包中的一个工具类，是用来创建锁和其他同步类的基本线程阻塞原语。
 * <p>
 * LockSupport类的核心方法其实就两个：park()和unpark()，其中park()方法用来阻塞当前调用线程，unpark()方法用于唤醒指定线程。
 * 这其实和Object类的wait()和signal()方法有些类似，但是LockSupport的这两种方法从语意上讲比Object类的方法更清晰，而且可以针对指定线程进行阻塞和唤醒。
 * <p>
 * LockSupport类使用了一种名为Permit（许可）的概念来做到阻塞和唤醒线程的功能，可以把许可看成是一种(0,1)信号量（Semaphore），
 * 但与 Semaphore 不同的是，许可的累加上限是1。
 * 初始时，permit为0，当调用unpark()方法时，线程的permit加1，当调用park()方法时，如果permit为0，则调用线程进入阻塞状态。
 */
public class FIFOMutex {
    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<Thread>();

    public void lock() {
        Thread current = Thread.currentThread();
        waiters.add(current);
        // 如果当前线程不在队首，或锁已被占用，则当前线程阻塞
        // NOTE：这个判断的意图其实就是：锁必须由队首元素拿到
        while (waiters.peek() != current || !locked.compareAndSet(false, true)) {
            LockSupport.park(this);
        }
        waiters.remove(); // 删除队首元素
    }

    public void unlock() {
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }

    /**
     * 测试类
     */
    static class Main {
        public static void main(String[] args) throws InterruptedException {
            FIFOMutex mutex = new FIFOMutex();
            MyThread a1 = new MyThread("a1", mutex);
            MyThread a2 = new MyThread("a2", mutex);
            MyThread a3 = new MyThread("a3", mutex);
            a1.start();
            a2.start();
            a3.start();
            a1.join();
            a2.join();
            a3.join();
            assert MyThread.count == 300;
            System.out.print("Finished");
        }
    }

    static class MyThread extends Thread {
        private String name;
        private FIFOMutex mutex;
        public static int count;

        public MyThread(String name, FIFOMutex mutex) {
            this.name = name;
            this.mutex = mutex;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                mutex.lock();
                count++;
                System.out.println("name:" + name + "  count:" + count);
                mutex.unlock();
            }
        }
    }

}
