package singalton;

public class LazySingletonTest {
    public static void main(String[] args) {
        /*LazySingleton instance = LazySingleton.getInstance();
        System.out.println(instance);*/
        //多线程状态下，不安全
        /*new Thread(() -> {
            LazySingleton instance1 = LazySingleton.getInstance();
            System.out.println(instance1);
        }).start();
        new Thread(() -> {
            LazySingleton instance1 = LazySingleton.getInstance();
            System.out.println(instance1);
        }).start();*/

        //===================
        new Thread(() -> {
            LazySingleton instance1 = LazySingleton.getInstanceBySync();
            System.out.println(instance1);
        }).start();
        new Thread(() -> {
            LazySingleton instance1 = LazySingleton.getInstanceBySync();
            System.out.println(instance1);
        }).start();

    }

    static class LazySingleton {
        private static LazySingleton instance;

        private LazySingleton() {

        }

        public static LazySingleton getInstance() {
            if (null == instance) {
                try {
                    //模拟多线程状态下，不安全
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                instance = new LazySingleton();
            }
            return instance;
        }
        //性能损耗比较大
        public synchronized static LazySingleton getInstanceBySync() {
            if (null == instance) {
                try {
                    //模拟多线程状态下，不安全
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                instance = new LazySingleton();
            }
            return instance;
        }

        public static LazySingleton getInstanceBySync2() {
            if (null == instance) {
                try {
                    //模拟多线程状态下，不安全
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //粒度更小，但是还会有问题。
                synchronized (LazySingleton.class){
                    instance = new LazySingleton();
                }
            }
            return instance;
        }
    }
}
