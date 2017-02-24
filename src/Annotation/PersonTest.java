package Annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by yyq
 * Date  2016/12/22.
 * version 1.0.0.0
 */
public class PersonTest {
    @Person
    public String name ;

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        Method method = PersonTest.class.getMethod("setName",String.class);
        Field field = PersonTest.class.getField("name");
        Person person = field.getAnnotation(Person.class);
        if (method.isAnnotationPresent(Person.class)){
            System.out.printf("set name() 方法有注解！");
        }else{
            System.out.printf("set name() 方法没有注解！");
        }
        Method m = PersonTest.class.getMethod("getName",null);
        m.getDefaultValue();
        System.out.printf("-----------"+m.getName());
        new PersonTest().getName();
    }
}
