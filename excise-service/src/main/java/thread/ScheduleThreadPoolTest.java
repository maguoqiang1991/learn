package thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPoolTest {
    private final static Logger logger = LoggerFactory.getLogger(ScheduleThreadPoolTest.class);

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

        executor.schedule(() -> {
                    logger.info("123");
                }
                , 1000, TimeUnit.MILLISECONDS);

        executor.scheduleAtFixedRate(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logger.info("456");
                }
                , 1000, 2000, TimeUnit.MILLISECONDS);
    }
}
