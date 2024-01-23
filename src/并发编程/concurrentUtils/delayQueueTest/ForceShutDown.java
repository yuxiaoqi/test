package 并发编程.concurrentUtils.delayQueueTest;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/9/18 17:14
 */
public class ForceShutDown implements Delayed,Function{
    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    @Override
    public Object apply(Object o) {
        return null;
    }
}
