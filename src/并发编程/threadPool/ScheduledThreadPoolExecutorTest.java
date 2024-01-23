package 并发编程.threadPool;


import org.joda.time.DateTime;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/11/5 15:37
 */
public class ScheduledThreadPoolExecutorTest {
    /*
    * 执行的方式：

scheduleAtFixedRate(commod,initialDelay,period,unit)

initialDelay是说系统启动后，需要等待多久才开始执行。

period为固定周期时间，按照一定频率来重复执行任务。

如果period设置的是3秒，系统执行要5秒；那么等上一次任务执行完就立即执行，也就是任务与任务之间的差异是5s；

如果period设置的是3s，系统执行要2s；那么需要等到3S后再次执行下一次任务。
    *
    *
    * */


    /*
    *
    * scheduleWithFixedDelay(commod,initialDelay,delay,unit)  延时的周期性
initialDelay是说系统启动后，需要等待多久才开始执行。

period为固定周期时间，按照一定频率来重复执行任务。

这个方式必须等待上一个任务结束才开始计时period。

如果设置的period为3s;任务执行耗时为5S那么下次任务执行时间为第8S。
    * */

    /*任务周期性的执行关键的两个类： https://www.jianshu.com/p/502f9952c09b
      ScheduledFutureTask :最主要的功能是根据当前任务是否具有周期性，对异步任务进行进一步封装。
                           如果不是周期性任务（调用schedule方法）则直接通过run()执行，若是周期性任务，
                           则需要在每一次执行完后，重设下一次执行的时间，
                           然后将下一次任务继续放入到阻塞队列中。



     *DelayedWorkQueue: ScheduledThreadPoolExecutor中的内部类
     *                  是一个基于堆的数据结构，类似于DelayQueue和PriorityQueue。
     *                  在执行定时任务的时候，每个任务的执行时间都不同，
     *                  所以DelayedWorkQueue的工作就是按照执行时间的升序来排列，
     *                  执行时间距离当前时间越近的任务在队列的前面。


    *
    * */

    /*
      都是基于 ScheduledThreadPoolExecutor 的内部类
    *   ScheduledFutureTask：封装了 runnable
    *   中：
    *      sequenceNumber ：添加到任务的序列号
    *      time：任务具体执行的时间
    *      period：任务的时间间隔期
    *      这几个数据取保任务的的排序，与任务的执行有很密切的关系
    *      该算法规则是：首先按照time排序，time小的排在前面，大的排在后面，如果time相同，则使用sequenceNumber排序，小的排在前面，
    *      大的排在后面。那么为什么在这个类里面提供compareTo()方法呢？
    *      在前面就介绍过ScheduledThreadPoolExecutor在构造方法中提供的是DelayedWorkQueue()队列中，
    *      也就是说ScheduledThreadPoolExecutor是把任务添加到DelayedWorkQueue中的，
    *      而DelayedWorkQueue则是类似于DelayQueue，内部维护着一个以时间为先后顺序的队列，
    *      所以compareTo()方法使用与DelayedWorkQueue队列对其元素ScheduledThreadPoolExecutor task进行排序的算法。
     *      排序已经解决了，那么ScheduledThreadPoolExecutor 是如何对task任务进行调度和延迟的呢？任何线程的执行，都是通过run()方法执行，
     *      ScheduledThreadPoolExecutor 的run()方法如下：
     *
     *      任务延时队列
     *      DelayedWorkQueue：
    * */

    private static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"我是负责任务A调度的任务线程");
        }
    });
    public static void main(String[] args) {

        System.out.printf("开始时间：time="+new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("周期性执行任务： time="+ new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
            }
        },4,1, TimeUnit.SECONDS);

    }
}
