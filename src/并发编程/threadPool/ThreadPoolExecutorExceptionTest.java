package 并发编程.threadPool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/10/10 16:07
 */
public class ThreadPoolExecutorExceptionTest {

    /*
    *
    * 1）如果当前运行的线程少于corePoolSize，则创建新线程来执行任务（注意，执行这一步骤需要获取全局锁）。
      2）如果运行的线程等于或多于corePoolSize，则将任务加入BlockingQueue。
      3）如果无法将任务加入BlockingQueue（队列已满），则创建新的线程来处理任务（注意，执行这一步骤需要获取全局锁）。
      4）如果创建新线程将使当前运行的线程超出maximumPoolSize，任务将被拒绝，并调用RejectedExecutionHandler.rejectedExecution()方法。根据不同的拒绝策略去处理。
     ThreadPoolExecutor采取上述步骤的总体设计思路，是为了在执行execute()方法时，尽可能地避免获取全局锁（那将会是一个严重的可伸缩瓶颈）。在ThreadPoolExecutor完成预热之后（当前运行的线程数大于等于corePoolSize），几乎所有的execute()方法调用都是执行步骤2，而
步骤2不需要获取全局锁。
    * */


    /*--------------参数解释---------------------------------------------------------------*/
    /*
    *
    * 介绍一下这几个参数：
      1）corePoolSize（线程池的基本大小）：当提交一个任务到线程池时，如果当前poolSize<corePoolSize时，线程池会创建一个线程来执行任务，即使其他空闲的基本线程能够执行新任务也会创建线程，等到需要执行的任务数大于线程池基本大小时就不再创建。如果调用了线程池的prestartAllCoreThreads()方法，线程池会提前创建并启动所有基本线程。

      2）maximumPoolSize（线程池最大数量）：线程池允许创建的最大线程数。如果队列满了，并且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。值得注意的是，如果使用了无界的任务队列这个参数就没什么效果。

      3）keepAliveTime（线程活动保持时间）：线程池的工作线程空闲后，保持存活的时间。所以，如果任务很多，并且每个任务执行的时间比较短，可以调大时间，提高线程的利用率。

      4）TimeUnit（线程活动保持时间的单位）：可选的单位有天（DAYS）、小时（HOURS）、分钟（MINUTES）、毫秒（MILLISECONDS）、微秒（MICROSECONDS，千分之一毫秒）和纳秒（NANOSECONDS，千分之一微秒）。

      5）runnableTaskQueue（任务队列）：用于保存等待执行的任务的阻塞队列。可以选择以下几个阻塞队列。
        - ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，此队列按FIFO（先进先出）原则对元素进行排序。

        - LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO排序元素，吞吐量通常要高于ArrayBlockingQueue。静态工厂方法Executors.newFixedThreadPool()使用了这个队列。

        - SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等到另一个线程调用移除操作，否则插入操作一直处于阻塞状态，吞吐量通常要高于Linked-BlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列。
        ·PriorityBlockingQueue：一个具有优先级的无限阻塞队列。

        6）ThreadFactory：用于设置创建线程的工厂，可以通过线程工厂给每个创建出来的线程设置更有意义的名字。使用开源框架guava提供的ThreadFactoryBuilder可以快速给线程池里的线
        程设置有意义的名字，代码如下。
         `new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build();`

        7）RejectedExecutionHandler（饱和策略）：当队列和线程池都满了，说明线程池处于饱和状态，那么必须采取一种策略处理提交的新任务。这个策略默认情况下是AbortPolicy，表示无法处理新任务时抛出异常。在JDK 1.5中Java线程池框架提供了以下4种策略。
        - AbortPolicy：直接抛出异常。
        - CallerRunsPolicy：只用调用者所在线程来运行任务。
        - DiscardOldestPolicy：丢弃队列里最  近的一个任务，并执行当前任务。
        - DiscardPolicy：不处理，丢弃掉。
        当然，也可以根据应用场景需要来实现RejectedExecutionHandler接口自定义策略。如记录日志或持久化存储不能处理的任务。
    *
    * */

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("my-task-%d").build();
    private static ThreadFactory threadFactory_1 = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            System.out.println("任务线程池:"+t.getName());
            return t;
        }
    };



    private static ThreadPoolExecutor threadPoolExecutor = new  ThreadPoolExecutor(2,
            4,
            10L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(2), threadFactory, new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                System.out.println("队列已满");
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }){

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            Future<?> f = (Future<?>)r;
            try {
                // 若是线程池执行任务用的是submit，则Runnable任务会被封装成future。而future执行任务
                // 的时候，会将异常给吃掉，也就是不会抛出异常。这样操作就可以把异常给拿出来了
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    static class DivTask implements Runnable {
        int a, b;

        public DivTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            double re = a / b;
            System.out.println("结果："+re);
        }
    }


    /**
     * 问题提出：1：exceute 和submit两种提交方法的不不同？
     * 、       2： 为什么excute会将异常跑出来
     *          3： submit 为什么会将异常吃掉
     *          4：怎么样操作可以将submit的的异常给打印出来
     *  相关指导连接：https://segmentfault.com/a/1190000000669942
     */

    /**
     * 如何优化线程池的异常信息？
     * @param args
     */

    public static void main(String[] args) {

        for(int i =0;i <=4;i++){
            threadPoolExecutor.submit(new DivTask(100,i));
        }
        threadPoolExecutor.shutdown();
    }

}
