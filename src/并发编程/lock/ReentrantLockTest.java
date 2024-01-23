package 并发编程.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2017/12/20 下午12:04
 */
public class ReentrantLockTest {
   static ReentrantLock reentrantLock = new ReentrantLock();
  static   Condition condition =reentrantLock.newCondition();




    public void say()  {
        try {
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+" 三秒 等待开始");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName()+" 三秒 等待结束");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest lockTest = new ReentrantLockTest();
        ExecutorService service = Executors.newFixedThreadPool(3);


        for (int i =0;i<10;i++){
            System.out.println("第"+i+"次");
            service.submit(new Runnable() {
                @Override
                public void run() {
                    lockTest.say();
                }
            } );
        }
        service.shutdown();
    }
}
