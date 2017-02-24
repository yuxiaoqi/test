package proxy.jdk;

/**
 * Created by admin on 2016/9/7.
 */
public class BookFacedImpl implements BookFaced {
    @Override
    public void addBook() {
        System.out.println("执行 add book 方法");
    }
}
