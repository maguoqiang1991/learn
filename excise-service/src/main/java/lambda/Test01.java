package lambda;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test01 {

    public static void main(String[] args) {
        //通过 java.util.Collection.stream() 方法用集合创建流
        List<String> strings = Arrays.asList("a", "b", "c");
        Stream<String> stream = strings.stream();
        Stream<String> stringStream = strings.parallelStream();

        //使用java.util.Arrays.stream(T[] array)方法用数组创建流
        int[] array = {1,2,3};
        IntStream stream1 = Arrays.stream(array);
        stream1.forEach(System.out::println);

        //使用Stream的静态方法：of()、iterate()、generate()
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 5, 6, 9);
        integerStream.forEach(System.out::println);
        Stream<int[]> array1 = Stream.of(array);
        //[I@37bba400 数组地址
        //array1.forEach(System.out::println);
        array1.forEach(value->{
            /*for (int i = 0; i < value.length; i++) {
                System.out.println(i);
            }*/
            Arrays.stream(value).forEach(System.out::println);
        });

        Stream<Integer> limit = Stream.iterate(0, x -> x + 3).limit(5);
        limit.forEach(System.out::print);

        Stream<Double> limit1 = Stream.generate(Math::random).limit(4);
        limit1.forEach(System.out::println);
    }
}
