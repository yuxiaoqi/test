package 并发编程.threadPool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2024/1/8 21:22
 */
public class ForkJoinTest2 {
  static   class SumTask extends RecursiveTask<Integer> {
        static final int THRESHOLD = 500;
        int[] array;
        int start;
        int end;

        SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= THRESHOLD) {
                // 如果任务足够小就直接计算
                int sum = 0;
                for (int i = start; i < end; i++)
                    sum += array[i];
                return sum;
            }

            // 任务大于阀值，拆分成两个子任务
            int middle = start + (end - start) / 2;
            SumTask left = new SumTask(array, start, middle);
            SumTask right = new SumTask(array, middle, end);
            left.fork();
            right.fork();

            // 获取子任务结果，得到最终结果
            return left.join() + right.join();
        }
    }
    public static void main(String[] args) {
        int[] array = new int[10000];
        // 初始化数组元素
        for (int i = 0; i < array.length; i++)
            array[i] = i;

        // 创建ForkJoinPool
        ForkJoinPool pool = new ForkJoinPool();
        // 创建任务
        SumTask task = new SumTask(array, 0, array.length);
        // 启动任务
        Integer result = pool.invoke(task);
        // 输出结果
        System.out.println("Sum: " + result);
    }
    }

