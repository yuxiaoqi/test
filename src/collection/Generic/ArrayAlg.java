package collection.Generic;

/**
 * Created by admin on 2016/3/25.
 */
public class ArrayAlg {
    public  static  <T> T getMiddle(T[] a)
    {
        return a[a.length/2];
    }
    public  static String getMiddle(String[] a)
    {
        return "Not Generic Method.";
    }

    public static  void main(String[] args)
    {
        String[] name={"cccc","bbbb","aaa"};
        //System.out.println(ArrayAlg.getMiddle(name));// 会被当成普通方法被使用
             System.out.println(ArrayAlg.getMiddle(name));
    }

}
