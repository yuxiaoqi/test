package proxy.jdk;

/**
 * Created by admin on 2016/9/7.
 */
public class proxyTest {
    public static void main(String[] args) {
        BookProxy proxy = new BookProxy();
        BookFaced bookProxy = (BookFaced)proxy.bind(new BookFacedImpl());
        bookProxy.addBook();
    }
}
