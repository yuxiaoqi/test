package ThreadSafe.Thread;

/**
 * Created by admin on 2016/9/21.
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("mythread");
        throw new RuntimeException("为了测试");
    }
}
