package thread;

import org.junit.Test;

import java.util.ServiceLoader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueTest {


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue(10);
        queue.add("ss");
        queue.offer("ss");
        queue.put("ss2");
        String poll = queue.poll();

        queue.take();
        System.out.println(poll);
    }

    @Test
    public void test1(){
        ServiceLoader.load()
    }
}
