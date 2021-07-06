package springcircle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author maguoqiang
 * @date 2021-06-29 9:57
 */
public class MainStarterAnnotation {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(MyConfigure.class);
    }
}
