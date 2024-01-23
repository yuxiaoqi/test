package 并发编程.concurrentUtils;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/8/15 下午3:29
 */
public class ConcurrentLinkedQueueList {

    private static final ConcurrentLinkedQueue<App> APP_QUENE = new ConcurrentLinkedQueue();

    static class App{
        public App(int a){
            App.a=a;
        }
        private static int a;
    }

    public static void main(String[] args) {
        APP_QUENE.add(new App(1));
        APP_QUENE.add(new App(2));

        App aa = APP_QUENE.poll();
        App a = APP_QUENE.poll();
       // System.out.println(APP_QUENE.isEmpty());
    }
}
