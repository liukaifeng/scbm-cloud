package com.scbm.aop.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理实现InvocationHandler接口
 *
 * @author 刘凯峰
 * @version V1.0
 * update-logs:方法变更说明
 * ****************************************************
 * name:
 * date:
 * description:
 * *****************************************************
 * @date 2020-04-09 15:26
 */

public class JdkProxy implements InvocationHandler {
    //需要代理的目标对象
    private Object target;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK动态代理，监听开始！");
        Object result = method.invoke(target, args);
        System.out.println("JDK动态代理，监听结束！");
        return result;
    }

    //定义获取代理对象方法
    private Object getJdkProxy(Object targetObject) {
        //为目标对象target赋值
        this.target = targetObject;
        //JDK动态代理只能针对实现了接口的类进行代理，newProxyInstance 函数所需参数就可看出
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }

    public static void main(String[] args) {
        //实例化JDKProxy对象
        JdkProxy jdkProxy = new JdkProxy();
        //获取代理对象
        UserManager user = (UserManager) jdkProxy.getJdkProxy(new UserManagerImpl());
        //执行新增方法
        user.addUser("admin", "123123");
    }
}
