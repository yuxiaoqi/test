package 算法;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2023/12/23 8:31
 */

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class AlternatePrintingC {
    // private static volatile ThreadSafe.AtomicInteger number = new ThreadSafe.AtomicInteger(1);

    // volatile语义上保证壳 可见性 在java内存模型角度上是 底层内存操作规则实现的
    //
    private static volatile Integer number   = new Integer       (1);

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> printNumbers(1));
        Thread thread2 = new Thread(() -> printNumbers(2));

        thread1.start();
        thread2.start();
    }

    private static void printNumbers(int expected) {
        for (int i = 0; i < 5; i++) {
            while (true) {
                //r
 /*               if (number.get() == expected) {
                    System.out.print(expected);
                    number.set(expected == 1 ? 2 : 1);
                    break;
                }*/

                if (number == expected) {
                    System.out.print(expected);
                    number = expected == 1 ? 2 : 1;
                    break;
                }
            }
        }
    }
}

