package collection;

import java.util.concurrent.SynchronousQueue;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2023/12/27 8:51
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        SynchronousQueue<Integer> integerSynchronousQueue = new SynchronousQueue();
        new Thread(()->{

            try {
                integerSynchronousQueue.put(44);
                System.out.println("我放入一个元素：44");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();
        new Thread(()->{

            try {
               Integer result=  integerSynchronousQueue.take();
                System.out.println("我拿到结果了 resu"+result);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }).start();


/*        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }
}
