package unsafe;

import sun.misc.Unsafe;

public class UnsafeTest {


    public static void main(String[] args) {
        Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();

    }
}
