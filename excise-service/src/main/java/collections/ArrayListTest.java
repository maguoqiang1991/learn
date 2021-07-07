package collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author maguoqiang
 * @date 2021-07-02 16:50
 */
public class ArrayListTest {
    @Test
    public void test01(){
        List<Integer> asList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        List<Integer> subList = asList.subList(0, 5);

        asList.subList(0,5).clear();


    }
}
