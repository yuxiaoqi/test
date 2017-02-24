package collection;

import sun.misc.Launcher;

import java.net.URL;

/**
 * Created by admin on 2017/2/22.
 */
public class RoundUpToPowerOf2 {
    private static final int MAXIMUM_CAPACITY = 1<<30;
    private static final int TEST = 5<<1;
    private static int roundUpToPowerOf2(int number) {
        // assert number >= 0 : "number must be non-negative";
        return number >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }

    public static void main(String[] args) {
        URL[] temp = Launcher.getBootstrapClassPath().getURLs();
        int a = RoundUpToPowerOf2.roundUpToPowerOf2(6);
        System.out.println(a);
    }
}
