package 并发编程.threadPool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2024/1/8 21:07
 */
public class ForkJoinTest {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        Fibonacci task = new Fibonacci(10);   // 计算斐波那契数列的第10个数字
        int result = pool.invoke(task);
        System.out.println("Fibonacci(10) = " + result);  // 输出结果
    }


   static class Fibonacci extends RecursiveTask<Integer> {
        final int n;
        Fibonacci(int n) { this.n = n; }
        protected Integer compute() {
            if (n <= 1)
                return n;
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);
            return f2.compute() + f1.join();
        }
    }
}
