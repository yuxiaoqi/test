package collection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yyq
 */
public class MapTest {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(2,3);
        map.put(2,4);
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            System.out.printf(entry.getKey()+"  "+entry.getValue());
        }
    }
}
