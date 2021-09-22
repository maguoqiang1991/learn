package lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author maguoqiang
 */
public class Test03 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 4, 5, 6, 7, 88, 8, 7);
        //遍历输出符合条件的元素
        list.stream().filter(x -> x > 6).forEach(System.out::println);
        System.out.println("=====================");

        //匹配第一个
        Optional<Integer> first = list.stream().filter(x -> x > 6).findFirst();
        System.out.println(first.orElse(null));

        System.out.println("=====================");
        //匹配任意一个（适用于并行流）
        Optional<Integer> any = list.stream().filter(x -> x > 6).findAny();
        System.out.println(any.orElse(null));

        System.out.println("=====================");
        //是否包含符合条件的元素
        boolean b = list.stream().anyMatch(x -> x > 69);
        System.out.println(b);
        System.out.println("=====================");

        //获取String集合中最长的元素
        List<String> strings = Arrays.asList("afasf", "dafasdfasf", "daf", "45dfsdfafafdasf");
        Optional<String> max = strings.stream().max(Comparator.comparing(String::length));
        System.out.println(max.orElse(null));
        System.out.println("=====================");

        //获取int中的最大值
        List<Integer> list1 = Arrays.asList(1, 2, 4, 232, 35, 4353, 6546);
        Optional<Integer> max1 = list1.stream().max(Comparator.comparing(Integer::intValue));
        Optional<Integer> max2 = list1.stream().max(Integer::compareTo);
        System.out.println(max1.orElse(null) + "," + max2.orElse(null));
        System.out.println("=====================");

        //计算Integer集合中大于6的元素的个数。
        long count = list1.stream().filter(x -> x > 6).count();
        System.out.println(count);
        System.out.println("=====================");

        //英文字符串数组的元素全部改为大写。整数数组每个元素+3
        String[] strArr = {"abcd", "bcdd", "defde", "fTr"};
        //List<String> collect = Stream.of(strArr).map(String::toUpperCase).collect(Collectors.toList());
        List<String> collect = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("=====================");

        List<Integer> intList = Arrays.asList(1, 3, 5, 7, 9, 11);
        List<Integer> collect1 = intList.stream().map(x -> x + 3).collect(Collectors.toList());
        System.out.println(collect1);

    }
}
