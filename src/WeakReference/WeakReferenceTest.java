package WeakReference;

import java.lang.ref.WeakReference;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/1/29 下午10:52
 */
public class WeakReferenceTest {


    public static void main(String[] args) throws InterruptedException {
        Apple apple = new Apple(1);
        Water water = new Water();
        WeakReference<Apple>  appleWeakReference = new WeakReference<Apple>(apple);
        WeakReference<Apple>  appleWeakReference1 = new WeakReference<Apple>(new Apple(1));
        appleWeakReference1.get();
        appleWeakReference.get();
        apple = new Apple(3);
        System.gc();
        appleWeakReference1.get();
        appleWeakReference.get();
        /*Banner banner = new Banner(apple,water);
        appleWeakReference.get();
        banner.get();
        apple = null;
        appleWeakReference.get();
        banner.get();
        apple1 = null;
        appleWeakReference.get();
        banner.get();
        apple2 = null;
        appleWeakReference.get();
        System.gc();
        Thread.sleep(1000);
        appleWeakReference.get();
        banner.get();*/
        System.out.println("test");
    }
}
