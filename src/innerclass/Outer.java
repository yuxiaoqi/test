package innerclass;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2017/12/19 下午4:39
 */
public class Outer {

    private int aa;

    private int bb;

    private final int cc=0;

    public void say(){
        int y=100;
         class Inner{
            public void say(){
                System.out.println("shuju="+y);
            }

        }
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.say();
    }
}
