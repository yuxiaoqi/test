package ParentAndChild;

/**
 * Created by admin on 2016/9/21.
 */
public class Child extends Parent {
    @Override
    public void test() {
        super.test();
        System.out.println("算法.test --child");
    }

    public static void main(String[] args) {
        new Child().test();
    }
}
