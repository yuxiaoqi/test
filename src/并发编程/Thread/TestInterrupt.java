package 并发编程.Thread;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/1/19 下午3:02
 */
public class TestInterrupt extends  Thread{

    @Override
    public void run() {
        System.out.println("开始");
        try {
            interrupt();
           // Thread.sleep(5000);
            System.out.println(" 一次 "+Thread.interrupted());
            System.out.println(" 二次 "+Thread.interrupted());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("结束");
    }


    public static void main(String[] args) {
        TestInterrupt testInterrupt = new TestInterrupt()   ;
        testInterrupt.start();

    }

}
