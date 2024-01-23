package 并发编程.Thread;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2024/1/11 15:57
 */
public class DefaultUncaughtExceptionHandlerTest {
    public static void main(String[] args) {
        // 设置默认的未捕获异常处理器
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程 " + t + " 发生了未捕获异常：" + e);
            }
        });

        // 创建一个会抛出运行时异常的线程
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                throw new RuntimeException("测试异常");
            }
        });

        thread1.start();

        // 创建另一个会抛出运行时异常的线程
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                throw new RuntimeException("另一个测试异常");
            }
        });

        thread2.start();
    }
    }
