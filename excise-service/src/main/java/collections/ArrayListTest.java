package collections;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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

    @Test
    public void test2(){
        BigDecimal a = new BigDecimal(1);
        BigDecimal b = new BigDecimal(1);
        BigDecimal c = new BigDecimal(1);
        a = a.add(b).add(c);
        System.out.println(a);
    }

    @Test
    public void test03(){
        ArrayList arrayList = new ArrayList();
        arrayList.get(1);//查询快

        //添加会涉及到扩容，移动
        arrayList.add(1);
        arrayList.add(1,1);


        LinkedList linkedList = new LinkedList();
        linkedList.get(1);


        linkedList.add(1);
        linkedList.add(1,1);
    }
}
