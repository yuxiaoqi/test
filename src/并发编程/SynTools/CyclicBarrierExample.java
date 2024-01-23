package 并发编程.SynTools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/*
*
*
* Java 中用于多线程间同步的一种机制。它允许一组线程相互等待，
* 直到所有线程都达到某个公共的屏障点，然后再继续执行后续操作。
*
* */
public class CyclicBarrierExample {
    public static void main(String[] args) {
        final int threadsCount = 3;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadsCount, () -> {
            // 当所有线程都到达屏障点后执行的操作
            System.out.println(Thread.currentThread().getName() +"All threads have reached the barrier. Let's do something...");
        });

        for (int i = 0; i < threadsCount; i++) {
            Thread thread = new Thread(new Worker(cyclicBarrier));
            thread.start();
        }
    }

    static class Worker implements Runnable {
        private final CyclicBarrier cyclicBarrier;

        Worker(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                // 模拟工作
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " has reached the barrier.");
                cyclicBarrier.await(); // 等待其他线程到达屏障点
                System.out.println(Thread.currentThread().getName() + " continues execution after barrier.");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
