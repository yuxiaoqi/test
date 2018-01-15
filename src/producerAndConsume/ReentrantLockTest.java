package producerAndConsume;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2017/12/19 上午10:27
 */
public class ReentrantLockTest {
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();


    public void say(){
        try {
            lock.lock();
            Thread.sleep(4000);
            System.out.println("say()");
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


    public void sing(){
        try {
            lock.lock();
            System.out.println("sing()");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockTest.say();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLockTest.sing();
            }
        }).start();
    }
}
