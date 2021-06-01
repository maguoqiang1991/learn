package offen;

public class Test1 {


    private int c=0;

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        test1.test01();
        test1.test02();
    }

    private void test01(){
        c=2;
        int c=1;
        System.out.println(c);
    }

    private void test02(){
        System.out.println(c);
    }
}
