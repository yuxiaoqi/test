package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by admin on 2016/9/7.
 */
public class BookProxy implements InvocationHandler{


    private Object target;


    public Object bind(Object target){
        this.target = target;

        Object o =  Proxy.newProxyInstance(this.target.getClass().getClassLoader(),this.target.getClass().getInterfaces(),this);
        return o;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("事物开始！");
        Object result = method.invoke(target,args);
        System.out.println("事物结束");
        return result;
    }
}
