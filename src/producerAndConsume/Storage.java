package producerAndConsume;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2017/12/14 下午8:07
 */
public class Storage {


    public void say(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName()+"开始进入 synchronized");
            try {
                System.out.println(Thread.currentThread().getName()+"开始wait");
                /*wait();*/
                /*Thread.sleep(4000);*/
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"结束wait");
        }

    }

    public void hello() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName()+"开始hello 休息 3秒");
            try {
/*                Thread.sleep(3000);
                notifyAll();
                System.out.println(Thread.currentThread().getName()+"notifyAll 结束 休息 3秒");
                Thread.sleep(3000);*/
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
