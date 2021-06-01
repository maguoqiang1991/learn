package thread;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();




        lock.unlock();
        ReentrantLockTest test = new ReentrantLockTest();
        test.test();
    }


    private void test() {
        Thread t1 = new Thread(() -> {
            System.out.println("start...");
            try {
                Thread.sleep(1000);//t1睡眠了一秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("park...");
            LockSupport.park();//t1线程一秒后暂停
            System.out.println("resume...");
        }, "t1");
        t1.start();

        try {
            Thread.sleep(2000);//主线程睡眠二秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("unpark...");
        LockSupport.unpark(t1);//二秒后由主线程恢复t1线程的运行
    }

}
