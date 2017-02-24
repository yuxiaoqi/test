package ClassFileStrc;

/**
 * Created by admin on 2016/10/31.
 */
public class TestClass {
    static final   int m = 9;

    public int inc(){
        return  m+1;
    }

    public static void main(String[] args) {
        new TestClass().hashCode();
    }

    public static Boolean valueof(Boolean b){
        return  b ? Boolean.TRUE : Boolean.FALSE ;
    }
}
