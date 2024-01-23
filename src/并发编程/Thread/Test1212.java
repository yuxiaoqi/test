package 并发编程.Thread;

/**
 * @ClassName:
 * @Description:  多线程下输出1212121212
 * @Author yuxiaoqi
 * @Create 2019/12/6 9:49
 */
public class Test1212 {

    private volatile int state =1;

    public synchronized void  print1() {

        while (state <=30) {
            try {
                if (state%2 !=0){
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("1");
            ++state;
            notifyAll();
        }

    }


    public synchronized void  print2() {

        while (state <=30) {
            try {
                if (state%2 !=1){
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("2");
            ++state;
            notifyAll();
        }

    }


    public static void main(String[] args) {

        Test1212 test = new Test1212();
        new Thread(new Runnable() {
            @Override
            public void run() {
                test.print1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.print2();
            }
        }).start();


    }



}
