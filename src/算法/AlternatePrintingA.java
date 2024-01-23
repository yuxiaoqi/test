package 算法;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2023/12/22 17:28
 */
public class AlternatePrintingA {

    //用户锁交互
    private static final Object lock = new Object();

    private static volatile boolean flag = false;


    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> printing(1));


        Thread thread2 = new Thread(() -> printing(2));

        thread1.start();
        thread2.start();
    }

    private static void printing(int number) {
        ThreadLocal<Integer> sThreadLocal = ThreadLocal.withInitial(()->number);
        for (int i = 0; i < 5; i++) {
            synchronized (lock) {
                try {
/*                    while ((number == 1 && flag) || (number == 2 && !flag)) {
                        并发编程.lock.wait();
                        */

/*  错误示范

                   while (flag) {
                        并发编程.lock.wait();
                    }*/


/*
 * flag作为状态切换 当状态切换之后当且仅当另外一个线程是可以进到代码块。
 *   （1） 需要有一个线程的特定标识 来识别出当前线程
 */
                    while( (sThreadLocal.get()==1 && flag) || (sThreadLocal.get()==2 && !flag)){
                        lock.wait();
                    }
                    System.out.print(number);
                    flag = !flag;
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }


}
