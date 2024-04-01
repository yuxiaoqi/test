package 并发编程;

/**
 * Created by yyq
 * Date  2016/12/6.
 * version 1.0.0.0
 */
public class B {

    public Integer add(Integer a){
        a++;
        a= a+9;
        return a;
    }

    public static void main(String[] args) {
        Integer a = new Integer(0);

        a = new B().add(a);
        System.out.println("jieguo a ="+a);
    }
}
