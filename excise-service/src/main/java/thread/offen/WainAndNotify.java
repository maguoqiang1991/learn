package thread.offen;

/**
 * @author maguoqiang
 * @date 2021-08-05 9:34
 */
public class WainAndNotify {

    public static void main(String[] args) {
        Object lock = new Object();
        WaitThread t1 = new WaitThread(lock);
        t1.start();
        Object lock2 = new Object();
        NotifyThread t2 = new NotifyThread(lock2);
        t2.start();
    }

}

class NotifyThread extends Thread {
    private final Object lock;


    public NotifyThread(Object lock) {
        this.lock = lock;
    }


    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {
        synchronized (lock) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("开始 Notify time= " + System.currentTimeMillis());
            lock.notify();
            System.out.println("结束 Notify time= " + System.currentTimeMillis());
        }
    }
}

class WaitThread extends Thread {
    private final Object lock;

    public WaitThread(Object lock) {
        this.lock = lock;
    }

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     * @see #Thread(ThreadGroup, Runnable, String)
     */
    @Override
    public void run() {

        synchronized (lock) {
            long start = System.currentTimeMillis();
            System.out.println("开始 wait time = " + start);
            try {
                lock.wait(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            System.out.println("结束 wait time = " + end);
            System.out.println("wait time = " + (end - start));

        }
    }
}

