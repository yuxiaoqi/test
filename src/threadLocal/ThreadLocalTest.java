package threadLocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/1/22 下午11:32
 */
public class ThreadLocalTest {
    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();



    public static void main(String[] args) {

        ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        ThreadLocal<String> threadLocal2 = new ThreadLocal<>();



          final int HASH_INCREMENT = 0x61c88647;
        AtomicInteger nextHashCode = new AtomicInteger();
        int a = nextHashCode.getAndAdd(HASH_INCREMENT);

        int b = nextHashCode.getAndAdd(HASH_INCREMENT);

        System.out.println("1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("yuyanqi");
                System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
                System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
                threadLocal.set("nihao");
                Thread thread = Thread.currentThread();
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
                threadLocal.set("yuyanqi2");
                System.out.println(Thread.currentThread().getName()+" "+threadLocal.get());
            }
        }).start();
    }
}
