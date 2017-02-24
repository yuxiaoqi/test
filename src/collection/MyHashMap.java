package collection;

import com.sun.javafx.collections.MappingChange;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yyq
 */
public class MyHashMap extends HashMap{
    @Override
    public Object put(Object key, Object value) {
        //调用put的方法不覆盖之前的值
        if (!this.containsKey(key)){
            return super.put(key, value);
        }
        return null;
    }

    public static void main(String[] args) {
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("4",5);
        objectMap.put("5","test");
    }
}
