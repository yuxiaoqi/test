package WeakReference;

import java.lang.ref.WeakReference;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/8/2 下午12:04
 */
public class WaterMelon<T> extends WeakReference<T>{

    private T t;

    public WaterMelon(T t) {
        super(t);
    }

    public T say(T t){
        return t;
    }

    public <O> void  test(O... args){
        for (O arg : args) {

        }
    }

    public static void main(String[] args) {
        WaterMelon waterMelon = new WaterMelon(new Apple(2));
        waterMelon.get();
        System.out.println("");
       // System.gc();
        waterMelon.get();
        System.out.println("");
    }
}
