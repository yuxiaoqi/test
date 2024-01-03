package ThreadSafe.synchronizeTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/1/4 上午11:24
 */
public class SynTest {

    private SynTestInner synTestInner = new SynTestInner();

    public synchronized void  say(){
        System.out.println("SynTest");
    }

    public void hello(){

        synchronized (synTestInner){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synTestInner.say();
        }
    }


    class SynTestInner{

        public void say(){
            System.out.println("SynTestInner");
        }
    }

    public static void main(String[] args) {
        SynTest synTest = new SynTest();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                synTest.hello();
            }
        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                synTest.say();
            }
        });
    }
}
