package collections;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author maguoqiang
 * @date 2021-06-04 12:34
 */
public class ConcurrentHashMapTest {

    @Test
    public void test01() {

        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("1", "1");

        map.get("1");
    }
}
