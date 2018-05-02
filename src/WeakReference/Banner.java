package WeakReference;

import java.lang.ref.WeakReference;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/1/30 下午7:25
 */
public class Banner extends WeakReference<Apple> {
    private Water water;

    public Banner(Apple apple, Water water) {
        super(apple);
        this.water = water;
    }
}
