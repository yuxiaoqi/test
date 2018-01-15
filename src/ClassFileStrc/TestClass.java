package ClassFileStrc;

/**
 * Created by admin on 2016/10/31.
 */
public class TestClass {
    static final   int m = 9;

    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int inc(){
        return  m+1;
    }

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        TestClass testClass1 = new TestClass();
        testClass1.setNum(199);
        TestClass test = testClass;
        test.setNum(10);

        test=testClass1;
        test.getNum();
        testClass.getNum();
        System.out.println(testClass.getNum());
    }

    public static Boolean valueof(Boolean b){
        return  b ? Boolean.TRUE : Boolean.FALSE ;
    }
}
