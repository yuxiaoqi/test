package coding;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/1/17 下午12:57
 */
public class AlibabaCodingTest {

    //过于依赖 idea 有很多方法名，关键字错误，类名错误，语法问题暴露无遗

    private Boolean numberFlag = true;

    private Boolean letterFlag = false;

    AtomicInteger i = new AtomicInteger(1);

    AtomicInteger j = new AtomicInteger(1);


    private static Map<Integer,String> mapLetter = new HashMap<Integer,String>(){
        {
            put(1,"A");
            put(2,"B");
            put(3,"C");
            put(4,"D");
            put(5,"E");
            put(6,"F");
            put(7,"G");
            put(8,"H");
        }
    };
    // 初始化 1-，2—B,对应的26


    public void printNumber(){

        synchronized (this) {

            for (; ; ) {
                if (i.intValue() > 8) {
                    return;
                }

                while (letterFlag) {

                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.printf((2 * i.intValue() - 1) + "" + 2 * i.intValue());
                i.incrementAndGet();
                letterFlag = true;
                numberFlag = false;
                notifyAll();
            }
        }
    }

    public void printLetter(){

        synchronized(this) {

            for (; ; ) {
                if (j.intValue() > 8) {
                    return;
                }

                while (numberFlag) {

                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.printf(mapLetter.get(j.intValue()));
                j.incrementAndGet();
                numberFlag = true;
                letterFlag = false;
                notifyAll();
            }
        }
    }



public  static void main(String[] args){
    AlibabaCodingTest test = new AlibabaCodingTest();

        new Thread(new Runnable(){
            @Override
            public void run() {
                test.printNumber();
            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                test.printLetter();
            }
        }).start();

        }
}
