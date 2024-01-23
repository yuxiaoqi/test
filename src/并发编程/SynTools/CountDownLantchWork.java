package 并发编程.SynTools;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2023/12/29 22:12
 */
public class CountDownLantchWork {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(5);


        for (int i = 0; i <5 ; i++) {
           new Thread(new Worker(startSignal,doneSignal)).start();
        }
        System.out.println("主线程开始启动 任务线程");                            // don't let run yet
        startSignal.countDown();      // let all threads proceed();

        doneSignal.await();           // wait for all to finish
        System.out.println("任务线程执行结束，主线程等待成功");
    }

    static class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        public void run() {
            try {
                startSignal.await();
                //work something
                System.out.println("Thread work" + Thread.currentThread().getName() + " executing task done...");
                doneSignal.countDown();
            } catch (InterruptedException ex) {} // return;
        }

    }
}
