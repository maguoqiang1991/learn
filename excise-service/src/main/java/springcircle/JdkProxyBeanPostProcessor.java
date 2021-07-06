package springcircle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author maguoqiang
 * @date 2021-06-29 10:09
 */
@Component
public class JdkProxyBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {

    @Override
    public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {

        // 假设B 被切点命中 需要创建代理
        /*判断是不是被增强的类，是不是需要创建动态代理*/
        if(bean instanceof InstanceA) {
            JdkDynimcProxy jdkDynimcProxy = new JdkDynimcProxy(bean);
            System.out.println("执行了。。");
            return  bean;
        }
        return bean;
    }
}
