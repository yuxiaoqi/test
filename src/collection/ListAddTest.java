package collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author yuyanqi
 * @Create 2017/6/5 0005 上午 11:07
 */
public class ListAddTest {
    public static void main(String[] args) {
        // git test
        List<String> stringList = new ArrayList<>();
        stringList.add("a");
        stringList.add("c");
        stringList.add("b");
        System.out.printf("  ",stringList);
        stringList.remove("b");
        stringList.add(1,"b");
        System.out.printf("test");

    }
}
