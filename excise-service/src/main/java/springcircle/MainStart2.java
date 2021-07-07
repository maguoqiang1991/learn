package springcircle;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author maguoqiang
 * @date 2021-06-29 9:50
 *
 * 使用二级缓存完全可以解决循环依赖。
 * 需要判断下，如果
 *
 *
 * 但是需要在getSingleton的时候，进行后置处理器的动态代理。这个不符合spring的单一职责要求。
 */
public class MainStart2 {

    private static Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

    /**
     * 读取bean定义，当然在spring中肯定是根据配置 动态扫描注册
     */
    public static void loadBeanDefinitions() {
        RootBeanDefinition aBeanDefinition = new RootBeanDefinition(InstanceA.class);
        RootBeanDefinition bBeanDefinition = new RootBeanDefinition(InstanceB.class);
        beanDefinitionMap.put("instanceA", aBeanDefinition);
        beanDefinitionMap.put("instanceB", bBeanDefinition);
    }

    public static void main(String[] args) throws Exception {
        // 加载了BeanDefinition
        loadBeanDefinitions();
        // 注册Bean的后置处理器

        getBean("instanceA");
        getBean("instanceB");

        // 循环创建Bean
        /*for (String key : beanDefinitionMap.keySet()) {
            // 先创建A
            getBean(key);
        }*/
        InstanceA instanceA = (InstanceA) getBean("instanceA");
        instanceA.say();
        InstanceB instanceB = (InstanceB) getBean("instanceB");
        instanceB.say();
    }

    // 一级缓存
    public static Map<String, Object> singletonObjects = new ConcurrentHashMap<>();


    // 二级缓存： 为了将 成熟Bean和纯净Bean分离，避免读取到不完整得Bean
    public static Map<String, Object> earlySingletonObjects = new ConcurrentHashMap<>();

    // 三级缓存 此处不需要
    public static Map<String, ObjectFactory> singletonFactories = new ConcurrentHashMap<>();

    // 循环依赖标识
    public static Set<String> singletonsCurrennlyInCreation = new HashSet<>();


    // 假设A 使用了Aop @PointCut("execution(* *..InstanceA.*(..))")   要给A创建动态代理
    // 获取Bean
    public static Object getBean(String beanName) throws Exception {
        System.out.println("+++++++++++++"+beanName);
        //先去获取bean
        Object singleton = getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }

        // 正在创建
        if (!singletonsCurrennlyInCreation.contains(beanName)) {
            singletonsCurrennlyInCreation.add(beanName);
        }
        // createBean


        // 实例化
        RootBeanDefinition beanDefinition = (RootBeanDefinition) beanDefinitionMap.get(beanName);
        Class<?> beanClass = beanDefinition.getBeanClass();
        Object instanceBean = beanClass.newInstance();  // 通过无参构造函数

        // 创建动态代理  （耦合 、BeanPostProcessor)    Spring还是希望正常的Bean 还是再初始化后创建
        // 只在循环依赖的情况下在实例化后创建proxy   判断当前是不是循环依赖
        //Object finalInstanceBean = instanceBean;
        //singletonFactories.put(beanName, () -> new JdkProxyBeanPostProcessor().getEarlyBeanReference(finalInstanceBean, beanName));

        // 添加到二级缓存
        earlySingletonObjects.put(beanName,instanceBean);

        // 属性赋值
        Field[] declaredFields = beanClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Autowired annotation = declaredField.getAnnotation(Autowired.class);
            // 说明属性上面有Autowired
            if (annotation != null) {
                declaredField.setAccessible(true);
                // byname  bytype  byconstrator
                // instanceB
                String name = declaredField.getName();
                Object fileObject = getBean(name);   //拿到B得Bean
                declaredField.set(instanceBean, fileObject);
            }

        }


        // 初始化   init-mthod
        // 放在这里创建已经完了  B里面的A 不是proxy
        // 正常情况下会再 初始化之后创建proxy


        // 由于递归完后A 还是原实例，， 所以要从二级缓存中拿到proxy 。
        if (earlySingletonObjects.containsKey(beanName)) {
            instanceBean = earlySingletonObjects.get(beanName);
        }

        // 添加到一级缓存   A
        singletonObjects.put(beanName, instanceBean);


        // remove 二级缓存和三级缓存
        return instanceBean;
    }


    public static Object getSingleton(String beanName) {
        System.out.println("========"+beanName);
        // 先从一级缓存中拿
        Object bean = singletonObjects.get(beanName);

        // 说明是循环依赖
        //如果一级缓存中没有，且正在创建，则说明有循环依赖。
        if (bean == null && singletonsCurrennlyInCreation.contains(beanName)) {
            bean = earlySingletonObjects.get(beanName);
            // 如果二级缓存没有就从三级缓存中拿
            if (bean != null) {
                bean = new JdkProxyBeanPostProcessor().getEarlyBeanReference(bean, beanName);
                earlySingletonObjects.put(beanName, bean);

            }

        }

        return bean;

    }
}
