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
        Apple apple1 =apple;
        Apple apple2 = apple;
        WeakReference<Apple>  appleWeakReference = new WeakReference<Apple>(apple);
        Banner banner = new Banner(apple,water);
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
        banner.get();
        System.out.println("test");
    }
}
