package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2023/12/23 13:50
 */
public class CollectionSychronized {
    public static void main(String[] args) {
      List<Integer> synList = Collections.synchronizedList(new ArrayList<Integer>());
        synList.add(8);

    }
}
