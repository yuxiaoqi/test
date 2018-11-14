package WeakReference;

import java.lang.ref.SoftReference;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/8/2 下午3:39
 */
public class SoftReferenceTest {
    public static void main(String[] args) {
        SoftReference reference = new SoftReference(new Water());
        System.out.println("");
        reference.get();
        System.out.println();
        System.gc();
        reference.get();
        System.out.println("");
        System.gc();
        System.out.println("");
    }
}
