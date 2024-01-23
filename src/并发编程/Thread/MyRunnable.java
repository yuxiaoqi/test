package 并发编程.Thread;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2019/12/2 15:58
 */
public class MyRunnable implements Runnable{


    private Thread thread;




    public void start(){
/*
        thread = new Thread(this,"MyRunnable线程");
*/
        thread = new Thread(this::run);
        System.out.printf(thread.getName());
        thread.start();

    }


    @Override
    public void run() {
        System.out.printf("我执行的任务");
    }

    public static void main(String[] args) {
        new MyRunnable().start();
    }
}
