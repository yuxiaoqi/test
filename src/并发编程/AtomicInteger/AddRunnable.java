package 并发编程.AtomicInteger;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2017/12/19 下午3:40
 */
public class AddRunnable {

    private int value=0;

    public void add(){
        value++;
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        Long a = 100L;
        String aa =a.toString();
        System.out.println(aa);
    }
}
