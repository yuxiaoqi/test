package Thread;

/**
 * @ClassName:
 * @Description:
 * @Author yuyanqi
 * @Create 2017/4/7 0007下午 2:19
 */
public class ExceptionThreadTest {

    public static void main(String[] args) {

        System.out.println("main kaishi");
/*        if (1==1){
            throw new RuntimeException("main 测试");
        }*/
        new MyThread().start();
        System.out.println("main jiesu");
    }
}
