package 并发编程.concurrentUtils;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * @ClassName:
 * @Description: 链式操作
 * @Author yuxiaoqi
 * @Create 2018/7/27 下午2:58
 */
public class ListenableFutureChainTest {

    private static final ExecutorService executor = Executors.newFixedThreadPool(2);

    private static final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(executor);

    public void executeChain() {

        AsyncFunction<String, String> asyncFunction = new AsyncFunction<String, String>() {

            @Override
            public ListenableFuture<String> apply(final String input) throws Exception {
                ListenableFuture<String> future = executorService.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("STEP1 >>>" + Thread.currentThread().getName());
                        return input + "|||step 1 ===--===||| ";
                    }
                });

                return future;

            }
        };

        AsyncFunction<String, String> asyncFunction2 = new AsyncFunction<String, String>() {

            @Override
            public ListenableFuture<String> apply(final String input) throws Exception {
                ListenableFuture<String> future = executorService.submit(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println("STEP2 >>>" + Thread.currentThread().getName());
                        return input + "|||step 2 ===--===---||| ";
                    }
                });

                return future;

            }
        };

        ListenableFuture startFuture = executorService.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("BEGIN >>>" + Thread.currentThread().getName());
                return "BEGIN--->";
            }
        });

        ListenableFuture future = Futures.transformAsync(startFuture, asyncFunction, executor);

        ListenableFuture endFuture = Futures.transformAsync(future, asyncFunction2, executor);

        Futures.addCallback(endFuture, new FutureCallback() {
            @Override
            public void onSuccess(Object result) {
                System.out.println(result);
                System.out.println("=======OK=======");
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();

            }
        });

    }

    public static void main(String[] args) {
        System.out.println("========START=======");
        System.out.println("MAIN >>>" + Thread.currentThread().getName());
        ListenableFutureChainTest chain = new ListenableFutureChainTest();
        chain.executeChain();
        System.out.println("========END=======");

        executor.shutdown();
        // System.exit(0);
    }
}
