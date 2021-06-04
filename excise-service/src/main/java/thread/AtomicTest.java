package thread;

import org.junit.Test;
import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author maguoqiang
 * @date 2021-06-03 9:34
 */
public class AtomicTest {

    @Test
    public void test01() throws InterruptedException {
        AtomicInteger integer = new AtomicInteger(0);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    integer.incrementAndGet();
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(integer.get());
    }


    @Test
    public void testUnsafe() throws NoSuchFieldException {
        /*Unsafe unsafe = Unsafe.getUnsafe();
        long ageOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("age"));*/
        UnsafeTest test = new UnsafeTest();
        System.out.println(test);
        //System.out.println("ageOffSet:========>" + ageOffset);
    }

    @Test
    public void testAtomicArray(){
        int[] value = new int[]{1,2};
        //不修改原值。
        AtomicIntegerArray aiArray = new AtomicIntegerArray(value);

        aiArray.getAndSet(0,2);
        System.out.println(aiArray.get(0));
        System.out.println(value[0]);
    }


    static class UnsafeTest {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
    //要用cas修改某个属性的值，首先要知道属性在对象在内存空间的哪个位置，必须知道属性的偏移量
    //偏移量：某个属性相对于其对象的内存地址的位置。例：UnSafeTest的地址是：thread.AtomicTest$UnsafeTest@1376c05c，age的偏移量是10。

    @Test
    public void testAba(){

    }
}
