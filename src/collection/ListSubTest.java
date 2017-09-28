package collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2017/9/11 下午12:07
 */
public class ListSubTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("aaa");//index_0
        list.add("bbb");//index_1
        list.add("ccc");//index_2
        list.add("ddd");//index_3
        list.add("eee");//index_4
        list.add("fff");//index_5
        list.add("ggg");//index_6

        int flag = 7;//每次取的数据

        int size = 0;
        int temp = size / flag + 1;
        boolean special = size % flag == 0;
        List<String> cutList = null;
        for (int i = 0; i < temp; i++) {
            if (i == temp - 1) {
                if (special) {
                    break;
                }
                cutList = list.subList(flag * i, size);
            } else {
                cutList = list.subList(flag * i, flag * (i + 1));
            }
            System.out.println("第" + (i + 1) + "组：" + cutList.toString());
        }
    }
}
