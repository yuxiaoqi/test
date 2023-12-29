package Thread;

import static java.lang.Thread.sleep;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2023/12/28 15:03
 */
public class Interrupt_interrupted {


    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread();
        thread.start();

        // 中断线程
        thread.interrupt();

        // 重新输出


        // 检查当前线程的中断状态
        boolean isInterrupted = Thread.interrupted();
        System.out.println("中断状态：" + isInterrupted);

    }

   static class MyThread extends Thread {
        public void run() {


           // while (Thread.interrupted()) {  // 这里调用 Thread.interrupted() 不是把interrupt清楚了 重新设为false
                                            // 那sleep状态下自然不会唤醒抛出异常 ChatGPT给的错误示例代码
                // 线程执行的操作
                //System.out.println("Thread " +"进入到while");
                try {
                    sleep(1000); // 可能会抛出InterruptedException异常
                } catch (InterruptedException e) {
                    // 处理中断异常
                    System.out.println("线程被中断");
                    // 中断状态被清除，重新设置中断状态
                    Thread.currentThread().interrupt();
                }


          //  }
            System.out.println("线程执行结束=加个文件");
        }
    }

}
