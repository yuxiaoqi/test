package TryFinally;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/1/15 下午4:12
 */
public class TryTest {


    public int aVoid(){
        int a =10;

        try{
            for (;;){
                if (a==10){
                    return a;
                }
            }

        }finally {
            a=a+5;
        }

    }


    public static void main(String[] args) {


         new TryTest().aVoid();
        System.out.println("算法.test");
    }
}
