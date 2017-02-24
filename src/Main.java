
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.lang.Throwable.*;

public class Main {

    public static void main(String[] args) throws BeansException {
        System.out.println("Hello World!");
        A a0=null;
        A a1=new A();
        a1.setA(1);
        A a2=new A();
//        a2.setA(2);
        a2.setB("test");
        a2.setC(3);
        a2.setD(4);
       // BeanUtils.copyProperties(a2,a1);
        BeanUtils.copyProperties(a2,a1);


        System.out.println(" "+a1.b+" "+a1.c+" "+a1.d);
        if(a0 == null)
        {
            System.out.println("jinru null == a1");
        }


    }
}
