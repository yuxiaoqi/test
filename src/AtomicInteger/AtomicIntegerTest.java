package AtomicInteger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2017/12/19 下午3:10
 */
public class AtomicIntegerTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicInteger integer = new AtomicInteger(0);
        AddRunnable add = new AddRunnable();

        for (int i= 0;i<10000;i++){
            integer.incrementAndGet();
        }

        for (int i =0;i<10000;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    atomicInteger.incrementAndGet();
                }
            });

        }

        for (int i =0;i<10000;i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    add.add();
                }
            });

        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("atomicInteger = "+atomicInteger);
        System.out.println("integer = "+integer);
        System.out.println("add = "+add.getValue());
    }
}
