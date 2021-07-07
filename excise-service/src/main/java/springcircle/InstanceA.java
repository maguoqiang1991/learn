package springcircle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author maguoqiang
 * @date 2021-06-29 10:01
 */
@Component
public class InstanceA {

    @Autowired
    private InstanceB instanceB;

    public void say() {
        System.out.println("instanceA say...");
        System.out.println("call instanceB say() start ....");
        //instanceB.say();相当于循环调用了。
        System.out.println("call instanceB say() end ....");
    }

    public InstanceB getInstanceB() {
        return instanceB;
    }

    public void setInstanceB(InstanceB instanceB) {
        this.instanceB = instanceB;
    }
}
