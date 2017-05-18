package classInit;

/**
 * Created by admin on 2016/10/10.
 */
public class ClassInit {

    public String getValue() {
        return value;
    }

    private String value ="5656656";

    public static void main(String[] args) {
        /*System.out.println(Child.a);*/
        /*Parent[] parents = new Parent[10];*/
        Parent parent = new Parent();
        parent.test();
    }
}


class Parent{
    public static int a = 9;
    static {
        System.out.println("parent  init --");
    }
    public void test(){
        System.out.printf("parent----test");
    }
}

class Child extends Parent{
    static {
        System.out.println("child  init --");
    }
}
