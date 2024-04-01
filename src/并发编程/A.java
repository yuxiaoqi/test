package 并发编程;

/**
 * Created by admin on 2016/3/30.
 */
public class A {
    private Integer m;
    private Integer a;
    public String b;
    public Integer c;
    public  Integer d;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
    }

    public static void main(String[] args) {
        A a = new A();
        String ss = a.b;
        int aa = a.m;
    }


}
