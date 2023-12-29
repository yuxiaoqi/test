package Thread;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/1/19 下午3:02
 */
public class TestInterrupt extends  Thread{



    /*
    *
interrupt()方法：
interrupt()方法用于中断线程的执行。当我们调用这个方法时，线程的中断状态会被设置为true。
如果线程处于阻塞状态（如调用了sleep()、wait()、join()等方法），
则会抛出一个InterruptedException异常，线程将被唤醒并且中断状态被清除（即重新设置为false）。

如果线程处于非阻塞状态，中断状态仅仅被设置为true，并不会直接停止线程的执行。在线程的代码中，
可以通过检查Thread.currentThread().isInterrupted()来判断线程是否被中断，并在合适的时候退出线程的执行。

interrupted()方法：
interrupted()是Thread类的静态方法，用于检查当前线程的中断状态并清除中断状态。
当调用interrupted()方法时，它会检查当前线程的中断状态并返回结果，然后会清除中断状态，将中断状态重新设置为false。
如果线程当前的中断状态为true，那么调用interrupted()方法后返回的结果为true，否则返回false。
    *
    * */
    @Override
    public void run() {
        System.out.println("开始");
        try {
            interrupt(); //设置中断信号
           // Thread.sleep(5000);
            System.out.println(" 一次 "+Thread.interrupted()); // interrupted()检查当前线程的中断状态并清除中断状态。
            System.out.println(" 二次 "+Thread.interrupted());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("结束");
    }


    public static void main(String[] args) throws InterruptedException {
        TestInterrupt testInterrupt = new TestInterrupt()   ;
        testInterrupt.start();

        sleep(30000);

    }

}
