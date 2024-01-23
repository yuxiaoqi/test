package 并发编程.Thread;

/**
 * Created by admin on 2016/9/21.
 */
public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println("mythread");
        throw new RuntimeException("为了测试");
    }


    public static void main(String[] args) {
    }
}
