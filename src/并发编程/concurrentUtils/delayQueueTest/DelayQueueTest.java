package 并发编程.concurrentUtils.delayQueueTest;

import com.google.common.collect.Maps;
import org.joda.time.DateTime;

import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @ClassName:
 * @Description: 订单在规定时间内未变更自动关闭订单
 * @Author yuxiaoqi
 * @Create 2018/9/18 下午14:53
 */
public class DelayQueueTest {

    private static DelayQueue<Order> orderDelayQueue =new DelayQueue<>();

    private static Map<String,Function<String,String>> functionMap = Maps.newHashMap();


    private static ThreadPoolExecutor threadPoolTaskExecutor = new ThreadPoolExecutor(5,10, 1, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

    private  String docation(String param) {
        return param;
    }


    public static void main(String[] args) {

        functionMap.put("aaa",(String param)->new DelayQueueTest().docation(param));


        threadPoolTaskExecutor.submit(()->{

            System.out.println("开始监控过期失效的订单");
            for (;;){
                try {
                    Order order = orderDelayQueue.take();
                    order.setOrderStatus("canceled");
                    System.out.println("订单：" + order.getOrderNo() + "付款超时，自动取消，当前时间："+ new DateTime().toString("yyyy-MM-dd HH:mm:ss"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPoolTaskExecutor.submit(()->{
            for (int i = 1;i<10 ;i++){
                orderDelayQueue.put(new Order().setOrderStatus("create")
                        .setOrderNo("201909_"+i)
                        .setCancelTime(new DateTime().plusMinutes(5*i).toDate()));
            }
            System.out.println("总订单数量"+orderDelayQueue.size());
        });

/*        Thread daemonThread = new Thread();
        daemonThread.setDaemon(true);
        daemonThread.setName("daemon-thread");
        daemonThread.start();*/


    }


}
