package 并发编程.concurrentUtils;

import com.google.common.util.concurrent.*;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/6/14 下午2:46
 */
public class ListenableFutureTaskTest {

    public static void main(String[] args) {
        /**
         *
         * MoreExecutors 就类似 JDK 的 Executors 提供一些静态的方法,创建线程池
         */
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));


        ListenableFuture<Integer> listenableFuture =  listeningExecutorService.submit(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                System.out.println("新的任务执行开始。。。。时间："+ new Date());

                TimeUnit.SECONDS.sleep(5);
                return 7;
            }
        });

        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out.println("任务执行成功，result："+result+"开始回调操作  时间："+new Date());
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("任务执行失败");
            }
        });

    }

}
