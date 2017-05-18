package IntegerCacheTest;

import classInit.ClassInit;

import java.lang.reflect.Field;

public class ChangeDemo {
    public static void change(String str) {
        if (null == str)
            return;
        try {
            Field f = String.class.getDeclaredField("value");
            f.setAccessible(true);
            char[] value_str = (char[]) f.get(str);
            value_str[0] = 'h';
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ClassInit change(Integer num) {
//        if (null == num)
//            return;
        ClassInit init = new ClassInit();
        try {

            Field f = ClassInit.class.getDeclaredField("value");
            f.setAccessible(true);
            String value = (String) f.get(init);
            f.set(init, "12131");
            System.out.println((String) f.get(init));
//            int value = (int) f.get(num);
//            f.set(num, value * 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return init;
    }

    public static void main(String[] args) {
//        String a = "cat";
//        System.out.println("before:a=" + a);
//        change(a);
//        System.out.println("after   :a=" + a);
        Integer num = 12;
        System.out.println("before:num=" + num);
        ClassInit classInit = change(num);
        System.out.println(classInit.getValue());
//        System.out.println("after   :num=" + num);
    }
}