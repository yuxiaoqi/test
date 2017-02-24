package Generic;

import collection.Generic.A;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yyq
 */
public class MyClass<T> implements MyClassInterface<T>{
    private T var;

    public void setVar(T var) {
        this.var = var;
    }

    public T getVar() {
        return var;
    }

    /**
     * 泛型接口方法测试
     *
     * @return
     */
    @Override
    public T doSomething() {
        return var;
    }

    public MyClass(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        this.var = tClass.newInstance();
    }


    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
      /*  MyClass<String> myClassInterface = new MyClass<>();
        myClassInterface.setVar("泛型方法测试！！");
        System.out.println("结果---"+myClassInterface.getVar());
        System.out.println(myClassInterface.doSomething());
*/

        List<String> arrayList = new ArrayList<>();
        arrayList.add("aaa");
        arrayList.add("bbb");
        System.out.println(Util.getFirstElement(arrayList));

        MyClass<A> aMyClass = new MyClass<>(A.class);
        aMyClass.getVar().dosomething();
    }
}
