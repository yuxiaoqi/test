package innerclass;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2017/12/13 上午11:02
 */
public class OuterClass {

    public void say(){

        String name = "dads";
        new TestInterface(){

            @Override
            public void say() {
                System.out.println(name);
            }
        }.say();
        System.out.println(name);
    }

    public static void main(String[] args) {

        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf(Thread.currentThread().getName()+" 2");
            }
        }.start();
        System.out.printf(Thread.currentThread().getName()+" 1");
    }
}
