package collections;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    @Test
    public void test1() {
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("", "");
    }
    @Test
    public void test2() {
        Map<NoChangeKey, String> map = new HashMap<NoChangeKey, String>(128);
        map.put(new NoChangeKey(), "1");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        map.put(new NoChangeKey(), "2");
        System.out.println(map.size());
    }

    static class NoChangeKey{
        private String name;

        @Override
        public int hashCode() {
            return 1;
        }
    }
}
