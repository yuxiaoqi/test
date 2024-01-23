package 并发编程.cas;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2017/12/19 下午4:02
 */
public class MainWork {
    public static void main(String[] args) throws InterruptedException {
        UserVersion userVersion = new UserVersion();
        userVersion.setVersion(3);
        int value;
        int temp;
        do {
            System.out.println("比较开始");
            value=4; //从数据库中获取到数据
            temp=value+1;
            Thread.sleep(1000);
        }while (!userVersion.compareAndSet(value,temp));
        System.out.println("结束");
    }
}
