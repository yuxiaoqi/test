package 并发编程.SynTools;

import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2024/1/2 20:00
 */
public class CyclicBarrierExampleA {
    public static void main(String[] args) {

        int threadCount =3;
        CyclicBarrier barrier=new CyclicBarrier(threadCount,()->{

            // 当所有线程都到达屏障点后执行的操作
            System.out.println("All threads have reached the barrier. Let's do something...");
        });



    }
}
