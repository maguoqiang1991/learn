package thread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author maguoqiang
 * @date 2021-06-02 12:21
 */
public class SemaphoreTest {




    public static void main(String[] args) {
        Semaphore s = new Semaphore(2);
        for (int i=0;i<10;i++){
            new Thread(new ThreadDeamo(s)).start();

        }


    }


    static class ThreadDeamo implements Runnable{
        Semaphore s ;



        ThreadDeamo(Semaphore s){
            this.s=s;
        }

        @Override
        public void run() {
            //可中断
            try {

                s.acquire();

                if (Thread.currentThread().isInterrupted()){
                    System.out.println("中断了。");
                }

                System.out.println(Thread.currentThread().getName()+"获得=========");
                Thread.sleep(1000);
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //降级--超时后，直接舍弃
            /*try {
                if (s.tryAcquire(500, TimeUnit.MILLISECONDS)){

                    System.out.println(Thread.currentThread().getName()+"获得=========");
                    Thread.sleep(1000);
                    s.release();
                }else {
                    fallback();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

        }

        private void fallback() {
            System.out.println(Thread.currentThread().getName()+"降级");
        }
    }
}
