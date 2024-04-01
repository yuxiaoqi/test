package 算法;

/**
 * @ClassName:
 * @Description:
 * @Author yuyanqi
 * @Create 2017/5/16 0016 下午 2:25
 */
public class PhoneTest {
    public static void main(String[] args) {
        String phone = "15999601733";
         phone.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        System.out.println(phone);
    }
}
