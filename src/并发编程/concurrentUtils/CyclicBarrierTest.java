package 并发编程.concurrentUtils;


import com.google.common.collect.Maps;

import java.util.concurrent.*;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/8/8 上午11:43
 */
public class CyclicBarrierTest {

    private static final ConcurrentMap<String,Integer> RESULT_MAP = Maps.<String, Integer>newConcurrentMap();

    private static ThreadPoolExecutor threadPoolTaskExecutor = new ThreadPoolExecutor(5,10, 1, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

    private static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(4, new Runnable() {
        @Override
        public void run() {

            int tatal =0;
            for (Integer integer : RESULT_MAP.values()) {
                tatal +=integer;
            }
            RESULT_MAP.put("tatal",tatal);
            System.out.println("工作结果统计"+tatal);
        }
    });


    public static void main(String[] args) {
        threadPoolTaskExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"开始工作");
                RESULT_MAP.put("1",1);
                try {
                    int index = CYCLIC_BARRIER.await();
                    System.out.println(Thread.currentThread().getName()+"我的工作。。。。"+"index："+index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPoolTaskExecutor.submit(new Runnable() {
            @Override
            public void run() {
                //开始处理业务--
                System.out.println(Thread.currentThread().getName()+"开始工作");
                //将结果放到map缓存中
                RESULT_MAP.put("2",1);
                try {
                    int index = CYCLIC_BARRIER.await();
                    System.out.println(Thread.currentThread().getName()+"我的工作。。。。"+"index："+index);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPoolTaskExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"开始工作");
                RESULT_MAP.put("3",1);
                try {
                    int index =  CYCLIC_BARRIER.await();
                    System.out.println(Thread.currentThread().getName()+"我的工作。。。。"+"index："+index);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPoolTaskExecutor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"开始工作");
                RESULT_MAP.put("4",1);
                try {
                    int index =   CYCLIC_BARRIER.await();
                    System.out.println(Thread.currentThread().getName()+"我的工作。。。。"+"index："+index);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPoolTaskExecutor.shutdown();
    }

}
