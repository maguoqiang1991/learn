package thread.pool;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author maguoqiang
 * @date 2021-06-04 15:41
 */
public class ExecutorTest {
    /* 线程池维护线程的最少数量 */
    private int minPoolSize = 1;

    /* 线程池维护线程的最大数量 */
    private int maxPoolSize = 5;

    /* 线程池维护线程所允许的空闲时间 */
    private int idleSeconds = 1800;

    /* 线程池所使用的缓冲队列 */
    private int queueBlockSize = 10;
    private ThreadPoolExecutor executor;

    @Before
    public void test01() {
        /* 重试添加当前加入失败的任务 */
        /* 时间单位,秒 */
        this.executor = new ThreadPoolExecutor(minPoolSize, maxPoolSize, idleSeconds,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueBlockSize),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Test
    public void test02() throws InterruptedException, ExecutionException {
        System.out.println("执行前activeCount:" + executor.getActiveCount());

        FutureTask<String> stringFutureTask = new FutureTask<>(() -> "123");
        executor.execute(stringFutureTask);
        //Thread.sleep(1000L);
        //System.out.println(stringFutureTask.get());
        System.out.println("执行后activeCount:" + executor.getActiveCount());
    }
}
