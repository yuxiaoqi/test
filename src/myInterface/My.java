package myInterface;

/**
 * Created by yyq
 * Date  2016/12/23.
 * version 1.0.0.0
 */
public class My extends MyAbstract implements MyInterface {
    int a =0;

    public static void main(String[] args) {
        int b =MyInterface.a;
    }
}
