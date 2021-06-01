package thread;

public class VolatileTest {

    private static boolean initFlag = false;

    private static int count=128;

    public static void main(String[] args) {

        Thread.currentThread().interrupt();
        Thread.currentThread().isInterrupted();
        Thread.interrupted();

        new Thread(() -> {

            while (!initFlag) {
                count++;
            }
            System.out.println("线程" + Thread.currentThread().getName() + "当前线程嗅探到initFlag值改变");

        }, "threadA").start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(() -> {
            refresh();
        }, "threadB").start();
    }

    private static void refresh() {
        System.out.println("refresh data.");
        initFlag = true;
        System.out.println("refresh data success.");
    }

}
