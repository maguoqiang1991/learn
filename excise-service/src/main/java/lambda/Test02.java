package lambda;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author maguoqiang
 */
public class Test02 {

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200, "female", "New York"));
        personList.add(new Person("Owen", 9500, "male", "New York"));
        personList.add(new Person("Alisa", 7900, "female", "New York"));


        //筛选员工中工资高于8000的人，并形成新的集合
        List<String> collect = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("=========================");
        //获取工资最高的人
        Optional<Person> max = personList.stream().max(Comparator.comparing(Person::getSalary));
        System.out.println(max.orElse(new Person()).getName());
        System.out.println("=========================");

        //将员工的薪资全部增加1000
        //不改变原来员工集合的方式
        List<Person> collect2 = personList.stream().map(x -> {
            return new Person(x.getName(),x.getSalary()+1000,x.getAge(),x.getSex(),x.getArea());
        }).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect2,true));
        System.out.println("=========================");
        personList.forEach(x -> System.out.println(x.getSalary()));
        System.out.println("=========================");

        //改变原来员工集合的方式
        List<Person> collect3 = personList.stream().map(x -> {
            x.setSalary(x.getSalary() + 1000);
            return x;
        }).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect3,true));
        System.out.println("=========================");
        personList.forEach(x -> System.out.println(x.getSalary()));
        System.out.println("==========改变后===============");

        //直接取值加上1000，仅仅取值。
        List<Integer> collect1 = personList.stream().map(x -> x.getSalary() + 1000).collect(Collectors.toList());
        System.out.println(collect1);
        System.out.println("=========================");
        personList.forEach(x -> System.out.println(x.getSalary()));

    }
}
