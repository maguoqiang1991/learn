package springcircle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author maguoqiang
 * @date 2021-06-29 10:10
 */
public class JdkDynimcProxy implements InvocationHandler {

    private Object target;

    public JdkDynimcProxy(Object target) {
        this.target = target;
    }

    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("测试");
        return method.invoke(target,args);
    }
}
