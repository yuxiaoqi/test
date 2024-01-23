package 并发编程.SynTools;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        // 创建 Semaphore 实例，设置许可证数量为 3
        Semaphore semaphore = new Semaphore(3);

        // 创建多个线程，并启动
        for (int i = 1; i <= 5; i++) {
            Thread thread = new Thread(new Worker(semaphore, i));
            thread.start();
        }
    }

    static class Worker implements Runnable {
        private final Semaphore semaphore;
        private final int id;

        Worker(Semaphore semaphore, int id) {
            this.semaphore = semaphore;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread " + id + " is trying to acquire...");
                semaphore.acquire(); // 获取一个许可证，如果没有，则阻塞
                System.out.println("Thread " + id + " has acquired the permit.");
                Thread.sleep(2000); // 模拟某些操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(); // 释放许可证
                System.out.println("Thread " + id + " has released the permit.");
            }
        }
    }
}
