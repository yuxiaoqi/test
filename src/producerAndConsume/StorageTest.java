package producerAndConsume;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2017/12/14 下午8:11
 */
public class StorageTest {
    public static void main(String[] args) {
        Storage storage = new Storage();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3,8,4,SECONDS,new LinkedBlockingQueue<Runnable>());

        StorageThread storageThread = new StorageThread(storage);

        StorageThread1 storageThread1 = new StorageThread1(storage);
        /*threadPoolExecutor.submit(storageThread);*/
        threadPoolExecutor.submit(storageThread1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.submit(storageThread);

        System.out.println(Thread.currentThread().getName()+" 结束");

    }
}
