package collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yyq
 * Date  2016/12/25.
 * version 1.0.0.0
 */
public class ListIndexOutOfTest {
    public static void main(String[] args) {
        //集合加
        List<String> strings = new ArrayList<>();
        List<Integer> add=new ArrayList<>();
        strings.add("算法.test");
        strings.size();
        //string
        String temp = strings.get(0);
        temp.length();
        //数组
        String[] test= new String[2];
        int length = test.length;
    }
}
