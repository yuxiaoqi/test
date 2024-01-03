package ThreadSafe.Thread;

import java.util.concurrent.TimeUnit;


/*
* 定义：
 *   ThreadSafe.Thread 类的 interrupt() 方法用于中断线程。调用 interrupt() 方法会设置线程的中断状态。
 *  这种中断实质上是通过设置线程的中断状态标志位为 true 来表示，
 *   但它并不会中止线程的执行。相反，它可以被线程本身检查并相应地处理中断请求。
 *意义：
 *  禁止覆写（Override）： 使用 final 关键字修饰的方法无法被子类覆写。如果一个类被声明为 final，其中所有的方法默认也是 final 的，无法被子类修改。

     安全性与一致性： final 方法可以确保某个方法在子类中不被修改，这对于确保类的设计一致性和完整性很有帮助。在一些情况下，特定的方法可能已经经过充分测试和验证，如果不希望子类修改这些方法，可以将其声明为 final。

     性能优化： final 方法可以被 Java 虚拟机（JVM）进行优化，因为它不需要动态绑定。这意味着编译器可以直接将对 final 方法的调用绑定到该方法的实际定义，而不需要在运行时进行查找。

     安全约束： 在设计类时，有时希望某些关键的方法不被修改，避免可能的安全隐患或错误，这时可以使用 final 关键字来限制对这些方法的覆写。

     总的来说，final 方法的作用在于确保方法的一致性，避免被意外修改，同时也能提供一定程度的性能优化。
*
* */
public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {
        //获取当前线程信息
       Thread previousThread= Thread.currentThread();
        for(int i=0;i<10;i++){
/*
            当=0的时候 一开始是 ThreadSafe.Thread-0线程调用main线程的join方法。那就是会等main线程执行完后
            thread -0就会被通知重新执行。后面依次 1 2 。。。。9 到9执行行结束

*/

            Thread thread=new Thread(new Domino(previousThread));
            thread.start();
            previousThread=thread;
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println("ThreadSafe.Thread.currentThread().getName()+\" terminate.\" = " + Thread.currentThread().getName()+" terminate.");

    }

    static class Domino implements Runnable{
        //将前置线程的对象传入。当前线程会执行previousThread的join方法。并等待前置线程完成
        private Thread previousThread;
        public Domino(Thread previousThread){
            this.previousThread=previousThread;
        }
        @Override
        public void run() {
            try {
                previousThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}