package ThreadSafe.asynTask;

import java.util.concurrent.*;

public class FutureTaskExample {
    public static void main(String[] args) {
        // 创建一个 FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            // 模拟一个耗时任务
            Thread.sleep(500000);
            return 42;
        });

       /* // 创建 ExecutorService
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 提交 FutureTask 给 ExecutorService 执行
        executor.submit(futureTask);
*/

        new Thread(futureTask).start();
        // 可以继续做其他工作...

        try {
            // 获取任务结果，如果任务未完成，会阻塞当前线程直到任务完成
            int result = futureTask.get();
            System.out.println("Task Result: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
/*
        // 关闭 ExecutorService
        executor.shutdown();*/
    }
}
