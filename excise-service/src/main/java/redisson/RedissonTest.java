package redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;

/**
 * @author maguoqiang
 * @date 2021-06-10 12:34
 */
public class RedissonTest {

    public static void main(String[] args) {
        Redisson redisson = null;
        RLock key = redisson.getLock("key");
        //底层使用了Lua脚本（原子性），可重入，获得锁后，返回，未获得，阻塞。如果重入后，会对设置 的值+1
        key.lock();
        key.unlock();
    }
}
