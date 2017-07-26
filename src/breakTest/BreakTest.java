package breakTest;

/**
 * @ClassName:
 * @Description:
 * @Author yuyanqi
 * @Create 2017/6/6 0006 下午 2:37
 */
public class BreakTest {
    public static void main(String[] args) {
        aaa:
        for (int i = 0 ;i<4; i++){
            System.out.printf("test");
            if (i==2)
                break;
        }
    }
}
