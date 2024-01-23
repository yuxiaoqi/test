package 并发编程.SynTools;

import java.util.concurrent.CountDownLatch;


/*
*  CountDownLatch是Java并发编程中的一个类，它允许一个或多个线程等待直至其他线程完成其操作。
* 其工作原理是基于计数器的：当我们创建一个CountDownLatch对象时，
*  我们设定一个初始计数器值；然后每次调用countDown方法就会把这个值减一，
* 而await方法则会阻塞当前线程，直到计数器的值变为零。  chatGpt
*
* 笔记：计数器就是一个许可证 根据cas原理 确保 countDown能正确的将计数器值 减一。知道计数器为零
* 而调用await方法底层就是调用lockSupport.park方法
*
* */
/*
* 注意注意！！！
* 每个CountDownLatch对象都是只能使用一次，一旦计数值被降为0，就不再有任何用处，
*  因此在使用时要考虑清楚是用一个CountDownLatch，还是用多个CountDownLatch
*
* 计算器降为0 内部没有子啊重新给它初始化
* */
public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int workNums = 3;
        int watchers =2 ;
        CountDownLatch latch = new CountDownLatch(workNums);

        for (int i = 0; i < workNums; i++) {
            new Thread(() -> {
                // 线程执行任务
                System.out.println("Thread work" + Thread.currentThread().getName() + " executing task...");
                // 任务完成后调用 countDown()
                latch.countDown();
            }).start();
        }

        for (int i = 0; i < watchers; i++) {
            new Thread(() -> {
                // 等待workThreads 完成工作后再工作
                try {
                    latch.await();
                } catch (InterruptedException e) {

                }
                System.out.println("Thread watcher " + Thread.currentThread().getName() + " executing watcher...");

            }).start();
        }

        // 主线程等待所有线程完成任务
        latch.await();
        System.out.println("All threads have completed their tasks.");



    }
}
