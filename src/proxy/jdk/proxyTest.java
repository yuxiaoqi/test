package proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by admin on 2016/9/7.
 */
public class proxyTest {
    public static void main(String[] args) {
        BookProxy proxy = new BookProxy();
        BookFaced bookProxy = (BookFaced)proxy.bind(new BookFacedImpl());
        bookProxy.addBook();


        /**通过查看代理类的源代码 分析
         * 2. invoke方法是怎么调用的？
         3. invoke和add方法有什么对应关系？
         4. 生成的代理对象是什么样子的？
         */
        String path = "/Users/yuxiaoqi/yyq学习/test/out/production/test/proxy/$Proxy0.class";
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0",
                BookFacedImpl.class.getInterfaces());
        FileOutputStream out = null;

        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
