package springcircle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author maguoqiang
 * @date 2021-06-29 10:01
 */
@Component
public class InstanceB {

    @Autowired
    private InstanceA instanceA;

    public void say(){
        System.out.println("instanceB say ...");
        System.out.println("call instanceA say() start ....");
        //instanceA.say();
        System.out.println("call instanceA say() end ....");
    }

    public InstanceA getInstanceA() {
        return instanceA;
    }

    public void setInstanceA(InstanceA instanceA) {
        this.instanceA = instanceA;
    }
}
