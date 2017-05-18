package IntegerCacheTest;

import java.lang.reflect.Field;

/**
 * @ClassName:
 * @Description:
 * @Author yuyanqi
 * @Create 2017/5/15 0015 下午 3:32
 */
public class Test {
    public static void main(String[] args)
            throws Exception {
        Integer a = 1;
        Integer b = 2;
        System.out.println("before:a = " + a + ", b = " + b);
        swap(a, b);
        System.out.println(" after  :a = " + a + ", b = " + b);
    }

    public static void swap(Integer a, Integer b) throws Exception {
        Field valueField = Integer.class.getDeclaredField("value");
        valueField.setAccessible(true);
        int aValue = (int) valueField.get(a);
        int bValue = (int) valueField.get(b);
        valueField.set(a, bValue);
        valueField.set(b, aValue);
    }
}
